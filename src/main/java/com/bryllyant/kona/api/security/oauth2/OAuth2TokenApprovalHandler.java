/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.api.security.oauth2;

import com.bryllyant.kona.api.security.token.AccessToken;
import com.bryllyant.kona.api.service.ApiAuthService;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.util.KJsonUtil;
import com.bryllyant.kona.util.KStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 */
public class OAuth2TokenApprovalHandler extends TokenStoreUserApprovalHandler {
	private static Logger logger = LoggerFactory.getLogger(OAuth2TokenApprovalHandler.class);
    
	private String approvalParameter = OAuth2Utils.USER_OAUTH_APPROVAL;
	private String scopeParameter = OAuth2Utils.SCOPE;
    
	@Autowired
    private ApiAuthService apiAuthService;

	//private TokenStore tokenStore;
	//private OAuth2RequestFactory requestFactory;
    
	private ClientDetailsService clientDetailsService;
    
	
	@Override
	public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		
		boolean approved = false;
		
		String clientId = authorizationRequest.getClientId();
		Set<String> scopes = authorizationRequest.getScope();
		
		if (clientDetailsService != null) {
			try {
				ClientDetails client = clientDetailsService.loadClientByClientId(clientId);
				approved = true;
				
				for (String scope : scopes) {
					if (!client.isAutoApprove(scope)) {
						approved = false;
					}
				}
				
				if (approved) {
					authorizationRequest.setApproved(true);
					return authorizationRequest;
				}
			} catch (ClientRegistrationException e) {
				logger.warn("Client registration problem prevent autoapproval check for client=" + clientId);
			}		
		}
        
        // see if we have a token for this user
		Token token = apiAuthService.fetchTokenByAccessToken(userAuthentication.getName(), false);
		
        if (token != null 
        		&& token.isApproved() 
        		&& !AccessToken.isExpired(token) 
        		&& isTokenValid(authorizationRequest, token)) {
            approved = true;
        } else {
			logger.debug("Checking explicit approval");
			approved = userAuthentication.isAuthenticated() && approved;
		}
		
		if (logger.isDebugEnabled()) {
			StringBuilder builder = new StringBuilder("Looking up existing token for ");
			builder.append("client_id=" + clientId);
			builder.append(", scope=" + KJsonUtil.toJson(scopes));
			builder.append(" and username=" + userAuthentication.getName());
			logger.debug(builder.toString());
		}

		authorizationRequest.setApproved(approved);

		return authorizationRequest;
	}
	
	// make sure this token matches the authorization request
	private boolean isTokenValid(AuthorizationRequest authorizationRequest, Token token) {
		String clientId = authorizationRequest.getClientId();
		Set<String> scopes = authorizationRequest.getScope();
		
		logger.debug("isTokenValid called\n--- token ---\n" + KJsonUtil.toJson(token)
				+ "\n--- clientId ---\n" + clientId
				+ "\n--- scopes ---\n" + KJsonUtil.toJson(scopes));
		
		if (clientId == null || token == null || token.getAppClientId() == null) {
			logger.debug("isTokenValid: clientId or token null; return false");
			return false;
		}
		
		// make sure clientId matches
		if (!token.getAppClientId().equals(clientId)) {
			logger.debug("isTokenValid: clientIds don't match; return false");
			return false;
		}
		
		
		if (scopes != null && scopes.size() > 0 && token.getScope() != null) {
            List<String> scopeList = apiAuthService.toScopeList(token.getScope());
			for (String s : scopeList) {
				s = s.trim().toLowerCase();
				if (!scopes.contains(s)) {
					logger.debug("isTokenValid: authorization scope and token scope don't match; return false");
					return false;
				}
			}
		}
		
        // The requested scope must match or be a sublist of the scopes authorized for the app
		if (scopes != null && scopes.size() > 0 && token.getScope() != null) {
            List<String> scopeList = apiAuthService.toScopeList(token.getScope());
			
			for (String s : scopes) {
				s = s.trim().toLowerCase();
				logger.debug("isTokenValid: checking requested scope: " + s);
				if (!scopeList.contains(s)) {
					logger.debug("isTokenValid: requested scope: " + s + "  not found in scopeList: " + KJsonUtil.toJson(scopeList));
					logger.debug("isTokenValid: authorization scope and creds scope don't match; return false");
					return false;
				}
			}
		}
		
		
		logger.debug("isTokenValid: token valid; return true");
		return true;
	}
	

	@Override
	public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		Map<String, String> approvalParameters = authorizationRequest.getApprovalParameters();
		
		String flag = approvalParameters.get(approvalParameter);
		
		//String scope = approvalParameters.get(scopeParameter);
		
		boolean approved = flag != null && flag.toLowerCase().equals("true");
		
		authorizationRequest.setApproved(approved);
		
		Set<String> scope = authorizationRequest.getScope();
		logger.debug("updateAfterApproval: scope: " + scope);
        
        boolean dirty = false;
        
		Token token = apiAuthService.fetchTokenByAccessToken(userAuthentication.getName(), false);
		
        if (token.isApproved() != approved) {
        	token.setApproved(approved);
            dirty = true;
        }
        
        if (scope != null) {
        	String s = KStringUtil.toCommaList(scope);
        	token.setScope(s);
            dirty = true;
        }

        if (dirty) {
            apiAuthService.updateToken(token);
        }

        
        logger.debug("oauth approval: token state: " + token);
        
		return authorizationRequest;
	}
}
