package com.bryllyant.kona.app.entity;

import java.io.Serializable;

public class Device extends KBaseDevice implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kona__device.capabilities
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private String capabilities;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kona__device
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kona__device.capabilities
     *
     * @return the value of kona__device.capabilities
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public String getCapabilities() {
        return capabilities;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kona__device.capabilities
     *
     * @param capabilities the value for kona__device.capabilities
     *
     * @mbg.generated Tue Oct 17 16:06:32 EDT 2017
     */
    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities == null ? null : capabilities.trim();
    }
}