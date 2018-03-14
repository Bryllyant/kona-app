package com.bryllyant.kona.app.api.model.geo.position;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.api.model.device.DeviceModel;
import com.bryllyant.kona.app.api.model.geo.place.PlaceModel;
import com.bryllyant.kona.app.api.model.user.UserModel;
import com.bryllyant.kona.app.entity.Position;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;

public class PositionModel extends KJsonModel implements KEntityModel {
    private static final long serialVersionUID = 1L;

    public enum NetworkType {
        WIFI,
        CELL,
        UNKNOWN;

        public static NetworkType from(String name) {
            if (name == null) return null;
            name = name.trim().toUpperCase();
            return NetworkType.valueOf(name);
        }
    }

    public enum SourceType {
        GPS,
        ACCESS_POINT,
        IP_ADDRESS,
        BEACON,
        MIXED,
        UNKNOWN;

        public static SourceType from(String name) {
            if (name == null) return null;
            name = name.trim().toUpperCase();
            return SourceType.valueOf(name);
        }
    }

    public static class Error extends KJsonModel {
        private String code;
        private String message;
        private Date errorDate;

        public static Error from(Position position) {
            Error model = new Error();
            model.setCode(position.getErrorCode());
            model.setMessage(position.getErrorMessage());
            model.setErrorDate(position.getErrorDate());
            return model;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.set("code", code);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.set("message", message);
        }

        public Date getErrorDate() {
            return errorDate;
        }

        public void setErrorDate(Date errorDate) {
            this.set("errorDate", errorDate);
        }
    }

    public static class Coordinates extends KJsonModel {
        private Double latitude;
        private Double longitude;
        private Double horizontalAccuracy;
        private Double altitude;
        private Double altitudeAccuracy;
        private Integer indoorFloor;
        private Double heading;
        private Double speed;
        private Long dwellTime;

        public static Coordinates from(Position position) {
            Coordinates model = new Coordinates();
            model.setLatitude(position.getLatitude());
            model.setLongitude(position.getLongitude());
            model.setHorizontalAccuracy(position.getHorizontalAccuracy());
            model.setAltitude(position.getAltitude());
            model.setAltitudeAccuracy(position.getAltitudeAccuracy());
            model.setIndoorFloor(position.getIndoorFloor());
            model.setHeading(position.getHeading());
            model.setSpeed(position.getSpeed());
            model.setDwellTime(position.getDwellTime());
            return model;
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

        public Double getHorizontalAccuracy() {
            return horizontalAccuracy;
        }

        public void setHorizontalAccuracy(Double horizontalAccuracy) {
            this.set("horizontalAccuracy", horizontalAccuracy);
        }

        public Double getAltitude() {
            return altitude;
        }

        public void setAltitude(Double altitude) {
            this.set("altitude", altitude);
        }

        public Double getAltitudeAccuracy() {
            return altitudeAccuracy;
        }

        public void setAltitudeAccuracy(Double altitudeAccuracy) {
            this.set("altitudeAccuracy", altitudeAccuracy);
        }

        public Integer getIndoorFloor() {
            return indoorFloor;
        }

        public void setIndoorFloor(Integer indoorFloor) {
            this.set("indoorFloor", indoorFloor);
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

        public Long getDwellTime() {
            return dwellTime;
        }

        public void setDwellTime(Long dwellTime) {
            this.set("dwellTime", dwellTime);
        }
    }

    public static class GeoDeviceModel extends DeviceModel {
        private String userAgent;
        private Double battery;
        private Boolean charging;
        private Boolean background;

        public static GeoDeviceModel from(DeviceModel deviceModel, Position position) {
            GeoDeviceModel model = new GeoDeviceModel();

            if (deviceModel != null) {
                model.fromBean(deviceModel);
            }

            model.setUserAgent(position.getUserAgent());
            model.setBattery(position.getBattery());
            model.setCharging(position.getCharging());
            model.setBackground(position.getBackground());
            return model;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.set("userAgent", userAgent);
        }

        public Double getBattery() {
            return battery;
        }

        public void setBattery(Double battery) {
            this.set("battery", battery);
        }

        public Boolean getCharging() {
            return charging;
        }

        public void setCharging(Boolean charging) {
            this.set("charging", charging);
        }

        public Boolean getBackground() {
            return background;
        }

        public void setBackground(Boolean background) {
            this.set("background", background);
        }
    }

    public static class GeoSource extends KJsonModel {
        private SourceType type;
        private NetworkType network;
        private String carrier;
        private String ipv4;
        private String ipv6;
        private String wifiSsid;
        private String wifiBssid;
        private String country;

        public static GeoSource from(Position position) {
            GeoSource model = new GeoSource();
            model.setType(SourceType.from(position.getSource()));
            model.setNetwork(NetworkType.from(position.getNetwork()));
            model.setCarrier(position.getCarrier());
            model.setIpv4(position.getIpv4());
            model.setIpv6(position.getIpv6());
            model.setWifiSsid(position.getWifiSsid());
            model.setWifiBssid(position.getWifiBssid());
            model.setCountry(position.getCountry());
            return model;
        }

        public SourceType getType() {
            return type;
        }

        public void setType(SourceType type) {
            this.set("type", type);
        }

        public NetworkType getNetwork() {
            return network;
        }

        public void setNetwork(NetworkType network) {
            this.set("network", network);
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.set("carrier", carrier);
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

        public String getWifiSsid() {
            return wifiSsid;
        }

        public void setWifiSsid(String wifiSsid) {
            this.set("wifiSsid", wifiSsid);
        }

        public String getWifiBssid() {
            return wifiBssid;
        }

        public void setWifiBssid(String wifiBssid) {
            this.set("wifiBssid", wifiBssid);
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.set("country", country);
        }
    }

    private String uid;

    @RestdocsNotExpanded
    private UserModel user;

    @RestdocsNotExpanded
    private GeoDeviceModel device;

    @RestdocsNotExpanded
    private Coordinates coordinates;

    @RestdocsNotExpanded
    private GeoSource source;

    @RestdocsNotExpanded
    private PlaceModel place;

    @RestdocsNotExpanded
    private Error error;


    private Date positionDate;
    private Date capturedDate;
    private Date createdDate;



    public static PositionModel from(Position position) {
        PositionModel model = new PositionModel();
        Coordinates coordinates = new Coordinates();
        coordinates.fromBean(position);
        model.setUid(position.getUid());
        model.setCoordinates(coordinates);
        model.setPositionDate(position.getPositionDate());
        return model;
    }

    public static PositionModel create(
            String uid,
            Double latitude,
            Double longitude,
            Double horizontalAccuracy,
            Date positionDate) {

        PositionModel model = new PositionModel();

        Coordinates coords = new Coordinates();
        coords.setLatitude(latitude);
        coords.setLongitude(longitude);
        coords.setLongitude(longitude);
        coords.setHorizontalAccuracy(horizontalAccuracy);

        model.setUid(uid);
        model.setCoordinates(coords);
        model.setPositionDate(positionDate);

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

    public GeoDeviceModel getDevice() {
        return device;
    }

    public void setDevice(GeoDeviceModel device) {
        this.set("device", device);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.set("coordinates", coordinates);
    }

    public GeoSource getSource() {
        return source;
    }

    public void setSource(GeoSource source) {
        this.set("source", source);
    }

    public PlaceModel getPlace() {
        return place;
    }

    public void setPlace(PlaceModel place) {
        this.set("place", place);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.set("error", error);
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}
