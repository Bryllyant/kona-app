package com.bryllyant.kona.api.model.media;

import com.bryllyant.kona.api.model.account.AccountModel;
import com.bryllyant.kona.api.model.auth.TokenModel;
import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.File;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;


public class FileModel extends KJsonModel implements KEntityModel {
	private static final long serialVersionUID = 1L;

    private String uid;
    private FileModel parent;
    private File.Type type;
    private File.Access access;
    private UserModel user;
    private AccountModel account;
    private TokenModel token;
    private String name;
    private String contentType;
    private Long size;
    private Boolean hidden;
    private Boolean enabled;
    private Boolean tempFile;
    private String srcHostname;
    private String srcFilename;
    private String localPath;
    private String urlPath;
    private String url;
    private Long uploadTime;
    private Date createdDate;
    private Date deletedDate;
    private Date updatedDate;


    public static FileModel from(File file, String url) {
        FileModel model = new FileModel();
        model.setUid(file.getUid());
        model.setName(file.getName());
        model.setType(file.getType());
        model.setContentType(file.getContentType());
        model.setSize(file.getSize());
        model.setUrl(url);
        return model;
    }

	public static FileModel create(String uid) {
	    FileModel model = new FileModel();
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

    public FileModel getParent() {
        return parent;
    }

    public void setParent(FileModel parent) {
        this.set("parent", parent);
    }

    public File.Type getType() {
        return type;
    }

    public void setType(File.Type type) {
        this.set("type", type);
    }

    public File.Access getAccess() {
        return access;
    }

    public void setAccess(File.Access access) {
        this.set("access", access);
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

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.set("token", token);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.set("name", name);
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.set("contentType", contentType);
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.set("size", size);
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.set("hidden", hidden);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getTempFile() {
        return tempFile;
    }

    public void setTempFile(Boolean tempFile) {
        this.set("tempFile", tempFile);
    }

    public String getSrcHostname() {
        return srcHostname;
    }

    public void setSrcHostname(String srcHostname) {
        this.set("srcHostname", srcHostname);
    }

    public String getSrcFilename() {
        return srcFilename;
    }

    public void setSrcFilename(String srcFilename) {
        this.set("srcFilename", srcFilename);
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.set("localPath", localPath);
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.set("urlPath", urlPath);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.set("url", url);
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.set("uploadTime", uploadTime);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.set("deletedDate", deletedDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
