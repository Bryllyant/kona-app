package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseAuthCode.Type;
import java.io.Serializable;
import java.util.Date;

public class AuthCode extends BaseAuthCode implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.valid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private boolean valid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Integer useCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.max_use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Integer maxUseCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.expiration_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date expirationDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.confirmed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date confirmedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.last_accessed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date lastAccessedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__auth_code.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__auth_code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.type
     *
     * @return the value of kona__auth_code.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.type
     *
     * @param type the value for kona__auth_code.type
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.user_id
     *
     * @return the value of kona__auth_code.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.user_id
     *
     * @param userId the value for kona__auth_code.user_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.code
     *
     * @return the value of kona__auth_code.code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.code
     *
     * @param code the value for kona__auth_code.code
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.valid
     *
     * @return the value of kona__auth_code.valid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.valid
     *
     * @param valid the value for kona__auth_code.valid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.use_count
     *
     * @return the value of kona__auth_code.use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Integer getUseCount() {
        return useCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.use_count
     *
     * @param useCount the value for kona__auth_code.use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.max_use_count
     *
     * @return the value of kona__auth_code.max_use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Integer getMaxUseCount() {
        return maxUseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.max_use_count
     *
     * @param maxUseCount the value for kona__auth_code.max_use_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setMaxUseCount(Integer maxUseCount) {
        this.maxUseCount = maxUseCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.expiration_date
     *
     * @return the value of kona__auth_code.expiration_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.expiration_date
     *
     * @param expirationDate the value for kona__auth_code.expiration_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.confirmed_date
     *
     * @return the value of kona__auth_code.confirmed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getConfirmedDate() {
        return confirmedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.confirmed_date
     *
     * @param confirmedDate the value for kona__auth_code.confirmed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setConfirmedDate(Date confirmedDate) {
        this.confirmedDate = confirmedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.last_accessed_date
     *
     * @return the value of kona__auth_code.last_accessed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getLastAccessedDate() {
        return lastAccessedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.last_accessed_date
     *
     * @param lastAccessedDate the value for kona__auth_code.last_accessed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setLastAccessedDate(Date lastAccessedDate) {
        this.lastAccessedDate = lastAccessedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.created_date
     *
     * @return the value of kona__auth_code.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.created_date
     *
     * @param createdDate the value for kona__auth_code.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__auth_code.updated_date
     *
     * @return the value of kona__auth_code.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__auth_code.updated_date
     *
     * @param updatedDate the value for kona__auth_code.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}