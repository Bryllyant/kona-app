package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.dao.EmailCampaignMapper;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.EmailCampaign;
import com.bryllyant.kona.app.entity.EmailCampaignExample;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.EmailFooter;
import com.bryllyant.kona.app.model.EmailStats;
import com.bryllyant.kona.app.service.EmailCampaignService;
import com.bryllyant.kona.app.service.EmailContentService;
import com.bryllyant.kona.app.service.EmailService;
import com.bryllyant.kona.app.service.SystemService;
import com.bryllyant.kona.config.KConfig;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.data.service.KAbstractService;
import com.bryllyant.kona.data.service.KServiceException;
import com.bryllyant.kona.util.AppUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service(EmailCampaignService.SERVICE_PATH)
public class EmailCampaignServiceImpl
        extends KAbstractService<EmailCampaign, EmailCampaignExample, EmailCampaignMapper>
        implements EmailCampaignService {

    private static Logger logger = LoggerFactory.getLogger(EmailCampaignServiceImpl.class);

    @Autowired
    private KConfig config;

    @Autowired
    private EmailCampaignMapper emailCampaignMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailContentService emailContentService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private AppUtil util;


    @Override @SuppressWarnings("unchecked")
    protected EmailCampaignMapper getMapper() {
        return emailCampaignMapper;
    }

    @Override
    protected boolean entityHasBlobs() {
        return false;
    }


    @Override
    public void validate(EmailCampaign emailCampaign) {
        if (emailCampaign.getCreatedDate() == null) {
            emailCampaign.setCreatedDate(new Date());
        }

        emailCampaign.setUpdatedDate(new Date());

        if (emailCampaign.getUid() == null) {
            emailCampaign.setUid(uuid());
        }

        emailCampaign.setSlug(KInflector.getInstance().slug(emailCampaign.getName()));
    }

    @Override
    public List<EmailCampaign> fetchByEmailGroupId(Long emailGroupId) {
        Map<String, Object> filter = KMyBatisUtil.createFilter("emailGroupId", emailGroupId);
        return (fetchByCriteria(filter));
    }

    @Override
    public EmailCampaign create(
            User owner,
            String name,
            CampaignChannel channel,
            EmailGroup group,
            EmailContent content,
            String fromAddress,
            String replyTo,
            String subject
    ) {

        if (fromAddress == null) {
            fromAddress = config.getString("system.mail.from");
        }

        if (replyTo == null) {
            replyTo = fromAddress;
        }

        EmailCampaign emailCampaign = new EmailCampaign();

        emailCampaign.setOwnerId(owner.getId());

        emailCampaign.setCampaignId(channel.getCampaignId());
        emailCampaign.setCampaignGroupId(channel.getGroupId());
        emailCampaign.setCampaignChannelId(channel.getId());
        emailCampaign.setEmailGroupId(group.getId());
        emailCampaign.setEmailContentId(content.getId());

        emailCampaign.setName(name);
        emailCampaign.setFromAddress(fromAddress);
        emailCampaign.setReplyTo(replyTo);
        emailCampaign.setSubject(subject);

        emailCampaign.setStatus(EmailCampaign.Status.CREATED);

        return add(emailCampaign);
    }


    @Override
    public EmailCampaign start(final EmailCampaign emailCampaign, Long throttleTime, boolean force) {

        if (emailCampaign.getStatus() == null) {
            if (force) {
                logger.warn("WARNING: Starting campaign with unknown status");
            } else {
                throw new KServiceException("Cannot start campaign with unknown status without 'force' option.");
            }
        }

        if (emailCampaign.getStatus() != EmailCampaign.Status.CREATED) {
            if (force) {
                logger.warn("WARNING: Starting campaign that has already executed with status: " + emailCampaign.getStatus());
            } else {
                throw new KServiceException("Cannot start campaign that has already executed without 'force' option.");
            }
        }

        EmailContent content = emailContentService.fetchById(emailCampaign.getEmailContentId());

        EmailFooter footer = new EmailFooter();

        new Thread(() -> {

            try {
                emailService.deliver(
                        emailCampaign,
                        emailCampaign.getFromAddress(),
                        emailCampaign.getReplyTo(),
                        emailCampaign.getSubject(),
                        content,
                        footer,
                        throttleTime);

                emailCampaign.setStatus(EmailCampaign.Status.PROCESSING);

                update(emailCampaign);

                monitor(emailCampaign);
            } catch (Throwable t) {
                emailCampaign.setStatus(EmailCampaign.Status.ERROR);
                update(emailCampaign);
                logger.error(t.getMessage(), t);
                systemService.alert("[EmailCampaign.blast] Error running email campaign", t);
            }
        }).start();

        emailCampaign.setStatus(EmailCampaign.Status.RUNNING);

        EmailCampaign _emailCampaign =  update(emailCampaign);

        return _emailCampaign;
    }

    private void monitor(final EmailCampaign campaign) {
        Long sleepTime = 1 * 60 * 1000L;
        Integer maxSleepCount = 4 * 60;

        new Thread(() -> {
            Integer sleepCount = 0;

            while (campaign.getStatus() != EmailCampaign.Status.COMPLETED && sleepCount < maxSleepCount) {
                updateStats(campaign, true);

                sleepCount += 1;

                util.sleep(sleepTime);
            }
        }).start();
    }

    @Override
    public EmailCampaign updateStats(final EmailCampaign campaign, boolean processNotifications) {
        if (processNotifications) {
            emailService.processSESNotifications();
        }

        EmailStats stats = emailService.calcStatsByEmailCampaignId(campaign.getId());

        campaign.setEmailCount(stats.getEmailCount());
        campaign.setFailedCount(stats.getFailed());
        campaign.setDeliveredCount(stats.getDelivered());
        campaign.setBouncedCount(stats.getBounced());
        campaign.setComplainedCount(stats.getComplained());
        campaign.setOptedOutCount(stats.getOptedOut());
        campaign.setOpenedCount(stats.getOpened());
        campaign.setClickedCount(stats.getClicked());
        campaign.setPrintedCount(stats.getPrinted());
        campaign.setForwardedCount(stats.getForwarded());

        campaign.setFailedRate(stats.getFailedRate());
        campaign.setDeliveredRate(stats.getDeliveredRate());
        campaign.setBouncedRate(stats.getBouncedRate());
        campaign.setComplainedRate(stats.getComplainedRate());
        campaign.setOptedOutRate(stats.getOptedOutRate());

        campaign.setOpenedAllRate(stats.getOpenedAllRate());
        campaign.setOpenedDeliveredRate(stats.getOpenedDeliveredRate());

        campaign.setClickedAllRate(stats.getClickedAllRate());
        campaign.setClickedDeliveredRate(stats.getClickedDeliveredRate());
        campaign.setClickedOpenedRate(stats.getClickedOpenedRate());

        campaign.setPrintedAllRate(stats.getPrintedAllRate());
        campaign.setPrintedDeliveredRate(stats.getPrintedDeliveredRate());
        campaign.setPrintedOpenedRate(stats.getPrintedOpenedRate());

        campaign.setForwardedAllRate(stats.getForwardedAllRate());
        campaign.setForwardedDeliveredRate(stats.getForwardedDeliveredRate());
        campaign.setForwardedOpenedRate(stats.getForwardedOpenedRate());

        Double totalSent =
                stats.getBounced()
                + stats.getFailed()
                + stats.getDelivered()
                + stats.getComplained();

        logger.debug("[updateStats]  Total messages: {}   Total sent: {}", stats.getEmailCount(), totalSent);

        if (totalSent < stats.getEmailCount() && campaign.getStatus() == EmailCampaign.Status.RUNNING) {
            campaign.setStatus(EmailCampaign.Status.PROCESSING);
        }

        if (totalSent >= stats.getEmailCount() && campaign.getStatus() == EmailCampaign.Status.PROCESSING) {
            campaign.setStatus(EmailCampaign.Status.COMPLETED);
        }

        return save(campaign);
    }
}
