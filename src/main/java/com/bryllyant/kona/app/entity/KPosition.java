package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.app.model.KGeoPoint;
import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;

public interface KPosition extends KEntityObject, KGeoPoint {

    Long getId();

    void setId(Long id);

    String getUid();

    void setUid(String uid);

    Long getUserId();

    void setUserId(Long userId);

    Long getDeviceId();

    void setDeviceId(Long deviceId);

    Long getPlaceId();

    void setPlaceId(Long placeId);

    Long getDwellTime();

    void setDwellTime(Long dwellTime);

    Long getSampleNo();

    void setSampleNo(Long sampleNo);

    Boolean getCharging();

    void setCharging(Boolean charging);

    String getNetwork();

    void setNetwork(String network);

    Double getBattery();

    void setBattery(Double battery);

    String getSource();

    void setSource(String source);

    Boolean getBackground();

    void setBackground(Boolean background);

    String getCarrier();

    void setCarrier(String carrier);

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);

    Integer getIndoorFloor();

    void setIndoorFloor(Integer indoorFloor);

    Double getAltitude();

    void setAltitude(Double altitude);

    Double getHorizontalAccuracy();

    void setHorizontalAccuracy(Double horizontalAccuracy);

    Double getAltitudeAccuracy();

    void setAltitudeAccuracy(Double altitudeAccuracy);

    Double getHeading();

    void setHeading(Double heading);

    Double getSpeed();

    void setSpeed(Double speed);

    public Date getPositionDate();

    public void setPositionDate(Date positionDate);

    public Date getCapturedDate();

    public void setCapturedDate(Date capturedDate);

    String getIpv4();

    void setIpv4(String ipv4);

    String getIpv6();

    void setIpv6(String ipv6);

    String getWifiSsid();

    void setWifiSsid(String wifiSsid);

    String getWifiBssid();

    void setWifiBssid(String wifiBssid);

    String getUserAgent();

    void setUserAgent(String userAgent);

    String getCountry();

    void setCountry(String country);

    String getErrorCode();

    void setErrorCode(String errorCode);

    String getErrorMessage();

    void setErrorMessage(String errorMessage);

    Date getErrorDate();

    void setErrorDate(Date errorDate);

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

}
