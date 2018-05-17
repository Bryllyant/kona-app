package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.api.model.media.FileModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;
import java.util.List;

public class EmailContentModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel owner;
    private EmailTemplateModel template;
    private String name;
    private String slug;
    private String description;
    private Boolean system;
    private String html;
    private String text;
    private Date createdDate;
    private Date updatedDate;
    private List<FileModel> attachments;


    public static EmailContentModel from(EmailContent content) {
        EmailContentModel model = new EmailContentModel();
        model.setUid(content.getUid());
        model.setName(content.getName());
        model.setSlug(content.getSlug());
        return model;
    }

    public static EmailContentModel create(String uid) {
        EmailContentModel model = new EmailContentModel();
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

    public EmailTemplateModel getTemplate() {
        return template;
    }

    public void setTemplate(EmailTemplateModel template) {
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

    public Boolean getSystem() {
        return system;
    }

    public void setSystem(Boolean system) {
        this.set("system", system);
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.set("html", html);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.set("text", text);
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

    public List<FileModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<FileModel> attachments) {
        this.set("attachments", attachments);
    }
}
