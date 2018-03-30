/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBaseMedia.
 */
public class KBaseMedia extends KBaseEntity implements KMedia {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long accountId;
    private Long parentId;
    private Long thumbnailId;
    private Long fileId;

    private String folderPath;
    private String name;
    private String slug;
    private String description;

    private String url;
    private String thumbnailUrl;

    private String urlPath;
    private String thumbnailUrlPath;
    private String contentType;


    private Double latitude;
    private Double longitude;

    private boolean enabled;
    private boolean resizeable;
    private boolean sprite;

    private Integer framesPerSecond;
    private Long duration;
    private Integer bitsPerPixel;
    private Integer spriteXOffset;
    private Integer spriteYOffset;

    private Integer width;
    private Integer height;
    private Long size;

    private Integer thumbnailWidth;
    private Integer thumbnailHeight;
    private Long thumbnailSize;

    private Date updatedDate;

    private Date createdDate;


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
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public Long getThumbnailId() {
        return thumbnailId;
    }

    @Override
    public void setThumbnailId(Long thumbnailId) {
        this.thumbnailId = thumbnailId;
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
    public String getFolderPath() {
        return folderPath;
    }

    @Override
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    @Override
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String getUrlPath() {
        return urlPath;
    }

    @Override
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    @Override
    public String getThumbnailUrlPath() {
        return thumbnailUrlPath;
    }

    @Override
    public void setThumbnailUrlPath(String thumbnailUrlPath) {
        this.thumbnailUrlPath = thumbnailUrlPath;
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
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
    public boolean isResizeable() {
        return resizeable;
    }

    @Override
    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    @Override
    public boolean isSprite() {
        return sprite;
    }

    @Override
    public void setSprite(boolean sprite) {
        this.sprite = sprite;
    }

    @Override
    public Integer getFramesPerSecond() {
        return framesPerSecond;
    }

    @Override
    public void setFramesPerSecond(Integer framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    @Override
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Override
    public Integer getBitsPerPixel() {
        return bitsPerPixel;
    }

    @Override
    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    @Override
    public Integer getSpriteXOffset() {
        return spriteXOffset;
    }

    @Override
    public void setSpriteXOffset(Integer spriteXOffset) {
        this.spriteXOffset = spriteXOffset;
    }

    @Override
    public Integer getSpriteYOffset() {
        return spriteYOffset;
    }

    @Override
    public void setSpriteYOffset(Integer spriteYOffset) {
        this.spriteYOffset = spriteYOffset;
    }

    @Override
    public Integer getWidth() {
        return width;
    }

    @Override
    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    @Override
    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    @Override
    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    @Override
    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    @Override
    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    @Override
    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
