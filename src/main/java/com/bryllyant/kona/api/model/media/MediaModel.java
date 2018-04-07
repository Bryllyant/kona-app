package com.bryllyant.kona.api.model.media;

import com.bryllyant.kona.api.model.account.AccountModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.app.entity.Media;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;


public class MediaModel extends KJsonModel implements KEntityModel {
	private static final long serialVersionUID = 1L;

    private String uid;
    private MediaModel parent;
    private UserModel user;
    private AccountModel account;

    private File.Type fileType;
    private String contentType;

    private String name;
    private String slug;
    private String folderPath;
    private String description;

    private String url;
    private Integer width;
    private Integer height;
    private Long size;

    private String thumbnailUrl;
    private Integer thumbnailWidth;
    private Integer thumbnailHeight;
    private Long thumbnailSize;

    private Integer bitsPerPixel;
    private Integer framesPerSecond;
    private Long duration;

    private Boolean enabled;
    private Boolean resizeable;

    private Boolean sprite;
    private Integer spriteXOffset;
    private Integer spriteYOffset;

    private Double latitude;
    private Double longitude;

    private Date createdDate;
    private Date updatedDate;


    public static MediaModel from(Media media, String url) {
        MediaModel model = new MediaModel();
        model.setUid(media.getUid());
        model.setName(media.getName());
        model.setContentType(media.getContentType());
        model.setUrl(url);
        return model;
    }

	public static MediaModel create(String uid) {
	    MediaModel model = new MediaModel();
	    model.setUid(uid);
	    return model;
	}

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public MediaModel getParent() {
        return parent;
    }

    public void setParent(MediaModel parent) {
        this.set("parent", parent);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.set("account", account);
    }

    public File.Type getFileType() {
        return fileType;
    }

    public void setFileType(File.Type fileType) {
        this.set("fileType", fileType);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.set("contentType", contentType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.set("slug", slug);
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.set("folderPath", folderPath);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.set("description", description);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.set("width", width);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.set("height", height);
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.set("size", size);
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.set("thumbnailUrl", thumbnailUrl);
    }

    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.set("thumbnailWidth", thumbnailWidth);
    }

    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.set("thumbnailHeight", thumbnailHeight);
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.set("thumbnailSize", thumbnailSize);
    }

    public Integer getBitsPerPixel() {
        return bitsPerPixel;
    }

    public void setBitsPerPixel(Integer bitsPerPixel) {
        this.set("bitsPerPixel", bitsPerPixel);
    }

    public Integer getFramesPerSecond() {
        return framesPerSecond;
    }

    public void setFramesPerSecond(Integer framesPerSecond) {
        this.set("framesPerSecond", framesPerSecond);
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.set("duration", duration);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getResizeable() {
        return resizeable;
    }

    public void setResizeable(Boolean resizeable) {
        this.set("resizeable", resizeable);
    }

    public Boolean getSprite() {
        return sprite;
    }

    public void setSprite(Boolean sprite) {
        this.set("sprite", sprite);
    }

    public Integer getSpriteXOffset() {
        return spriteXOffset;
    }

    public void setSpriteXOffset(Integer spriteXOffset) {
        this.set("spriteXOffset", spriteXOffset);
    }

    public Integer getSpriteYOffset() {
        return spriteYOffset;
    }

    public void setSpriteYOffset(Integer spriteYOffset) {
        this.set("spriteYOffset", spriteYOffset);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.set("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.set("longitude", longitude);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
