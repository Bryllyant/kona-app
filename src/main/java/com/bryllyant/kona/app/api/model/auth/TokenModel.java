package com.bryllyant.kona.app.api.model.auth;

import com.bryllyant.kona.app.entity.Token;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;


public class TokenModel extends KJsonModel {
    private static final long serialVersionUID = 1L;
    

    @NotNull
    private Token.Type tokenType;

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

    @NotNull
    private String scope;

    @NotNull
    private Integer expiresIn;



    public Token.Type getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }



    public void setTokenType(Token.Type tokenType) {
        set("tokenType", tokenType);
    }

    public void setAccessToken(String accessToken) {
        set("accessToken", accessToken);
    }

    public void setRefreshToken(String refreshToken) {
        set("refreshToken", refreshToken);
    }

    public void setScope(String scope) {
        set("scope", scope);
    }

    public void setExpiresIn(Integer expiresIn) {
        set("expiresIn", expiresIn);
    }
    

    
    
    

}
