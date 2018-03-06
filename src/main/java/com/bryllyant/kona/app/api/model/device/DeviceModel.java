package com.bryllyant.kona.app.api.model.device;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.entity.Device;
import com.bryllyant.kona.app.entity.KDevice;
import com.bryllyant.kona.data.model.KEntityModel;
import com.bryllyant.kona.data.model.KJsonModel;

import java.util.Date;
import java.util.List;

public class DeviceModel extends KJsonModel implements KEntityModel {

    private static final long serialVersionUID = 1L;

    public enum AdvertiserIdType {
        IDFA,
        AAID;

        public static AdvertiserIdType from(String name) {
            if (name == null) return null;

            name = name.trim().toUpperCase();

            return AdvertiserIdType.valueOf(name);
        }

        public static AdvertiserIdType from(Device device) {
            if (device == null || device.getOsName() == null) return null;

            String osName = device.getOsName().toLowerCase();

            switch(osName) {
                case "ios":
                    return IDFA;

                case "android":
                    return AAID;

                default:
                    throw new IllegalArgumentException("Invalid OS name: " + osName);

            }
        }
    }

    private String uid;

    private Device.Type type;

    @RestdocsNotExpanded
    private DeviceModel parent;

    private String advertiserId;
    private AdvertiserIdType advertiserIdType;
    private Boolean limitAdTrackingEnabled;
    private String osName;
    private String osVersion;
    private String bleMacAddress;
    private String lanMacAddress;
    private String pnpId;
    private String vendor;
    private String manufacturer;
    private String modelNo;
    private String serialNo;
    private String deviceUuid;
    private String hardwareVersion;
    private String firmwareVersion;
    private List<KDevice.Capability> capabilities;
    private Boolean enabled;
    private Date createdDate;


    public static DeviceModel create(String uid) {
        DeviceModel model = new DeviceModel();
        model.setUid(uid);
        return model;
    }

    public static DeviceModel from(Device device) {
        DeviceModel model = new DeviceModel();
        model.fromBean(device);
        model.setAdvertiserIdType(AdvertiserIdType.from(device));
        model.setType(device.getType());
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

    public Device.Type getType() {
        return type;
    }

    public void setType(Device.Type type) {
        this.set("type", type);
    }

    public DeviceModel getParent() {
        return parent;
    }

    public void setParent(DeviceModel parent) {
        this.set("parent", parent);
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.set("advertiserId", advertiserId);
    }

    public AdvertiserIdType getAdvertiserIdType() {
        return advertiserIdType;
    }

    public void setAdvertiserIdType(AdvertiserIdType advertiserIdType) {
        this.set("advertiserIdType", advertiserIdType);
    }

    public Boolean getLimitAdTrackingEnabled() {
        return limitAdTrackingEnabled;
    }

    public void setLimitAdTrackingEnabled(Boolean limitAdTrackingEnabled) {
        this.set("limitAdTrackingEnabled", limitAdTrackingEnabled);
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.set("osName", osName);
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.set("osVersion", osVersion);
    }

    public String getBleMacAddress() {
        return bleMacAddress;
    }

    public void setBleMacAddress(String bleMacAddress) {
        this.set("bleMacAddress", bleMacAddress);
    }

    public String getLanMacAddress() {
        return lanMacAddress;
    }

    public void setLanMacAddress(String lanMacAddress) {
        this.set("lanMacAddress", lanMacAddress);
    }

    public String getPnpId() {
        return pnpId;
    }

    public void setPnpId(String pnpId) {
        this.set("pnpId", pnpId);
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.set("vendor", vendor);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.set("manufacturer", manufacturer);
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.set("modelNo", modelNo);
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.set("serialNo", serialNo);
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.set("deviceUuid", deviceUuid);
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.set("hardwareVersion", hardwareVersion);
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.set("firmwareVersion", firmwareVersion);
    }

    public List<KDevice.Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<KDevice.Capability> capabilities) {
        this.set("capabilities", capabilities);
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.set("enabled", enabled);
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.set("createdDate", createdDate);
    }
}

