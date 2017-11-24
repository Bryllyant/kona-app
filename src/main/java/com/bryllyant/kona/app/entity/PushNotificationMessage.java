package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class PushNotificationMessage extends KBasePushNotificationMessage implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__push_notification_message.devices
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    private byte[] devices;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__push_notification_message
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__push_notification_message.devices
     *
     * @return the value of kona__push_notification_message.devices
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public byte[] getDevices() {
        return devices;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__push_notification_message.devices
     *
     * @param devices the value for kona__push_notification_message.devices
     *
     * @mbg.generated Fri Nov 24 12:43:45 EST 2017
     */
    public void setDevices(byte[] devices) {
        this.devices = devices;
    }
}