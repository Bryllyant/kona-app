package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromoCode;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPromoCodeService<
        PROMO_CODE extends KPromoCode,
        PROMO_CODE_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PROMO_CODE, PROMO_CODE_EXAMPLE>>
		extends KAbstractService<PROMO_CODE,PROMO_CODE_EXAMPLE,MAPPER>
		implements KPromoCodeService<PROMO_CODE> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPromoCodeService.class);

	protected abstract PROMO_CODE getNewObject();

	@Override
	public void validate(PROMO_CODE promoCode) {
		if (promoCode.getCreatedDate() == null) {
			promoCode.setCreatedDate(new Date());
		}

		if (promoCode.getUid() == null) {
			promoCode.setUid(uuid());
		}
        
		promoCode.setUpdatedDate(new Date());
	}


    @Override
    public PROMO_CODE fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

	@Override
	public PROMO_CODE fetchByPromoCode(String promoCode) {
		Map<String,Object> filter = KMyBatisUtil.createFilter("promoCode", promoCode);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
	}

    @Override
    public PROMO_CODE fetchByCampaignChannelId(Long campaignChannelId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("campaignChannelId", campaignChannelId);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<PROMO_CODE> fetchByPromoId(Long promoId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoId", promoId);
        return fetchByCriteria(filter);
    }


    @Override
    public PROMO_CODE create(Long promoId, Long campaignChannelId, String promoCode) {
	    PROMO_CODE pc = getNewObject();
	    pc.setPromoId(promoId);
	    pc.setCampaignChannelId(campaignChannelId);
	    pc.setPromoCode(promoCode);

	    return add(pc);
    }
}
