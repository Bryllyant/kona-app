package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KInvoiceItem extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

	Long getInvoiceId();
	void setInvoiceId(Long invoiceId);

    Long getProductSkuId();
    void setProductSkuId(Long productSkuId);

    Long getPromoId();
    void setPromoId(Long promoId);

	Long getCampaignChannelId();
	void setCampaignChannelId(Long campaignChannelId);

	String getDescription();
	void setDescription(String description);

	String getDiscountDescription();
	void setDiscountDescription(String discountDescription);

	BigDecimal getUnitPrice();
	void setUnitPrice(BigDecimal unitPrice);

	BigDecimal getSetupFee();
	void setSetupFee(BigDecimal setupFee);

	Integer getQuantity();
	void setQuantity(Integer quantity);

	BigDecimal getSubtotal();
	void setSubtotal(BigDecimal subtotal);

	BigDecimal getDiscount();
	void setDiscount(BigDecimal discount);

	BigDecimal getTotal();
	void setTotal(BigDecimal total);

	Date getSubscriptionStartDate();
	void setSubscriptionStartDate(Date subscriptionStartDate);

	Date getSubscriptionEndDate();
	void setSubscriptionEndDate(Date subscriptionEndDate);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
