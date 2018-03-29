package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignReplyMessageModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignReplyModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignTargetModel;
import com.bryllyant.kona.app.api.model.sales.campaign.EmailModel;
import com.bryllyant.kona.app.api.model.sales.campaign.PushModel;
import com.bryllyant.kona.app.api.model.sales.campaign.SmsModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.entity.Email;
import com.bryllyant.kona.app.entity.Push;
import com.bryllyant.kona.app.entity.Sms;
import com.bryllyant.kona.app.service.CampaignReplyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignReplyMessageModelService extends BaseEntityModelService<CampaignReplyMessageModel,CampaignReplyMessage> {
    private static final Logger logger = LoggerFactory.getLogger(CampaignReplyMessageModelService.class);
    
    @Autowired
    private CampaignReplyMessageService campaignReplyMessageService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;

    @Autowired
    private CampaignReplyModelService campaignReplyModelService;

    @Autowired
    private EmailModelService emailModelService;

    @Autowired
    private SmsModelService smsModelService;

    @Autowired
    private PushModelService pushModelService;


    protected CampaignReplyMessageService getEntityService() {
        return campaignReplyMessageService;
    }


    protected void setForeignModelProperties(CampaignReplyMessageModel model, CampaignReplyMessage message) {
        if (message.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(message.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (message.getGroupId() != null) {
            CampaignGroup group = campaignGroupModelService.getCampaignGroup(message.getGroupId());
            model.setGroup(CampaignGroupModel.from(group));
        }

        if (message.getChannelId() != null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(message.getChannelId());
            model.setChannel(CampaignChannelModel.from(channel));
        }

        if (message.getTargetId() != null) {
            CampaignTarget target = campaignTargetModelService.getCampaignTarget(message.getTargetId());
            model.setTarget(CampaignTargetModel.from(target));
        }

        if (message.getReplyId() != null) {
            CampaignReply reply = campaignReplyModelService.getEntity(message.getTargetId());
            model.setReply(CampaignReplyModel.from(reply));
        }

        if (message.getEmailId() != null) {
            Email email = emailModelService.getEntity(message.getEmailId());
            model.setEmail(EmailModel.from(email));
        }

        if (message.getSmsId() != null) {
            Sms sms = smsModelService.getEntity(message.getSmsId());
            model.setSms(SmsModel.from(sms));
        }

        if (message.getPushId() != null) {
            Push push = pushModelService.getEntity(message.getPushId());
            model.setPush(PushModel.from(push));
        }
    }

    protected void setEntityProperty(String key, CampaignReplyMessageModel model, CampaignReplyMessage message) {
        switch (key) {

            case "campaign":
                Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                message.setCampaignId(campaign == null ? null : campaign.getId());
                break;

            case "group":
                CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(model.getGroup());
                message.setGroupId(campaignGroup == null ? null : campaignGroup.getId());
                break;

            case "channel":
                CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(model.getChannel());
                message.setChannelId(campaignChannel == null ? null : campaignChannel.getId());
                break;

            case "target":
                CampaignTarget target = campaignTargetModelService.getCampaignTarget(model.getTarget());
                message.setTargetId(target == null ? null : target.getId());
                break;

            case "reply":
                CampaignReply reply = campaignReplyModelService.getEntity(model.getReply());
                message.setReplyId(reply == null ? null : reply.getId());
                break;

            case "email":
                Email email = emailModelService.getEntity(model.getEmail());
                message.setEmailId(email == null ? null : email.getId());
                break;

            case "sms":
                Sms sms = smsModelService.getEntity(model.getSms());
                message.setSmsId(sms == null ? null : sms.getId());
                break;

            case "push":
                Push push = pushModelService.getEntity(model.getPush());
                message.setPushId(push == null ? null : push.getId());
                break;
        }
    }
}
