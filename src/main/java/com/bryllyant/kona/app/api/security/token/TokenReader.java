/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.api.security.token;

import javax.servlet.http.HttpServletRequest;

public interface TokenReader {

    /**
     * Reads a token (if any) from the request
     *
     * @param request the HTTP request
     * @param response the response, in case any status code has to be sent
     * @return the token when found, null otherwise
     */
    public AccessToken findToken(HttpServletRequest request);

}
