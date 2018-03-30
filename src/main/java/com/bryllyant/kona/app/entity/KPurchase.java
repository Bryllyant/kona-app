package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPurchase extends KEntityObject {
    @Override
    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Long getParentId();
    void setParentId(Long parentId);

    Long getAccountId();
    void setAccountId(Long accountId);

    Long getUserId();
    void setUserId(Long userId);

    Long getProductId();
    void setProductId(Long productId);

    Long getProductSkuId();
    void setProductSkuId(Long productSkuId);

    Long getPromoId();
    void setPromoId(Long promoId);

    Long getPartnerId();
    void setPartnerId(Long partnerId);

    Long getCampaignChannelId();
    void setCampaignChannelId(Long campaignChannelId);

    Long getInvoiceId();
    void setInvoiceId(Long invoiceId);

    KPayment.Type getPaymentType();
    void setPaymentType(KPayment.Type paymentType);

    String getKind();
    void setKind(String kind);

    Integer getQuantity();
    void setQuantity(Integer quantity);

    boolean isAutoRenew();
    void setAutoRenew(boolean autoRenew);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    Date getExpirationDate();
    void setExpirationDate(Date expirationDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
