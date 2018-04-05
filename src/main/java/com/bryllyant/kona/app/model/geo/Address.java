package com.bryllyant.kona.app.model.geo;

public interface Address {
    String getStreet1();

    String getStreet2();

    String getCity();

    String getState();

    String getPostalCode();

    String getCountry();

    String getFormattedAddress();

    static Address from(String street1, String street2, String city, String state, String postalCode, String country, String formattedAddress) {
        return new Address() {
            @Override
            public String getStreet1() {
                return street1;
            }

            @Override
            public String getStreet2() {
                return street2;
            }

            @Override
            public String getCity() {
                return city;
            }

            @Override
            public String getState() {
                return state;
            }

            @Override
            public String getPostalCode() {
                return postalCode;
            }

            @Override
            public String getCountry() {
                return country;
            }

            @Override
            public String getFormattedAddress() {
                return formattedAddress;
            }
        };
    }
}
