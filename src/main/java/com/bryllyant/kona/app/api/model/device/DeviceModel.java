package com.bryllyant.kona.app.api.model.device;

import capital.scalable.restdocs.jackson.RestdocsNotExpanded;
import com.bryllyant.kona.app.entity.KDeviceType;
import com.bryllyant.kona.rest.model.KBaseModel;
import com.bryllyant.kona.rest.model.KEntityModel;

import java.util.Date;

public class DeviceModel extends KBaseModel implements KEntityModel {

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------

    private String uid;

    /**
     * Device type.
     */
    private KDeviceType type;

    @RestdocsNotExpanded
    private DeviceModel parent;

    private String advertiserId;
    private Boolean limitAdTrackingEnabled;
    private String os;
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
    private String capabilities;
    private Boolean enabled;
    private Date createdDate;

    // ----------------------------------------------------------------------

    public static DeviceModel create(String uid) {
        DeviceModel model = new DeviceModel();
        model.setUid(uid);
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

    public KDeviceType getType() {
        return type;
    }

    public void setType(KDeviceType type) {
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

    public Boolean getLimitAdTrackingEnabled() {
        return limitAdTrackingEnabled;
    }

    public void setLimitAdTrackingEnabled(Boolean limitAdTrackingEnabled) {
        this.set("limitAdTrackingEnabled", limitAdTrackingEnabled);
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.set("os", os);
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

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
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

