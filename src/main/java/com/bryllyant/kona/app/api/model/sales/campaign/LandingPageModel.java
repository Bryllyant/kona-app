package com.bryllyant.kona.app.api.model.sales.campaign;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;

    @RestdocsNotExpanded
    private AppModel app;

    @RestdocsNotExpanded
    private UserModel addedBy;

    @RestdocsNotExpanded
    private LandingPageTemplateModel template;

//    private List<LandingPageParamModel> params;

    private String name;
    private String description;
    private String urlPath;
    private String shortUrl;
    private String facebookTrackingId;
    private String googleTrackingId;
    private Boolean enabled;
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

    public AppModel getApp() {
        return app;
    }

    public void setApp(AppModel app) {
        this.set("app", app);
    }

    public UserModel getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserModel addedBy) {
        this.set("addedBy", addedBy);
    }

    public LandingPageTemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(LandingPageTemplateModel template) {
        this.set("template", template);
    }

//    public List<LandingPageParamModel> getParams() {
//        return params;
//    }
//
//    public void setParams(List<LandingPageParamModel> params) {
//        this.set("params", params);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.set("urlPath", urlPath);
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.set("shortUrl", shortUrl);
    }

    public String getFacebookTrackingId() {
        return facebookTrackingId;
    }

    public void setFacebookTrackingId(String facebookTrackingId) {
        this.set("facebookTrackingId", facebookTrackingId);
    }

    public String getGoogleTrackingId() {
        return googleTrackingId;
    }

    public void setGoogleTrackingId(String googleTrackingId) {
        this.set("googleTrackingId", googleTrackingId);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
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
