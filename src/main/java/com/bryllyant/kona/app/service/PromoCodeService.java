/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.PromoCode;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface PromoCodeService extends KService, KEntityService<PromoCode> {
	String SERVICE_PATH = "rpc/PromoCodeService";

    PromoCode fetchByPromoCode(String promoCode);

    PromoCode fetchByCampaignChannelId(Long campaignChannelId);

    List<PromoCode> fetchByPromoId(Long promoId);

    PromoCode create(Long promoId, Long campaignChannelId, String promoCode);
}
