/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KMedia extends KEntityObject {
	
	@Override
    public Long getId();
    public void setId(Long id);
    
    public String getUid();
    public void setUid(String uid);
    
    public Long getParentId();
    public void setParentId(Long parentId);

    public Long getUserId();
    public void setUserId(Long userId);
    
    public Long getAccountId();
    public void setAccountId(Long accountId);

    public Long getFileId();
    public void setFileId(Long fileId);
    
	public Long getThumbnailId();
    public void setThumbnailId(Long thumbnailId);
    
	/**
	 * User defined internal organization of the media objects.
	 * @return
	 */
    public String getFolderPath();
    public void setFolderPath(String folderPath);

    /**
     * User defined name of media object. May or may not be same as file name.
     */
    public String getName();
    public void setName(String name);

    public String getSlug();
    public void setSlug(String slug);

    public String getDescription();
    public void setDescription(String description);

    /**
     * Absolute (external) URL of media.
     */
    public String getUrl();
    public void setUrl(String url);

    /**
     * Local 
     */
    public String getUrlPath();
    public void setUrlPath(String urlPath);

    
    /**
     * Absolute (external) URL of thumbnail.
     */
    public String getThumbnailUrl();
    public void setThumbnailUrl(String thumbnailUrl);

    
    public String getThumbnailUrlPath();
    public void setThumbnailUrlPath(String thumbnailUrlPath);

    public String getContentType();
    public void setContentType(String contentType);

    /**
     * Indicates this media object is a sprite image.
     * 
     * Individual sprite elements will be children of this object with spriteXOffset and spriteYOffset set.
     * 
     * @return
     */
	boolean isSprite();
	void setSprite(boolean sprite);


	/**
	 * X offset of the sprite image.  
	 * 
	 * Implies that a parent object is defined contains the actual sprite image.
	 * 
	 * @return
	 */
	Integer getSpriteXOffset();
	void setSpriteXOffset(Integer spriteXOffset);


	/**
	 * Y offset of the sprite image.  
	 * 
	 * Implies that a parent object is defined contains the actual sprite image.
	 * 
	 * @return
	 */
	Integer getSpriteYOffset();
	void setSpriteYOffset(Integer spriteYOffset);
	
    
	boolean isEnabled();
	void setEnabled(boolean enabled);
	
    public Double getLatitude();
    public void setLatitude(Double latitude);
    
    public Double getLongitude();
    public void setLongitude(Double longitude);
    
    public Integer getWidth();
    public void setWidth(Integer width);

    public Integer getHeight();
    public void setHeight(Integer height);
    
    public Long getSize();
    public void setSize(Long size);
    
    public Integer getThumbnailWidth();
    public void setThumbnailWidth(Integer thumbnailWidth);

    public Integer getThumbnailHeight();
    public void setThumbnailHeight(Integer thumbnailHeight);

    public Long getThumbnailSize();
    public void setThumbnailSize(Long thumbnailSize);

    public Integer getBitsPerPixel();
    public void setBitsPerPixel(Integer bitPerPixel);

    public Integer getFramesPerSecond();
    public void setFramesPerSecond(Integer framesPerSecond);

    public Long getDuration();
    public void setDuration(Long duration);

    public boolean isResizeable();
    public void setResizeable(boolean resizeable);
    
	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();
	void setUpdatedDate(Date updatedDate);
}
