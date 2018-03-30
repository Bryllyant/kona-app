package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBasePromo extends KBaseEntity implements KPromo {
    private static final long serialVersionUID = 1L;

    private Long id;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
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
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Integer getUseCount() {
        return useCount;
    }

    @Override
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    @Override
    public Integer getUsePerAccount() {
        return usePerAccount;
    }

    @Override
    public void setUsePerAccount(Integer usePerAccount) {
        this.usePerAccount = usePerAccount;
    }

    @Override
    public Integer getMaxUseCount() {
        return maxUseCount;
    }

    @Override
    public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    @Override
    public Integer getDiscountPct() {
        return discountPct;
    }

    @Override
    public void setDiscountPct(Integer discountPct) {
        this.discountPct = discountPct;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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
    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    @Override
    public void setSubscriptionDays(Integer subscriptionDays) {
        this.subscriptionDays = subscriptionDays;
    }

    @Override
    public String getValidationRule() {
        return validationRule;
    }

    @Override
    public void setValidationRule(String validationRule) {
        this.validationRule = validationRule;
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