package com.bryllyant.kona.app.service;

import java.util.Date;
import java.util.List;

import com.bryllyant.kona.app.entity.KPartner;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;


public interface KPartnerService<PARTNER extends KPartner> 
		extends KService, KEntityService<PARTNER> {
    
    PARTNER fetchByUid(String uid);

    PARTNER fetchBySlug(String slug);

    List<PARTNER> fetchAllByParentId(Long parentId);
    
	PARTNER retire(PARTNER partner);
	
    List<PARTNER> fetchProximate(
        Double latitude,
        Double longitude,
        Double radius,
        Date startDate,
        Date endDate,
        List<Long> objectIdList
    );
}
