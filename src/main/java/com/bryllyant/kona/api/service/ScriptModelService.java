package com.bryllyant.kona.api.service;

import com.bryllyant.kona.api.model.script.ScriptModel;
import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.app.service.ScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptModelService extends BaseEntityModelService<ScriptModel,Script> {
    private static final Logger logger = LoggerFactory.getLogger(ScriptModelService.class);
    
    @Autowired
    private ScriptService campaignReplyService;

    @Autowired
    private CampaignModelService campaignModelService;

    @Autowired
    private CampaignGroupModelService campaignGroupModelService;

    @Autowired
    private CampaignChannelModelService campaignChannelModelService;

    @Autowired
    private CampaignTargetModelService campaignTargetModelService;


    protected ScriptService getEntityService() {
        return campaignReplyService;
    }


    protected void setForeignModelProperties(ScriptModel model, Script campaignReply) {

    }

    protected void setEntityProperty(String key, ScriptModel model, Script campaignReply) {

    }
}
