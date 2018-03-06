package com.pribas.m3.hotelservice.controllers;

import com.pribas.m3.hotelservice.models.Hotel;
import com.pribas.m3.hotelservice.models.ReturnData;
import com.pribas.m3.hotelservice.services.HotelServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HotelController extends HttpServlet{

    @Autowired
    private HotelServiceHandler hotelServiceHandler;



    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    public ReturnData getHotels(HttpServletRequest request, HttpServletResponse response){
        ReturnData returnData = hotelServiceHandler.getHotels(request, response);
        return returnData;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelCode}")
    public ReturnData getHotel(HttpServletRequest request, HttpServletResponse response, @PathVariable("hotelCode") String hotelCode){
        return hotelServiceHandler.getHotel(request, response, hotelCode);
    }

    @RequestMapping(method = RequestMethod.POST, value= "/hotels")
    public ReturnData newHotel(HttpServletRequest request, HttpServletResponse response, @RequestBody Hotel hotel){
        return hotelServiceHandler.newHotel(request, response, hotel);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelCode}")
    public ReturnData updateHotel(HttpServletRequest request, HttpServletResponse response, @RequestBody Hotel hotel, @PathVariable("hotelCode") String hotelCode){
        return hotelServiceHandler.updateHotel(request, response, hotelCode, hotel);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelCode}")
    public ReturnData deleteHotel(HttpServletRequest request, HttpServletResponse response, @PathVariable("hotelCode") String hotelCode){
        return hotelServiceHandler.deleteHotel(request, response, hotelCode);
    }

}
