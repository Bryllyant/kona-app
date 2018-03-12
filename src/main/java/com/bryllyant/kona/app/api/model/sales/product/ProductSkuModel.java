package com.bryllyant.kona.app.api.model.sales.product;

import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ProductSkuModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private ProductModel product;
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
    

    public static ProductSkuModel create(String uid) {
        ProductSkuModel model = new ProductSkuModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.set("product", product);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.set("sku", sku);
    }

    public Map<String, Object> getVariants() {
        return variants;
    }

    public void setVariants(Map<String, Object> variants) {
        this.set("variants", variants);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.set("displayOrder", displayOrder);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.set("price", price);
    }

    public BigDecimal getSetupFee() {
        return setupFee;
    }

    public void setSetupFee(BigDecimal setupFee) {
        this.set("setupFee", setupFee);
    }

    public Integer getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(Integer trialDays) {
        this.set("trialDays", trialDays);
    }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.set("subscription", subscription);
    }

    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    public void setSubscriptionDays(Integer subscriptionDays) {
        this.set("subscriptionDays", subscriptionDays);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.set("enabled", enabled);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
