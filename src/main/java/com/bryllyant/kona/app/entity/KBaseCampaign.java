package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseCampaign extends KBaseEntity implements KCampaign {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long ownerId;
    private Goal goal;
    private KPI kpi;
    private String name;
    private String slug;
    private String description;
    private Integer conversionCount;
    private Integer conversionTarget;
    private boolean enabled;
    private Date startDate;
    private Date endDate;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Goal getGoal() {
        return goal;
    }

    @Override
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    @Override
    public KPI getKpi() {
        return kpi;
    }

    @Override
    public void setKpi(KPI kpi) {
        this.kpi = kpi;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSlug() {
        return slug;
    }

    @Override
    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getConversionCount() {
        return conversionCount;
    }

    @Override
    public void setConversionCount(Integer conversionCount) {
        this.conversionCount = conversionCount;
    }

    @Override
    public Integer getConversionTarget() {
        return conversionTarget;
    }

    @Override
    public void setConversionTarget(Integer conversionTarget) {
        this.conversionTarget = conversionTarget;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
