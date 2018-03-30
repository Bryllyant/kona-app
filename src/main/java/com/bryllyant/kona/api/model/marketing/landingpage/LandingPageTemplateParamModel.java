package com.bryllyant.kona.api.model.marketing.landingpage;

import com.bryllyant.kona.app.entity.KLandingPageTemplateParam;
import com.bryllyant.kona.app.entity.LandingPageTemplateParam;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageTemplateParamModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private LandingPageTemplateModel template;
    private String name;
    private String slug;
    private String displayName;
    private KLandingPageTemplateParam.Type type;
    private Boolean required;
    private Date createdDate;
    private Date updatedDate;

    public static LandingPageTemplateParamModel from(LandingPageTemplateParam param) {
        LandingPageTemplateParamModel model = new LandingPageTemplateParamModel();
        model.setUid(param.getUid());
        model.setName(param.getName());
        model.setDisplayName(param.getDisplayName());
        model.setType(param.getType());
        model.setRequired(param.isRequired());
        return model;
    }

    public static LandingPageTemplateParamModel create(String uid) {
        LandingPageTemplateParamModel model = new LandingPageTemplateParamModel();
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.set("displayName", displayName);
    }

    public KLandingPageTemplateParam.Type getType() {
        return type;
    }

    public void setType(KLandingPageTemplateParam.Type type) {
        this.set("type", type);
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.set("required", required);
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
