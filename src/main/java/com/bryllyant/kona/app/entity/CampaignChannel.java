package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class CampaignChannel extends KBaseCampaignChannel implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign_channel.slug
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private String slug;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign_channel
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign_channel.slug
     *
     * @return the value of kona__campaign_channel.slug
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign_channel.slug
     *
     * @param slug the value for kona__campaign_channel.slug
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }
}