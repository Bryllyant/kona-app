/*
 * Copyright (C) 2013 Bryllyant, Inc.  All Rights Reserved.
 */
package com.bryllyant.kona.app.entity;

import java.util.Date;

/**
 * KBasePosition.
 */
public class KBasePosition extends KBaseEntity implements KPosition {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String uid;
    private Long userId;
    private Long deviceId;
    private Long placeId;
    private Long dwellTime;
    private Long sampleNo;
    private Double battery;
    private Boolean charging;
    private String network;
    private String source;
    private Boolean background;
    private String carrier;
    private Double latitude;
    private Double longitude;
    private Integer indoorFloor;
    private Double horizontalAccuracy;
    private Double altitude;
    private Double altitudeAccuracy;
    private Double heading;
    private Double speed;
    private Date positionDate;
    private Date capturedDate;
    private String ipv4;
    private String ipv6;
    private String wifiSsid;
    private String wifiBssid;
    private String userAgent;
    private String country;
    private String errorCode;
    private String errorMessage;
    private Date errorDate;
    private Date createdDate;
    private Date updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public Long getDeviceId() {
        return deviceId;
    }

    @Override
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public Long getPlaceId() {
        return placeId;
    }

    @Override
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @Override
    public Long getDwellTime() {
        return dwellTime;
    }

    @Override
    public void setDwellTime(Long dwellTime) {
        this.dwellTime = dwellTime;
    }

    @Override
    public Long getSampleNo() {
        return sampleNo;
    }

    @Override
    public void setSampleNo(Long sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Override
    public Double getBattery() {
        return battery;
    }

    @Override
    public void setBattery(Double battery) {
        this.battery = battery;
    }

    @Override
    public Boolean getCharging() {
        return charging;
    }

    @Override
    public void setCharging(Boolean charging) {
        this.charging = charging;
    }

    @Override
    public String getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Boolean getBackground() {
        return background;
    }

    @Override
    public void setBackground(Boolean background) {
        this.background = background;
    }

    @Override
    public String getCarrier() {
        return carrier;
    }

    @Override
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public Double getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public Double getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Integer getIndoorFloor() {
        return indoorFloor;
    }

    @Override
    public void setIndoorFloor(Integer indoorFloor) {
        this.indoorFloor = indoorFloor;
    }

    @Override
    public Double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    @Override
    public void setHorizontalAccuracy(Double horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }

    @Override
    public Double getAltitude() {
        return altitude;
    }

    @Override
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    @Override
    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    @Override
    public void setAltitudeAccuracy(Double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    @Override
    public Double getHeading() {
        return heading;
    }

    @Override
    public void setHeading(Double heading) {
        this.heading = heading;
    }

    @Override
    public Double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public Date getPositionDate() {
        return positionDate;
    }

    @Override
    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    @Override
    public Date getCapturedDate() {
        return capturedDate;
    }

    @Override
    public void setCapturedDate(Date capturedDate) {
        this.capturedDate = capturedDate;
    }

    @Override
    public String getIpv4() {
        return ipv4;
    }

    @Override
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    @Override
    public String getIpv6() {
        return ipv6;
    }

    @Override
    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6;
    }

    @Override
    public String getWifiSsid() {
        return wifiSsid;
    }

    @Override
    public void setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid;
    }

    @Override
    public String getWifiBssid() {
        return wifiBssid;
    }

    @Override
    public void setWifiBssid(String wifiBssid) {
        this.wifiBssid = wifiBssid;
    }

    @Override
    public String getUserAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Date getErrorDate() {
        return errorDate;
    }

    @Override
    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
