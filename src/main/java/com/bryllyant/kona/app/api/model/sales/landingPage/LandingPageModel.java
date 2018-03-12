package com.bryllyant.kona.app.api.model.sales.landingPage;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    @RestdocsNotExpanded
    private LandingPageTemplateModel template;
    private String name;
    private String slug;
    private String description;
    private boolean enabled;
    private Date createdDate;
    private Date updatedDate;


    public static LandingPageModel create(String uid) {
        LandingPageModel model = new LandingPageModel();
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

    public LandingPageTemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(LandingPageTemplateModel template) {
        this.set("template", template);
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.set("enabled", enabled);
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
