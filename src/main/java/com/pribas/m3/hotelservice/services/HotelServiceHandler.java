package com.pribas.m3.hotelservice.services;

import com.pribas.m3.hotelservice.models.Hotel;
import com.pribas.m3.hotelservice.models.ReturnData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HotelServiceHandler {

     ReturnData getHotels(HttpServletRequest request, HttpServletResponse response);

     ReturnData getHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode);

     ReturnData newHotel(HttpServletRequest request, HttpServletResponse response, Hotel hotel);

     ReturnData updateHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode, Hotel hotel);

     ReturnData deleteHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode);

}
