package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KRemoteService extends KEntityObject {

	@Override
	Long getId();

	void setId(Long id);

	String getUid();

	void setUid(String uid);

	String getName();

	void setName(String name);

	String getSlug();

	void setSlug(String slug);

    String getAuthorizeUri();

    void setAuthorizeUri(String authorizeUri);

    String getTokenUri();

    void setTokenUri(String tokenUri);

    String getScope();

    void setScope(String scope);

    String getClientKey();

    void setClientKey(String clientKey);

    String getClientSecret();

    void setClientSecret(String clientSecret);

    String getRedirectUri();

    void setRedirectUri(String redirectUri);

    String getNamespace();

    void setNamespace(String namespace);

    String getRegion();

    void setRegion(String region);


    Date getCreatedDate();

	void setCreatedDate(Date createdDate);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

}