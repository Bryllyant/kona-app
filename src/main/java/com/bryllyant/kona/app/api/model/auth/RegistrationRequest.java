package com.bryllyant.kona.app.api.model.auth;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;

import javax.validation.constraints.NotNull;

public class RegistrationRequest extends KJsonModel {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
    public static class Metadata {
        private String platformName;
        private String platformVersion;
        private String appVersion;
        private String appBuild;
        private String promoCode;
        private String referredByUid;
        private String partnerUid;
        private String campaignUid;
        private Long signupTime;

        public String getPlatformName() {
            return platformName;
        }
        public String getPlatformVersion() {
            return platformVersion;
        }
        public String getAppVersion() {
            return appVersion;
        }
        public String getAppBuild() {
            return appBuild;
        }
        public Long getSignupTime() {
            return signupTime;
        }
        public String getPromoCode() {
            return promoCode;
        }
        public String getReferredByUid() {
            return referredByUid;
        }
        public String getPartnerUid() {
            return partnerUid;
        }
        public String getCampaignUid() {
            return campaignUid;
        }
        public void setPlatformName(String platformName) {
            this.platformName = platformName;
        }
        public void setPlatformVersion(String platformVersion) {
            this.platformVersion = platformVersion;
        }
        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }
        public void setAppBuild(String appBuild) {
            this.appBuild = appBuild;
        }
        public void setSignupTime(Long signupTime) {
            this.signupTime = signupTime;
        }
        public void setPromoCode(String promoCode) {
            this.promoCode = promoCode;
        }
        public void setReferredByUid(String referredByUid) {
            this.referredByUid = referredByUid;
        }
        public void setPartnerUid(String partnerUid) {
            this.partnerUid = partnerUid;
        }
        public void setCampaignUid(String campaignUid) {
            this.campaignUid = campaignUid;
        }
    }

    // ----------------------------------------------------------------------

    @NotNull
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;

    /**
     * Metadata associated with the registration.
     * 
     * <p>
     * Please see <<resources-kona-auth-registerUser,Metadata>> object.
     * </p>
     */
    @RestdocsNotExpanded
    private Metadata meta;
    
    @RestdocsNotExpanded
    private DeviceModel device;

    // ----------------------------------------------------------------------

    public RegistrationRequest() {
        this.meta = new Metadata();
    }

    // ----------------------------------------------------------------------

    public UserModel toUserModel() {
        UserModel model = new UserModel();

        model.setUsername(username);
        model.setFirstName(firstName);
        model.setLastName(lastName);
        model.setMobileNumber(mobileNumber);
        model.setEmail(email);

        return model;

    }

    // ----------------------------------------------------------------------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public Metadata getMeta() {
        return meta;
    }

    public DeviceModel getDevice() {
        return device;
    }

    // ----------------------------------------------------------------------
    public void setUsername(String username) {
        set("username", username);
    }

    public void setPassword(String password) {
        set("password", password);
    }

    public void setFirstName(String firstName) {
        set("firstName", firstName);
    }

    public void setLastName(String lastName) {
        set("lastName", lastName);
    }

    public void setMobileNumber(String mobileNumber) {
        set("mobileNumber", mobileNumber);
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public void setMeta(Metadata meta) {
        set("meta", meta);
    }

    public void setDevice(DeviceModel device) {
        set("device", device);
    }



    // ----------------------------------------------------------------------
}

