package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PromoProduct extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long promoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.product_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.product_category_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long productCategoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.product_sku_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long productSkuId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_product.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__promo_product
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.uid
     *
     * @return the value of kona__promo_product.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.uid
     *
     * @param uid the value for kona__promo_product.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.promo_id
     *
     * @return the value of kona__promo_product.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getPromoId() {
        return promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.promo_id
     *
     * @param promoId the value for kona__promo_product.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.product_id
     *
     * @return the value of kona__promo_product.product_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.product_id
     *
     * @param productId the value for kona__promo_product.product_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.product_category_id
     *
     * @return the value of kona__promo_product.product_category_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.product_category_id
     *
     * @param productCategoryId the value for kona__promo_product.product_category_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.product_sku_id
     *
     * @return the value of kona__promo_product.product_sku_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getProductSkuId() {
        return productSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.product_sku_id
     *
     * @param productSkuId the value for kona__promo_product.product_sku_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.created_date
     *
     * @return the value of kona__promo_product.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.created_date
     *
     * @param createdDate the value for kona__promo_product.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_product.updated_date
     *
     * @return the value of kona__promo_product.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_product.updated_date
     *
     * @param updatedDate the value for kona__promo_product.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}