package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KPromo extends KEntityObject {

	Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

	String getName();
	void setName(String name);

    String getSlug();
    void setSlug(String slug);

	String getDescription();
	void setDescription(String description);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	Date getStartDate();
	void setStartDate(Date startDate);

	Date getEndDate();
	void setEndDate(Date endDate);

	Integer getUseCount();
	void setUseCount(Integer useCount);

	Integer getUsePerAccount();
	void setUsePerAccount(Integer usePerAccount);

	Integer getMaxUseCount();
	void setMaxUseCount(Integer maxUseCount);

	Integer getDiscountPct();
	void setDiscountPct(Integer discountPct);

	BigDecimal getDiscountAmount();
	void setDiscountAmount(BigDecimal discountAmount);

	BigDecimal getSetupFee();
	void setSetupFee(BigDecimal setupFee);

	Integer getTrialDays();
	void setTrialDays(Integer trialDays);

	Integer getSubscriptionDays();
	void setSubscriptionDays(Integer subscriptionDays);

	String getValidationRule();
	void setValidationRule(String validationRule);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);

}
