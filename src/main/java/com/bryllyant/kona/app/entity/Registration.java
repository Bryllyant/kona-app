package com.bryllyant.kona.app.entity;

import java.io.Serializable;
import java.util.Date;

public class Registration extends KBaseRegistration implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_version
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private String appVersion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.app_build
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private String appBuild;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.latitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.longitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.reminded_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private Date remindedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.deactivated_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private Date deactivatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__registration.deleted_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private Date deletedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__registration
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_version
     *
     * @return the value of kona__registration.app_version
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_version
     *
     * @param appVersion the value for kona__registration.app_version
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.app_build
     *
     * @return the value of kona__registration.app_build
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public String getAppBuild() {
        return appBuild;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.app_build
     *
     * @param appBuild the value for kona__registration.app_build
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setAppBuild(String appBuild) {
        this.appBuild = appBuild == null ? null : appBuild.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.latitude
     *
     * @return the value of kona__registration.latitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.latitude
     *
     * @param latitude the value for kona__registration.latitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.longitude
     *
     * @return the value of kona__registration.longitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.longitude
     *
     * @param longitude the value for kona__registration.longitude
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.reminded_date
     *
     * @return the value of kona__registration.reminded_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public Date getRemindedDate() {
        return remindedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.reminded_date
     *
     * @param remindedDate the value for kona__registration.reminded_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setRemindedDate(Date remindedDate) {
        this.remindedDate = remindedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.deactivated_date
     *
     * @return the value of kona__registration.deactivated_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public Date getDeactivatedDate() {
        return deactivatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.deactivated_date
     *
     * @param deactivatedDate the value for kona__registration.deactivated_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setDeactivatedDate(Date deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__registration.deleted_date
     *
     * @return the value of kona__registration.deleted_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__registration.deleted_date
     *
     * @param deletedDate the value for kona__registration.deleted_date
     *
     * @mbg.generated Sun Nov 19 04:45:21 EST 2017
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }
}