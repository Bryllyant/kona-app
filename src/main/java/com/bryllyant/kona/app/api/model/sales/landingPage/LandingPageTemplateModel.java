package com.bryllyant.kona.app.api.model.sales.landingPage;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.LandingPageTemplate;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageTemplateModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    @RestdocsNotExpanded
    private UserModel owner;
    private String url;
    private String name;
    private String slug;
    private String description;
    private Date createdDate;
    private Date updatedDate;

    public static LandingPageTemplateModel from(LandingPageTemplate template) {
        LandingPageTemplateModel model = new LandingPageTemplateModel();
        model.setUid(template.getUid());
        model.setName(template.getName());
        return model;
    }

    public static LandingPageTemplateModel create(String uid) {
        LandingPageTemplateModel model = new LandingPageTemplateModel();
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

    public UserModel getOwner() {
        return owner;
    }

    public void setOwner(UserModel owner) {
        this.set("owner", owner);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
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
