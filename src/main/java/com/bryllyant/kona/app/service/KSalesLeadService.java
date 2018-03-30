package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KSalesLead;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface KSalesLeadService<SALES_LEAD extends KSalesLead>
        extends KService, KEntityService<SALES_LEAD> {

    SALES_LEAD fetchByUid(String uid);

	List<SALES_LEAD> fetchByReferredById(Long referredById);

    List<SALES_LEAD> fetchByCampaignId(Long campaignId);

    @Transactional
    SALES_LEAD create(SALES_LEAD salesLead, KServiceClient client);

}
