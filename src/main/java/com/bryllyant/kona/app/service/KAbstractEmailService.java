package com.bryllyant.kona.app.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.amazonaws.services.sqs.model.Message;
import com.bryllyant.kona.app.entity.KCampaignChannel;
import com.bryllyant.kona.app.entity.KCampaignGroup;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KEmailAddress;
import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.app.entity.KEmailEvent;
import com.bryllyant.kona.app.entity.KEmailGroup;
import com.bryllyant.kona.app.entity.KEmailGroupAddress;
import com.bryllyant.kona.app.entity.KUser;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.model.KEmailStats;
import com.bryllyant.kona.app.util.KUtil;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.mailer.KMailer;
import com.bryllyant.kona.mailer.KMailerException;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.templates.KTemplateException;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class KAbstractEmailService<
        EMAIL extends KEmail,
        EMAIL_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<EMAIL, EMAIL_EXAMPLE>,
        EMAIL_EVENT extends KEmailEvent,
        EMAIL_EVENT_EXAMPLE extends KEntityExample,
        EMAIL_GROUP extends KEmailGroup,
        EMAIL_ADDRESS extends KEmailAddress,
        EMAIL_CONTENT extends KEmailContent,
        EMAIL_ATTACHMENT extends KEmailAttachment,
        EMAIL_GROUP_ADDRESS extends KEmailGroupAddress,
        CAMPAIGN_CHANNEL extends KCampaignChannel,
        CAMPAIGN_GROUP extends KCampaignGroup,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_REPLY extends KCampaignReply,
        USER extends KUser>
        extends KAbstractService<EMAIL, EMAIL_EXAMPLE,MAPPER>
        implements KEmailService<
        EMAIL,EMAIL_EVENT,
        EMAIL_CONTENT,
        EMAIL_ATTACHMENT> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractEmailService.class);

    private Queue<MimeMessage> localMailQueue = new LinkedList<>();

    private AtomicBoolean processingSESNotifications = new AtomicBoolean(false);

    protected abstract <D extends KMyBatisMapper<EMAIL_EVENT,EMAIL_EVENT_EXAMPLE>> D getEmailEventDao();

    protected abstract AmazonSimpleEmailServiceClient getAmazonSESClient();

    protected abstract String getAmazonSESBounceQueueName();

    protected abstract String getAmazonSESComplaintQueueName();

    protected abstract String getAmazonSESDeliveryQueueName();

    protected abstract EMAIL getNewEmailObject();

    protected abstract EMAIL_EVENT getNewEmailEventObject();

    protected abstract EMAIL_ADDRESS getNewEmailAddressObject();

    protected abstract String getSystemMailhost();

    protected abstract String getSystemSenderEmailAddress();

    protected abstract String getSystemFromEmailAddress();

    protected abstract String getSystemBaseUrl();

    protected abstract String getEmailEventUrl();

    protected abstract String getEmailFooterHtmlSelector();

    protected abstract String getEmailTextFooterTemplatePath();

    protected abstract String getEmailHtmlFooterTemplatePath();



    protected abstract <S extends KUserService<USER>> S getUserService();

    protected abstract <S extends KEmailAddressService<EMAIL_ADDRESS>> S getEmailAddressService();

    protected abstract <S extends KEmailGroupService<EMAIL_GROUP,EMAIL_ADDRESS,EMAIL_GROUP_ADDRESS>> S getEmailGroupService();

    protected abstract <S extends KEmailGroupAddressService<EMAIL_GROUP_ADDRESS>> S getEmailGroupAddressService();

    protected abstract <S extends KEmailContentService<EMAIL_CONTENT,EMAIL_ATTACHMENT>> S getEmailContentService();

    protected abstract <S extends KEmailAttachmentService<EMAIL_ATTACHMENT>> S getEmailAttachmentService();

    protected abstract <S extends KCampaignChannelService<CAMPAIGN_CHANNEL, CAMPAIGN_GROUP, CAMPAIGN_TARGET, CAMPAIGN_REPLY>> S getCampaignChannelService();

    protected abstract <S extends KQueueService> S getQueueService();




    @Override
    public void validate(EMAIL email) {
        if (email.getCreatedDate() == null) {
            email.setCreatedDate(new Date());
        }

        email.setUpdatedDate(new Date());

        if (email.getUid() == null) {
            email.setUid(uuid());
        }

        if (email.getOpenCount() == null) {
            email.setOpenCount(0);
        }

        if (email.getPrintCount() == null) {
            email.setPrintCount(0);
        }

        if (email.getForwardCount() == null) {
            email.setForwardCount(0);
        }

        if (email.getClickCount() == null) {
            email.setClickCount(0);
        }

    }



    @Override
    public void processLocalQueue() {
        Iterator<MimeMessage> it = localMailQueue.iterator();

        while (it.hasNext()) {
            MimeMessage message = it.next();

            try {
                KMailer.send(message);
                it.remove();
            } catch (KMailerException e) {
                logger.warn("processQueue: unable to send message: " + message);
            }
        }
    }



    @Override
    public EMAIL send(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            boolean html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer
    ) throws KEmailException {

        String textBody = null;
        String htmlBody = null;

        if (html) {
            htmlBody = body;
        } else {
            textBody = body;
        }

        EMAIL email = deliver(
                from,
                replyTo,
                to,
                subject,
                textBody,
                htmlBody,
                attachmentList,
                footer);

        String testDomain = getEmailTestDomain().toLowerCase();

        if (!to.toLowerCase().endsWith(testDomain)) {
            Thread t = new Thread() {
                public void run() {
                    processSESNotifications();
                }
            };

            t.start();
        }

        return email;
    }



    @Override
    public void sendRawSMTP(String body, String subject, String from, String replyTo,
                            String to, String cc, String bcc, boolean html, List<EMAIL_ATTACHMENT> mediaList) throws KEmailException {

        String mailhost = getSystemMailhost();
        String sender = getSystemSenderEmailAddress();

        KMailer mailer = new KMailer(mailhost);
        mailer.setSender(sender); // sender's domain must match mailer's authenticated domain
        mailer.setFrom(from); // could be different from sender but will likely be shown as "on behalf of" sender
        mailer.setReplyTo(replyTo);
        mailer.setTo(to);
        mailer.setCc(cc);
        mailer.setBcc(bcc);
        mailer.setSubject(subject);

        if (mediaList != null) {
            for (EMAIL_ATTACHMENT media : mediaList) {
                mailer.addByteArray(media.getName(), media.getData(), media.getContentType());
            }
        }

        if (body == null) {
            body = "";
        }

        if (html) {
            mailer.setHtmlBody(body);
        } else {
            mailer.setTextBody(body);
        }

        mailer.setHTML(html);

        String email = "\n"
                + "\nmailhost: " + mailhost
                + "\nsender: " + sender
                + "\nsubject: " + subject
                + "\nfrom: " + from
                + "\nreplyTo: " + replyTo
                + "\nto: " + to
                + "\ncc: " + cc
                + "\nbcc: " + bcc
                + "\nhtml: " + html
                + "\nbody: " + (body.length() > 100 ? body.substring(1, 100) : body);

        logger.debug("sendEmail: message properties:" + email);


        logger.debug("--- EMAIL MESSAGE START ---\n\n"
                + mailer.toString() + "\n\n"
                + "--- EMAIL MESSAGE END ---\n\n");


        String testDomain = getEmailTestDomain().toLowerCase();

        if (to.toLowerCase().endsWith(testDomain)) {
            logger.warn("Email address [{}] ends in test domain [{}]: skipping ...", to, testDomain);
            return;
        }

        try {
            mailer.send();
        } catch (KMailerException e) {
            MimeMessage message = e.getMimeMessage();

            // if message is null, we had a problem composing the message so give up
            if (message == null) {
                throw new KEmailException(e);
            }

            // otherwise, we had some issue where the message couldn't be delivered, so queue
            // it and try again.

            // FIXME: setup a thread to resend messages in queue. until then throw exception
            //localMailQueue.add(message);

            throw new KEmailException(e);
        }
    }



    @Override
    public String sendRawAWS(
            String from,
            String replyTo,
            String to,
            String subject,
            String textBody,
            String htmlBody,
            List<EMAIL_ATTACHMENT> mediaList
    ) throws KEmailException {

        String[] replyToArray = null;

        if (replyTo != null) {
            replyToArray = new String[] { replyTo };
        }

        return sendRawAWS(
                from,
                replyToArray,
                null,
                new String[]{to},
                null,
                null,
                subject,
                textBody,
                htmlBody,
                mediaList
        );

    }




    @Override
    public String sendRawAWS(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            String cc,
            String bcc,
            boolean html,
            List<EMAIL_ATTACHMENT> mediaList) throws KEmailException {

        String textBody = null;
        String htmlBody = null;

        if (html) {
            htmlBody = body;
        } else {
            textBody = body;
        }

        String[] replyToArray = null;
        String[] ccArray = null;
        String[] bccArray = null;

        if (replyTo != null) {
            replyToArray = new String[] { replyTo };
        }

        if (cc != null) {
            ccArray = new String[] { cc };
        }

        if (bcc != null) {
            bccArray = new String[] { bcc };
        }

        return sendRawAWS(
                from,
                replyToArray,
                null,
                new String[]{to},
                ccArray,
                bccArray,
                subject,
                textBody,
                htmlBody,
                mediaList
        );
    }



    @Override
    public String sendRawAWS(
            String from,
            String[] replyTo,
            String returnPath,
            String[] to,
            String[] cc,
            String[] bcc,
            String subject,
            String textBody,
            String htmlBody,
            List<EMAIL_ATTACHMENT> mediaList
    ) throws KEmailException {

        KMailer mailer = new KMailer();
        mailer.setFrom(from);
        mailer.setReplyTo(replyTo);
        mailer.setReturnPath(returnPath);
        mailer.setTo(to);
        mailer.setCc(cc);
        mailer.setBcc(bcc);
        mailer.setSubject(subject);
        mailer.setTextBody(textBody);
        mailer.setHtmlBody(htmlBody);

        if (mediaList != null) {
            for (EMAIL_ATTACHMENT media : mediaList) {
                mailer.addByteArray(media.getName(), media.getData(), media.getContentType());
            }
        }

        String testDomain = getEmailTestDomain().toLowerCase();

        /*
            logger.debug("sendRawAWS: from:  " + KJsonUtil.toJson(from));
            logger.debug("sendRawAWS: replyTo:  " + KJsonUtil.toJson(replyTo));
            logger.debug("sendRawAWS: returnPath:  " + KJsonUtil.toJson(returnPath));
            logger.debug("sendRawAWS: to:  " + KJsonUtil.toJson(to));
            logger.debug("sendRawAWS: cc:  " + KJsonUtil.toJson(cc));
            logger.debug("sendRawAWS: bcc:  " + KJsonUtil.toJson(bcc));
         */

        try {
            Session session = Session.getInstance(new Properties(), null);
            MimeMessage mimeMessage = mailer.getMimeMessage(session);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);

            String stringMessage = outputStream.toString();
            logger.debug("rawMessage:\n" + stringMessage);

            RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

            SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);

            // make sure from/to are encoded
            from = KMailer.toEncodedAddress(from);

            List<String> toList = new ArrayList<String>();

            for (String s : to) {
                s = KMailer.toEncodedAddress(s);

                if (s.toLowerCase().endsWith(testDomain)) {
                    logger.info("Email address [{}] ends in test domain [{}]: skipping ...", s, testDomain);
                    continue;
                }

                toList.add(s);
            }

            if (toList.size() == 0) {
                logger.info("Email toList is empty. Aborting send.");
                return null;
            }

            //rawEmailRequest.setDestinations(Arrays.asList(to));
            rawEmailRequest.setDestinations(toList);

            rawEmailRequest.setSource(from);

            SendRawEmailResult result = getAmazonSESClient().sendRawEmail(rawEmailRequest);

            return result.getMessageId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public EMAIL_EVENT addEvent(EMAIL_EVENT event) {
        if (event.getCreatedDate() == null) {
            event.setCreatedDate(new Date());
        }

        event.setUpdatedDate(new Date());

        if (event.getUid() == null) {
            event.setUid(uuid());
        }

        getEmailEventDao().insert(event);

        return event;
    }



    @Override
    public EMAIL fetchBySesId(String sesId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("sesId", sesId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public EMAIL fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<EMAIL> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<EMAIL> fetchByCampaignGroupId(Long campaignGroupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignGroupId", campaignGroupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<EMAIL> fetchByCampaignChannelId(Long campaignChannelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public EMAIL fetchByCampaignChannelIdAndToId(Long campaignChannelId, Long emailAddressId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        filter.put("emailAddressId", emailAddressId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<EMAIL> fetchByEmailGroupId(Long emailGroupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("emailGroupId", emailGroupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<EMAIL> fetchByEmailGroupSlug(String emailGroupSlug) {
        EMAIL_GROUP group = getEmailGroupService().fetchBySlug(emailGroupSlug);
        return fetchByEmailGroupId(group.getId());
    }

    @Override
    public KEmailStats calcStatsByGroupSlug(String groupSlug) {
        List<EMAIL> emailList = fetchByEmailGroupSlug(groupSlug);
        return calcStats(emailList);
    }

    @Override
    public KEmailStats calcStatsByCampaignId(Long campaignId) {
        List<EMAIL> emailList = fetchByCampaignId(campaignId);
        return calcStats(emailList);
    }

    @Override
    public KEmailStats calcStatsByCampaignGroupId(Long campaignGroupId) {
        List<EMAIL> emailList = fetchByCampaignGroupId(campaignGroupId);
        return calcStats(emailList);
    }

    @Override
    public KEmailStats calcStatsByCampaignChannelId(Long campaignChannelId) {
        List<EMAIL> emailList = fetchByCampaignChannelId(campaignChannelId);
        return calcStats(emailList);
    }

    @Override
    public KEmailStats calcStats(List<EMAIL> emailList) {

        double failed = 0.0;		double failedRate = 0.0;
        double delivered = 0.0;		double deliveredRate = 0.0;
        double bounced = 0.0;		double bouncedRate = 0.0;
        double complained = 0.0;	double complainedRate = 0.0;
        double optedOut = 0.0;		double optedOutRate = 0.0;
        double opened = 0.0;		double openedAllRate = 0.0;
        double clicked = 0.0;		double clickedAllRate = 0.0;
        double printed = 0.0;		double printedAllRate = 0.0;
        double forwarded = 0.0;		double forwardedAllRate = 0.0;

        double openedDeliveredRate = 0.0;
        double clickedDeliveredRate = 0.0;
        double printedDeliveredRate = 0.0;
        double forwardedDeliveredRate = 0.0;

        double clickedOpenedRate = 0.0;
        double printedOpenedRate = 0.0;
        double forwardedOpenedRate = 0.0;


        double emailCount = (double) emailList.size();

        for (EMAIL email : emailList) {
            if (email.isFailed()) 				failed += 1.0;
            if (email.isDelivered()) 			delivered += 1.0;
            if (email.isBounced()) 				bounced += 1.0;
            if (email.isComplained()) 			complained += 1.0;
            if (email.isOptedOut()) 			optedOut += 1.0;
            if (email.getOpenCount() > 0) 		opened += 1.0;
            if (email.getClickCount() > 0) 		clicked += 1.0;
            if (email.getPrintCount() > 0) 		printed += 1.0;
            if (email.getForwardCount() > 0) 	forwarded += 1.0;
        }

        failedRate = failed / emailCount;
        deliveredRate = delivered / emailCount;
        bouncedRate = bounced / emailCount;
        complainedRate = complained / emailCount;
        optedOutRate = optedOut / emailCount;

        openedAllRate = opened / emailCount;
        openedDeliveredRate = opened / delivered;

        clickedAllRate = clicked / emailCount;
        clickedDeliveredRate = clicked / delivered;
        clickedOpenedRate = clicked / opened;

        printedAllRate = printed / emailCount;
        printedDeliveredRate = printed / delivered;
        printedOpenedRate = printed / opened;

        forwardedAllRate = forwarded / emailCount;
        forwardedDeliveredRate = forwarded / delivered;
        forwardedOpenedRate = forwarded / opened;


        KEmailStats stats = new KEmailStats();
        stats.setEmailCount(emailCount);
        stats.setFailed(failed);
        stats.setDelivered(delivered);
        stats.setBounced(bounced);
        stats.setComplained(complained);
        stats.setOptedOut(optedOut);
        stats.setOpened(opened);
        stats.setClicked(clicked);
        stats.setPrinted(printed);
        stats.setForwarded(forwarded);

        stats.setFailedRate(failedRate);
        stats.setDeliveredRate(deliveredRate);
        stats.setBouncedRate(bouncedRate);
        stats.setComplainedRate(complainedRate);
        stats.setOptedOutRate(optedOutRate);

        stats.setOpenedAllRate(openedAllRate);
        stats.setOpenedDeliveredRate(openedDeliveredRate);

        stats.setClickedAllRate(clickedAllRate);
        stats.setClickedDeliveredRate(clickedDeliveredRate);
        stats.setClickedOpenedRate(clickedOpenedRate);

        stats.setPrintedAllRate(printedAllRate);
        stats.setPrintedDeliveredRate(printedDeliveredRate);
        stats.setPrintedOpenedRate(printedOpenedRate);

        stats.setForwardedAllRate(forwardedAllRate);
        stats.setForwardedDeliveredRate(forwardedDeliveredRate);
        stats.setForwardedOpenedRate(forwardedOpenedRate);

        return stats;
    }



    @Override
    public void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String fromAddress,
            String replyTo,
            String subject,
            String text,
            String html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer,
            Long throttleTime
    ) throws KEmailException {

        try {
            USER systemUser = getUserService().getSystemUser();
            EMAIL_CONTENT content = getEmailContentService().create(systemUser.getId(), html, text, attachmentList);
            deliver(campaignChannelId, emailGroupId, fromAddress, replyTo, subject, content, footer, throttleTime);
        } catch (Exception e) {
            throw new KEmailException(e);
        }
    }



    @Override
    public void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String fromAddress,
            String replyTo,
            String subject,
            EMAIL_CONTENT content,
            KEmailFooter footer,
            Long throttleTime
    ) throws KEmailException {

        List<EMAIL_GROUP_ADDRESS> addressList = getEmailGroupAddressService().fetchByGroupId(emailGroupId);

        for (EMAIL_GROUP_ADDRESS ega : addressList) {
            EMAIL_ADDRESS address = getEmailAddressService().fetchById(ega.getAddressId());

            if (!getEmailAddressService().isValid(address)) {
                logger.debug("deliver: emailAddress is invalid: " + address.getEmail());
                continue;
            }

            deliver(campaignChannelId, emailGroupId, address, fromAddress, replyTo, subject, content, footer);

            throttle(throttleTime);
        }
    }



    @Override
    public EMAIL deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            String text,
            String html,
            List<EMAIL_ATTACHMENT> attachmentList,
            KEmailFooter footer) throws KEmailException {

        try {
            USER systemUser = getUserService().getSystemUser();
            EMAIL_CONTENT content = getEmailContentService().create(systemUser.getId(), html, text, attachmentList);

            return deliver(from, replyTo, to, subject, content, footer);
        } catch (Exception e) {
            throw new KEmailException(e);
        }
    }



    @Override
    public EMAIL deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            EMAIL_CONTENT content,
            KEmailFooter footer) throws KEmailException {

        if (to == null || !KValidator.isEmail(to)) {
            throw new KEmailException("Email address is null or invalid: " + to);
        }

        EMAIL_ADDRESS address = getEmailAddressService().fetchByEmail(to);

        boolean saveAddress = false;

        if (address == null) {
            address = getNewEmailAddressObject();
            address.setEmail(to);
            address.setEnabled(true);
            address.setCreatedDate(new Date());
            saveAddress = true;
        }

        if (address.getUserId() == null) {
            USER user = getUserService().fetchByEmail(to);

            if (user != null) {
                address.setUserId(user.getId());
                saveAddress = true;
            }
        }

        if (saveAddress) {
            address = getEmailAddressService().save(address);
        }

        if (!getEmailAddressService().isValid(address)) {
            throw new KEmailException("deliver: emailAddress is invalid: " + address.getEmail());
        }

        //logger.debug("deliver: address: " + KJsonUtil.toJson(address));

        return deliver(null, null, address, from, replyTo, subject, content, footer);
    }




    /**
     * Send and track email messages.
     *
     * NOTE: processSESNotifications() needs to be called manually after this method completes.
     *
     * @param campaignChannelId
     * @param emailGroupId
     * @param address
     * @param fromAddress
     * @param replyTo
     * @param subject
     * @param content
     * @param footer
     * @throws KEmailException
     */
    private EMAIL deliver(
            Long campaignChannelId,
            Long emailGroupId,
            EMAIL_ADDRESS address,
            String fromAddress,
            String replyTo,
            String subject,
            EMAIL_CONTENT content,
            KEmailFooter footer
    ) throws KEmailException {

        String toAddress = formatAddress(address);

        // make sure we haven't already sent an email to this user
        EMAIL email = null;

        CAMPAIGN_CHANNEL campaignChannel = null;

        if (campaignChannelId != null) {
            email = fetchByCampaignChannelIdAndToId(campaignChannelId, address.getId());

            if (email != null) {
                logger.warn("EmailService.deliver: Skipping:  Email already delivered for campaignChannelId: "
                        + campaignChannelId);
                return null;
            }

            campaignChannel = getCampaignChannelService().fetchById(campaignChannelId);
        }

        List<EMAIL_ATTACHMENT> attachments = null;

        if (content.getId() != null) {
            attachments = getEmailAttachmentService().fetchByContentId(content.getId());
        }

        String uid = uuid();

        String text1 = processContent(content.getText(), uid, address, footer, false);

        String html1 = processContent(content.getHtml(), uid, address, footer, true);

        Date now = new Date();

        email = getNewEmailObject();
        email.setUid(uid);
        email.setCampaignChannelId(campaignChannelId);
        email.setEmailGroupId(emailGroupId);
        email.setFromAddress(fromAddress);
        email.setToAddress(toAddress);
        email.setEmailAddressId(address.getId());
        email.setEmailContentId(content.getId());
        email.setSubject(subject);
        email.setCreatedDate(now);
        email.setSentDate(now);
        email.setOpenCount(0);
        email.setPrintCount(0);
        email.setForwardCount(0);
        email.setClickCount(0);

        if (campaignChannel != null) {
            email.setCampaignId(campaignChannel.getCampaignId());
            email.setCampaignGroupId(campaignChannel.getGroupId());
        }

        EMAIL_EVENT event = getNewEmailEventObject();
        event.setEventDate(now);
        event.setCreatedDate(now);

        String testDomain = getEmailTestDomain().toLowerCase();

        if (toAddress.toLowerCase().endsWith(testDomain)) {
            logger.warn("Email address [{}] ends in test domain [{}]: skipping ...", toAddress, testDomain);
            return null;
        }

        try {
            String sesId = sendRawAWS(fromAddress, replyTo, toAddress, subject, text1, html1, attachments);
            email.setSesId(sesId);
            email = add(email);

            event.setType(EMAIL_EVENT.Type.ATTEMPTED);
            event.setEmailId(email.getId());
            addEvent(event);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            email.setFailed(true);
            email = add(email);

            event.setType(EMAIL_EVENT.Type.FAILED);
            event.setEmailId(email.getId());
            event.setError(e.getMessage());
            addEvent(event);
        }

        return email;
    }



    private void throttle(Long throttleTime) {
        if (throttleTime != null) {
            try {
                Thread.sleep(throttleTime);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }



    private String formatAddress(EMAIL_ADDRESS address) {
        String email = "";
        if (address.getFirstName() != null) {
            String firstName = address.getFirstName().replaceAll("[^a-zA-Z -]", "");
            email = firstName;
        }

        if (address.getLastName() != null) {
            String lastName = address.getLastName().replaceAll("[^a-zA-Z -]", "");
            if (email.length() == 0) {
                email = lastName;
            } else {
                email = email + " " + lastName;
            }
        }

        if (email.length() == 0) {
            email = address.getEmail();
        } else {
            email = email + " <" + address.getEmail() + ">";
        }
        return email;
    }



    protected String transformUrls(String text, String clickUrl) {
        if (text == null) return null;

        String urlValidationRegex = "(https?|ftp)://(www\\d?|[a-zA-Z0-9]+)?.[a-zA-Z0-9-]+(\\:|.)([a-zA-Z0-9.]+|(\\d+)?)([/?:].*)?";

        Pattern p = Pattern.compile(urlValidationRegex);

        Matcher m = p.matcher(text);

        StringBuffer sb = new StringBuffer();

        while(m.find()){
            String targetUrl = m.group(0);

            try {
                targetUrl = URLEncoder.encode(targetUrl, "UTF-8");
            } catch (UnsupportedEncodingException e1) { }

            targetUrl = clickUrl + targetUrl;

            m.appendReplacement(sb, targetUrl);
        }

        m.appendTail(sb);

        return sb.toString();
    }



    private String processContent(String content, String messageId, EMAIL_ADDRESS address, KEmailFooter footer, boolean html) {
        if (content == null) return null;

        String systemBaseUrl = getSystemBaseUrl();
        String emailEventUrl = getEmailEventUrl();
        String clickUrl = emailEventUrl +"click/" + messageId + "?u=";
        String openUrl = emailEventUrl +"open/" + messageId;
        String unsubscribeUrl = emailEventUrl +"unsubscribe/" + messageId;

        if (footer != null) {
            footer.setUnsubscribeUrl(unsubscribeUrl);
        }


        // Assume the content is a string template that needs to have emailAddress injected
        // along with other variables.  It's possible that the content is not a valid
        // Velocity template and could throw an exception so catch all Throwables and not 
        // just KTemplateException

        try {
            Map<String,Object> map = new HashMap<>();
            map.put("emailAddress", address);
            map.put("systemBaseUrl", systemBaseUrl);
            map.put("unsubscribeUrl", unsubscribeUrl);
            map.put("Util", KUtil.getInstance());

            KTemplate t = new KTemplate();
            t.setStringTemplate(content);
            t.addContextMap(map);
            content = t.toString();
            content = content.trim();
        } catch (Throwable t) {
            logger.warn("Error processing string template.  Ignoring ...\n" + t.getMessage());
        }

        if (!html) {
            content = transformUrls(content, clickUrl);
            content = content + generateEmailFooter(footer, false);
            content = content.trim();
            return content;
        }

        //Document doc = Jsoup.parseBodyFragment(content);
        Document doc = Jsoup.parse(content);
        Element body = doc.body();

        Elements elements = body.select("a");
        for (Element e : elements) {
            String targetUrl = e.attr("href");

            if (targetUrl != null && targetUrl.trim().equals(unsubscribeUrl)) {
                continue;
            }

            try {
                targetUrl = URLEncoder.encode(targetUrl, "UTF-8");
            } catch (UnsupportedEncodingException e1) { }

            targetUrl = clickUrl + targetUrl;
            e.attr("href", targetUrl);
        }

        String selector = getEmailFooterHtmlSelector();

        if (selector != null) {
            Element element = doc.select(selector).first();
            if (element != null) {
                element.append(generateEmailFooter(footer, html));
            }

            element = new Element(Tag.valueOf("img"), "");
            element.attr("src", openUrl);
            body.appendChild(element);
        }


        content = doc.html();

        logger.debug("processed email html:\n" + content);
        return content;
    }



    private String generateEmailFooter(KEmailFooter footer, boolean html) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("footer", footer);

        String footerTmpl = getEmailTextFooterTemplatePath();

        if (html) {
            footerTmpl = getEmailHtmlFooterTemplatePath();
        }

        try {
            KTemplate t = new KTemplate(footerTmpl);
            t.addContextMap(map);
            return t.toString();
        } catch (KTemplateException e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }



    private Map<String,Object> parseMessage(Message message) {
        String json = message.getBody();

        logger.debug("Processing SQS Message:\n" + json);
        //json = StringEscapeUtils.unescapeJava(json);

        Map<String,Object> body = KJsonUtil.toMap(json);

        String m = (String) body.get("Message");

        logger.debug("Processing SQS Message Body:\n" + m);

        Map<String,Object> map = KJsonUtil.toMap(m);

        return map;
    }



    @Override
    public void processSESNotifications() {
        boolean processing = processingSESNotifications.getAndSet(true);

        // if already true, then it's already running
        if (processing) return;

        processBounceQueue();

        processComplaintQueue();

        processDeliveryQueue();

        processingSESNotifications.getAndSet(false);
    }



    @SuppressWarnings("unchecked")
    private void processBounceQueue() {
        String bounceQueue = getAmazonSESBounceQueueName();

        List<Message> messageList = getQueueService().fetchMessages(bounceQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                getQueueService().deleteMessage(bounceQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("bounce")) {
                logger.warn("bounce queue: found notification type: " + notificationType);
                getQueueService().deleteMessage(bounceQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> bounce = (Map<String, Object>) notification.get("bounce");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) bounce.get("timestamp");

            Date bounceDate = KDateUtil.parseISO8601(timestamp);

            EMAIL email = fetchBySesId(sesId);

            if (email != null && email.isBounced() == false) {
                email.setBounced(true);

                update(email);

                EMAIL_EVENT event = getNewEmailEventObject();

                event.setEmailId(email.getId());

                event.setType(EMAIL_EVENT.Type.BOUNCED);

                event.setEventDate(bounceDate);

                event.setCreatedDate(new Date());

                addEvent(event);

                disableEmailAddress(email, EMAIL_EVENT.Type.BOUNCED);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            getQueueService().deleteMessage(bounceQueue, message);
        }
    }



    @SuppressWarnings("unchecked")
    private void processComplaintQueue() {
        String complaintQueue = getAmazonSESComplaintQueueName();

        List<Message> messageList = getQueueService().fetchMessages(complaintQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                getQueueService().deleteMessage(complaintQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("Complaint")) {
                logger.warn("Complaint queue: found notification type: " + notificationType);
                getQueueService().deleteMessage(complaintQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> complaint = (Map<String, Object>) notification.get("complaint");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) complaint.get("timestamp");

            Date complainedDate = KDateUtil.parseISO8601(timestamp);

            EMAIL email = fetchBySesId(sesId);

            if (email != null && email.isComplained() == false) {
                email.setComplained(true);

                update(email);

                EMAIL_EVENT event = getNewEmailEventObject();

                event.setEmailId(email.getId());

                event.setType(EMAIL_EVENT.Type.COMPLAINED);

                event.setEventDate(complainedDate);

                event.setCreatedDate(new Date());

                addEvent(event);

                disableEmailAddress(email, EMAIL_EVENT.Type.COMPLAINED);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            getQueueService().deleteMessage(complaintQueue, message);
        }
    }



    @SuppressWarnings("unchecked")
    private void processDeliveryQueue() {
        String deliveryQueue = getAmazonSESDeliveryQueueName();

        List<Message> messageList = getQueueService().fetchMessages(deliveryQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                getQueueService().deleteMessage(deliveryQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("Delivery")) {
                logger.warn("Delivery queue: found notification type: " + notificationType);
                getQueueService().deleteMessage(deliveryQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> delivery = (Map<String, Object>) notification.get("delivery");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) delivery.get("timestamp");

            Date deliveredDate = KDateUtil.parseISO8601(timestamp);

            EMAIL email = fetchBySesId(sesId);

            if (email != null && email.isDelivered() == false) {
                email.setDelivered(true);

                update(email);

                EMAIL_EVENT event = getNewEmailEventObject();

                event.setEmailId(email.getId());

                event.setType(EMAIL_EVENT.Type.DELIVERED);

                event.setEventDate(deliveredDate);

                event.setCreatedDate(new Date());

                addEvent(event);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            getQueueService().deleteMessage(deliveryQueue, message);
        }
    }



    private void disableEmailAddress(EMAIL email, EMAIL_EVENT.Type type) {
        EMAIL_ADDRESS address = null;

        if (email.getEmailAddressId() != null) {
            address = getEmailAddressService().fetchById(email.getEmailAddressId());
        } else if (email.getToAddress() != null) {
            address = getEmailAddressService().fetchByEmail(email.getToAddress());
        }

        if (address != null) {
            if (type == EMAIL_EVENT.Type.BOUNCED) {
                address.setBouncedDate(new Date());
            } else if  (type == EMAIL_EVENT.Type.COMPLAINED) {
                address.setComplainedDate(new Date());
            }

            address.setEnabled(false);

            getEmailAddressService().update(address);
        }
    }


}
