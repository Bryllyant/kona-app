package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.SocialHandle;
import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;
import java.util.List;

public interface KSalesLead extends KEntityObject {


    @Override
    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getCampaignId();

    void setCampaignId(Long campaignId);

    Long getGroupId();

    void setGroupId(Long groupId);

    Long getChannelId();

    void setChannelId(Long channelId);

    Long getTargetId();

    void setTargetId(Long targetId);

    Long getAnalyticsId();
    void setAnalyticsId(Long analyticsId);

    boolean isCampaignConversion();
    void setCampaignConversion(boolean campaignConversion);

    Long getReferredById();
    void setReferredById(Long referredById);

    Long getUserId();
    void setUserId(Long userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getEmail();

    void setEmail(String email);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getMobileNumber();

    void setMobileNumber(String mobileNumber);

    List<SocialHandle> getSocialHandles();

    void setSocialHandles(List<SocialHandle> socialHandles);

    String getStreet1();

    void setStreet1(String street1);

    String getStreet2();

    void setStreet2(String street2);

    String getCity();

    void setCity(String city);

    String getState();

    void setState(String state);

    String getPostalCode();

    void setPostalCode(String postalCode);

    String getCountry();

    void setCountry(String country);

    String getTimeZone();

    void setTimeZone(String timeZone);

    Integer getUtcOffset();

    void setUtcOffset(Integer utcOffset);

    String getFormattedAddress();

    void setFormattedAddress(String formattedAddress);

    String getMessage();

    void setMessage(String message);

    String getInterests();

    void setInterests(String interests);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);
}

