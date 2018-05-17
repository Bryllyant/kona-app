package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Email extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.email_campaign_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long emailCampaignId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.email_address_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long emailAddressId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.email_content_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Long emailContentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.ses_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String sesId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.from_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String fromAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.to_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String toAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.subject
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private String subject;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.failed
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean failed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.delivered
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean delivered;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.bounced
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean bounced;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.complained
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean complained;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.opted_out
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private boolean optedOut;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.open_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer openCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.click_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer clickCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.print_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer printCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.forward_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Integer forwardCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.sent_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date sentDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__email.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__email
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.uid
     *
     * @return the value of kona__email.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.uid
     *
     * @param uid the value for kona__email.uid
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.email_campaign_id
     *
     * @return the value of kona__email.email_campaign_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getEmailCampaignId() {
        return emailCampaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.email_campaign_id
     *
     * @param emailCampaignId the value for kona__email.email_campaign_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setEmailCampaignId(Long emailCampaignId) {
        this.emailCampaignId = emailCampaignId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.email_address_id
     *
     * @return the value of kona__email.email_address_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getEmailAddressId() {
        return emailAddressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.email_address_id
     *
     * @param emailAddressId the value for kona__email.email_address_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setEmailAddressId(Long emailAddressId) {
        this.emailAddressId = emailAddressId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.email_content_id
     *
     * @return the value of kona__email.email_content_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Long getEmailContentId() {
        return emailContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.email_content_id
     *
     * @param emailContentId the value for kona__email.email_content_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setEmailContentId(Long emailContentId) {
        this.emailContentId = emailContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.ses_id
     *
     * @return the value of kona__email.ses_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getSesId() {
        return sesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.ses_id
     *
     * @param sesId the value for kona__email.ses_id
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSesId(String sesId) {
        this.sesId = sesId == null ? null : sesId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.from_address
     *
     * @return the value of kona__email.from_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.from_address
     *
     * @param fromAddress the value for kona__email.from_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress == null ? null : fromAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.to_address
     *
     * @return the value of kona__email.to_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.to_address
     *
     * @param toAddress the value for kona__email.to_address
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress == null ? null : toAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.subject
     *
     * @return the value of kona__email.subject
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public String getSubject() {
        return subject;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.subject
     *
     * @param subject the value for kona__email.subject
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.failed
     *
     * @return the value of kona__email.failed
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isFailed() {
        return failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.failed
     *
     * @param failed the value for kona__email.failed
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.delivered
     *
     * @return the value of kona__email.delivered
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isDelivered() {
        return delivered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.delivered
     *
     * @param delivered the value for kona__email.delivered
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.bounced
     *
     * @return the value of kona__email.bounced
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isBounced() {
        return bounced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.bounced
     *
     * @param bounced the value for kona__email.bounced
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.complained
     *
     * @return the value of kona__email.complained
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isComplained() {
        return complained;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.complained
     *
     * @param complained the value for kona__email.complained
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setComplained(boolean complained) {
        this.complained = complained;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.opted_out
     *
     * @return the value of kona__email.opted_out
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public boolean isOptedOut() {
        return optedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.opted_out
     *
     * @param optedOut the value for kona__email.opted_out
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setOptedOut(boolean optedOut) {
        this.optedOut = optedOut;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.open_count
     *
     * @return the value of kona__email.open_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getOpenCount() {
        return openCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.open_count
     *
     * @param openCount the value for kona__email.open_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setOpenCount(Integer openCount) {
        this.openCount = openCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.click_count
     *
     * @return the value of kona__email.click_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getClickCount() {
        return clickCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.click_count
     *
     * @param clickCount the value for kona__email.click_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.print_count
     *
     * @return the value of kona__email.print_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getPrintCount() {
        return printCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.print_count
     *
     * @param printCount the value for kona__email.print_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setPrintCount(Integer printCount) {
        this.printCount = printCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.forward_count
     *
     * @return the value of kona__email.forward_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Integer getForwardCount() {
        return forwardCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.forward_count
     *
     * @param forwardCount the value for kona__email.forward_count
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setForwardCount(Integer forwardCount) {
        this.forwardCount = forwardCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.sent_date
     *
     * @return the value of kona__email.sent_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.sent_date
     *
     * @param sentDate the value for kona__email.sent_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.created_date
     *
     * @return the value of kona__email.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.created_date
     *
     * @param createdDate the value for kona__email.created_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__email.updated_date
     *
     * @return the value of kona__email.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__email.updated_date
     *
     * @param updatedDate the value for kona__email.updated_date
     *
     * @mbg.generated Tue May 15 18:14:51 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}