package com.bryllyant.kona.api.model.account;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Account;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AccountModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String uid;

    @NotNull
    @RestdocsNotExpanded
    private UserModel owner;

    @NotNull
    private String name;
    private String slug;

    private Boolean enabled;
    private Boolean active;
    private Boolean verified;

    private Date createdDate;


    public static AccountModel from(Account account) {
        AccountModel model = new AccountModel();
        model.setUid(account.getUid());
        model.setName(account.getName());
        return model;
    }

    public static AccountModel create(String uid) {
        AccountModel model = new AccountModel();
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.set("active", active);
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.set("verified", verified);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
