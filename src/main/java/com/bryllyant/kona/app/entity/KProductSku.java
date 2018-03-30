package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface KProductSku extends KEntityObject {
    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getProductId();

    void setProductId(Long productId);

    String getName();

    void setName(String name);

    String getSku();

    void setSku(String sku);

    Map<String, Object> getVariants();

    void setVariants(Map<String, Object> variants);

    String getDescription();

    void setDescription(String description);

    Integer getDisplayOrder();

    void setDisplayOrder(Integer displayOrder);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    BigDecimal getSetupFee();

    void setSetupFee(BigDecimal setupFee);

    Integer getTrialDays();

    void setTrialDays(Integer trialDays);

    boolean isSubscription();

    void setSubscription(boolean subscription);

    Integer getSubscriptionDays();

    void setSubscriptionDays(Integer subscriptionDays);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
