package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseRemoteServiceUserCreds extends KBaseEntity implements KRemoteServiceUserCreds {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long accountId;
    private Long userId;
    private Long remoteServiceId;
    private String name;
    private String slug;
    private String remoteServiceUserId;
    private String remoteServiceScreenName;
    private AuthType authType;
    private String accessToken;
    private String refreshToken;
    private String tokenSecret;
    private Date expireDate;
    private Date connectedDate;
    private Date createdDate;
    private Date updatedDate;


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
    public Long getAccountId() {
        return accountId;
    }

    @Override
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
    public Long getRemoteServiceId() {
        return remoteServiceId;
    }

    @Override
    public void setRemoteServiceId(Long remoteServiceId) {
        this.remoteServiceId = remoteServiceId;
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
    public String getRemoteServiceUserId() {
        return remoteServiceUserId;
    }

    @Override
    public void setRemoteServiceUserId(String remoteServiceUserId) {
        this.remoteServiceUserId = remoteServiceUserId;
    }

    @Override
    public String getRemoteServiceScreenName() {
        return remoteServiceScreenName;
    }

    @Override
    public void setRemoteServiceScreenName(String remoteServiceScreenName) {
        this.remoteServiceScreenName = remoteServiceScreenName;
    }

    @Override
    public AuthType getAuthType() {
        return authType;
    }

    @Override
    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String getTokenSecret() {
        return tokenSecret;
    }

    @Override
    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    @Override
    public Date getExpireDate() {
        return expireDate;
    }

    @Override
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public Date getConnectedDate() {
        return connectedDate;
    }

    @Override
    public void setConnectedDate(Date connectedDate) {
        this.connectedDate = connectedDate;
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
