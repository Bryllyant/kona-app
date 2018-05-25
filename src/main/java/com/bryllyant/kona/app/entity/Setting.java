package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Setting extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.value
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String value;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.override_global
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private boolean overrideGlobal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__setting.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__setting
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.uid
     *
     * @return the value of kona__setting.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.uid
     *
     * @param uid the value for kona__setting.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.parent_id
     *
     * @return the value of kona__setting.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.parent_id
     *
     * @param parentId the value for kona__setting.parent_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.account_id
     *
     * @return the value of kona__setting.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.account_id
     *
     * @param accountId the value for kona__setting.account_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.user_id
     *
     * @return the value of kona__setting.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.user_id
     *
     * @param userId the value for kona__setting.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.name
     *
     * @return the value of kona__setting.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.name
     *
     * @param name the value for kona__setting.name
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.value
     *
     * @return the value of kona__setting.value
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.value
     *
     * @param value the value for kona__setting.value
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.override_global
     *
     * @return the value of kona__setting.override_global
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public boolean isOverrideGlobal() {
        return overrideGlobal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.override_global
     *
     * @param overrideGlobal the value for kona__setting.override_global
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setOverrideGlobal(boolean overrideGlobal) {
        this.overrideGlobal = overrideGlobal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.created_date
     *
     * @return the value of kona__setting.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.created_date
     *
     * @param createdDate the value for kona__setting.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__setting.updated_date
     *
     * @return the value of kona__setting.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__setting.updated_date
     *
     * @param updatedDate the value for kona__setting.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}