package com.bryllyant.kona.api.model.script;

import com.bryllyant.kona.app.entity.Script;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class ScriptModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private Script.Language language;
    private Script.ReturnType returnType;
    private String name;
    private String slug;
    private String body;
    private Boolean enabled;
    private Date createdDate;
    private Date updatedDate;


    public static ScriptModel from(Script script) {
        ScriptModel model = new ScriptModel();
        model.setUid(script.getUid());
        model.setName(script.getName());
        return model;
    }

    public static ScriptModel create(String uid) {
        ScriptModel model = new ScriptModel();
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

    public Script.Language getLanguage() {
        return language;
    }

    public void setLanguage(Script.Language language) {
        this.set("language", language);
    }

    public Script.ReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(Script.ReturnType returnType) {
        this.set("returnType", returnType);
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.set("body", body);
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
