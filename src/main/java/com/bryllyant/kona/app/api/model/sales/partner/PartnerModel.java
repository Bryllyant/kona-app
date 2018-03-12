package com.bryllyant.kona.app.api.model.sales.partner;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.geo.place.PlaceModel;
import com.bryllyant.kona.app.api.model.user.PersonModel;
import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;
import java.util.List;

public class PartnerModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;

    @RestdocsNotExpanded
    private PartnerModel parent;

    private String name;
    private String slug;
    private String description;
    private String logoUrl;

    private String url;
    private String email;
    private String phoneNumber;
    private List<SocialHandle> socialHandles;

    @RestdocsNotExpanded
    private PlaceModel place;

    @RestdocsNotExpanded
    private PersonModel contact;

    private Boolean enabled;
    private Integer population;
    private Date deletedDate;
    private Date createdDate;
    private Date updatedDate;

    public static PartnerModel create(String uid) {
        PartnerModel model = new PartnerModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public PartnerModel getParent() {
        return parent;
    }

    public void setParent(PartnerModel parent) {
        this.set("parent", parent);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.set("logoUrl", logoUrl);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.set("phoneNumber", phoneNumber);
    }

    public List<SocialHandle> getSocialHandles() {
        return socialHandles;
    }

    public void setSocialHandles(List<SocialHandle> socialHandles) {
        this.set("socialHandles", socialHandles);
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.set("place", place);
    }

    public PersonModel getContact() {
        return contact;
    }

    public void setContact(PersonModel contact) {
        this.set("contact", contact);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.set("population", population);
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.set("deletedDate", deletedDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
