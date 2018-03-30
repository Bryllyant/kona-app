package com.bryllyant.kona.app.entity;


import java.util.Date;

public class KBaseNotificationDelivery extends KBaseEntity implements KNotificationDelivery {
    private Long id;
    private Long notificationId;
    private KNotification.Channel channel;
    private String code;
    private Date deliveredDate;
    private Date viewedDate;
    private Date createdDate;
    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    @Override
	public Long getId() {
        return id;
    }

    @Override
	public void setId(Long id) {
        this.id = id;
    }

    @Override
	public Long getNotificationId() {
        return notificationId;
    }

    @Override
	public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    @Override
	public KNotification.Channel getChannel() {
        return channel;
    }

    @Override
	public void setChannel(KNotification.Channel channel) {
        this.channel = channel;
    }

    @Override
	public String getCode() {
        return code;
    }

    @Override
	public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
	public Date getDeliveredDate() {
        return deliveredDate;
    }

    @Override
	public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    @Override
	public Date getViewedDate() {
        return viewedDate;
    }

    @Override
	public void setViewedDate(Date viewedDate) {
        this.viewedDate = viewedDate;
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
