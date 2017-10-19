/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.user;

import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.KAppType;
import com.bryllyant.kona.app.entity.KUserRole;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.rest.exception.SystemException;
import com.bryllyant.kona.util.KJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * UserSpringAuthService
 */
public class UserSpringAuthService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(UserSpringAuthService.class);

    @Autowired
    private ApiAuthService apiAuthService;
    
    // ----------------------------------------------------------------------
    
    // username provided is an application accessToken
    @Override
    public UserDetails loadUserByUsername(String accessToken) 
            throws UsernameNotFoundException, DataAccessException {
        org.springframework.security.core.userdetails.User details = null;

        try {
            logger.debug("loadUserByUsername called for accessToken: " + accessToken);
            
            User user = null;
            
            Token token = apiAuthService.fetchTokenByAccessToken(accessToken);
            
            if (token != null && token.getUserId() != null) {
            	user = apiAuthService.fetchUserById(token.getUserId());
            }
            
            if (user == null) {
                throw new UsernameNotFoundException("User not found for Access Token: " + accessToken);
            }
            
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            Set<KUserRole> roles = KUserRole.parse(user.getRoles());

            for (KUserRole role : roles) {
            	authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
            }
            
            List<GrantedAuthority> appAuthorities = getAppAuthorities(token);

            if (appAuthorities != null) {
            	authorities.addAll(appAuthorities);
            }

            /* 
             * details = new User(
             *              String username, 
             *              String password, 
             *              boolean enabled, 
             *              boolean accountNonExpired, 
             *              boolean credentialsNonExpired, 
             *              boolean accountNonLocked, 
             *              Collection<? extends GrantedAuthority> authorities
             *          )
             */
            boolean enabled = user.isEnabled();
            boolean accountNonExpired = user.isEnabled();
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = user.isActive();

            details = new org.springframework.security.core.userdetails.User(
                        accessToken, "", enabled, accountNonExpired, 
                        credentialsNonExpired, accountNonLocked,
                        authorities);
            
            logger.debug("Authorities for accessToken [" + accessToken + "]: " + KJsonUtil.toJson(authorities));
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SystemException ("Error loading user for Access Token: " + accessToken, e);
        }

        logger.debug("loadUserByUsername: details: " + KJsonUtil.toJson(details));

        return details;
    }
    
    // ----------------------------------------------------------------------
    
    private List<GrantedAuthority> getAppAuthorities(Token token) {
        if (token == null || token.getAppClientId() == null) return null;
        
    	App app = apiAuthService.fetchAppByClientId(token.getAppClientId());

    	if (app == null)  return null;

    	List<GrantedAuthority> authorities = 
    			new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_APP"));

    	KAppType type = KAppType.getInstance(app.getTypeId());
    	switch (type) {
    	case INTERNAL:
    		authorities.add(new SimpleGrantedAuthority("ROLE_APP_INTERNAL"));
    		break;
    	case PARTNER:
    		authorities.add(new SimpleGrantedAuthority("ROLE_APP_PARTNER"));
    		break;
    	case PUBLIC:
    		authorities.add(new SimpleGrantedAuthority("ROLE_APP_PUBLIC"));
    		break;
    	default:
    		throw new IllegalArgumentException("Invalid app type: " + type);
    	}

    	return authorities;
    }
}
