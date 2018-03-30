package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPromoProduct extends KEntityObject {
    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getPromoId();

    void setPromoId(Long promoId);

    Long getProductId();

    void setProductId(Long productId);

    Long getProductCategoryId();

    void setProductCategoryId(Long productCategoryId);

    Long getProductSkuId();

    void setProductSkuId(Long productSkuId);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
