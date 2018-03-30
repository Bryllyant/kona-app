package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KInvoice extends KEntityObject {

	Long getId();
	void setId(Long id);

    String getUid();
    void setUid(String uid);

	Long getUserId();
	void setUserId(Long userId);

	Long getAccountId();
	void setAccountId(Long accountId);

	Long getCurrencyId();
	void setCurrencyId(Long currencyId);

    Long getPromoId();
    void setPromoId(Long promoId);

    Long getCampaignChannelId();
    void setCampaignChannelId(Long campaignChannelId);

	String getInvoiceNo();
	void setInvoiceNo(String invoiceNo);

	BigDecimal getStartBalance();
	void setStartBalance(BigDecimal startBalance);

	BigDecimal getEndBalance();
	void setEndBalance(BigDecimal endBalance);

	BigDecimal getSubtotal();
	void setSubtotal(BigDecimal subtotal);

	BigDecimal getTax();
	void setTax(BigDecimal tax);

	BigDecimal getShipping();
	void setShipping(BigDecimal shipping);

	BigDecimal getDiscount();
	void setDiscount(BigDecimal discount);

	BigDecimal getTotal();
	void setTotal(BigDecimal total);

	BigDecimal getAmountDue();
	void setAmountDue(BigDecimal amountDue);

	BigDecimal getAmountPaid();
	void setAmountPaid(BigDecimal amountPaid);

	boolean isPaid();
	void setPaid(boolean paid);

	boolean isClosed();
	void setClosed(boolean closed);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getInvoiceDate();
	void setInvoiceDate(Date invoiceDate);

	Date getDueDate();
	void setDueDate(Date dueDate);

	Date getPaidDate();
	void setPaidDate(Date paidDate);

	Date getClosedDate();
	void setClosedDate(Date closedDate);

	boolean isPaymentAttempted();
	void setPaymentAttempted(boolean paymentAttempted);

	Integer getPaymentAttemptCount();
	void setPaymentAttemptCount(Integer paymentAttemptCount);

	Date getLastPaymentAttemptDate();
	void setLastPaymentAttemptDate(Date lastPaymentAttemptDate);

	Date getNextPaymentAttemptDate();
	void setNextPaymentAttemptDate(Date nextPaymentAttemptDate);

	String getPaymentCardLast4();
	void setPaymentCardLast4(String paymentCardLast4);

	String getPaymentRef();
	void setPaymentRef(String paymentRef);

	String getNotes();
	void setNotes(String notes);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
