package com.bryllyant.kona.api.model.sales.promo;

import com.bryllyant.kona.app.entity.Promo;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PromoModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String name;
    private String slug;
    private String description;
    private boolean enabled;
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

    private List<PromoCodeModel> codes;
    private List<PromoProductModel> products;

    private Date createdDate;
    private Date updatedDate;

    public static PromoModel from(Promo promo) {
        PromoModel model = new PromoModel();
        model.setUid(promo.getUid());
        model.setName(promo.getName());
        return model;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.set("enabled", enabled);
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
