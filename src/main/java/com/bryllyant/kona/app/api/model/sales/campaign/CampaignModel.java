package com.bryllyant.kona.app.api.model.sales.campaign;

import com.bryllyant.kona.app.entity.Campaign;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class CampaignModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private Long ownerId;
    private Campaign.Goal goal;
    private String name;
    private String slug;
    private String description;
    private Integer conversionCount;
    private boolean enabled;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;

    public static CampaignModel create(String uid) {
        CampaignModel model = new CampaignModel();
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.set("ownerId", ownerId);
    }

    public Campaign.Goal getGoal() {
        return goal;
    }

    public void setGoal(Campaign.Goal goal) {
        this.set("goal", goal);
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

    public Integer getConversionCount() {
        return conversionCount;
    }

    public void setConversionCount(Integer conversionCount) {
        this.set("conversionCount", conversionCount);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.set("enabled", enabled);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.set("startDate", startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.set("endDate", endDate);
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
