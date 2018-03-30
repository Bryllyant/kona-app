package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBaseCart extends KBaseEntity implements KCart {

    private Long id;
    private String uid;
    private Long accountId;
    private Long userId;
    private Long tokenId;
    private Long currencyId;
    private Long invoiceId;
    private String defaultCardLast4;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal shipping;
    private BigDecimal tax;
    private BigDecimal total;
    private boolean checkedOut;
    private boolean invoiced;
    private boolean expired;
    private Date checkedOutDate;
    private Date invoicedDate;
    private Date expiredDate;
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
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getTokenId() {
        return tokenId;
    }

    @Override
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    @Override
    public Long getCurrencyId() {
        return currencyId;
    }

    @Override
    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public Long getInvoiceId() {
        return invoiceId;
    }

    @Override
    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String getDefaultCardLast4() {
        return defaultCardLast4;
    }

    @Override
    public void setDefaultCardLast4(String defaultCardLast4) {
        this.defaultCardLast4 = defaultCardLast4;
    }

    @Override
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    @Override
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public BigDecimal getShipping() {
        return shipping;
    }

    @Override
    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    @Override
    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean isCheckedOut() {
        return checkedOut;
    }

    @Override
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    @Override
    public boolean isInvoiced() {
        return invoiced;
    }

    @Override
    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    @Override
    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    @Override
    public Date getInvoicedDate() {
        return invoicedDate;
    }

    @Override
    public void setInvoicedDate(Date invoicedDate) {
        this.invoicedDate = invoicedDate;
    }

    @Override
    public Date getExpiredDate() {
        return expiredDate;
    }

    @Override
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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
