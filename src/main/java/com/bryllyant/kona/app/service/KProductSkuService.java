package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.KProductSku;
import com.bryllyant.kona.data.service.KEntityService;
import com.bryllyant.kona.remote.service.KService;

import java.util.List;


public interface KProductSkuService<PRODUCT_SKU extends KProductSku>
        extends KService, KEntityService<PRODUCT_SKU> {

    PRODUCT_SKU fetchByUid(String uid);

    PRODUCT_SKU fetchBySku(String sku);

    PRODUCT_SKU fetchDefaultByProductId(Long productId);

	List<PRODUCT_SKU> fetchByProductId(Long productId);
}
