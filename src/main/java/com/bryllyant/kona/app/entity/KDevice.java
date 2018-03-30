package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KEntityObject;

import java.util.Date;
import java.util.List;

public interface KDevice extends KEntityObject {
    enum Type {
        PHONE,
        WATCH,
        TABLET,
        DESKTOP,
        WEARABLE,
        SENSOR,
        OTHER
    }

    enum Capability {
        BLE,
        RFID,
        WIFI,
        CELLULAR,
        GPS,
        CAMERA,
        AUDIO,
        MICROPHONE,
        ACCELEROMETER,
        GYROSCOPE
    }

    Long getId();
    void setId(Long id);

    String getUid();
    void setUid(String uid);

    Type getType();
    void setType(Type type);

    Long getParentId();
    void setParentId(Long parentId);

    String getAdvertiserId();
    void setAdvertiserId(String advertiserId);

    boolean isLimitAdTrackingEnabled();
    void setLimitAdTrackingEnabled(boolean limitAdTrackingEnabled);

    String getOsName();
    void setOsName(String osName);

    String getBleMacAddress();
    void setBleMacAddress(String bleMacAddress);

    String getLanMacAddress();
    void setLanMacAddress(String lanMacAddress);

    String getPnpId();
    void setPnpId(String pnpId);

    String getVendor();
    void setVendor(String vendor);

    String getManufacturer();
    void setManufacturer(String manufacturer);

    String getModel();
    void setModel(String model);

    String getSerialNo();
    void setSerialNo(String serialNo);

    String getDeviceUuid();
    void setDeviceUuid(String deviceUuid);

    String getHardwareVersion();
    void setHardwareVersion(String hardwareVersion);

    String getFirmwareVersion();
    void setFirmwareVersion(String firmwareVersion);

    List<Capability> getCapabilities();
    void setCapabilities(List<Capability> capabilities);

    String getOsVersion();
    void setOsVersion(String osVersion);

    boolean isEnabled();
    void setEnabled(boolean enabled);

    Date getDeletedDate();
    void setDeletedDate(Date deletedDate);

    Date getCreatedDate();
    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();
    void setUpdatedDate(Date updatedDate);

}