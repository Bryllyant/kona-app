package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignReplyModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignTargetModel;
import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.app.entity.CampaignChannel;
import com.bryllyant.kona.app.entity.CampaignGroup;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignTarget;
import com.bryllyant.kona.app.service.CampaignReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignReplyModelService extends BaseEntityModelService<CampaignReplyModel,CampaignReply> {
    private static final Logger logger = LoggerFactory.getLogger(CampaignReplyModelService.class);
    
    @Autowired
    private CampaignReplyService campaignReplyService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;


    protected CampaignReplyService getEntityService() {
        return campaignReplyService;
    }


    protected void setForeignModelProperties(CampaignReplyModel model, CampaignReply campaignReply) {
        if (campaignReply.getCampaignId() != null) {
            Campaign campaign = campaignModelService.getCampaign(campaignReply.getCampaignId());
            model.setCampaign(CampaignModel.from(campaign));
        }

        if (campaignReply.getGroupId() != null) {
            CampaignGroup group = campaignGroupModelService.getCampaignGroup(campaignReply.getGroupId());
            model.setGroup(CampaignGroupModel.from(group));
        }

        if (campaignReply.getChannelId() != null) {
            CampaignChannel channel = campaignChannelModelService.getCampaignChannel(campaignReply.getChannelId());
            model.setChannel(CampaignChannelModel.from(channel));
        }

        if (campaignReply.getTargetId() != null) {
            CampaignTarget target = campaignTargetModelService.getCampaignTarget(campaignReply.getTargetId());
            model.setTarget(CampaignTargetModel.from(target));
        }
    }

    protected void setEntityProperty(String key, CampaignReplyModel model, CampaignReply campaignReply) {
        switch (key) {

            case "campaign":
                Campaign campaign = campaignModelService.getCampaign(model.getCampaign());
                campaignReply.setCampaignId(campaign == null ? null : campaign.getId());
                break;

            case "group":
                CampaignGroup campaignGroup = campaignGroupModelService.getCampaignGroup(model.getGroup());
                campaignReply.setGroupId(campaignGroup == null ? null : campaignGroup.getId());
                break;

            case "channel":
                CampaignChannel campaignChannel = campaignChannelModelService.getCampaignChannel(model.getChannel());
                campaignReply.setChannelId(campaignChannel == null ? null : campaignChannel.getId());
                break;

            case "target":
                CampaignTarget target = campaignTargetModelService.getCampaignTarget(model.getTarget());
                campaignReply.setTargetId(target == null ? null : target.getId());
                break;
        }
    }
}
