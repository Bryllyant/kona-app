package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageParamModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private LandingPageModel landingPage;
    private LandingPageTemplateModel template;
    private String name;
    private String value;
    private Date createdDate;
    private Date updatedDate;


    public static LandingPageParamModel create(String uid, String name, String value) {
        LandingPageParamModel model = new LandingPageParamModel();
        model.setUid(uid);
        model.setName(name);
        model.setValue(value);
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

    public LandingPageModel getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(LandingPageModel landingPage) {
        this.set("landingPage", landingPage);
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.set("value", value);
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
