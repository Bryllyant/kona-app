package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePromoProduct extends KBaseEntity implements KPromoProduct {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long promoId;
    private Long productId;
    private Long productCategoryId;
    private Long productSkuId;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getPromoId() {
        return promoId;
    }

    @Override
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    @Override
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    @Override
    public Long getProductSkuId() {
        return productSkuId;
    }

    @Override
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}