/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.token;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.util.KDateUtil;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Encapsulates an OAuth 2.0 access token.
 */
public class AccessToken extends AbstractAuthenticationToken implements OAuth2AccessToken {

    private static Logger logger = LoggerFactory.getLogger(AccessToken.class);

    static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String tokenType = "bearer";
    private String accessToken;
    private String refreshToken;
    private Date accessExpirationDate; // seconds before access_token expires
    private Date refreshExpirationDate; // seconds before access_token expires
    private Set<String> scope;
    private Map<String,Object> info = new HashMap<String,Object>();

    /** The username */
    private UserDetails principal;

    public AccessToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);

        logger.debug("AccessToken: authorities: " + KJsonUtil.toJson(authorities));
    }

    public AccessToken(UserDetails principal, Collection<? extends GrantedAuthority> authorities, 
            String accessToken, String refreshToken, Date accessExpirationDate, Date refreshExpirationDate,
            String scope) {
        this(authorities);
        this.principal = principal;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpirationDate = accessExpirationDate;
        this.refreshExpirationDate = refreshExpirationDate;
        setScope(scope);
    }

    public AccessToken(String accessToken, String refreshToken, 
    		Date accessExpirationDate, Date refreshExpirationDate, 
    		String scope) {
        super(null);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpirationDate = accessExpirationDate;
        this.refreshExpirationDate = refreshExpirationDate;
        setScope(scope);
        super.setAuthenticated(false);
    }
    
    public AccessToken(String accessToken, String refreshToken, 
    		Date accessExpirationDate, Date refreshExpirationDate, 
    		Set<String> scope) {
        super(null);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpirationDate = accessExpirationDate;
        this.refreshExpirationDate = refreshExpirationDate;
        setScope(scope);
        super.setAuthenticated(false);
    }
    
    public AccessToken(String accessToken) {
        this(accessToken, null, null, null, (String) null);
    }

	@Override
    public Object getCredentials() {
        return null;
    }

	@Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }
    
	public void setTokenType(String tokenType) {
		switch(tokenType) {
		case "bearer":
			this.tokenType = tokenType;
			break;
		default:
			throw new IllegalArgumentException("Invalid tokenType: " + tokenType);
		}
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
    
    @Override
	public String getTokenType() {
		return tokenType;
	}
    

	@Override
	public Map<String, Object> getAdditionalInformation() {
        return info;
	}
    
	public void addInfo(String key, Object value) {
		info.put(key, value);
	}
    
    public void setAdditionalInformation(Map<String,Object> info) {
    	this.info = info;
    }
    
    /** Comma or space separated list. */
    public void setScope(String scope) {
    	 Set<String> scopeSet = new HashSet<String>();

         if (scope != null) {
             String[] scopes = scope.split("\\s*(,|\\s)\\s*");

             for (String s : scopes) {
                s = s.trim().toLowerCase();
             	scopeSet.add(s);
             }
         	
         } 

         this.scope = scopeSet;
    }

    public void setScope(Set<String> scope) {
    	this.scope = scope;
    }
    
	@Override
	public Set<String> getScope() {
        return scope;
	}

	@Override
	public OAuth2RefreshToken getRefreshToken() {
        return new RefreshToken(refreshToken, refreshExpirationDate);
	}
    
	@Override
	public String getValue() {
		return accessToken;
	}
    
	public String getRefreshValue() {
		return getRefreshToken().getValue();
	}

	@Override
	public boolean isExpired() {
        if (getExpiration() == null) return false;
        return getExpiration().getTime() < new Date().getTime();
	}
    
	public static boolean isExpired(Token token) {
        if (token.getAccessExpirationDate() == null) return false;
        return token.getAccessExpirationDate().getTime() < new Date().getTime();
	}
    
	public boolean isRefreshExpired() {
        if (getRefreshExpiration() == null) return false;
        return getRefreshExpiration().getTime() < new Date().getTime();
	}

	@Override
	public Date getExpiration() {
        return accessExpirationDate;
	}
    
	public Date getRefreshExpiration() {
        return refreshExpirationDate;
	}
    

	/**
	 * Return number of seconds token will expire.
	 */
	@Override
	public int getExpiresIn() {
        if (getExpiration() == null) return -1;
        return KDateUtil.diffSecs(getExpiration(), new Date());
	}
    
	public Integer getRefreshExpiresIn() {
        if (getRefreshExpiration() == null) return -1;
        return KDateUtil.diffSecs(getRefreshExpiration(), new Date());
	}
}
