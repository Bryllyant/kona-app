package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Partner extends KBasePartner implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.slug
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private String slug;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__partner
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.slug
     *
     * @return the value of kona__partner.slug
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.slug
     *
     * @param slug the value for kona__partner.slug
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }
}