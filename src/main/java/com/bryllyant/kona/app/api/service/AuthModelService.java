package com.bryllyant.kona.app.api.service;

import com.bryllyant.kona.app.api.model.auth.AuthSessionModel;
import com.bryllyant.kona.app.api.model.auth.TokenModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.app.model.AuthSession;
import com.bryllyant.kona.app.service.TokenService;
import com.bryllyant.kona.rest.exception.BadRequestException;
import com.bryllyant.kona.rest.exception.NotFoundException;
import com.bryllyant.kona.util.KDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AuthModelService extends BaseModelService {
    private static final Logger logger = LoggerFactory.getLogger(AuthModelService.class);
    


    @Autowired
    private ApiAuthService apiAuthService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserModelService userModelService;
    


    public Token getToken(String accessToken) {
        Token token = tokenService.fetchByAccessToken(accessToken, false);

        if (token == null) {
            throw new NotFoundException("Token not found for access_token: " + accessToken);
        }

        return token;
    }
    


    public Token getToken(Long tokenId) {
        Token token = tokenService.fetchById(tokenId);

        if (token == null) {
            throw new NotFoundException("Token not found for id: " + tokenId);
        }

        return token;
    }
    


    public Token getToken(TokenModel model) {
        if (model == null) return null;

        String accessToken = model.getAccessToken();

        if (accessToken == null) {
            throw new NotFoundException("Token not found for model: " + model);
        }

        return getToken(accessToken);
    }
    
    


    public TokenModel toModel(Token token, String... includeKeys) {
        if (token == null) return null;

        Integer expiresIn = null;

        if (token.getAccessExpirationDate() != null) {
            expiresIn = KDateUtil.diffSecs(token.getAccessExpirationDate(), new Date());
        }
        
        String scope = token.getScope();
        if (scope != null) {
            scope = scope.replaceAll(",", " ");
        }

        TokenModel model = new TokenModel();

        model.setTokenType(token.getType());
        model.setAccessToken(token.getAccessToken());
        model.setRefreshToken(token.getRefreshToken());
        model.setScope(scope);
        model.setExpiresIn(expiresIn);
        
        if (includeKeys != null && includeKeys.length > 0) {
            model.includeKeys(includeKeys);
        }

        return model;
    }


    
    public final List<TokenModel> toTokenModelList(List<Token> tokens, String... includeKeys) {
        List<TokenModel> modelList = new ArrayList<TokenModel>();

        for (Token token : tokens) {
            modelList.add(toModel(token, includeKeys));
        }

        return modelList;
    }
    


    public AuthSessionModel toModel(User user, Token token) {
        if (user == null && token == null) return null;

        AuthSession session = new AuthSession();

        session.setUser(user);
        session.setToken(token);

        return toModel(session);
    } 
    


    public AuthSessionModel toModel(AuthSession authSession) {
        if (authSession == null) return null;

        AuthSessionModel model = new AuthSessionModel();
        
        UserModel userModel = userModelService.toModel(authSession.getUser());
        TokenModel tokenModel = toModel(authSession.getToken());
        
        model.setUser(userModel);
        model.setToken(tokenModel);
        
        return model;
    } 
    


    public AuthSession toEntity(AuthSessionModel model) {

        User user = new User();
        UserModel userModel = model.getUser();
        user = userModelService.mergeEntity(user, userModel, true, false);

        Token token = null;
        TokenModel tokenModel = model.getToken();

        if (tokenModel != null) {
            token = new Token();
            token = mergeTokenEntity(token, tokenModel);
        }

        
        AuthSession session = new AuthSession();
        session.setUser(user);
        session.setToken(token);
        
        return session;
    }



    public Token toTokenEntity(TokenModel model) {
        Token token = new Token();

        return mergeTokenEntity(token, model);
    }



    public Token mergeTokenEntity(Token token, TokenModel model) {
        logger.debug("toEntity called for model: " + model);
        
        for (String key : model.initializedKeys()) {

            switch (key) {
                case "tokenType":
                    token.setType(model.getTokenType());
                    break;

                case "accessToken":
                    token.setAccessToken(model.getAccessToken());
                    break;

                case "refreshToken":
                    token.setRefreshToken(model.getRefreshToken());
                    break;
                    
                case "scope":
                    String scope = model.getScope();
                    token.setScope(apiAuthService.toScopeString(scope));
                    break;

                case "expiresIn":
                    // cannot be set since expires_in changes depending on when the token request
                    // was received by the client.
                    // Integer expiresIn = model.getInteger(key);
                    break;

                default: 
                    throw new BadRequestException("Invalid property: " + key);
            }

        }

        return token;
    }
    

    
}
