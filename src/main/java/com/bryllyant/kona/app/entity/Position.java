package com.bryllyant.kona.app.entity;

import java.io.Serializable;
import java.util.Date;

public class Position extends BasePosition implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.uid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.user_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.device_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Long deviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.place_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Long placeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.dwell_time
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Long dwellTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.sample_no
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Long sampleNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.battery
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double battery;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.charging
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Boolean charging;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.network
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String network;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.source
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String source;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.background
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Boolean background;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.carrier
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String carrier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.latitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double latitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.longitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double longitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.indoor_floor
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Integer indoorFloor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.horizontal_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double horizontalAccuracy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.altitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double altitude;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.altitude_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double altitudeAccuracy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.heading
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double heading;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.speed
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Double speed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.position_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Date positionDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.captured_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Date capturedDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.ipv4
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String ipv4;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.ipv6
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String ipv6;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.wifi_ssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String wifiSsid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.wifi_bssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String wifiBssid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.user_agent
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String userAgent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.country
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.error_code
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String errorCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.error_message
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private String errorMessage;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.error_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Date errorDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.created_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Date createdDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__position.updated_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private Date updatedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__position
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.uid
     *
     * @return the value of kona__position.uid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.uid
     *
     * @param uid the value for kona__position.uid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.user_id
     *
     * @return the value of kona__position.user_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.user_id
     *
     * @param userId the value for kona__position.user_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.device_id
     *
     * @return the value of kona__position.device_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.device_id
     *
     * @param deviceId the value for kona__position.device_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.place_id
     *
     * @return the value of kona__position.place_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Long getPlaceId() {
        return placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.place_id
     *
     * @param placeId the value for kona__position.place_id
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.dwell_time
     *
     * @return the value of kona__position.dwell_time
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Long getDwellTime() {
        return dwellTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.dwell_time
     *
     * @param dwellTime the value for kona__position.dwell_time
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setDwellTime(Long dwellTime) {
        this.dwellTime = dwellTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.sample_no
     *
     * @return the value of kona__position.sample_no
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Long getSampleNo() {
        return sampleNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.sample_no
     *
     * @param sampleNo the value for kona__position.sample_no
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setSampleNo(Long sampleNo) {
        this.sampleNo = sampleNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.battery
     *
     * @return the value of kona__position.battery
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getBattery() {
        return battery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.battery
     *
     * @param battery the value for kona__position.battery
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setBattery(Double battery) {
        this.battery = battery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.charging
     *
     * @return the value of kona__position.charging
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Boolean getCharging() {
        return charging;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.charging
     *
     * @param charging the value for kona__position.charging
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setCharging(Boolean charging) {
        this.charging = charging;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.network
     *
     * @return the value of kona__position.network
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getNetwork() {
        return network;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.network
     *
     * @param network the value for kona__position.network
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setNetwork(String network) {
        this.network = network == null ? null : network.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.source
     *
     * @return the value of kona__position.source
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.source
     *
     * @param source the value for kona__position.source
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.background
     *
     * @return the value of kona__position.background
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Boolean getBackground() {
        return background;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.background
     *
     * @param background the value for kona__position.background
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setBackground(Boolean background) {
        this.background = background;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.carrier
     *
     * @return the value of kona__position.carrier
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.carrier
     *
     * @param carrier the value for kona__position.carrier
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier == null ? null : carrier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.latitude
     *
     * @return the value of kona__position.latitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.latitude
     *
     * @param latitude the value for kona__position.latitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.longitude
     *
     * @return the value of kona__position.longitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.longitude
     *
     * @param longitude the value for kona__position.longitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.indoor_floor
     *
     * @return the value of kona__position.indoor_floor
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Integer getIndoorFloor() {
        return indoorFloor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.indoor_floor
     *
     * @param indoorFloor the value for kona__position.indoor_floor
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setIndoorFloor(Integer indoorFloor) {
        this.indoorFloor = indoorFloor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.horizontal_accuracy
     *
     * @return the value of kona__position.horizontal_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.horizontal_accuracy
     *
     * @param horizontalAccuracy the value for kona__position.horizontal_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setHorizontalAccuracy(Double horizontalAccuracy) {
        this.horizontalAccuracy = horizontalAccuracy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.altitude
     *
     * @return the value of kona__position.altitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.altitude
     *
     * @param altitude the value for kona__position.altitude
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.altitude_accuracy
     *
     * @return the value of kona__position.altitude_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.altitude_accuracy
     *
     * @param altitudeAccuracy the value for kona__position.altitude_accuracy
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setAltitudeAccuracy(Double altitudeAccuracy) {
        this.altitudeAccuracy = altitudeAccuracy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.heading
     *
     * @return the value of kona__position.heading
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getHeading() {
        return heading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.heading
     *
     * @param heading the value for kona__position.heading
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setHeading(Double heading) {
        this.heading = heading;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.speed
     *
     * @return the value of kona__position.speed
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.speed
     *
     * @param speed the value for kona__position.speed
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.position_date
     *
     * @return the value of kona__position.position_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Date getPositionDate() {
        return positionDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.position_date
     *
     * @param positionDate the value for kona__position.position_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.captured_date
     *
     * @return the value of kona__position.captured_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Date getCapturedDate() {
        return capturedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.captured_date
     *
     * @param capturedDate the value for kona__position.captured_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setCapturedDate(Date capturedDate) {
        this.capturedDate = capturedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.ipv4
     *
     * @return the value of kona__position.ipv4
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getIpv4() {
        return ipv4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.ipv4
     *
     * @param ipv4 the value for kona__position.ipv4
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4 == null ? null : ipv4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.ipv6
     *
     * @return the value of kona__position.ipv6
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getIpv6() {
        return ipv6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.ipv6
     *
     * @param ipv6 the value for kona__position.ipv6
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setIpv6(String ipv6) {
        this.ipv6 = ipv6 == null ? null : ipv6.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.wifi_ssid
     *
     * @return the value of kona__position.wifi_ssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getWifiSsid() {
        return wifiSsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.wifi_ssid
     *
     * @param wifiSsid the value for kona__position.wifi_ssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setWifiSsid(String wifiSsid) {
        this.wifiSsid = wifiSsid == null ? null : wifiSsid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.wifi_bssid
     *
     * @return the value of kona__position.wifi_bssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getWifiBssid() {
        return wifiBssid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.wifi_bssid
     *
     * @param wifiBssid the value for kona__position.wifi_bssid
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setWifiBssid(String wifiBssid) {
        this.wifiBssid = wifiBssid == null ? null : wifiBssid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.user_agent
     *
     * @return the value of kona__position.user_agent
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.user_agent
     *
     * @param userAgent the value for kona__position.user_agent
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.country
     *
     * @return the value of kona__position.country
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.country
     *
     * @param country the value for kona__position.country
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.error_code
     *
     * @return the value of kona__position.error_code
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.error_code
     *
     * @param errorCode the value for kona__position.error_code
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.error_message
     *
     * @return the value of kona__position.error_message
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.error_message
     *
     * @param errorMessage the value for kona__position.error_message
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.error_date
     *
     * @return the value of kona__position.error_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Date getErrorDate() {
        return errorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.error_date
     *
     * @param errorDate the value for kona__position.error_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.created_date
     *
     * @return the value of kona__position.created_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.created_date
     *
     * @param createdDate the value for kona__position.created_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__position.updated_date
     *
     * @return the value of kona__position.updated_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__position.updated_date
     *
     * @param updatedDate the value for kona__position.updated_date
     *
     * @mbg.generated Wed Apr 11 12:22:58 EDT 2018
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}