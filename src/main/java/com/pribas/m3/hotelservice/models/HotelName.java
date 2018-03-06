package com.pribas.m3.hotelservice.models;

public class HotelName {
    private String locale;
    private String name;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HotelName{" +
                "locale='" + locale + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
