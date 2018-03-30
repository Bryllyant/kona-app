package com.bryllyant.kona.app.entity;

import java.util.Date;
import java.util.List;


public class KBaseDevice extends KBaseEntity implements KDevice {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Type type;
    private Long parentId;
    private String uid;
    private String advertiserId;
    private boolean limitAdTrackingEnabled;
    private String osName;
    private String osVersion;
    private String bleMacAddress;
    private String lanMacAddress;
    private String pnpId;
    private String vendor;
    private String manufacturer;
    private String model;
    private String serialNo;
    private String deviceUuid;
    private String hardwareVersion;
    private String firmwareVersion;
    private List<Capability> capabilities;
    private boolean enabled;
    private Date deletedDate;
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
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    public String getAdvertiserId() {
        return advertiserId;
    }

    @Override
    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    @Override
    public boolean isLimitAdTrackingEnabled() {
        return limitAdTrackingEnabled;
    }

    @Override
    public void setLimitAdTrackingEnabled(boolean limitAdTrackingEnabled) {
        this.limitAdTrackingEnabled = limitAdTrackingEnabled;
    }

    @Override
    public String getOsName() {
        return osName;
    }

    @Override
    public void setOsName(String osName) {
        this.osName = osName;
    }

    @Override
    public String getOsVersion() {
        return osVersion;
    }

    @Override
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    @Override
    public String getBleMacAddress() {
        return bleMacAddress;
    }

    @Override
    public void setBleMacAddress(String bleMacAddress) {
        this.bleMacAddress = bleMacAddress;
    }

    @Override
    public String getLanMacAddress() {
        return lanMacAddress;
    }

    @Override
    public void setLanMacAddress(String lanMacAddress) {
        this.lanMacAddress = lanMacAddress;
    }

    @Override
    public String getPnpId() {
        return pnpId;
    }

    @Override
    public void setPnpId(String pnpId) {
        this.pnpId = pnpId;
    }

    @Override
    public String getVendor() {
        return vendor;
    }

    @Override
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String getSerialNo() {
        return serialNo;
    }

    @Override
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String getDeviceUuid() {
        return deviceUuid;
    }

    @Override
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    @Override
    public String getHardwareVersion() {
        return hardwareVersion;
    }

    @Override
    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    @Override
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    @Override
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    @Override
    public List<Capability> getCapabilities() {
        return capabilities;
    }

    @Override
    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Date getDeletedDate() {
        return deletedDate;
    }

    @Override
    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
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
