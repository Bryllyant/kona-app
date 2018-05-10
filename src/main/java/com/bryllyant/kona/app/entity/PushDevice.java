package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePush.Platform;
import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PushDevice extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.provider_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long providerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.device_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.platform
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Platform platform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.endpoint
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private String endpoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.sandbox
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private boolean sandbox;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_device.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_device
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.uid
     *
     * @return the value of kona__push_device.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.uid
     *
     * @param uid the value for kona__push_device.uid
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.provider_id
     *
     * @return the value of kona__push_device.provider_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getProviderId() {
        return providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.provider_id
     *
     * @param providerId the value for kona__push_device.provider_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.user_id
     *
     * @return the value of kona__push_device.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.user_id
     *
     * @param userId the value for kona__push_device.user_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.device_id
     *
     * @return the value of kona__push_device.device_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.device_id
     *
     * @param deviceId the value for kona__push_device.device_id
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.platform
     *
     * @return the value of kona__push_device.platform
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.platform
     *
     * @param platform the value for kona__push_device.platform
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.token
     *
     * @return the value of kona__push_device.token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.token
     *
     * @param token the value for kona__push_device.token
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.endpoint
     *
     * @return the value of kona__push_device.endpoint
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.endpoint
     *
     * @param endpoint the value for kona__push_device.endpoint
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.sandbox
     *
     * @return the value of kona__push_device.sandbox
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public boolean isSandbox() {
        return sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.sandbox
     *
     * @param sandbox the value for kona__push_device.sandbox
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.enabled
     *
     * @return the value of kona__push_device.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.enabled
     *
     * @param enabled the value for kona__push_device.enabled
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.created_date
     *
     * @return the value of kona__push_device.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.created_date
     *
     * @param createdDate the value for kona__push_device.created_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_device.updated_date
     *
     * @return the value of kona__push_device.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_device.updated_date
     *
     * @param updatedDate the value for kona__push_device.updated_date
     *
     * @mbg.generated Thu May 10 08:22:10 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}