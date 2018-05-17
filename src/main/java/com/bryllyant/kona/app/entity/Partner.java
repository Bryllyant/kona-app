package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Partner extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.parent_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.place_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long placeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.slug
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.logo_url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String logoUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String phoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.social_handles
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private List socialHandles;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.contact_first_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String contactFirstName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.contact_last_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String contactLastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.contact_email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String contactEmail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.contact_phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String contactPhoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.contact_mobile_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String contactMobileNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.population
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer population;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.enabled
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.deleted_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date deletedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__partner.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__partner
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.uid
     *
     * @return the value of kona__partner.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.uid
     *
     * @param uid the value for kona__partner.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.parent_id
     *
     * @return the value of kona__partner.parent_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.parent_id
     *
     * @param parentId the value for kona__partner.parent_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.place_id
     *
     * @return the value of kona__partner.place_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getPlaceId() {
        return placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.place_id
     *
     * @param placeId the value for kona__partner.place_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.name
     *
     * @return the value of kona__partner.name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.name
     *
     * @param name the value for kona__partner.name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.slug
     *
     * @return the value of kona__partner.slug
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
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
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.description
     *
     * @return the value of kona__partner.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.description
     *
     * @param description the value for kona__partner.description
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.logo_url
     *
     * @return the value of kona__partner.logo_url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.logo_url
     *
     * @param logoUrl the value for kona__partner.logo_url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.url
     *
     * @return the value of kona__partner.url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.url
     *
     * @param url the value for kona__partner.url
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.email
     *
     * @return the value of kona__partner.email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.email
     *
     * @param email the value for kona__partner.email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.phone_number
     *
     * @return the value of kona__partner.phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.phone_number
     *
     * @param phoneNumber the value for kona__partner.phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.social_handles
     *
     * @return the value of kona__partner.social_handles
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public List getSocialHandles() {
        return socialHandles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.social_handles
     *
     * @param socialHandles the value for kona__partner.social_handles
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSocialHandles(List socialHandles) {
        this.socialHandles = socialHandles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.contact_first_name
     *
     * @return the value of kona__partner.contact_first_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getContactFirstName() {
        return contactFirstName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.contact_first_name
     *
     * @param contactFirstName the value for kona__partner.contact_first_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName == null ? null : contactFirstName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.contact_last_name
     *
     * @return the value of kona__partner.contact_last_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getContactLastName() {
        return contactLastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.contact_last_name
     *
     * @param contactLastName the value for kona__partner.contact_last_name
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName == null ? null : contactLastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.contact_email
     *
     * @return the value of kona__partner.contact_email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.contact_email
     *
     * @param contactEmail the value for kona__partner.contact_email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.contact_phone_number
     *
     * @return the value of kona__partner.contact_phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.contact_phone_number
     *
     * @param contactPhoneNumber the value for kona__partner.contact_phone_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber == null ? null : contactPhoneNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.contact_mobile_number
     *
     * @return the value of kona__partner.contact_mobile_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getContactMobileNumber() {
        return contactMobileNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.contact_mobile_number
     *
     * @param contactMobileNumber the value for kona__partner.contact_mobile_number
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setContactMobileNumber(String contactMobileNumber) {
        this.contactMobileNumber = contactMobileNumber == null ? null : contactMobileNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.population
     *
     * @return the value of kona__partner.population
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.population
     *
     * @param population the value for kona__partner.population
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.enabled
     *
     * @return the value of kona__partner.enabled
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.enabled
     *
     * @param enabled the value for kona__partner.enabled
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.deleted_date
     *
     * @return the value of kona__partner.deleted_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getDeletedDate() {
        return deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.deleted_date
     *
     * @param deletedDate the value for kona__partner.deleted_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.created_date
     *
     * @return the value of kona__partner.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.created_date
     *
     * @param createdDate the value for kona__partner.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__partner.updated_date
     *
     * @return the value of kona__partner.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__partner.updated_date
     *
     * @param updatedDate the value for kona__partner.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}