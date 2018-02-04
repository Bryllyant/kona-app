package com.bryllyant.kona.app.api.model.sales.promo;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.sales.product.ProductModel;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.math.BigDecimal;
import java.util.Date;

public class PromoModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;

    @RestdocsNotExpanded
    private AppModel app;

    @RestdocsNotExpanded
    ProductModel product;

    private String name;
    private String promoCode;
    private String description;
    private Boolean enabled;
    private Boolean visible;
    private Boolean signupDefault;
    private Date startDate;
    private Date endDate;
    private Integer useCount;
    private Integer usePerAccount;
    private Integer maxUseCount;
    private Integer discountPct;
    private BigDecimal discountAmount;
    private BigDecimal setupFee;
    private Integer trialDays;
    private Integer subscriptionDays;
    private String validationRule;
    private Date createdDate;
    private Date updatedDate;
    

    public static PromoModel create(String uid) {
        PromoModel model = new PromoModel();
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

    public AppModel getApp() {
        return app;
    }

    public void setApp(AppModel app) {
        this.set("app", app);
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

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.set("promoCode", promoCode);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.set("visible", visible);
    }

    public Boolean getSignupDefault() {
        return signupDefault;
    }

    public void setSignupDefault(Boolean signupDefault) {
        this.set("signupDefault", signupDefault);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.set("startDate", startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.set("endDate", endDate);
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.set("useCount", useCount);
    }

    public Integer getUsePerAccount() {
        return usePerAccount;
    }

    public void setUsePerAccount(Integer usePerAccount) {
        this.set("usePerAccount", usePerAccount);
    }

    public Integer getMaxUseCount() {
        return maxUseCount;
    }

    public void setMaxUseCount(Integer maxUseCount) {
        this.set("maxUseCount", maxUseCount);
    }

    public Integer getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(Integer discountPct) {
        this.set("discountPct", discountPct);
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.set("discountAmount", discountAmount);
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

    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    public void setSubscriptionDays(Integer subscriptionDays) {
        this.set("subscriptionDays", subscriptionDays);
    }

    public String getValidationRule() {
        return validationRule;
    }

    public void setValidationRule(String validationRule) {
        this.set("validationRule", validationRule);
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
