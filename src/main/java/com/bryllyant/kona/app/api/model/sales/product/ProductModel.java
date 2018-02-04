package com.bryllyant.kona.app.api.model.sales.product;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.math.BigDecimal;
import java.util.Date;

public class ProductModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;

    @RestdocsNotExpanded
    private AppModel app;

    private String name;
    private String slug;
    private Integer displayOrder;
    private String description;
    private BigDecimal price;
    private BigDecimal setupFee;
    private Integer trialDays;
    private Boolean subscription;
    private Integer subscriptionDays;
    private String supportType;
    private Boolean active;
    private Date createdDate;
    private Date updatedDate;
    

    public static ProductModel create(String uid) {
        ProductModel model = new ProductModel();
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

    public AppModel getApp() {
        return app;
    }

    public void setApp(AppModel app) {
        this.set("app", app);
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

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.set("displayOrder", displayOrder);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.set("price", price);
    }

    public BigDecimal getSetupFee() {
        return setupFee;
    }

    public void setSetupFee(BigDecimal setupFee) {
        this.set("setupFee", setupFee);
    }

    public Integer getTrialDays() {
        return trialDays;
    }

    public void setTrialDays(Integer trialDays) {
        this.set("trialDays", trialDays);
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(Boolean subscription) {
        this.set("subscription", subscription);
    }

    public Integer getSubscriptionDays() {
        return subscriptionDays;
    }

    public void setSubscriptionDays(Integer subscriptionDays) {
        this.set("subscriptionDays", subscriptionDays);
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String supportType) {
        this.set("supportType", supportType);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.set("active", active);
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
