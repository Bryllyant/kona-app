package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromoCode;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;

public interface KPromoCodeService<PROMO_CODE extends KPromoCode> extends KService, KEntityService<PROMO_CODE> {
    
	PROMO_CODE fetchByUid(String uid);

    PROMO_CODE fetchByPromoCode(String promoCode);

    PROMO_CODE fetchByCampaignChannelId(Long campaignChannelId);

    List<PROMO_CODE> fetchByPromoId(Long promoId);

    PROMO_CODE create(Long promoId, Long campaignChannelId, String promoCode);
}
