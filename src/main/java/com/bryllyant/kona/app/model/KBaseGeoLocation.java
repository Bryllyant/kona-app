package com.bryllyant.kona.app.model;

import java.io.Serializable;

public class KBaseGeoLocation extends KBaseGeoPoint implements Serializable, KGeoLocation {
    private static final long serialVersionUID = 1L;

    private String address;
    
	
	/* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#getAddress()
     */
    @Override
    public String getAddress() {
        return address;
    }


    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#setAddress(java.lang.String)
     */
    @Override
    public void setAddress(String address) {
        this.address = address;
    }

}

