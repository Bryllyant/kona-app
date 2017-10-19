package com.bryllyant.kona.app.api.model.auth;

import com.bryllyant.kona.app.entity.KTokenType;
import com.bryllyant.kona.rest.model.KBaseModel;

import javax.validation.constraints.NotNull;


public class TokenModel extends KBaseModel {
    private static final long serialVersionUID = 1L;
    
    // ----------------------------------------------------------------------

    public enum TokenType {
        BASIC,
        BEARER;
        
        public KTokenType toKTokenType() {
            return KTokenType.getInstance(name());
        }
        
        public static TokenType value(KTokenType type) {
            if (type == null) return null;

            TokenType[] values = TokenType.class.getEnumConstants();

            for (TokenType value : values) {
                if (value.name().equalsIgnoreCase(type.name().trim())) {
                    return value;
                }
            }

            throw new IllegalArgumentException("ERROR: TokenType: type not found: " + type);
        }
    }

    // ----------------------------------------------------------------------

    @NotNull
    private TokenType tokenType;

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

    @NotNull
    private String scope;

    @NotNull
    private Integer expiresIn;

    // ----------------------------------------------------------------------

    public TokenType getTokenType() {
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

    // ----------------------------------------------------------------------

    public void setTokenType(TokenType tokenType) {
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
    
    // ----------------------------------------------------------------------
    
    
    

}
