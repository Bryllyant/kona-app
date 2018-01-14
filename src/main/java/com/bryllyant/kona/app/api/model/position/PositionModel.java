package com.bryllyant.kona.app.api.model.position;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.app.AppModel;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.place.PlaceModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.data.model.KJsonModel;
import com.bryllyant.kona.data.model.KEntityModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PositionModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String uid;

    @NotNull
    @RestdocsNotExpanded
    private AppModel app;

    @NotNull
    @RestdocsNotExpanded
    private UserModel user;

    @NotNull
    @RestdocsNotExpanded
    private DeviceModel device;

    @NotNull
    @RestdocsNotExpanded
    private PlaceModel place;

    private Long dwellTime;

    private String network;
    private String battery;
    private Boolean background;
    private String source; // gps|access-point|ip-address|beacon|mixed
    private Long sampleNo;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Double altitude;

    private Integer indoorFloor;

    private String indoorDetail;

    @NotNull
    private Double horizontalAccuracy;

    private Double altitudeAccuracy;

    private Double heading;
    private Double speed;

    @NotNull
    private Date positionDate;

    private Date capturedDate;

    private String ipv4;
    private String ipv6;
    private String userAgent;
    private String country;

    private String errorCode;
    private String errorMessage;
    private String errorDate;

    private Date createdDate;

    // ----------------------------------------------------------------------

    public static PositionModel create(
            String uid,
            Double latitude,
            Double longitude,
            Integer indoorFloor,
            Double horizontalAccuracy,
            Date positionDate) {

        PositionModel model = new PositionModel();

        model.setUid(uid);
        model.setLatitude(latitude);
        model.setLongitude(longitude);
        model.setIndoorFloor(indoorFloor);
        model.setHorizontalAccuracy(horizontalAccuracy);
        model.setPositionDate(positionDate);

        return model;
    }

    // ----------------------------------------------------------------------

    @Override
    public String getUid() {
        return uid;
    }

    @Override
    public void setUid(String uid) {
        this.set("uid", uid);
    }

    public AppModel getApp() {
        return app;
    }

    public void setApp(AppModel app) {
        this.set("app", app);
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.set("user", user);
    }

    public DeviceModel getDevice() {
        return device;
    }

    public void setDevice(DeviceModel device) {
        this.set("device", device);
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.set("place", place);
    }

    public Long getDwellTime() {
        return dwellTime;
    }

    public void setDwellTime(Long dwellTime) {
        this.set("dwellTime", dwellTime);
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.set("network", network);
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.set("battery", battery);
    }

    public Boolean getBackground() {
        return background;
    }

    public void setBackground(Boolean background) {
        this.set("background", background);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.set("source", source);
    }

    public Long getSampleNo() {
        return sampleNo;
    }

    public void setSampleNo(Long sampleNo) {
        this.set("sampleNo", sampleNo);
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.set("latitude", latitude);
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.set("longitude", longitude);
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.set("altitude", altitude);
    }

    public Integer getIndoorFloor() {
        return indoorFloor;
    }

    public void setIndoorFloor(Integer indoorFloor) {
        this.set("indoorFloor", indoorFloor);
    }

    public String getIndoorDetail() {
        return indoorDetail;
    }

    public void setIndoorDetail(String indoorDetail) {
        this.set("indoorDetail", indoorDetail);
    }

    public Double getHorizontalAccuracy() {
        return horizontalAccuracy;
    }

    public void setHorizontalAccuracy(Double horizontalAccuracy) {
        this.set("horizontalAccuracy", horizontalAccuracy);
    }

    public Double getAltitudeAccuracy() {
        return altitudeAccuracy;
    }

    public void setAltitudeAccuracy(Double altitudeAccuracy) {
        this.set("altitudeAccuracy", altitudeAccuracy);
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.set("heading", heading);
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.set("speed", speed);
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.set("positionDate", positionDate);
    }

    public Date getCapturedDate() {
        return capturedDate;
    }

    public void setCapturedDate(Date capturedDate) {
        this.set("capturedDate", capturedDate);
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.set("ipv4", ipv4);
    }

    public String getIpv6() {
        return ipv6;
    }

    public void setIpv6(String ipv6) {
        this.set("ipv6", ipv6);
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.set("userAgent", userAgent);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.set("country", country);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.set("errorCode", errorCode);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.set("errorMessage", errorMessage);
    }

    public String getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(String errorDate) {
        this.set("errorDate", errorDate);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }

    // ----------------------------------------------------------------------






}
