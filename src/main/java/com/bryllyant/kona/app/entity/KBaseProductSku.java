package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class KBaseProductSku extends KBaseEntity implements KProductSku {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long productId;
    private String name;
    private String sku;
    private Map<String,Object> variants;
    private String description;
    private Integer displayOrder;
    private BigDecimal price;
    private BigDecimal setupFee;
    private Integer trialDays;
    private boolean subscription;
    private Integer subscriptionDays;
    private boolean enabled;
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
    public Long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSku() {
        return sku;
    }

    @Override
    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public Map<String, Object> getVariants() {
        return variants;
    }

    @Override
    public void setVariants(Map<String, Object> variants) {
        this.variants = variants;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    @Override
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getSetupFee() {
        return setupFee;
    }

    @Override
    public void setSetupFee(BigDecimal setupFee) {
        this.setupFee = setupFee;
    }

    @Override
    public Integer getTrialDays() {
        return trialDays;
    }

    @Override
    public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    @Override
    public boolean isSubscription() {
        return subscription;
    }

    @Override
    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    @Override
    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    @Override
    public void setSubscriptionDays(Integer subscriptionDays) {
        this.subscriptionDays = subscriptionDays;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
