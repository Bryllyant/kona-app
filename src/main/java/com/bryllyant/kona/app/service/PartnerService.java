/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Partner;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.Date;
import java.util.List;

public interface PartnerService extends KService, KEntityService<Partner> {
	String SERVICE_PATH = "rpc/PartnerService";

    Partner fetchBySlug(String slug);

    List<Partner> fetchAllByParentId(Long parentId);

    Partner retire(Partner partner);

    List<Partner> fetchProximate(
            Double latitude,
            Double longitude,
            Double radius,
            Date startDate,
            Date endDate,
            List<Long> objectIdList
    );
	
}
