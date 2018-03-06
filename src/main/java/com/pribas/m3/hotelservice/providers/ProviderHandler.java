package com.pribas.m3.hotelservice.providers;

import com.pribas.m3.hotelservice.models.Hotel;
import com.pribas.m3.hotelservice.models.ReturnData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProviderHandler {

    public ReturnData getHotels(HttpServletRequest request, HttpServletResponse response);

    public ReturnData getHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode);

    public ReturnData newHotel(HttpServletRequest request, HttpServletResponse response, Hotel hotel);

    public ReturnData updateHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode, Hotel hotel);

    public ReturnData deleteHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode);

    public ReturnData.User userInformation (HttpServletRequest request, HttpServletResponse response);

    public boolean checkHotelCode(String hotelCode);

}
