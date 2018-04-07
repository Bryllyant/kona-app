package com.bryllyant.kona.api.model.message;

import com.bryllyant.kona.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.EmailAddress;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class EmailAddressModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private UserModel user;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String gender;
    private Integer birthYear;
    private String company;
    private String title;
    private String extra;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String source;

    private Boolean scrubbed;
    private Boolean enabled;
    private Boolean confirmed;
    private Date optedInDate;
    private Date optedOutDate;
    private Date bouncedDate;
    private Date complainedDate;

    private Date createdDate;
    private Date updatedDate;


    public static EmailAddressModel from(EmailAddress address) {
        EmailAddressModel model = new EmailAddressModel();
        model.setUid(address.getUid());
        model.setEmail(address.getEmail());
        model.setMobileNumber(address.getMobileNumber());
        return model;
    }

    public static EmailAddressModel create(String uid) {
        EmailAddressModel model = new EmailAddressModel();
        model.setUid(uid);
        return model;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.set("firstName", firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.set("lastName", lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.set("email", email);
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.set("mobileNumber", mobileNumber);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.set("gender", gender);
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.set("birthYear", birthYear);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.set("company", company);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.set("title", title);
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.set("extra", extra);
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.set("street1", street1);
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.set("street2", street2);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.set("city", city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.set("state", state);
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.set("postalCode", postalCode);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.set("country", country);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.set("source", source);
    }

    public Boolean getScrubbed() {
        return scrubbed;
    }

    public void setScrubbed(Boolean scrubbed) {
        this.set("scrubbed", scrubbed);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.set("confirmed", confirmed);
    }

    public Date getOptedInDate() {
        return optedInDate;
    }

    public void setOptedInDate(Date optedInDate) {
        this.set("optedInDate", optedInDate);
    }

    public Date getOptedOutDate() {
        return optedOutDate;
    }

    public void setOptedOutDate(Date optedOutDate) {
        this.set("optedOutDate", optedOutDate);
    }

    public Date getBouncedDate() {
        return bouncedDate;
    }

    public void setBouncedDate(Date bouncedDate) {
        this.set("bouncedDate", bouncedDate);
    }

    public Date getComplainedDate() {
        return complainedDate;
    }

    public void setComplainedDate(Date complainedDate) {
        this.set("complainedDate", complainedDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.set("updatedDate", updatedDate);
    }
}
