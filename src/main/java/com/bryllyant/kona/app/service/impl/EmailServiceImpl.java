/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.amazonaws.services.sqs.model.Message;
import com.bryllyant.kona.app.dao.EmailEventMapper;
import com.bryllyant.kona.app.dao.EmailMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.app.entity.EmailAttachment;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailEvent;
import com.bryllyant.kona.app.entity.EmailExample;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.EmailGroupAddress;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.model.EmailStats;
import com.bryllyant.kona.app.service.CampaignChannelService;
import com.bryllyant.kona.app.service.EmailAddressService;
import com.bryllyant.kona.app.service.EmailAttachmentService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.EmailGroupAddressService;
import com.bryllyant.kona.app.service.EmailGroupService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.FileService;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.app.service.EmailException;
import com.bryllyant.kona.app.service.QueueService;
import com.bryllyant.kona.app.service.UserService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.locale.KValidator;
import com.bryllyant.kona.mailer.KMailer;
import com.bryllyant.kona.mailer.KMailerException;
import com.bryllyant.kona.templates.KTemplate;
import com.bryllyant.kona.templates.KTemplateException;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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


@Service(EmailService.SERVICE_PATH)
public class EmailServiceImpl
        extends KAbstractService<Email,EmailExample,EmailMapper>
        implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private Queue<MimeMessage> localMailQueue = new LinkedList<>();

    private AtomicBoolean processingSESNotifications = new AtomicBoolean(false);


    @Autowired
    private EmailMapper emailMapper;

    @Autowired
    private EmailEventMapper emailEventMapper;

    @Autowired
    private KConfig config;

    @Autowired
    private EmailAddressService emailAddressService;

    @Autowired
    private EmailGroupService emailGroupService;

    @Autowired
    private EmailGroupAddressService emailGroupAddressService;

    @Autowired
    private EmailContentService emailContentService;

    @Autowired
    private EmailAttachmentService emailAttachmentService;

    @Autowired
    private QueueService queueService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private CampaignChannelService campaignChannelService;

    private AmazonSimpleEmailServiceClient client = null;

    @Override @SuppressWarnings("unchecked")
    protected EmailMapper getMapper() {
        return emailMapper;
    }


    @Override
    public String getEmailTestDomain() {
        return config.getString("email.testDomain");
    }


    protected AmazonSimpleEmailServiceClient getAmazonSESClient() {

        if (client == null) {
            String accessKey = config.getString("aws.ses.accessKey");
            String secretKey = config.getString("aws.ses.secretKey");
            String region = config.getString("aws.ses.region");

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            client = new AmazonSimpleEmailServiceClient(credentials);

            Region REGION = null;

            region = region.trim().toLowerCase();

            switch (region) {
                case "us-east-1":
                    REGION = Region.getRegion(Regions.US_EAST_1);
                    break;
                case "us-west-2":
                    REGION = Region.getRegion(Regions.US_WEST_2);
                    break;
            }

            client.setRegion(REGION);
        }

        return client;
    }


    protected String getAmazonSESBounceQueueName() {
        return config.getString("aws.ses.bounce.queue");
    }

    protected String getAmazonSESComplaintQueueName() {
        return config.getString("aws.ses.complaint.queue");
    }

    protected String getAmazonSESDeliveryQueueName() {
        return config.getString("aws.ses.delivery.queue");
    }

    protected String getSystemMailhost() {
        return config.getString("system.mail.mailhost");
    }

    protected String getSystemSenderEmailAddress() {
        return config.getString("system.mail.from");
    }

    protected String getSystemFromEmailAddress() {
        return config.getString("system.mail.from");
    }

    protected String getSystemBaseUrl() {
        return config.getString("system.app.baseUrl");
    }

    protected String getEmailEventUrl() {
        return config.getString("email.event.baseUrl");
    }

    protected String getEmailFooterHtmlSelector() {
        return config.getString("email.footer.selector");
    }

    protected String getEmailTextFooterTemplatePath() {
        return config.getString("email.templates.footer.emailTextFooter");
    }

    protected String getEmailHtmlFooterTemplatePath() {
        return config.getString("email.templates.footer.emailHtmlFooter");
    }


    @Override
    public void validate(Email email) {
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
    public Email send(
            String body,
            String subject,
            String from,
            String replyTo,
            String to,
            boolean html,
            List<File> attachmentList,
            EmailFooter footer
    ) throws EmailException {

        String textBody = null;
        String htmlBody = null;

        if (html) {
            htmlBody = body;
        } else {
            textBody = body;
        }

        Email email = deliver(
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
                            String to, String cc, String bcc, boolean html, List<File> mediaList) throws EmailException {

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
            for (File media : mediaList) {
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


        logger.debug("--- Email MESSAGE START ---\n\n"
                + mailer.toString() + "\n\n"
                + "--- Email MESSAGE END ---\n\n");


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
                throw new EmailException(e);
            }

            // otherwise, we had some issue where the message couldn't be delivered, so queue
            // it and try again.

            // FIXME: setup a thread to resend messages in queue. until then throw exception
            //localMailQueue.add(message);

            throw new EmailException(e);
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
            List<File> mediaList
    ) throws EmailException {

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
            List<File> mediaList) throws EmailException {

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
            List<File> mediaList
    ) throws EmailException {

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
            for (File media : mediaList) {
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
    public EmailEvent addEvent(EmailEvent event) {
        if (event.getCreatedDate() == null) {
            event.setCreatedDate(new Date());
        }

        event.setUpdatedDate(new Date());

        if (event.getUid() == null) {
            event.setUid(uuid());
        }

        emailEventMapper.insert(event);

        return event;
    }



    @Override
    public Email fetchBySesId(String sesId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("sesId", sesId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }



    @Override
    public Email fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<Email> fetchByCampaignId(Long campaignId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }


    @Override
    public List<Email> fetchByCampaignGroupId(Long campaignGroupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignGroupId", campaignGroupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<Email> fetchByCampaignChannelId(Long campaignChannelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public Email fetchByCampaignChannelIdAndToId(Long campaignChannelId, Long emailAddressId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        filter.put("emailAddressId", emailAddressId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public List<Email> fetchByEmailGroupId(Long emailGroupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("emailGroupId", emailGroupId);
        return fetchByCriteria(0, 99999, null, filter, false);
    }

    @Override
    public List<Email> fetchByEmailGroupSlug(String emailGroupSlug) {
        EmailGroup group = emailGroupService.fetchBySlug(emailGroupSlug);
        return fetchByEmailGroupId(group.getId());
    }

    @Override
    public EmailStats calcStatsByGroupSlug(String groupSlug) {
        List<Email> emailList = fetchByEmailGroupSlug(groupSlug);
        return calcStats(emailList);
    }

    @Override
    public EmailStats calcStatsByCampaignId(Long campaignId) {
        List<Email> emailList = fetchByCampaignId(campaignId);
        return calcStats(emailList);
    }

    @Override
    public EmailStats calcStatsByCampaignGroupId(Long campaignGroupId) {
        List<Email> emailList = fetchByCampaignGroupId(campaignGroupId);
        return calcStats(emailList);
    }

    @Override
    public EmailStats calcStatsByCampaignChannelId(Long campaignChannelId) {
        List<Email> emailList = fetchByCampaignChannelId(campaignChannelId);
        return calcStats(emailList);
    }

    @Override
    public EmailStats calcStats(List<Email> emailList) {

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

        for (Email email : emailList) {
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


        EmailStats stats = new EmailStats();
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
            List<File> attachmentList,
            EmailFooter footer,
            Long throttleTime
    ) throws EmailException {

        try {
            User systemUser = userService.getSystemUser();
            EmailContent content = emailContentService.create(systemUser.getId(), html, text, attachmentList);
            deliver(campaignChannelId, emailGroupId, fromAddress, replyTo, subject, content, footer, throttleTime);
        } catch (Exception e) {
            throw new EmailException(e);
        }
    }



    @Override
    public void deliver(
            Long campaignChannelId,
            Long emailGroupId,
            String fromAddress,
            String replyTo,
            String subject,
            EmailContent content,
            EmailFooter footer,
            Long throttleTime
    ) throws EmailException {

        List<EmailGroupAddress> addressList = emailGroupAddressService.fetchByGroupId(emailGroupId);

        for (EmailGroupAddress ega : addressList) {
            EmailAddress address = emailAddressService.fetchById(ega.getAddressId());

            if (!emailAddressService.isValid(address, false)) {
                logger.debug("deliver: emailAddress is invalid: " + address.getEmail());
                continue;
            }

            deliver(campaignChannelId, emailGroupId, address, fromAddress, replyTo, subject, content, footer);

            throttle(throttleTime);
        }
    }



    @Override
    public Email deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            String text,
            String html,
            List<File> attachmentList,
            EmailFooter footer) throws EmailException {

        try {
            User systemUser = userService.getSystemUser();
            EmailContent content = emailContentService.create(systemUser.getId(), html, text, attachmentList);

            return deliver(from, replyTo, to, subject, content, footer);
        } catch (Exception e) {
            throw new EmailException(e);
        }
    }



    @Override
    public Email deliver(
            String from,
            String replyTo,
            String to,
            String subject,
            EmailContent content,
            EmailFooter footer) throws EmailException {

        if (to == null || !KValidator.isEmail(to)) {
            throw new EmailException("Email address is null or invalid: " + to);
        }

        EmailAddress address = emailAddressService.fetchByEmail(to);

        boolean saveAddress = false;

        if (address == null) {
            address = new EmailAddress();
            address.setEmail(to);
            address.setEnabled(true);
            address.setCreatedDate(new Date());
            saveAddress = true;
        }

        if (address.getUserId() == null) {
            User user = userService.fetchByEmail(to);

            if (user != null) {
                address.setUserId(user.getId());
                saveAddress = true;
            }
        }

        if (saveAddress) {
            address = emailAddressService.save(address);
        }

        if (!emailAddressService.isValid(address, false)) {
            throw new EmailException("deliver: emailAddress is invalid: " + address.getEmail());
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
     * @throws EmailException
     */
    private Email deliver(
            Long campaignChannelId,
            Long emailGroupId,
            EmailAddress address,
            String fromAddress,
            String replyTo,
            String subject,
            EmailContent content,
            EmailFooter footer
    ) throws EmailException {

        String toAddress = formatAddress(address);

        // make sure we haven't already sent an email to this user
        Email email = null;

        CampaignChannel campaignChannel = null;

        if (campaignChannelId != null) {
            email = fetchByCampaignChannelIdAndToId(campaignChannelId, address.getId());

            if (email != null) {
                logger.warn("EmailService.deliver: Skipping:  Email already delivered for campaignChannelId: "
                        + campaignChannelId);
                return null;
            }

            campaignChannel = campaignChannelService.fetchById(campaignChannelId);
        }

        String uid = uuid();

        String text1 = processContent(content.getText(), uid, address, footer, false);

        String html1 = processContent(content.getHtml(), uid, address, footer, true);

        List<File> attachments = null;

        try {
            List<EmailAttachment> _attachments = emailAttachmentService.fetchByContentId(content.getId());

            for (EmailAttachment _attachment : _attachments) {
                File file = fileService.fetchById(_attachment.getFileId(), true);
                attachments.add(file);
            }
        } catch (IOException e) {
            throw new EmailException(e);
        }

        Date now = new Date();

        email = new Email();
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

        EmailEvent event = new EmailEvent();
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

            event.setType(EmailEvent.Type.ATTEMPTED);
            event.setEmailId(email.getId());
            addEvent(event);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            email.setFailed(true);
            email = add(email);

            event.setType(EmailEvent.Type.FAILED);
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



    private String formatAddress(EmailAddress address) {
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



    private String processContent(String content, String messageId, EmailAddress address, EmailFooter footer, boolean html) {
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



    private String generateEmailFooter(EmailFooter footer, boolean html) {
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

        List<Message> messageList = queueService.fetchMessages(bounceQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                queueService.deleteMessage(bounceQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("bounce")) {
                logger.warn("bounce queue: found notification type: " + notificationType);
                queueService.deleteMessage(bounceQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> bounce = (Map<String, Object>) notification.get("bounce");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) bounce.get("timestamp");

            Date bounceDate = KDateUtil.parseISO8601(timestamp);

            Email email = fetchBySesId(sesId);

            if (email != null && email.isBounced() == false) {
                email.setBounced(true);

                update(email);

                EmailEvent event = new EmailEvent();

                event.setEmailId(email.getId());

                event.setType(EmailEvent.Type.BOUNCED);

                event.setEventDate(bounceDate);

                event.setCreatedDate(new Date());

                addEvent(event);

                disableEmailAddress(email, EmailEvent.Type.BOUNCED);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            queueService.deleteMessage(bounceQueue, message);
        }
    }



    @SuppressWarnings("unchecked")
    private void processComplaintQueue() {
        String complaintQueue = getAmazonSESComplaintQueueName();

        List<Message> messageList = queueService.fetchMessages(complaintQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                queueService.deleteMessage(complaintQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("Complaint")) {
                logger.warn("Complaint queue: found notification type: " + notificationType);
                queueService.deleteMessage(complaintQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> complaint = (Map<String, Object>) notification.get("complaint");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) complaint.get("timestamp");

            Date complainedDate = KDateUtil.parseISO8601(timestamp);

            Email email = fetchBySesId(sesId);

            if (email != null && email.isComplained() == false) {
                email.setComplained(true);

                update(email);

                EmailEvent event = new EmailEvent();

                event.setEmailId(email.getId());

                event.setType(EmailEvent.Type.COMPLAINED);

                event.setEventDate(complainedDate);

                event.setCreatedDate(new Date());

                addEvent(event);

                disableEmailAddress(email, EmailEvent.Type.COMPLAINED);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            queueService.deleteMessage(complaintQueue, message);
        }
    }



    @SuppressWarnings("unchecked")
    private void processDeliveryQueue() {
        String deliveryQueue = getAmazonSESDeliveryQueueName();

        List<Message> messageList = queueService.fetchMessages(deliveryQueue, -1);

        for (Message message : messageList) {
            Map<String,Object> notification = parseMessage(message);

            String notificationType = (String) notification.get("notificationType");

            if (notificationType.equalsIgnoreCase("AmazonSnsSubscriptionSucceeded")) {
                queueService.deleteMessage(deliveryQueue, message);
                continue;
            }

            if (!notificationType.equalsIgnoreCase("Delivery")) {
                logger.warn("Delivery queue: found notification type: " + notificationType);
                queueService.deleteMessage(deliveryQueue, message);
                continue;
            }

            Map<String,Object> mail = (Map<String, Object>) notification.get("mail");

            Map<String,Object> delivery = (Map<String, Object>) notification.get("delivery");

            String sesId = (String) mail.get("messageId");

            String timestamp = (String) delivery.get("timestamp");

            Date deliveredDate = KDateUtil.parseISO8601(timestamp);

            Email email = fetchBySesId(sesId);

            if (email != null && email.isDelivered() == false) {
                email.setDelivered(true);

                update(email);

                EmailEvent event = new EmailEvent();

                event.setEmailId(email.getId());

                event.setType(EmailEvent.Type.DELIVERED);

                event.setEventDate(deliveredDate);

                event.setCreatedDate(new Date());

                addEvent(event);
            } else {
                logger.warn("Email not found for sesId: " + sesId);
            }

            queueService.deleteMessage(deliveryQueue, message);
        }
    }



    private void disableEmailAddress(Email email, EmailEvent.Type type) {
        EmailAddress address = null;

        if (email.getEmailAddressId() != null) {
            address = emailAddressService.fetchById(email.getEmailAddressId());
        } else if (email.getToAddress() != null) {
            address = emailAddressService.fetchByEmail(email.getToAddress());
        }

        if (address != null) {
            if (type == EmailEvent.Type.BOUNCED) {
                address.setBouncedDate(new Date());
            } else if  (type == EmailEvent.Type.COMPLAINED) {
                address.setComplainedDate(new Date());
            }

            address.setEnabled(false);

            emailAddressService.update(address);
        }
    }


}
