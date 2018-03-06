package com.pribas.m3.hotelservice.models;

import java.util.List;

public class Hotel {
    private String hotelCode;
    private List<HotelName> names;
    private HotelAddress address;

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public List<HotelName> getNames() {
        return names;
    }

    public void setNames(List<HotelName> names) {
        this.names = names;
    }

    public HotelAddress getAddress() {
        return address;
    }

    public void setAddress(HotelAddress address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelCode='" + hotelCode + '\'' +
                ", names=" + names +
                ", address=" + address +
                '}';
    }
}
