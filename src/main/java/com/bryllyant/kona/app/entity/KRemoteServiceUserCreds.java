package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KRemoteServiceUserCreds extends KEntityObject {

    enum AuthType {
        OAUTH,
        OAUTH2,
        CREDENTIALS
    }

	Long getId();
	void setId(Long id);

	String getUid();
	void setUid(String uid);

	Long getAccountId();
	void setAccountId(Long accountId);

	Long getUserId();
	void setUserId(Long userId);

	Long getRemoteServiceId();
	void setRemoteServiceId(Long remoteServiceId);

	String getName();
	void setName(String name);

    String getSlug();
    void setSlug(String slug);

	String getRemoteServiceUserId();

	void setRemoteServiceUserId(String remoteServiceUserId);

	String getRemoteServiceScreenName();

	void setRemoteServiceScreenName(String remoteServiceScreenName);

    AuthType getAuthType();

	void setAuthType(AuthType authType);

	String getAccessToken();

	void setAccessToken(String accessToken);

	String getRefreshToken();

	void setRefreshToken(String refreshToken);

	String getTokenSecret();

	void setTokenSecret(String tokenSecret);

	Date getExpireDate();

	void setExpireDate(Date expireDate);

	Date getConnectedDate();

	void setConnectedDate(Date connectedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}
