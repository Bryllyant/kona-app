/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security;

import com.bryllyant.kona.app.api.security.token.AccessToken;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A {@link SecurityContextRepository} implementation that stores the security
 * context in EHCACHE between requests.
 */
public class EhcacheSecurityContextRepository implements SecurityContextRepository {
	private static CacheManager CACHE_MANAGER = CacheManager.getInstance();

	/**
	 * Gets a cache for storing {@link SecurityContext}s.
	 *
	 * @return A {@link Cache}.
	 */
	private Cache getCache() {
		return CACHE_MANAGER.getCache("SSSC");
	}

	/**
	 * Gets the Spring Security authentication token from an
	 * {@link HttpServletRequest}.
	 *
	 * @param request
	 *            The {@link HttpServletRequest} from which to extract the
	 *            token.
	 * @return The Spring Security authentication token from the request.
	 */
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("X-API-TOKEN");
		return token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsContext(HttpServletRequest req) {
		return (getCache().get(this.getToken(req)) != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SecurityContext loadContext(HttpRequestResponseHolder holder) {
		SecurityContext context = null;
		if (containsContext(holder.getRequest())) {
			context = (SecurityContext) getCache().get(this.getToken(holder.getRequest())).getObjectValue();
		} else {
			context = SecurityContextHolder.createEmptyContext();
		}
		return context;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

		Authentication authentication = context.getAuthentication();

		if (authentication != null && (authentication instanceof AccessToken)) {
			String token = (String) authentication.getDetails();
			if (token != null) {
				this.getCache().put(new Element(token, context));
			}
		}
	}

}
