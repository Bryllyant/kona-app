package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractProductSkuService<
        PRODUCT_SKU extends KProductSku,
        PRODUCT_SKU_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PRODUCT_SKU, PRODUCT_SKU_EXAMPLE>>
        extends KAbstractService<PRODUCT_SKU,PRODUCT_SKU_EXAMPLE,MAPPER>
        implements KProductSkuService<PRODUCT_SKU> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractProductSkuService.class);


    @Override
    public void validate(PRODUCT_SKU productSku) {
        if (productSku.getCreatedDate() == null) {
            productSku.setCreatedDate(new Date());
        }

        if (productSku.getUid() == null) {
            productSku.setUid(uuid());
        }

        productSku.setUpdatedDate(new Date());
    }

    @Override
    public PRODUCT_SKU fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }


    @Override
    public PRODUCT_SKU fetchBySku(String sku) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("sku", sku);
        return KMyBatisUtil.fetchOne(fetchByCriteria(filter));
    }

    @Override
    public PRODUCT_SKU fetchDefaultByProductId(Long productId) {
        List<PRODUCT_SKU> list = fetchByProductId(productId);

        if (list != null && list.size()  > 0) {
            list.sort((PRODUCT_SKU a, PRODUCT_SKU b) -> {
                Long diff = a.getCreatedDate().getTime() - b.getCreatedDate().getTime();
                return diff.intValue();
            });

            return list.get(0);
        }

        return null;
    }

    @Override
    public List<PRODUCT_SKU> fetchByProductId(Long productId) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("productId", productId);
        return fetchByCriteria(filter);
    }

}
