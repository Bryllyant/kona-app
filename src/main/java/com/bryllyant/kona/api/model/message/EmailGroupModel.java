package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.app.entity.EmailGroup;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailGroupModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private String name;
    private String slug;
    private Date createdDate;
    private Date updatedDate;


    public static EmailGroupModel from(EmailGroup group) {
        EmailGroupModel model = new EmailGroupModel();
        model.setUid(group.getUid());
        model.setName(group.getName());
        return model;
    }

    public static EmailGroupModel create(String uid) {
        EmailGroupModel model = new EmailGroupModel();
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
