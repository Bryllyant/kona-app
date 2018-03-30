package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseEmailAttachment extends KBaseEntity implements KEmailAttachment {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long  contentId;
    private Long  fileId;
    private String name;
    private String contentType;
    private byte[] data;
    private Date createdDate; 
    private Date updatedDate; 
    
    public KBaseEmailAttachment() {
        
    }
    
    public KBaseEmailAttachment(String name, String contentType, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }
    
    public Long getId() {
        return id;
    }

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
    public Long getContentId() {
        return contentId;
    }

    @Override
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }
    
    @Override
    public Long getFileId() {
        return fileId;
    }

    @Override
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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
    public String getContentType() {
        return contentType;
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
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
