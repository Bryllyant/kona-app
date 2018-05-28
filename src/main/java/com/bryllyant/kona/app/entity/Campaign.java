package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseCampaign.Goal;
import com.bryllyant.kona.app.entity.BaseCampaign.KPI;
import java.io.Serializable;
import java.util.Date;

public class Campaign extends BaseCampaign implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.goal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Goal goal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.kpi
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private KPI kpi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.description
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Integer conversionCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.conversion_target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Integer conversionTarget;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__campaign.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__campaign
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.uid
     *
     * @return the value of kona__campaign.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.uid
     *
     * @param uid the value for kona__campaign.uid
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.owner_id
     *
     * @return the value of kona__campaign.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.owner_id
     *
     * @param ownerId the value for kona__campaign.owner_id
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.goal
     *
     * @return the value of kona__campaign.goal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.goal
     *
     * @param goal the value for kona__campaign.goal
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.kpi
     *
     * @return the value of kona__campaign.kpi
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public KPI getKpi() {
        return kpi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.kpi
     *
     * @param kpi the value for kona__campaign.kpi
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setKpi(KPI kpi) {
        this.kpi = kpi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.name
     *
     * @return the value of kona__campaign.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.name
     *
     * @param name the value for kona__campaign.name
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.slug
     *
     * @return the value of kona__campaign.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.slug
     *
     * @param slug the value for kona__campaign.slug
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.description
     *
     * @return the value of kona__campaign.description
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.description
     *
     * @param description the value for kona__campaign.description
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.conversion_count
     *
     * @return the value of kona__campaign.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Integer getConversionCount() {
        return conversionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.conversion_count
     *
     * @param conversionCount the value for kona__campaign.conversion_count
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setConversionCount(Integer conversionCount) {
        this.conversionCount = conversionCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.conversion_target
     *
     * @return the value of kona__campaign.conversion_target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Integer getConversionTarget() {
        return conversionTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.conversion_target
     *
     * @param conversionTarget the value for kona__campaign.conversion_target
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setConversionTarget(Integer conversionTarget) {
        this.conversionTarget = conversionTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.enabled
     *
     * @return the value of kona__campaign.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.enabled
     *
     * @param enabled the value for kona__campaign.enabled
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.start_date
     *
     * @return the value of kona__campaign.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.start_date
     *
     * @param startDate the value for kona__campaign.start_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.end_date
     *
     * @return the value of kona__campaign.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.end_date
     *
     * @param endDate the value for kona__campaign.end_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.created_date
     *
     * @return the value of kona__campaign.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.created_date
     *
     * @param createdDate the value for kona__campaign.created_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__campaign.updated_date
     *
     * @return the value of kona__campaign.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__campaign.updated_date
     *
     * @param updatedDate the value for kona__campaign.updated_date
     *
     * @mbg.generated Mon May 28 09:46:31 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}