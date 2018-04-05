package com.bryllyant.kona.app.model;

import com.bryllyant.kona.app.entity.User;
import com.bryllyant.kona.data.model.KBaseModel;

import java.util.HashMap;
import java.util.Map;

public class SmsMessage extends KBaseModel {
    private User user;
    private String mobileNumber;
    private String messageSid;
    private boolean inbound;
    private String rawMessage;
    private Map<String,Object> data;
    private Map<String,String> mediaMap;
    private Integer mediaCount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMessageSid() {
        return messageSid;
    }

    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
    }

    public boolean isInbound() {
        return inbound;
    }

    public void setInbound(boolean inbound) {
        this.inbound = inbound;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    public void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

    public Map<String,String> getMediaMap() {
        return mediaMap;
    }

    public void setMediaMap(Map<String,String> mediaMap) {
        this.mediaMap = mediaMap;
    }

    public Integer getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
    }

    public Map<String,Object> getData() {
        return data;
    }

    public void setData(Map<String,Object> data) {
        this.data = data;
    }

    public void addDataItem(String key, Object value) {
        if (data == null) {
            data = new HashMap<String,Object>();
        }

        data.put(key, value);
    }
}
