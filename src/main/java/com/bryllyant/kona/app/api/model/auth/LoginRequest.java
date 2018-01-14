package com.bryllyant.kona.app.api.model.auth;

import com.bryllyant.kona.app.api.security.Credentials;
import com.bryllyant.kona.data.model.KJsonModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class LoginRequest extends KJsonModel implements Credentials {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String loginCode;
    private String appVersion;
    private String appBuild;

    @JsonIgnore
    private Boolean authRequest; // used by oauth2 login flow
    
    @JsonIgnore
    private Boolean mobileVerified;

    // ----------------------------------------------------------------------


    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.set("username", username);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.set("password", password);
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.set("loginCode", loginCode);
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.set("appVersion", appVersion);
    }

    public String getAppBuild() {
        return appBuild;
    }

    public void setAppBuild(String appBuild) {
        this.set("appBuild", appBuild);
    }

    public Boolean getAuthRequest() {
        return authRequest;
    }

    public void setAuthRequest(Boolean authRequest) {
        this.set("authRequest", authRequest);
    }

    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.set("mobileVerified", mobileVerified);
    }
}

