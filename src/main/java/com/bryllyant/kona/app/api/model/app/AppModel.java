package com.bryllyant.kona.app.api.model.app;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.KAppType;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AppModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------

    @NotNull
    private String uid;

    @NotNull
    private KAppType type;

    @NotNull
    @RestdocsNotExpanded
    private UserModel user;

    @NotNull
    private String name;

    private String description;

    private String logoUrl;

    private String appUrl;

    private String companyName;

    private String companyUrl;

    private String privacyUrl;

    @NotNull
    private Boolean enabled;

    @NotNull
    private Date createdDate;
    
    // ----------------------------------------------------------------------
    
    public static AppModel create(String uid) {
        AppModel model = new AppModel();
        model.setUid(uid);
        return model;
    }

    // ----------------------------------------------------------------------


    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public KAppType getType() {
        return type;
    }

    public void setType(KAppType type) {
        this.set("type", type);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.set("logoUrl", logoUrl);
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.set("appUrl", appUrl);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.set("companyName", companyName);
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.set("companyUrl", companyUrl);
    }

    public String getPrivacyUrl() {
        return privacyUrl;
    }

    public void setPrivacyUrl(String privacyUrl) {
        this.set("privacyUrl", privacyUrl);
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
}
