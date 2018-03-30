package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KPayment extends KEntityObject {

    enum Type {
        CASH,
        CHECK,
        CARD,
        WIRE,
        ACH,
        PAYPAL,
        CREDIT,
        PROMO,
        EXTERNAL,
        APPLE_APPSTORE,
        GOOGLE_PLAY,
        PARTNER,
        OTHER
    }

    enum Status {
        SUCCESS,
        CARD_INVALID_NUMBER,
        CARD_INVALID_MONTH,
        CARD_INVALID_YEAR,
        CARD_INVALID_CVC,
        CARD_INVALID_ADDRESS,
        CARD_INVALID_ZIP,
        CARD_EXPIRED,
        CARD_DECLINED,
        CARD_MISSING,
        ACCOUNT_INVALID,
        ACCOUNT_DISABLED,
        AMOUNT_INVALID,
        PROCESSOR_ERROR,
        SYSTEM_ERROR
    }

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getPaymentAccountId();
	void setPaymentAccountId(Long paymentAccountId);

	Type getType();
	void setType(Type type);

	Status getStatus();
	void setStatus(Status status);

	Long getCurrencyId();
	void setCurrencyId(Long currencyId);

    Long getAccountId();
    void setAccountId(Long accountId);

	Long getUserId();
	void setUserId(Long userId);

    Long getTokenId();
    void setTokenId(Long tokenId);

	Long getInvoiceId();
	void setInvoiceId(Long invoiceId);

    Long getPromoId();
    void setPromoId(Long promoId);

	Long getCampaignChannelId();
	void setCampaignChannelId(Long campaignChannelId);

	String getHostname();
	void setHostname(String hostname);

	String getUserAgent();
	void setUserAgent(String userAgent);

	Double getLatitude();
	void setLatitude(Double latitude);

	Double getLongitude();
	void setLongitude(Double longitude);

	String getCardToken();
	void setCardToken(String cardToken);

	String getCardLast4();
	void setCardLast4(String cardLast4);

	BigDecimal getAmount();
	void setAmount(BigDecimal amount);

	BigDecimal getAmountRefunded();
	void setAmountRefunded(BigDecimal amountRefunded);

	String getProcessorRef();
	void setProcessorRef(String processorRef);

    String getProcessorReceipt();
    void setProcessorReceipt(String processorReceipt);

	String getProcessorError();
	void setProcessorError(String processorError);

	BigDecimal getProcessorFee();
	void setProcessorFee(BigDecimal processorFee);

	boolean isPaid();
	void setPaid(boolean paid);

	boolean isRefunded();
	void setRefunded(boolean refunded);

	boolean isDisputed();
	void setDisputed(boolean disputed);

	boolean isFailed();
	void setFailed(boolean failed);

	Date getPaidDate();
	void setPaidDate(Date paidDate);

	Date getDisputedDate();
	void setDisputedDate(Date disputedDate);

	Date getRefundedDate();
	void setRefundedDate(Date refundedDate);

	Date getFailedDate();
	void setFailedDate(Date failedDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);


}
