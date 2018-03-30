package com.bryllyant.kona.app.entity;

import java.util.Date;

public class KBaseRemoteService extends KBaseEntity implements KRemoteService {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private String name;
    private String slug;
    private String authorizeUri;
    private String tokenUri;
    private String scope;
    private String clientKey;
    private String clientSecret;
    private String redirectUri;
    private String namespace;
    private String region;
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
    public String getAuthorizeUri() {
        return authorizeUri;
    }

    @Override
    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri;
    }

    @Override
    public String getTokenUri() {
        return tokenUri;
    }

    @Override
    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String getClientKey() {
        return clientKey;
    }

    @Override
    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Override
    public String getRedirectUri() {
        return redirectUri;
    }

    @Override
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public String getNamespace() {
        return namespace;
    }

    @Override
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String getRegion() {
        return region;
    }

    @Override
    public void setRegion(String region) {
        this.region = region;
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
