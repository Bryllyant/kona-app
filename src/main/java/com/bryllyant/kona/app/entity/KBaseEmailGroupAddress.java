package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseEmailGroupAddress extends KBaseEntity implements KEmailGroupAddress {
    private Long id;
    private String uid;
    private Long groupId;
    private Long addressId;
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
        this.uid = uid;
    }

    @Override
	public Long getGroupId() {
        return groupId;
    }

    @Override
	public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
	public Long getAddressId() {
        return addressId;
    }

    @Override
	public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
