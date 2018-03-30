package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseAppWebhook extends KBaseEntity implements KAppWebhook {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long appId;
    private String name;
    private String slug;
    private String events;
    private String url;
    private boolean enabled;
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
        this.uid = uid == null ? null : uid.trim();
    }

    @Override
	public Long getAppId() {
        return appId;
    }

    @Override
	public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
	public String getName() {
        return name;
    }

    @Override
	public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
	public String getSlug() {
        return slug;
    }

    @Override
	public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    @Override
	public String getEvents() {
        return events;
    }

    @Override
	public void setEvents(String events) {
        this.events = events == null ? null : events.trim();
    }

    @Override
	public String getUrl() {
        return url;
    }

    @Override
	public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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
