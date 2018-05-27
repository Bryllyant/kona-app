package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Promo extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.start_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.end_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer useCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.use_per_account
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer usePerAccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.max_use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer maxUseCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.discount_pct
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer discountPct;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.discount_amount
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private BigDecimal discountAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.setup_fee
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private BigDecimal setupFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.trial_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer trialDays;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.subscription_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Integer subscriptionDays;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.validation_rule
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String validationRule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__promo
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.uid
     *
     * @return the value of kona__promo.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.uid
     *
     * @param uid the value for kona__promo.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.name
     *
     * @return the value of kona__promo.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.name
     *
     * @param name the value for kona__promo.name
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.slug
     *
     * @return the value of kona__promo.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.slug
     *
     * @param slug the value for kona__promo.slug
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.description
     *
     * @return the value of kona__promo.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.description
     *
     * @param description the value for kona__promo.description
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.enabled
     *
     * @return the value of kona__promo.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.enabled
     *
     * @param enabled the value for kona__promo.enabled
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.start_date
     *
     * @return the value of kona__promo.start_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.start_date
     *
     * @param startDate the value for kona__promo.start_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.end_date
     *
     * @return the value of kona__promo.end_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.end_date
     *
     * @param endDate the value for kona__promo.end_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.use_count
     *
     * @return the value of kona__promo.use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getUseCount() {
        return useCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.use_count
     *
     * @param useCount the value for kona__promo.use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.use_per_account
     *
     * @return the value of kona__promo.use_per_account
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getUsePerAccount() {
        return usePerAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.use_per_account
     *
     * @param usePerAccount the value for kona__promo.use_per_account
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUsePerAccount(Integer usePerAccount) {
        this.usePerAccount = usePerAccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.max_use_count
     *
     * @return the value of kona__promo.max_use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getMaxUseCount() {
        return maxUseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.max_use_count
     *
     * @param maxUseCount the value for kona__promo.max_use_count
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.discount_pct
     *
     * @return the value of kona__promo.discount_pct
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getDiscountPct() {
        return discountPct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.discount_pct
     *
     * @param discountPct the value for kona__promo.discount_pct
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setDiscountPct(Integer discountPct) {
        this.discountPct = discountPct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.discount_amount
     *
     * @return the value of kona__promo.discount_amount
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.discount_amount
     *
     * @param discountAmount the value for kona__promo.discount_amount
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.setup_fee
     *
     * @return the value of kona__promo.setup_fee
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public BigDecimal getSetupFee() {
        return setupFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.setup_fee
     *
     * @param setupFee the value for kona__promo.setup_fee
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSetupFee(BigDecimal setupFee) {
        this.setupFee = setupFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.trial_days
     *
     * @return the value of kona__promo.trial_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getTrialDays() {
        return trialDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.trial_days
     *
     * @param trialDays the value for kona__promo.trial_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setTrialDays(Integer trialDays) {
        this.trialDays = trialDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.subscription_days
     *
     * @return the value of kona__promo.subscription_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.subscription_days
     *
     * @param subscriptionDays the value for kona__promo.subscription_days
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSubscriptionDays(Integer subscriptionDays) {
        this.subscriptionDays = subscriptionDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.validation_rule
     *
     * @return the value of kona__promo.validation_rule
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getValidationRule() {
        return validationRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.validation_rule
     *
     * @param validationRule the value for kona__promo.validation_rule
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setValidationRule(String validationRule) {
        this.validationRule = validationRule == null ? null : validationRule.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.created_date
     *
     * @return the value of kona__promo.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.created_date
     *
     * @param createdDate the value for kona__promo.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo.updated_date
     *
     * @return the value of kona__promo.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo.updated_date
     *
     * @param updatedDate the value for kona__promo.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}