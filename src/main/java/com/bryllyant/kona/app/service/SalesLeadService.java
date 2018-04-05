/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SalesLeadService extends KService, KEntityService<SalesLead> {
	String SERVICE_PATH = "rpc/SalesLeadService";

    List<SalesLead> fetchByReferredById(Long referredById);

    List<SalesLead> fetchByCampaignId(Long campaignId);

    @Transactional
    SalesLead create(SalesLead salesLead, KServiceClient client);
}
