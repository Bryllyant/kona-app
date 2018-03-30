package com.bryllyant.kona.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.api.model.app.AppVersionModel;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.geo.position.PositionModel;
import com.bryllyant.kona.api.security.Credentials;
import com.bryllyant.kona.api.model.app.AppVersionModel;
import com.bryllyant.kona.api.model.device.DeviceModel;
import com.bryllyant.kona.api.model.geo.position.PositionModel;
import com.bryllyant.kona.api.security.Credentials;
import com.bryllyant.kona.data.model.KJsonModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;

public class LoginRequest extends KJsonModel implements Credentials {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String username;

    private String password;

    // used in login flows where password may be set
    // after user logs in with an email or mobile number
    private String loginCode;

    @RestdocsNotExpanded
    private DeviceModel device;

    @RestdocsNotExpanded
    private PositionModel position;

    @RestdocsNotExpanded
    private AppVersionModel appVersion;


    @JsonIgnore
    private Boolean authRequest; // used by oauth2 login flow
    
    @JsonIgnore
    private Boolean mobileVerified;

    @JsonIgnore
    private Boolean emailVerified;


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

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.set("device", device);
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.set("position", position);
    }

    public AppVersionModel getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersionModel appVersion) {
        this.set("appVersion", appVersion);
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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.set("emailVerified", emailVerified);
    }
}

