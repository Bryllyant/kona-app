package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KFile extends KEntityObject {
    enum Type {
        FOLDER,
        THUMBNAIL,
        IMAGE,
        AUDIO,
        VIDEO,
        DOCUMENT,
        ARCHIVE,
        EXECUTABLE,
        OTHER
    }

    enum Access {
        SYSTEM,
        OWNER,
        FRIEND,
        APP,
        PUBLIC,
        NONE
    }



    Long getId();
    void setId(Long id);
    
    String getUid();
    void setUid(String uid);

    Type getType();
    void setType(Type type);

    Access getAccess();
    void setAccess(Access access); // for ex. user, app, public

    Long getParentId(); //for multi-part docs such as email w/attachments
    void setParentId(Long parentId);

    Long getUserId();
    void setUserId(Long userId);

    Long getAccountId();
    void setAccountId(Long accountId);

    Long getTokenId();
    void setTokenId(Long tokenId);

	String getName();
	void setName(String name);

	String getContentType();
	void setContentType(String contentType);

	Long getSize();
	void setSize(Long bytes);

	byte[] getData();
	void setData(byte[] data);


    /**
     * Flag to indicate if this file should be hidden from the user's
     * normal view.  The flag is controlled by the user.
     */
    boolean isHidden();
    void setHidden(boolean hidden);

    /**
     * Flag to indicate if this file is currently enabled.
     * This flag is set by the system to control the user's  access
     * to the file.  For example, this flag may be set to false
     * if the file is pending some type of review.
     */
    boolean isEnabled();
    void setEnabled(boolean enabled);


    /**
     * Flag to indicate of this file is a temp file that can be 
     * automatically disposed when the system is shutdown.
     * 
     * Use case: create a public url for an image that needs to be uploaded 
     * by an external service.  Once the service has finished the transfer, 
     * the file can be removed.
     * @return
     */
    boolean isTempFile();
    void setTempFile(boolean tempFile);

	String getSrcHostname();
	void setSrcHostname(String srcHostName);

	String getSrcFilename();
	void setSrcFilename(String srcFilename);

    /**
     * Absolute filesystem path of where the file is stored locally.
     */
    String getLocalPath();
    void setLocalPath(String localPath);

    /**
     * URL path of the file relative to some absolute URL.
     * This value is stored in the database.
     */
    String getUrlPath();
    void setUrlPath(String publicPath);

    Long getUploadTime();
    void setUploadTime(Long uploadTime);

	Date getCreatedDate();
	void setCreatedDate(Date createdDate);

	Date getDeletedDate();
	void setDeletedDate(Date deletedDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);
}
