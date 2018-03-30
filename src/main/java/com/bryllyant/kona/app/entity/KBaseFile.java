package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.http.KMimeTypes;

import java.util.Date;

@SuppressWarnings("serial")
public class KBaseFile extends KBaseEntity implements KFile {
	private Long id;
    private String uid;
	private Long parentId;
	private Type type;
	private Access access;
	private Long userId;
	private Long accountId;
	private Long tokenId;
    private String name;
	private String contentType;
	private Long size;
	private byte[] data;
    private boolean hidden;
    private boolean enabled;
    private boolean tempFile;
	private String srcHostname;
	private String srcFilename;
    private String localPath;
    private String urlPath;
    private Long uploadTime;
	private Date createdDate;
	private Date deletedDate;
	private Date updatedDate;


    public static KFile.Type getTypeByContentType(String contentType) {
        if (contentType == null) return null;

        for (String type : KMimeTypes.IMAGE_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return KFile.Type.IMAGE;
            }
        }

        for (String type : KMimeTypes.AUDIO_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return KFile.Type.AUDIO;
            }
        }

        for (String type : KMimeTypes.VIDEO_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return KFile.Type.VIDEO;
            }
        }

        for (String type : KMimeTypes.DOCUMENT_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return KFile.Type.DOCUMENT;
            }
        }

        for (String type : KMimeTypes.ARCHIVE_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return KFile.Type.ARCHIVE;
            }
        }

        if (contentType.equalsIgnoreCase("application/octet-stream")) {
            return KFile.Type.OTHER;
        }

        return null;
    }


    
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
        return (uid);
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }
    


    @Override
    public Long getParentId() {
        return (parentId);
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }



    @Override
    public Type getType() {
        return (type);
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }


    
    @Override
    public Access getAccess() {
        return (access);
    }

    @Override
    public void setAccess(Access access) {
        this.access = access;
    }



    @Override
    public Long getUserId() {
        return (userId);
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }



    @Override
    public Long getAccountId() {
        return (accountId);
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


    
    @Override
    public Long getTokenId() {
        return tokenId;
    }

    @Override
    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }



    @Override
    public String getName() {
        return (name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String getContentType() {
        return (contentType);
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }



    @Override
    public Long getSize() {
        return (size);
    }

    @Override
    public void setSize(Long size) {
        this.size = size;
    }



    @Override
    public byte[] getData() {
        return (data);
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }



    @Override
    public boolean isHidden() {
        return (hidden);
    }

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }



    @Override
    public boolean isEnabled() {
        return (enabled);
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public boolean isTempFile() {
        return (tempFile);
    }

    @Override
    public void setTempFile(boolean tempFile) {
        this.tempFile = tempFile;
    }
    

    
    @Override
    public String getSrcHostname() {
        return (srcHostname);
    }

    @Override
    public void setSrcHostname(String srcHostname) {
        this.srcHostname = srcHostname;
    }



    @Override
    public String getSrcFilename() {
        return (srcFilename);
    }

    @Override
    public void setSrcFilename(String srcFilename) {
        this.srcFilename = srcFilename;
    }



    @Override
    public String getLocalPath() {
        return (localPath);
    }

    @Override
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }



    @Override
    public String getUrlPath() {
        return (urlPath);
    }

    @Override
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }


    
    @Override
    public Long getUploadTime() {
        return (uploadTime);
    }

    @Override
    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }



    @Override
    public Date getCreatedDate() {
        return (createdDate);
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }



    @Override
    public Date getDeletedDate() {
        return (deletedDate);
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }



    @Override
    public Date getUpdatedDate() {
        return (updatedDate);
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
