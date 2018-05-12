package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseApp.Type;
import java.io.Serializable;
import java.util.Date;

public class App extends BaseApp implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.uid
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.type
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Type type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.logo_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Long logoId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.logo_url_path
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String logoUrlPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.description
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.app_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String appUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.company_name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String companyName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.company_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String companyUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.privacy_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private String privacyUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.enabled
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.deleted_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Date deletedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__app.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__app
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.id
     *
     * @return the value of kona__app.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.id
     *
     * @param id the value for kona__app.id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.uid
     *
     * @return the value of kona__app.uid
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.uid
     *
     * @param uid the value for kona__app.uid
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.type
     *
     * @return the value of kona__app.type
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Type getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.type
     *
     * @param type the value for kona__app.type
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.user_id
     *
     * @return the value of kona__app.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.user_id
     *
     * @param userId the value for kona__app.user_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.logo_id
     *
     * @return the value of kona__app.logo_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Long getLogoId() {
        return logoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.logo_id
     *
     * @param logoId the value for kona__app.logo_id
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setLogoId(Long logoId) {
        this.logoId = logoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.logo_url_path
     *
     * @return the value of kona__app.logo_url_path
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getLogoUrlPath() {
        return logoUrlPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.logo_url_path
     *
     * @param logoUrlPath the value for kona__app.logo_url_path
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setLogoUrlPath(String logoUrlPath) {
        this.logoUrlPath = logoUrlPath == null ? null : logoUrlPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.name
     *
     * @return the value of kona__app.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.name
     *
     * @param name the value for kona__app.name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.slug
     *
     * @return the value of kona__app.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.slug
     *
     * @param slug the value for kona__app.slug
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.description
     *
     * @return the value of kona__app.description
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.description
     *
     * @param description the value for kona__app.description
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.app_url
     *
     * @return the value of kona__app.app_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getAppUrl() {
        return appUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.app_url
     *
     * @param appUrl the value for kona__app.app_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.company_name
     *
     * @return the value of kona__app.company_name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.company_name
     *
     * @param companyName the value for kona__app.company_name
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.company_url
     *
     * @return the value of kona__app.company_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getCompanyUrl() {
        return companyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.company_url
     *
     * @param companyUrl the value for kona__app.company_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl == null ? null : companyUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.privacy_url
     *
     * @return the value of kona__app.privacy_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public String getPrivacyUrl() {
        return privacyUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.privacy_url
     *
     * @param privacyUrl the value for kona__app.privacy_url
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl == null ? null : privacyUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.enabled
     *
     * @return the value of kona__app.enabled
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.enabled
     *
     * @param enabled the value for kona__app.enabled
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.deleted_date
     *
     * @return the value of kona__app.deleted_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.deleted_date
     *
     * @param deletedDate the value for kona__app.deleted_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.created_date
     *
     * @return the value of kona__app.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.created_date
     *
     * @param createdDate the value for kona__app.created_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__app.updated_date
     *
     * @return the value of kona__app.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__app.updated_date
     *
     * @param updatedDate the value for kona__app.updated_date
     *
     * @mbg.generated Sat May 12 06:10:33 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}