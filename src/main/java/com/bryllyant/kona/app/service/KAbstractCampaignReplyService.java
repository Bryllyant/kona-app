package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignAnalytics;
import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.app.entity.KCampaignTarget;
import com.bryllyant.kona.app.entity.KEmail;
import com.bryllyant.kona.app.entity.KEmailAttachment;
import com.bryllyant.kona.app.entity.KEmailContent;
import com.bryllyant.kona.app.entity.KEmailEvent;
import com.bryllyant.kona.app.model.KEmailFooter;
import com.bryllyant.kona.app.entity.KPush;
import com.bryllyant.kona.app.entity.KSms;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import com.bryllyant.kona.util.KInflector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractCampaignReplyService<
        CAMPAIGN_REPLY extends KCampaignReply,
        CAMPAIGN_REPLY_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_REPLY, CAMPAIGN_REPLY_EXAMPLE>,
        CAMPAIGN_TARGET extends KCampaignTarget,
        CAMPAIGN_ANALYTICS extends KCampaignAnalytics,
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage,
        EMAIL extends KEmail,
        EMAIL_EVENT extends KEmailEvent,
        EMAIL_CONTENT extends KEmailContent,
        EMAIL_ATTACHMENT extends KEmailAttachment,
        SMS extends KSms,
        PUSH extends KPush>
        extends KAbstractService<CAMPAIGN_REPLY,CAMPAIGN_REPLY_EXAMPLE,MAPPER>
		implements KCampaignReplyService<CAMPAIGN_REPLY,CAMPAIGN_TARGET,CAMPAIGN_ANALYTICS,CAMPAIGN_REPLY_MESSAGE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignReplyService.class);

    protected abstract String getSystemMailFrom();
    protected abstract String getSystemMailReplyTo();
    protected abstract KEmailFooter getSystemMailFooter();

    protected abstract <S extends KSmsService<SMS>> S getSmsService();
    protected abstract <S extends KPushService<PUSH>> S getPushService();
    protected abstract <S extends KEmailService<EMAIL,EMAIL_EVENT,EMAIL_CONTENT,EMAIL_ATTACHMENT>> S getEmailService();
    protected abstract <S extends KCampaignReplyMessageService<CAMPAIGN_REPLY_MESSAGE, CAMPAIGN_REPLY>> S getCampaignReplyMessageService();

	@Override
	public void validate(CAMPAIGN_REPLY campaignReply) {
		if (campaignReply.getCreatedDate() == null) {
			campaignReply.setCreatedDate(new Date());
		}

        campaignReply.setUpdatedDate(new Date());
        
		if (campaignReply.getUid() == null) {
			campaignReply.setUid(uuid());
		}

        String slug = KInflector.getInstance().slug(campaignReply.getName());
		campaignReply.setSlug(slug);
	}

    @Override
    public CAMPAIGN_REPLY fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public CAMPAIGN_REPLY fetchByTargetIdAndSlug(Long targetId, String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId", targetId);
        filter.put("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

	@Override
	public List<CAMPAIGN_REPLY> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(filter);
	}

    @Override
    public List<CAMPAIGN_REPLY> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_REPLY> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_REPLY> fetchByTargetId(Long targetId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId", targetId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CAMPAIGN_REPLY create(CAMPAIGN_TARGET target, CAMPAIGN_REPLY reply) {
        reply.setCampaignId(target.getCampaignId());
        reply.setGroupId(target.getGroupId());
        reply.setTargetId(target.getId());
        reply.setEnabled(true);

        if (reply.getStartDate() == null) {
            reply.setStartDate(target.getStartDate());
        }

        if (reply.getEndDate() == null) {
            reply.setEndDate(target.getEndDate());
        }

        return add(reply);
    }

    @Override @Transactional
    public CAMPAIGN_REPLY create(
            CAMPAIGN_TARGET target,
            String name,
            CAMPAIGN_REPLY.Type type,
            String content,
            Date startDate,
            Date endDate
    ) {
        CAMPAIGN_REPLY reply = getEntityObject();

        reply.setName(name);
        reply.setType(type);
        reply.setContent(content);
        reply.setEnabled(true);
        reply.setStartDate(startDate);
        reply.setEndDate(endDate);

        return create(target, reply);
    }

    @Override @Transactional
    public CAMPAIGN_REPLY_MESSAGE execute(CAMPAIGN_REPLY reply, CAMPAIGN_ANALYTICS analytics) {
	    switch (reply.getType()) {
            case EMAIL:
                return sendEmail(reply, analytics);

            case SMS:
                return sendSms(reply, analytics);

            case PUSH:
                return sendPush(reply, analytics);

            default:
                throw new IllegalArgumentException("Invalid reply type: " + reply.getType());
        }
    }

    protected CAMPAIGN_REPLY_MESSAGE sendSms(CAMPAIGN_REPLY reply, CAMPAIGN_ANALYTICS analytics) {
        if (analytics.getConversionMobileNumber() == null) {
            throw new KServiceException("sendPush: conversionMobileNumber is null: " + analytics);
        }

        SMS sms = getSmsService().sendMessage(analytics.getConversionMobileNumber(), reply.getContent());

        return getCampaignReplyMessageService().createSms(reply, analytics.getConversionMobileNumber(), sms.getId());
    }

    protected CAMPAIGN_REPLY_MESSAGE sendEmail(CAMPAIGN_REPLY reply, CAMPAIGN_ANALYTICS analytics) {
        if (analytics.getConversionEmail() == null) {
            throw new KServiceException("sendPush: conversionEmail is null: " + analytics);
        }

        String subject = reply.getSubject();
        String body = reply.getContent();
        String from = getSystemMailFrom();
        String to = analytics.getConversionEmail();
        String replyTo = getSystemMailReplyTo();
        KEmailFooter footer = getSystemMailFooter();
        boolean html = true;

        EMAIL email = getEmailService().send(body, subject, from, replyTo, to, html, null, footer);

        return getCampaignReplyMessageService().createEmail(reply, analytics.getConversionEmail(), email.getId());
    }

    protected CAMPAIGN_REPLY_MESSAGE sendPush(CAMPAIGN_REPLY reply, CAMPAIGN_ANALYTICS analytics) {
	    if (analytics.getConversionUserId() == null) {
	        throw new KServiceException("sendPush: conversionUserId is null: " + analytics);
        }

        boolean sandbox = false;

        PUSH push = getPushService().send(
                analytics.getConversionUserId(),
                reply.getSubject(),
                reply.getContent(),
                null,
                null,
                sandbox
        );

        return getCampaignReplyMessageService().createPush(reply, analytics.getConversionUserId(), push.getId());
    }

}
