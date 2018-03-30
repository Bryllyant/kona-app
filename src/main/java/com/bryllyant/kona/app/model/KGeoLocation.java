package com.bryllyant.kona.app.model;

public interface KGeoLocation extends KGeoPoint {

    /**
     * @return the address
     */
    String getAddress();

    /**
     * @param address the address to set
     */
    void setAddress(String address);
}
