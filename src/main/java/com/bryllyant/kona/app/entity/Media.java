package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Media extends KBaseMedia implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.app_id
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    private Long appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__media
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.app_id
     *
     * @return the value of kona__media.app_id
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.app_id
     *
     * @param appId the value for kona__media.app_id
     *
     * @mbg.generated Thu Mar 08 08:48:58 EST 2018
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }
}