package com.bryllyant.kona.app.model.geo;

import com.bryllyant.kona.data.model.KModel;

public interface Point extends KModel {

    Double getLatitude();
    Double getLongitude();

    static Point from(Double latitude, Double longitude) {
        return new Point() {
            @Override
            public Double getLatitude() {
                return latitude;
            }

            @Override
            public Double getLongitude() {
                return longitude;
            }
        };
    }
}

