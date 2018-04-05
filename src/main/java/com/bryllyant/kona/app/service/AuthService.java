/*
 * Copyright (C) 2017 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.service;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.remote.service.KService;
import com.bryllyant.kona.remote.service.KServiceClient;

public interface AuthService extends KService {
	String SERVICE_PATH = "rpc/AuthService";

    Token login(String clientId, String username, String password) throws AuthException;

    Token login(String clientId, User user);

    Token login(Token token, KServiceClient client) throws AuthException;

    Token login(String accessToken, KServiceClient client) throws AuthException;

    void logout(User user);

    void logout(Token token);

    void logout(String accessToken);

    /** Return the token type issued when a user logs in.
     *  Assumes an external reference to TokenType object that includes references to BASIC, BEARER, etc. */
    Token.Type getLoginTokenType();



    /**
     * Create a token without logging the user in.
     *
     * This method does not check if the user already has an active token associated with this clientId.
     */
    Token createToken(User user, String clientId, String scope);

    Token createToken(User user, String clientId);
}
