package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePolicy extends KBaseEntity implements KPolicy {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Type type;
    private String content;
    private Integer version;
    private boolean active;
    private Date publishedDate;
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
	public Type getType() {
        return type;
    }

    @Override
	public void setType(Type type) {
        this.type = type;
    }

    @Override
	public String getContent() {
        return content;
    }

    @Override
	public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
	public Integer getVersion() {
        return version;
    }

    @Override
	public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
	public boolean isActive() {
        return active;
    }

    @Override
	public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
	public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
	public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
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
