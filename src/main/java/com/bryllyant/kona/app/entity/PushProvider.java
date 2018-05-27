package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BasePush.Platform;
import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class PushProvider extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.platform
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Platform platform;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.endpoint
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String endpoint;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.sandbox
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private boolean sandbox;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.server_key
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String serverKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_provider.server_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private String serverSecret;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_provider
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.uid
     *
     * @return the value of kona__push_provider.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.uid
     *
     * @param uid the value for kona__push_provider.uid
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.platform
     *
     * @return the value of kona__push_provider.platform
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Platform getPlatform() {
        return platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.platform
     *
     * @param platform the value for kona__push_provider.platform
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.endpoint
     *
     * @return the value of kona__push_provider.endpoint
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.endpoint
     *
     * @param endpoint the value for kona__push_provider.endpoint
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.sandbox
     *
     * @return the value of kona__push_provider.sandbox
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public boolean isSandbox() {
        return sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.sandbox
     *
     * @param sandbox the value for kona__push_provider.sandbox
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.created_date
     *
     * @return the value of kona__push_provider.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.created_date
     *
     * @param createdDate the value for kona__push_provider.created_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.updated_date
     *
     * @return the value of kona__push_provider.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.updated_date
     *
     * @param updatedDate the value for kona__push_provider.updated_date
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.server_key
     *
     * @return the value of kona__push_provider.server_key
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getServerKey() {
        return serverKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.server_key
     *
     * @param serverKey the value for kona__push_provider.server_key
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setServerKey(String serverKey) {
        this.serverKey = serverKey == null ? null : serverKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_provider.server_secret
     *
     * @return the value of kona__push_provider.server_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public String getServerSecret() {
        return serverSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_provider.server_secret
     *
     * @param serverSecret the value for kona__push_provider.server_secret
     *
     * @mbg.generated Sun May 27 07:12:06 EDT 2018
     */
    public void setServerSecret(String serverSecret) {
        this.serverSecret = serverSecret == null ? null : serverSecret.trim();
    }
}