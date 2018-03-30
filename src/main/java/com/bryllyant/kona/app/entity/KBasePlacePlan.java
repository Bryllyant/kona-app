package com.bryllyant.kona.app.entity;

import java.util.Date;
import java.util.Map;

public class KBasePlacePlan extends KBaseEntity implements KPlacePlan {
    private static final long serialVersionUID = 1L;

    Long id;
    String uid;
    Long placeId;
    String name;
    String slug;
    Map<String,Object> plan;
    Integer floor;
    String perimeter;
    String encodedPerimeter;
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
    public Map<String, Object> getPlan() {
        return plan;
    }

    @Override
    public void setPlan(Map<String, Object> plan) {
        this.plan = plan;
    }

    @Override
    public Integer getFloor() {
        return floor;
    }

    @Override
    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
    public String getPerimeter() {
        return perimeter;
    }

    @Override
    public void setPerimeter(String perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public String getEncodedPerimeter() {
        return encodedPerimeter;
    }

    @Override
    public void setEncodedPerimeter(String encodedPerimeter) {
        this.encodedPerimeter = encodedPerimeter;
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
