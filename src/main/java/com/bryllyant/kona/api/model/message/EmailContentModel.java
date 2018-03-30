package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailContent;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailContentModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel owner;
    private String html;
    private String text;
    private Date createdDate;
    private Date updatedDate;


    public static EmailContentModel from(EmailContent content) {
        EmailContentModel model = new EmailContentModel();
        model.setUid(content.getUid());
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
}
