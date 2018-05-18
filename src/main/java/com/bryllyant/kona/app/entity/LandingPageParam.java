package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class LandingPageParam extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.landing_page_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long landingPageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.template_param_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long templateParamId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__landing_page_param.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__landing_page_param
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.uid
     *
     * @return the value of kona__landing_page_param.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.uid
     *
     * @param uid the value for kona__landing_page_param.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.landing_page_id
     *
     * @return the value of kona__landing_page_param.landing_page_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getLandingPageId() {
        return landingPageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.landing_page_id
     *
     * @param landingPageId the value for kona__landing_page_param.landing_page_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLandingPageId(Long landingPageId) {
        this.landingPageId = landingPageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.template_param_id
     *
     * @return the value of kona__landing_page_param.template_param_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getTemplateParamId() {
        return templateParamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.template_param_id
     *
     * @param templateParamId the value for kona__landing_page_param.template_param_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setTemplateParamId(Long templateParamId) {
        this.templateParamId = templateParamId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.file_id
     *
     * @return the value of kona__landing_page_param.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.file_id
     *
     * @param fileId the value for kona__landing_page_param.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.created_date
     *
     * @return the value of kona__landing_page_param.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.created_date
     *
     * @param createdDate the value for kona__landing_page_param.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.updated_date
     *
     * @return the value of kona__landing_page_param.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.updated_date
     *
     * @param updatedDate the value for kona__landing_page_param.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__landing_page_param.value
     *
     * @return the value of kona__landing_page_param.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__landing_page_param.value
     *
     * @param value the value for kona__landing_page_param.value
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}