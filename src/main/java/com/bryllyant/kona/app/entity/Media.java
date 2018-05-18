package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import java.io.Serializable;
import java.util.Date;

public class Media extends KBaseEntity implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.parent_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.account_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long thumbnailId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.folder_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String folderPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String slug;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String urlPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String thumbnailUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String thumbnailUrlPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.content_type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private String contentType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer width;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer height;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long size;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.bits_per_pixel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer bitsPerPixel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.frames_per_second
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer framesPerSecond;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.duration
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long duration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer thumbnailWidth;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer thumbnailHeight;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.thumbnail_size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Long thumbnailSize;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.enabled
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.resizeable
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private boolean resizeable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.sprite
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private boolean sprite;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.sprite_x_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer spriteXOffset;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.sprite_y_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Integer spriteYOffset;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__media.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__media
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.uid
     *
     * @return the value of kona__media.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.uid
     *
     * @param uid the value for kona__media.uid
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.parent_id
     *
     * @return the value of kona__media.parent_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.parent_id
     *
     * @param parentId the value for kona__media.parent_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.user_id
     *
     * @return the value of kona__media.user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.user_id
     *
     * @param userId the value for kona__media.user_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.account_id
     *
     * @return the value of kona__media.account_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.account_id
     *
     * @param accountId the value for kona__media.account_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.file_id
     *
     * @return the value of kona__media.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.file_id
     *
     * @param fileId the value for kona__media.file_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_id
     *
     * @return the value of kona__media.thumbnail_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getThumbnailId() {
        return thumbnailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_id
     *
     * @param thumbnailId the value for kona__media.thumbnail_id
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailId(Long thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.folder_path
     *
     * @return the value of kona__media.folder_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.folder_path
     *
     * @param folderPath the value for kona__media.folder_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath == null ? null : folderPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.name
     *
     * @return the value of kona__media.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.name
     *
     * @param name the value for kona__media.name
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.slug
     *
     * @return the value of kona__media.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getSlug() {
        return slug;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.slug
     *
     * @param slug the value for kona__media.slug
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.description
     *
     * @return the value of kona__media.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.description
     *
     * @param description the value for kona__media.description
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.url
     *
     * @return the value of kona__media.url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.url
     *
     * @param url the value for kona__media.url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.url_path
     *
     * @return the value of kona__media.url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getUrlPath() {
        return urlPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.url_path
     *
     * @param urlPath the value for kona__media.url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_url
     *
     * @return the value of kona__media.thumbnail_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_url
     *
     * @param thumbnailUrl the value for kona__media.thumbnail_url
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl == null ? null : thumbnailUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_url_path
     *
     * @return the value of kona__media.thumbnail_url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getThumbnailUrlPath() {
        return thumbnailUrlPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_url_path
     *
     * @param thumbnailUrlPath the value for kona__media.thumbnail_url_path
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailUrlPath(String thumbnailUrlPath) {
        this.thumbnailUrlPath = thumbnailUrlPath == null ? null : thumbnailUrlPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.content_type
     *
     * @return the value of kona__media.content_type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.content_type
     *
     * @param contentType the value for kona__media.content_type
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.width
     *
     * @return the value of kona__media.width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.width
     *
     * @param width the value for kona__media.width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.height
     *
     * @return the value of kona__media.height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.height
     *
     * @param height the value for kona__media.height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.size
     *
     * @return the value of kona__media.size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.size
     *
     * @param size the value for kona__media.size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.bits_per_pixel
     *
     * @return the value of kona__media.bits_per_pixel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getBitsPerPixel() {
        return bitsPerPixel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.bits_per_pixel
     *
     * @param bitsPerPixel the value for kona__media.bits_per_pixel
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.frames_per_second
     *
     * @return the value of kona__media.frames_per_second
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getFramesPerSecond() {
        return framesPerSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.frames_per_second
     *
     * @param framesPerSecond the value for kona__media.frames_per_second
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setFramesPerSecond(Integer framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.duration
     *
     * @return the value of kona__media.duration
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.duration
     *
     * @param duration the value for kona__media.duration
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_width
     *
     * @return the value of kona__media.thumbnail_width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_width
     *
     * @param thumbnailWidth the value for kona__media.thumbnail_width
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_height
     *
     * @return the value of kona__media.thumbnail_height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_height
     *
     * @param thumbnailHeight the value for kona__media.thumbnail_height
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.thumbnail_size
     *
     * @return the value of kona__media.thumbnail_size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.thumbnail_size
     *
     * @param thumbnailSize the value for kona__media.thumbnail_size
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.enabled
     *
     * @return the value of kona__media.enabled
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.enabled
     *
     * @param enabled the value for kona__media.enabled
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.resizeable
     *
     * @return the value of kona__media.resizeable
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public boolean isResizeable() {
        return resizeable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.resizeable
     *
     * @param resizeable the value for kona__media.resizeable
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.sprite
     *
     * @return the value of kona__media.sprite
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public boolean isSprite() {
        return sprite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.sprite
     *
     * @param sprite the value for kona__media.sprite
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSprite(boolean sprite) {
        this.sprite = sprite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.sprite_x_offset
     *
     * @return the value of kona__media.sprite_x_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getSpriteXOffset() {
        return spriteXOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.sprite_x_offset
     *
     * @param spriteXOffset the value for kona__media.sprite_x_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSpriteXOffset(Integer spriteXOffset) {
        this.spriteXOffset = spriteXOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.sprite_y_offset
     *
     * @return the value of kona__media.sprite_y_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Integer getSpriteYOffset() {
        return spriteYOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.sprite_y_offset
     *
     * @param spriteYOffset the value for kona__media.sprite_y_offset
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setSpriteYOffset(Integer spriteYOffset) {
        this.spriteYOffset = spriteYOffset;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.latitude
     *
     * @return the value of kona__media.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.latitude
     *
     * @param latitude the value for kona__media.latitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.longitude
     *
     * @return the value of kona__media.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.longitude
     *
     * @param longitude the value for kona__media.longitude
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.created_date
     *
     * @return the value of kona__media.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.created_date
     *
     * @param createdDate the value for kona__media.created_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__media.updated_date
     *
     * @return the value of kona__media.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__media.updated_date
     *
     * @param updatedDate the value for kona__media.updated_date
     *
     * @mbg.generated Thu May 17 16:38:44 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}