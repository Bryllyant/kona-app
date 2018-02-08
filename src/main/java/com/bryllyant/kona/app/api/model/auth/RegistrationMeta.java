package com.bryllyant.kona.app.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.app.AppVersionModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.sales.partner.PartnerModel;
import com.bryllyant.kona.app.api.model.geo.position.PositionModel;
import com.bryllyant.kona.app.api.model.sales.promo.PromoModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;

public class RegistrationMeta extends  KJsonModel {

    private static final long serialVersionUID = 1L;

    public static class Options extends KJsonModel {
        @RestdocsNotExpanded
        // internal use only
        // send verification email and text message
        // default: true
        private Boolean verifyUser;

        @RestdocsNotExpanded
        // internal use only
        // indicate user email is already verified.
        // if true and verify is true no verification will be sent
        // default: false
        private Boolean emailVerified;

        @RestdocsNotExpanded
        // internal use only
        // indicate user mobile number is already verified.
        // if true and verify is true no verification will be sent
        // default: false
        private Boolean mobileVerified;

        @RestdocsNotExpanded
        // internal use only
        // indicate the email or mobile number belong to an existing user
        // registration process will simply update the existing user account
        // default: false
        private Boolean updateCurrentUser;

        // indicate if login token should be generated after user is registered
        private Boolean login;

        // TBD
        private String scope;

        // External webhook to call after user is registered. Used by external API clients.
        private String webhookUrl;

        public Boolean getVerifyUser() {
            return verifyUser;
        }

        public void setVerifyUser(Boolean verifyUser) {
            this.set("verifyUser", verifyUser);
        }

        public Boolean getEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(Boolean emailVerified) {
            this.set("emailVerified", emailVerified);
        }

        public Boolean getMobileVerified() {
            return mobileVerified;
        }

        public void setMobileVerified(Boolean mobileVerified) {
            this.set("mobileVerified", mobileVerified);
        }

        public Boolean getUpdateCurrentUser() {
            return updateCurrentUser;
        }

        public void setUpdateCurrentUser(Boolean updateCurrentUser) {
            this.set("updateCurrentUser", updateCurrentUser);
        }

        public Boolean getLogin() {
            return login;
        }

        public void setLogin(Boolean login) {
            this.set("login", login);
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.set("scope", scope);
        }

        public String getWebhookUrl() {
            return webhookUrl;
        }

        public void setWebhookUrl(String webhookUrl) {
            this.set("webhookUrl", webhookUrl);
        }
    }

    @RestdocsNotExpanded
    private DeviceModel device;

    @RestdocsNotExpanded
    private PositionModel position;

    @RestdocsNotExpanded
    private AppVersionModel appVersion;

    @RestdocsNotExpanded
    private PromoModel promo;

    @RestdocsNotExpanded
    private UserModel referredBy;

    @RestdocsNotExpanded
    private PartnerModel partner;

    @RestdocsNotExpanded
    private CampaignModel campaign;

    @RestdocsNotExpanded
    private Options options;

    private Long signupTime;

    public RegistrationMeta() {

        options = new Options();

        // Set default options
        options.setLogin(false);
        options.setVerifyUser(true);
        options.setEmailVerified(false);
        options.setMobileVerified(false);
        options.setUpdateCurrentUser(false);
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

    public PromoModel getPromo() {
        return promo;
    }

    public void setPromo(PromoModel promo) {
        this.set("promo", promo);
    }

    public UserModel getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(UserModel referredBy) {
        this.set("referredBy", referredBy);
    }

    public PartnerModel getPartner() {
        return partner;
    }

    public void setPartner(PartnerModel partner) {
        this.set("partner", partner);
    }

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.set("campaign", campaign);
    }

    public Long getSignupTime() {
        return signupTime;
    }

    public void setSignupTime(Long signupTime) {
        this.set("signupTime", signupTime);
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.set("options", options);
    }
}

