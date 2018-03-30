package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBasePlaceTag extends KBaseEntity implements KPlaceTag {
    private static final long serialVersionUID = 1L;

    Long id;
    String uid;
    Long placeId;
    String tag;
    String slug;
    Date createdDate;
    Date updatedDate;

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
    public Long getPlaceId() {
        return placeId;
    }

    @Override
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
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
