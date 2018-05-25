package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailTemplate;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailTemplateModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel owner;
    private String name;
    private String slug;
    private String description;
    private String textTemplate;
    private String htmlTemplate;
    private Date createdDate;
    private Date updatedDate;


    public static EmailTemplateModel from(EmailTemplate template) {
        EmailTemplateModel model = new EmailTemplateModel();
        model.setUid(template.getUid());
        model.setName(template.getName());
        model.setSlug(template.getSlug());
        return model;
    }

    public static EmailTemplateModel create(String uid) {
        EmailTemplateModel model = new EmailTemplateModel();
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

    public String getTextTemplate() {
        return textTemplate;
    }

    public void setTextTemplate(String textTemplate) {
        this.set("textTemplate", textTemplate);
    }

    public String getHtmlTemplate() {
        return htmlTemplate;
    }

    public void setHtmlTemplate(String htmlTemplate) {
        this.set("htmlTemplate", htmlTemplate);
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
