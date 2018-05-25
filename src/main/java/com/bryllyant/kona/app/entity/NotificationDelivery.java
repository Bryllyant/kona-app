package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.entity.BaseNotificationDelivery.Channel;
import java.io.Serializable;
import java.util.Date;

public class NotificationDelivery extends BaseNotificationDelivery implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.notification_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Long notificationId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.channel
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Channel channel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.view_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.sent_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date sentDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.viewed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date viewedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__notification_delivery.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__notification_delivery
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.uid
     *
     * @return the value of kona__notification_delivery.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.uid
     *
     * @param uid the value for kona__notification_delivery.uid
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.notification_id
     *
     * @return the value of kona__notification_delivery.notification_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Long getNotificationId() {
        return notificationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.notification_id
     *
     * @param notificationId the value for kona__notification_delivery.notification_id
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.channel
     *
     * @return the value of kona__notification_delivery.channel
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.channel
     *
     * @param channel the value for kona__notification_delivery.channel
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.view_count
     *
     * @return the value of kona__notification_delivery.view_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.view_count
     *
     * @param viewCount the value for kona__notification_delivery.view_count
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.sent_date
     *
     * @return the value of kona__notification_delivery.sent_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.sent_date
     *
     * @param sentDate the value for kona__notification_delivery.sent_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.viewed_date
     *
     * @return the value of kona__notification_delivery.viewed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getViewedDate() {
        return viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.viewed_date
     *
     * @param viewedDate the value for kona__notification_delivery.viewed_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.created_date
     *
     * @return the value of kona__notification_delivery.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.created_date
     *
     * @param createdDate the value for kona__notification_delivery.created_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__notification_delivery.updated_date
     *
     * @return the value of kona__notification_delivery.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__notification_delivery.updated_date
     *
     * @param updatedDate the value for kona__notification_delivery.updated_date
     *
     * @mbg.generated Sat May 19 06:25:18 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}