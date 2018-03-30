package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KPromoProduct;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractPromoProductService<
        PROMO_PRODUCT extends KPromoProduct,
        PROMO_PRODUCT_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PROMO_PRODUCT, PROMO_PRODUCT_EXAMPLE>>
		extends KAbstractService<PROMO_PRODUCT,PROMO_PRODUCT_EXAMPLE,MAPPER>
		implements KPromoProductService<PROMO_PRODUCT> {

	private static Logger logger = LoggerFactory.getLogger(KAbstractPromoProductService.class);


	@Override
	public void validate(PROMO_PRODUCT promoProduct) {
		if (promoProduct.getCreatedDate() == null) {
            promoProduct.setCreatedDate(new Date());
		}

		if (promoProduct.getUid() == null) {
            promoProduct.setUid(uuid());
		}

        promoProduct.setUpdatedDate(new Date());
	}


    @Override
    public PROMO_PRODUCT fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public List<PROMO_PRODUCT> fetchByPromoId(Long promoId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("promoId", promoId);
        return fetchByCriteria(filter);
    }
}
