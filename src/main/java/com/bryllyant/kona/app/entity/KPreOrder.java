package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KPreOrder extends KEntityObject {


	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getPartnerId();
	void setPartnerId(Long partnerId);

    Long getProductSkuId();
    void setProductSkuId(Long productSkuId);

	Long getCampaignChannelId();
	void setCampaignChannelId(Long campaignChannelId);

	Long getPaymentId();
	void setPaymentId(Long paymentId);

	Long getReferredById();
	void setReferredById(Long referredById);

	BigDecimal getAmount();
	void setAmount(BigDecimal amount);

	boolean isReconciled();
	void setReconciled(boolean reconciled);

	boolean isProxyPayment();
	void setProxyPayment(boolean proxyPayment);

	String getProcessor();
	void setProcessor(String processor);

	String getPaymentDescription();
	void setPaymentDescription(String paymentDescription);

	String getPaymentToken();
	void setPaymentToken(String paymentToken);

	String getPaymentCardLast4();
	void setPaymentCardLast4(String paymentCardLast4);

	String getPaymentRef();
	void setPaymentRef(String paymentRef);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);


	String getEmail();


	void setEmail(String email);


	String getMobileNumber();


	void setMobileNumber(String mobileNumber);


	String getShippingAddress1();


	void setShippingAddress1(String shippingAddress1);


	String getShippingAddress2();


	void setShippingAddress2(String shippingAddress2);


	String getShippingCity();


	void setShippingCity(String shippingCity);


	String getShippingState();


	void setShippingState(String shippingState);


	String getShippingPostalCode();


	void setShippingPostalCode(String shippingPostalCode);


	String getShippingCountry();


	void setShippingCountry(String shippingCountry);


	String getNotes();


	void setNotes(String notes);


	String getHostname();


	void setHostname(String hostname);


	String getUserAgent();


	void setUserAgent(String userAgent);


	Date getCreatedDate();


	void setCreatedDate(Date createdDate);


	Date getShippedDate();


	void setShippedDate(Date shippedDate);


	Date getUpdatedDate();


	void setUpdatedDate(Date updatedDate);

}