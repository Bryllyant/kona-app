package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CartItem extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.cart_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long cartId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.product_sku_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long productSkuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.promo_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long promoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.quantity
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer quantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.discount_description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String discountDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.unit_price
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private BigDecimal unitPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.setup_fee
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private BigDecimal setupFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.subtotal
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private BigDecimal subtotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.discount
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private BigDecimal discount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.total
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private BigDecimal total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.subscription_start_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date subscriptionStartDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.subscription_end_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date subscriptionEndDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__cart_item.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__cart_item
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.uid
     *
     * @return the value of kona__cart_item.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.uid
     *
     * @param uid the value for kona__cart_item.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.cart_id
     *
     * @return the value of kona__cart_item.cart_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getCartId() {
        return cartId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.cart_id
     *
     * @param cartId the value for kona__cart_item.cart_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.product_sku_id
     *
     * @return the value of kona__cart_item.product_sku_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getProductSkuId() {
        return productSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.product_sku_id
     *
     * @param productSkuId the value for kona__cart_item.product_sku_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.promo_id
     *
     * @return the value of kona__cart_item.promo_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getPromoId() {
        return promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.promo_id
     *
     * @param promoId the value for kona__cart_item.promo_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.quantity
     *
     * @return the value of kona__cart_item.quantity
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.quantity
     *
     * @param quantity the value for kona__cart_item.quantity
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.description
     *
     * @return the value of kona__cart_item.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.description
     *
     * @param description the value for kona__cart_item.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.discount_description
     *
     * @return the value of kona__cart_item.discount_description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getDiscountDescription() {
        return discountDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.discount_description
     *
     * @param discountDescription the value for kona__cart_item.discount_description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription == null ? null : discountDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.unit_price
     *
     * @return the value of kona__cart_item.unit_price
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.unit_price
     *
     * @param unitPrice the value for kona__cart_item.unit_price
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.setup_fee
     *
     * @return the value of kona__cart_item.setup_fee
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public BigDecimal getSetupFee() {
        return setupFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.setup_fee
     *
     * @param setupFee the value for kona__cart_item.setup_fee
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSetupFee(BigDecimal setupFee) {
        this.setupFee = setupFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.subtotal
     *
     * @return the value of kona__cart_item.subtotal
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.subtotal
     *
     * @param subtotal the value for kona__cart_item.subtotal
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.discount
     *
     * @return the value of kona__cart_item.discount
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.discount
     *
     * @param discount the value for kona__cart_item.discount
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.total
     *
     * @return the value of kona__cart_item.total
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.total
     *
     * @param total the value for kona__cart_item.total
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.subscription_start_date
     *
     * @return the value of kona__cart_item.subscription_start_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.subscription_start_date
     *
     * @param subscriptionStartDate the value for kona__cart_item.subscription_start_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.subscription_end_date
     *
     * @return the value of kona__cart_item.subscription_end_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.subscription_end_date
     *
     * @param subscriptionEndDate the value for kona__cart_item.subscription_end_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.created_date
     *
     * @return the value of kona__cart_item.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.created_date
     *
     * @param createdDate the value for kona__cart_item.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__cart_item.updated_date
     *
     * @return the value of kona__cart_item.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__cart_item.updated_date
     *
     * @param updatedDate the value for kona__cart_item.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}