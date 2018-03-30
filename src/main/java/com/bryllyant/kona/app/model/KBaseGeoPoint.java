package com.bryllyant.kona.app.model;

import com.bryllyant.kona.data.model.KBaseModel;

import java.io.Serializable;

public class KBaseGeoPoint extends KBaseModel implements Serializable, KGeoPoint {
    private static final long serialVersionUID = 1L;

    private Double latitude;
    private Double longitude;

    public KBaseGeoPoint() { }
	
    public KBaseGeoPoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#getLatitude()
     */
    @Override
    public Double getLatitude() {
        return latitude;
    }

    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#setLatitude(Double)
     */
    @Override
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#getLongitude()
     */
    @Override
    public Double getLongitude() {
        return longitude;
    }

    /* (non-Javadoc)
     * @see com.bryllyant.kona.app.entity.KGeoLocation#setLongitude(Double)
     */
    @Override
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}

