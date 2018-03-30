package com.bryllyant.kona.api.model.marketing.landingpage;

import com.bryllyant.kona.api.model.media.FileModel;
import com.bryllyant.kona.app.entity.LandingPageParam;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class LandingPageParamModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private LandingPageModel landingPage;
    private LandingPageTemplateParamModel templateParam;
    private String value;
    private FileModel file;
    private Date createdDate;
    private Date updatedDate;

    public static LandingPageParamModel from(LandingPageParam param) {
        LandingPageParamModel model = new LandingPageParamModel();
        model.setUid(param.getUid());
        model.setValue(param.getValue());
        return model;
    }

    public static LandingPageParamModel create(String uid, String value) {
        LandingPageParamModel model = new LandingPageParamModel();
        model.setUid(uid);
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
        this.set("landingpage", landingPage);
    }

    public LandingPageTemplateParamModel getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(LandingPageTemplateParamModel templateParam) {
        this.set("templateParam", templateParam);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.set("value", value);
    }

    public FileModel getFile() {
        return file;
    }

    public void setFile(FileModel file) {
        this.set("file", file);
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
