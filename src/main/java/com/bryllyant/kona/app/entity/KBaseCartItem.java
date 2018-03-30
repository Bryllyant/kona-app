package com.bryllyant.kona.app.entity;

import java.math.BigDecimal;
import java.util.Date;

public class KBaseCartItem extends KBaseEntity implements KCartItem {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long cartId;
    private Long productSkuId;
    private Long promoId;
    private Integer quantity;
    private String description;
    private String discountDescription;
    private BigDecimal unitPrice;
    private BigDecimal setupFee;
    private BigDecimal subtotal;
    private BigDecimal discount;
    private BigDecimal total;
    private Date subscriptionStartDate;
    private Date subscriptionEndDate;
    private Date updatedDate;
    private Date createdDate;

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
    public Long getCartId() {
        return cartId;
    }

    @Override
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Override
    public Long getProductSkuId() {
        return productSkuId;
    }

    @Override
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    @Override
    public Long getPromoId() {
        return promoId;
    }

    @Override
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDiscountDescription() {
        return discountDescription;
    }

    @Override
    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    @Override
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public BigDecimal getSetupFee() {
        return setupFee;
    }

    @Override
    public void setSetupFee(BigDecimal setupFee) {
        this.setupFee = setupFee;
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
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    @Override
    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    @Override
    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    @Override
    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
