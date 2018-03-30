package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;

import java.util.Date;
import java.util.List;

public class KBasePartner extends KBaseEntity implements KPartner {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long parentId;
    private Long placeId;
    private String name;
    private String slug;
    private String description;
    private String logoUrl;
    private String url;
    private String email;
    private String phoneNumber;
    private List<SocialHandle> socialHandles;

    private String contactFirstName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhoneNumber;
    private String contactMobileNumber;
    private Integer population;
    private boolean enabled;
    private Date deletedDate;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getLogoUrl() {
        return logoUrl;
    }

    @Override
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public List<SocialHandle> getSocialHandles() {
        return socialHandles;
    }

    @Override
    public void setSocialHandles(List<SocialHandle> socialHandles) {
        this.socialHandles = socialHandles;
    }

    @Override
    public String getContactFirstName() {
        return contactFirstName;
    }

    @Override
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    @Override
    public String getContactLastName() {
        return contactLastName;
    }

    @Override
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    @Override
    public String getContactEmail() {
        return contactEmail;
    }

    @Override
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    @Override
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    @Override
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Override
    public String getContactMobileNumber() {
        return contactMobileNumber;
    }

    @Override
    public void setContactMobileNumber(String contactMobileNumber) {
        this.contactMobileNumber = contactMobileNumber;
    }

    @Override
    public Integer getPopulation() {
        return population;
    }

    @Override
    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public Date getDeletedDate() {
        return deletedDate;
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
