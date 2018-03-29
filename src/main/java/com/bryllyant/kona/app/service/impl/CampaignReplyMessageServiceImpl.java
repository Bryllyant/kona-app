/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service.impl;

import com.bryllyant.kona.app.config.KConfig;
import com.bryllyant.kona.app.dao.CampaignReplyMessageMapper;
import com.bryllyant.kona.app.entity.CampaignReply;
import com.bryllyant.kona.app.entity.CampaignReplyMessage;
import com.bryllyant.kona.app.entity.CampaignReplyMessageExample;
import com.bryllyant.kona.app.service.CampaignReplyMessageService;
import com.bryllyant.kona.app.service.KAbstractCampaignReplyMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(CampaignReplyMessageService.SERVICE_PATH)
public class CampaignReplyMessageServiceImpl
        extends KAbstractCampaignReplyMessageService<
        CampaignReplyMessage,
        CampaignReplyMessageExample,
        CampaignReplyMessageMapper,
        CampaignReply>
        implements CampaignReplyMessageService {

    private static Logger logger = LoggerFactory.getLogger(CampaignReplyMessageServiceImpl.class);

    @Autowired
    private KConfig config;

    @Autowired
    private CampaignReplyMessageMapper campaignReplyMessageMapper;


    @Override @SuppressWarnings("unchecked")
    protected CampaignReplyMessageMapper getMapper() {
        return campaignReplyMessageMapper;
    }

}
