package com.bryllyant.kona.app.model;

import com.bryllyant.kona.data.model.KBaseModel;

public class EmailFooter extends KBaseModel {
    private static final long serialVersionUID = 1L;

    public enum Type {
        PROMOTIONAL,
        TRANSACTIONAL
    }

    private Type type;
    private Integer copyrightYear;
    private String copyrightHolder;
    private String companyName;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String permissionReminder;
    private String unsubscribeUrl;

    public EmailFooter() {
    }

    public EmailFooter(
            Type type, 
            String copyrightHolder, 
            Integer copyrightYear, 
            String companyName, 
            String street1, 
            String street2, 
            String city, 
            String state, 
            String postalCode, 
            String country
            ) {
        this.copyrightHolder = copyrightHolder;
        this.copyrightYear = copyrightYear;
        this.companyName = companyName;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCopyrightHolder() {
        return copyrightHolder;
    }

    public void setCopyrightHolder(String copyrightHolder) {
        this.copyrightHolder = copyrightHolder;
    }

    public Integer getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(Integer copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPermissionReminder() {
        return permissionReminder;
    }

    public void setPermissionReminder(String permissionReminder) {
        this.permissionReminder = permissionReminder;
    }

    public String getUnsubscribeUrl() {
        return unsubscribeUrl;
    }

    public void setUnsubscribeUrl(String unsubscribeUrl) {
        this.unsubscribeUrl = unsubscribeUrl;
    }


}
