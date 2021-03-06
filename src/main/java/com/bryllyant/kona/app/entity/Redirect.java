package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Redirect extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.short_url_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long shortUrlId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.request_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String requestUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.redirect_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String redirectUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String hostname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.referer
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String referer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.locale
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String locale;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.requested_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date requestedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.redirected_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date redirectedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__redirect.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__redirect
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.uid
     *
     * @return the value of kona__redirect.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.uid
     *
     * @param uid the value for kona__redirect.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.short_url_id
     *
     * @return the value of kona__redirect.short_url_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getShortUrlId() {
        return shortUrlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.short_url_id
     *
     * @param shortUrlId the value for kona__redirect.short_url_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setShortUrlId(Long shortUrlId) {
        this.shortUrlId = shortUrlId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.request_url
     *
     * @return the value of kona__redirect.request_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.request_url
     *
     * @param requestUrl the value for kona__redirect.request_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.redirect_url
     *
     * @return the value of kona__redirect.redirect_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.redirect_url
     *
     * @param redirectUrl the value for kona__redirect.redirect_url
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.hostname
     *
     * @return the value of kona__redirect.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.hostname
     *
     * @param hostname the value for kona__redirect.hostname
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.user_agent
     *
     * @return the value of kona__redirect.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.user_agent
     *
     * @param userAgent the value for kona__redirect.user_agent
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.referer
     *
     * @return the value of kona__redirect.referer
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getReferer() {
        return referer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.referer
     *
     * @param referer the value for kona__redirect.referer
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setReferer(String referer) {
        this.referer = referer == null ? null : referer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.locale
     *
     * @return the value of kona__redirect.locale
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getLocale() {
        return locale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.locale
     *
     * @param locale the value for kona__redirect.locale
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.latitude
     *
     * @return the value of kona__redirect.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.latitude
     *
     * @param latitude the value for kona__redirect.latitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.longitude
     *
     * @return the value of kona__redirect.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.longitude
     *
     * @param longitude the value for kona__redirect.longitude
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.requested_date
     *
     * @return the value of kona__redirect.requested_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getRequestedDate() {
        return requestedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.requested_date
     *
     * @param requestedDate the value for kona__redirect.requested_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.redirected_date
     *
     * @return the value of kona__redirect.redirected_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getRedirectedDate() {
        return redirectedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.redirected_date
     *
     * @param redirectedDate the value for kona__redirect.redirected_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setRedirectedDate(Date redirectedDate) {
        this.redirectedDate = redirectedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.created_date
     *
     * @return the value of kona__redirect.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.created_date
     *
     * @param createdDate the value for kona__redirect.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__redirect.updated_date
     *
     * @return the value of kona__redirect.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__redirect.updated_date
     *
     * @param updatedDate the value for kona__redirect.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}