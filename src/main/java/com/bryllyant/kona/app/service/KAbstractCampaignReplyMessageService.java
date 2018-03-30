package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KCampaignReply;
import com.bryllyant.kona.app.entity.KCampaignReplyMessage;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class KAbstractCampaignReplyMessageService<
        CAMPAIGN_REPLY_MESSAGE extends KCampaignReplyMessage,
        CAMPAIGN_REPLY_MESSAGE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<CAMPAIGN_REPLY_MESSAGE, CAMPAIGN_REPLY_MESSAGE_EXAMPLE>,
        CAMPAIGN_REPLY extends KCampaignReply>
        extends KAbstractService<CAMPAIGN_REPLY_MESSAGE,CAMPAIGN_REPLY_MESSAGE_EXAMPLE,MAPPER>
		implements KCampaignReplyMessageService<CAMPAIGN_REPLY_MESSAGE,CAMPAIGN_REPLY> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractCampaignReplyMessageService.class);

	@Override
	public void validate(CAMPAIGN_REPLY_MESSAGE campaignReplyMessage) {
		if (campaignReplyMessage.getCreatedDate() == null) {
			campaignReplyMessage.setCreatedDate(new Date());
		}

        campaignReplyMessage.setUpdatedDate(new Date());
        
		if (campaignReplyMessage.getUid() == null) {
			campaignReplyMessage.setUid(uuid());
		}
	}

    @Override
    public CAMPAIGN_REPLY_MESSAGE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


	@Override
	public List<CAMPAIGN_REPLY_MESSAGE> fetchByCampaignId(Long campaignId) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("campaignId", campaignId);
		return fetchByCriteria(filter);
	}

    @Override
    public List<CAMPAIGN_REPLY_MESSAGE> fetchByGroupId(Long groupId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("groupId", groupId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_REPLY_MESSAGE> fetchByChannelId(Long channelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("channelId", channelId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_REPLY_MESSAGE> fetchByTargetId(Long targetId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("targetId",targetId);
        return fetchByCriteria(filter);
    }

    @Override
    public List<CAMPAIGN_REPLY_MESSAGE> fetchByReplyId(Long replyId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("replyId", replyId);
        return fetchByCriteria(filter);
    }


    @Override @Transactional
    public CAMPAIGN_REPLY_MESSAGE create(CAMPAIGN_REPLY reply, CAMPAIGN_REPLY_MESSAGE message) {
        message.setCampaignId(reply.getCampaignId());
        message.setGroupId(reply.getGroupId());
        message.setChannelId(reply.getChannelId());
        message.setTargetId(reply.getTargetId());
        message.setReplyId(reply.getId());


        return add(message);
    }

    @Override @Transactional
    public CAMPAIGN_REPLY_MESSAGE createEmail(CAMPAIGN_REPLY reply, String toEmail, Long emailId) {
        CAMPAIGN_REPLY_MESSAGE message = getEntityObject();
        message.setToEmail(toEmail);
        message.setEmailId(emailId);
        return create(reply, message);
    }

    @Override @Transactional
    public CAMPAIGN_REPLY_MESSAGE createSms(CAMPAIGN_REPLY reply, String toMobileNumber, Long smsId) {
        CAMPAIGN_REPLY_MESSAGE message = getEntityObject();
        message.setToMobileNumber(toMobileNumber);
        message.setSmsId(smsId);
        return create(reply, message);
    }

    @Override @Transactional
    public CAMPAIGN_REPLY_MESSAGE createPush(CAMPAIGN_REPLY reply, Long toUserId, Long pushId) {
        CAMPAIGN_REPLY_MESSAGE message = getEntityObject();
        message.setToUserId(toUserId);
        message.setPushId(pushId);
        return create(reply, message);
    }

}
