package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PromoCode extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long promoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.campaign_channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long campaignChannelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.promo_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String promoCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__promo_code.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__promo_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.uid
     *
     * @return the value of kona__promo_code.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.uid
     *
     * @param uid the value for kona__promo_code.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.promo_id
     *
     * @return the value of kona__promo_code.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getPromoId() {
        return promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.promo_id
     *
     * @param promoId the value for kona__promo_code.promo_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.campaign_channel_id
     *
     * @return the value of kona__promo_code.campaign_channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getCampaignChannelId() {
        return campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.campaign_channel_id
     *
     * @param campaignChannelId the value for kona__promo_code.campaign_channel_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCampaignChannelId(Long campaignChannelId) {
        this.campaignChannelId = campaignChannelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.promo_code
     *
     * @return the value of kona__promo_code.promo_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.promo_code
     *
     * @param promoCode the value for kona__promo_code.promo_code
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode == null ? null : promoCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.created_date
     *
     * @return the value of kona__promo_code.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.created_date
     *
     * @param createdDate the value for kona__promo_code.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__promo_code.updated_date
     *
     * @return the value of kona__promo_code.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__promo_code.updated_date
     *
     * @param updatedDate the value for kona__promo_code.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}