package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class AuthRolePriv extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_role_priv.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_role_priv.role_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Long roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_role_priv.priv_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Long privId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_role_priv.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_role_priv.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_role_priv
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_role_priv.uid
     *
     * @return the value of kona__auth_role_priv.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_role_priv.uid
     *
     * @param uid the value for kona__auth_role_priv.uid
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_role_priv.role_id
     *
     * @return the value of kona__auth_role_priv.role_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_role_priv.role_id
     *
     * @param roleId the value for kona__auth_role_priv.role_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_role_priv.priv_id
     *
     * @return the value of kona__auth_role_priv.priv_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Long getPrivId() {
        return privId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_role_priv.priv_id
     *
     * @param privId the value for kona__auth_role_priv.priv_id
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setPrivId(Long privId) {
        this.privId = privId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_role_priv.created_date
     *
     * @return the value of kona__auth_role_priv.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_role_priv.created_date
     *
     * @param createdDate the value for kona__auth_role_priv.created_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_role_priv.updated_date
     *
     * @return the value of kona__auth_role_priv.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_role_priv.updated_date
     *
     * @param updatedDate the value for kona__auth_role_priv.updated_date
     *
     * @mbg.generated Thu May 10 08:22:09 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}