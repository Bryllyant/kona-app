package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Cart extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.token_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long tokenId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.currency_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long currencyId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.invoice_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long invoiceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.default_card_last4
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String defaultCardLast4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.subtotal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private BigDecimal subtotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.discount
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private BigDecimal discount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.shipping
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private BigDecimal shipping;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.tax
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private BigDecimal tax;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.total
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private BigDecimal total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.checked_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean checkedOut;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.invoiced
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean invoiced;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.expired
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean expired;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.checked_out_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date checkedOutDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.invoiced_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date invoicedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date expiredDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__cart
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.uid
     *
     * @return the value of kona__cart.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.uid
     *
     * @param uid the value for kona__cart.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.account_id
     *
     * @return the value of kona__cart.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.account_id
     *
     * @param accountId the value for kona__cart.account_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.user_id
     *
     * @return the value of kona__cart.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.user_id
     *
     * @param userId the value for kona__cart.user_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.token_id
     *
     * @return the value of kona__cart.token_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getTokenId() {
        return tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.token_id
     *
     * @param tokenId the value for kona__cart.token_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.currency_id
     *
     * @return the value of kona__cart.currency_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getCurrencyId() {
        return currencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.currency_id
     *
     * @param currencyId the value for kona__cart.currency_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.invoice_id
     *
     * @return the value of kona__cart.invoice_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.invoice_id
     *
     * @param invoiceId the value for kona__cart.invoice_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.default_card_last4
     *
     * @return the value of kona__cart.default_card_last4
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getDefaultCardLast4() {
        return defaultCardLast4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.default_card_last4
     *
     * @param defaultCardLast4 the value for kona__cart.default_card_last4
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDefaultCardLast4(String defaultCardLast4) {
        this.defaultCardLast4 = defaultCardLast4 == null ? null : defaultCardLast4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.subtotal
     *
     * @return the value of kona__cart.subtotal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.subtotal
     *
     * @param subtotal the value for kona__cart.subtotal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.discount
     *
     * @return the value of kona__cart.discount
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.discount
     *
     * @param discount the value for kona__cart.discount
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.shipping
     *
     * @return the value of kona__cart.shipping
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public BigDecimal getShipping() {
        return shipping;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.shipping
     *
     * @param shipping the value for kona__cart.shipping
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.tax
     *
     * @return the value of kona__cart.tax
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.tax
     *
     * @param tax the value for kona__cart.tax
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.total
     *
     * @return the value of kona__cart.total
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.total
     *
     * @param total the value for kona__cart.total
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.checked_out
     *
     * @return the value of kona__cart.checked_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isCheckedOut() {
        return checkedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.checked_out
     *
     * @param checkedOut the value for kona__cart.checked_out
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.invoiced
     *
     * @return the value of kona__cart.invoiced
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isInvoiced() {
        return invoiced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.invoiced
     *
     * @param invoiced the value for kona__cart.invoiced
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.expired
     *
     * @return the value of kona__cart.expired
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isExpired() {
        return expired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.expired
     *
     * @param expired the value for kona__cart.expired
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.checked_out_date
     *
     * @return the value of kona__cart.checked_out_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.checked_out_date
     *
     * @param checkedOutDate the value for kona__cart.checked_out_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.invoiced_date
     *
     * @return the value of kona__cart.invoiced_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getInvoicedDate() {
        return invoicedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.invoiced_date
     *
     * @param invoicedDate the value for kona__cart.invoiced_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setInvoicedDate(Date invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.expired_date
     *
     * @return the value of kona__cart.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.expired_date
     *
     * @param expiredDate the value for kona__cart.expired_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.created_date
     *
     * @return the value of kona__cart.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.created_date
     *
     * @param createdDate the value for kona__cart.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart.updated_date
     *
     * @return the value of kona__cart.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart.updated_date
     *
     * @param updatedDate the value for kona__cart.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}