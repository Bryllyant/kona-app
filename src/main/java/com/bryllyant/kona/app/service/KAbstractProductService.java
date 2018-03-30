package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KProduct;
import com.bryllyant.kona.data.dao.KMyBatisMapper;
import com.bryllyant.kona.data.mybatis.KEntityExample;
import com.bryllyant.kona.data.mybatis.KMyBatisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class KAbstractProductService<
        PRODUCT extends KProduct,
        PRODUCT_EXAMPLE extends KEntityExample,
        MAPPER extends KMyBatisMapper<PRODUCT, PRODUCT_EXAMPLE>>
        extends KAbstractService<PRODUCT,PRODUCT_EXAMPLE,MAPPER>
        implements KProductService<PRODUCT> {

    private static Logger logger = LoggerFactory.getLogger(KAbstractProductService.class);


    @Override
    public void validate(PRODUCT product) {
        if (product.getCreatedDate() == null) {
            product.setCreatedDate(new Date());
        }

        if (product.getUid() == null) {
            product.setUid(uuid());
        }

        product.setUpdatedDate(new Date());
    }
    

    /*
    @Override
    // FIXME: make sure that when adding/remove/updating products, the default
    // product is handled properly.
    public Product fetchDefault(Long appId) {
        Product defaultProduct = null;
        if (defaultProduct == null) {
            ProductExample example = new ProductExample();
            example.or()
            	.andAppIdEqualTo(appId)
            	.andDefaultPlanEqualTo(true);

                List<Product> result = 
                        productDao.selectByExample(example);
                if (result != null && result.size() >0) {
                    defaultProduct = result.get(0);
                }
        }
        return defaultProduct;
    }
    */

    @Override
    public List<PRODUCT> fetchAll(Boolean enabled) {
        Map<String,Object> filter = null;

        if (enabled != null) {
            filter = KMyBatisUtil.createFilter("enabled", enabled);
        }

        return fetchByCriteria(filter);
    }

    @Override
    public PRODUCT fetchBySlug(String slug) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("slug", slug);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }

    @Override
    public PRODUCT fetchByUid(String uid) {
        Map<String,Object> filter = KMyBatisUtil.createFilter("uid", uid);
        return KMyBatisUtil.fetchOne(fetchByCriteria(0, 99999, null, filter, false));
    }
}
