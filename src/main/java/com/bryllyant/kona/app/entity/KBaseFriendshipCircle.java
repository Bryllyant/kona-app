package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseFriendshipCircle extends KBaseEntity implements KFriendshipCircle {
    private Long id;
    private String uid;
    private Long userId;
    private String name;
    private String slug;
    private boolean defaultCircle;
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
	public String getUid() {
        return uid;
    }

    @Override
	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    @Override
	public Long getUserId() {
        return userId;
    }

    @Override
	public void setUserId(Long userId) {
        this.userId = userId;
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
	public void setSlug(String displayName) {
        this.slug = slug == null ? null : slug.trim();
    }

    @Override
	public boolean isDefaultCircle() {
        return defaultCircle;
    }

    @Override
	public void setDefaultCircle(boolean defaultCircle) {
        this.defaultCircle = defaultCircle;
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
