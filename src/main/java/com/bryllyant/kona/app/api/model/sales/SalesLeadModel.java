package com.bryllyant.kona.app.api.model.sales;

import com.bryllyant.kona.app.api.model.sales.campaign.CampaignAnalyticsModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignChannelModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignGroupModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignModel;
import com.bryllyant.kona.app.api.model.sales.campaign.CampaignTargetModel;
import com.bryllyant.kona.app.api.model.user.PersonModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.SalesLead;
import com.bryllyant.kona.data.model.KEntityModel;

import java.util.Date;

public class SalesLeadModel extends PersonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    private String uid;
    private CampaignModel campaign;
    private CampaignGroupModel group;
    private CampaignChannelModel channel;
    private CampaignTargetModel target;
    private CampaignAnalyticsModel analytics;
    private Boolean campaignConversion;
    private UserModel referredBy;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Integer utcOffset;
    private String formattedAddress;
    private String message;
    private String interests;
    private Date createdDate;
    private Date updatedDate;

    public static SalesLeadModel from(SalesLead salesLead) {
        SalesLeadModel model = new SalesLeadModel();
        model.setUid(salesLead.getUid());
        model.setFirstName(salesLead.getFirstName());
        model.setLastName(salesLead.getLastName());
        model.setEmail(salesLead.getEmail());
        model.setMobileNumber(salesLead.getMobileNumber());
        return model;
    }

    public static SalesLeadModel create(String uid) {
        SalesLeadModel model = new SalesLeadModel();
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

    public CampaignModel getCampaign() {
        return campaign;
    }

    public void setCampaign(CampaignModel campaign) {
        this.set("campaign", campaign);
    }

    public CampaignGroupModel getGroup() {
        return group;
    }

    public void setGroup(CampaignGroupModel group) {
        this.set("group", group);
    }

    public CampaignChannelModel getChannel() {
        return channel;
    }

    public void setChannel(CampaignChannelModel channel) {
        this.set("channel", channel);
    }

    public CampaignTargetModel getTarget() {
        return target;
    }

    public void setTarget(CampaignTargetModel target) {
        this.set("target", target);
    }

    public CampaignAnalyticsModel getAnalytics() {
        return analytics;
    }

    public void setAnalytics(CampaignAnalyticsModel analytics) {
        this.set("analytics", analytics);
    }

    public Boolean getCampaignConversion() {
        return campaignConversion;
    }

    public void setCampaignConversion(Boolean campaignConversion) {
        this.set("campaignConversion", campaignConversion);
    }

    public UserModel getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(UserModel referredBy) {
        this.set("referredBy", referredBy);
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



    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.set("utcOffset", utcOffset);
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.set("formattedAddress", formattedAddress);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.set("message", message);
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.set("interests", interests);
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
