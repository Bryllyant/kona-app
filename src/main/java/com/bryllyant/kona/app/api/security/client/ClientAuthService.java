/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.client;

import com.bryllyant.kona.app.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.App;
import com.bryllyant.kona.app.entity.AppCreds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.bryllyant.kona.app.entity.KApp.Type.INTERNAL;
import static com.bryllyant.kona.app.entity.KApp.Type.PARTNER;


/**
 * ApiAuthService
 */
public class ClientAuthService implements UserDetailsService, ClientDetailsService {
	private static Logger logger = LoggerFactory.getLogger(ClientAuthService.class);

	@Autowired
	private ApiAuthService apiAuthService;
	


	// username provided is an application clientId
	@Override
	public UserDetails loadUserByUsername(String clientId) 
			throws UsernameNotFoundException, DataAccessException {
		
		org.springframework.security.core.userdetails.User details = null;

		logger.debug("loadUserByUsername called for app clientId: " + clientId);

		App app = apiAuthService.fetchAppByClientId(clientId);
		
		if (app == null) {
			throw new UsernameNotFoundException("App not found for clientId: " + clientId);
		}
        
		List<GrantedAuthority> authorities = getAuthorities(app);

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
		boolean enabled = app.isEnabled();
		boolean accountNonExpired = app.isEnabled();
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = app.isEnabled();

		details = new org.springframework.security.core.userdetails.User(
				clientId, "", enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked,
				authorities);


		return (details);
	}
	


	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {

		BaseClientDetails details = null;
		logger.debug("loadUserByUsername called for app clientId: " + clientId);

        App app = apiAuthService.fetchAppByClientId(clientId);
        
		if (app == null) {
			throw new ClientRegistrationException("App not found for clientId: " + clientId);
		}
        

		/*
		 	client-id="353b302c44574f565045687e534e7d6a"
            secret="286924697e615a672a646a493545646c"
            authorized-grant-types="password,refresh_token"
            authorities="ROLE_TEST"
            access-token-validity="${oauth.token.access.expiresInSeconds}"
            refresh-token-validity="${oauth.token.refresh.expiresInSeconds}"
            scope="read, write"
		 */
		List<String> grants = new ArrayList<>();
		grants.add("authorization_code");
		grants.add("refresh_token");
        
        // NOTE: Only INTERNAL & PARTNER clients are allowed to log a user in.
		if (app.getType() == INTERNAL || app.getType() == PARTNER) {
			grants.add("password");
		}
		
		AppCreds creds = apiAuthService.fetchAppCredsByClientId(clientId);

		Set<String> redirectUris = null;
        if (creds.getRedirectUri() != null) {
        	redirectUris = new HashSet<>();
        	redirectUris.add(creds.getRedirectUri());
        }
        
        List<String> scope = apiAuthService.toScopeList(creds.getScope());

		details = new BaseClientDetails();
		details.setClientId(creds.getClientId());
		details.setClientSecret(creds.getClientSecret());
		details.setAuthorizedGrantTypes(grants);
		details.setAuthorities(getAuthorities(app));
		details.setRegisteredRedirectUri(redirectUris);
		details.setAccessTokenValiditySeconds(creds.getAccessTokenTimeout());
		details.setRefreshTokenValiditySeconds(creds.getRefreshTokenTimeout());
        details.setScope(scope);

		return details;
	}



    public List<GrantedAuthority> getAuthorities(App app) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_APP"));

        switch (app.getType()) {
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
                throw new IllegalArgumentException("Invalid app type: " + app.getType());
        }
        return authorities;
    }
}
