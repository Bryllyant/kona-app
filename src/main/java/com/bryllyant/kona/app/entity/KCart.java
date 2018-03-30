package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.math.BigDecimal;
import java.util.Date;

public interface KCart extends KEntityObject {

    long serialVersionUID = 1L;

    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getAccountId();

    void setAccountId(Long accountId);

    Long getUserId();

    void setUserId(Long userId);

    Long getTokenId();

    void setTokenId(Long tokenId);

    Long getCurrencyId();

    void setCurrencyId(Long currencyId);

    Long getInvoiceId();

    void setInvoiceId(Long invoiceId);

    String getDefaultCardLast4();

    void setDefaultCardLast4(String defaultCardLast4);

    BigDecimal getSubtotal();

    void setSubtotal(BigDecimal subtotal);

    BigDecimal getDiscount();

    void setDiscount(BigDecimal discount);

    BigDecimal getShipping();

    void setShipping(BigDecimal shipping);

    BigDecimal getTax();

    void setTax(BigDecimal tax);

    BigDecimal getTotal();

    void setTotal(BigDecimal total);

    boolean isCheckedOut();

    void setCheckedOut(boolean checkedOut);

    boolean isInvoiced();

    void setInvoiced(boolean invoiced);

    boolean isExpired();

    void setExpired(boolean expired);

    Date getCheckedOutDate();

    void setCheckedOutDate(Date checkedOutDate);

    Date getInvoicedDate();

    void setInvoicedDate(Date invoicedDate);

    Date getExpiredDate();

    void setExpiredDate(Date expiredDate);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}
