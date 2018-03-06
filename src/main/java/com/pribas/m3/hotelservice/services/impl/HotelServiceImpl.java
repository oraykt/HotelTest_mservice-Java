package com.pribas.m3.hotelservice.services.impl;

import com.pribas.m3.hotelservice.models.Hotel;
import com.pribas.m3.hotelservice.models.ReturnData;
import com.pribas.m3.hotelservice.providers.ProviderHandler;
import com.pribas.m3.hotelservice.services.HotelServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class HotelServiceImpl implements HotelServiceHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);
    private ReturnData returnData;

    @Autowired
    ProviderHandler providerHandler;

    @Override
    public ReturnData getHotels(HttpServletRequest request, HttpServletResponse response) {
        returnData = new ReturnData();
        returnData = providerHandler.getHotels(request, response);
        if(returnData.getStatusCode() == 1){
            returnData.setMessage("All hotels were listed!");
            LOGGER.info(returnData.getMessage());
            return returnData;
        }
        if(returnData.getStatusCode() == 0){
            returnData.setMessage("No hotels are registered!");
            LOGGER.warn(returnData.getMessage());
            return returnData;
        }
        else{
            returnData.setMessage("Exception");
            LOGGER.error("'getHotels' " + returnData.getMessage());
            return returnData;
        }
    }

    @Override
    public ReturnData getHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode) {
        returnData = new ReturnData();
        returnData = providerHandler.getHotel(request, response, hotelCode);
        if(returnData.getStatusCode() == 1){
            returnData.setMessage(hotelCode + " listed!");
            LOGGER.info(returnData.getMessage());
            return returnData;
        }
        if(returnData.getStatusCode() == 0){
            returnData.setMessage(hotelCode + " did not found!");
            LOGGER.warn(returnData.getMessage());
            return returnData;
        }
        else{
            returnData.setMessage("Exception");
            LOGGER.error("'getHotel' " + returnData.getMessage());
            return returnData;
        }
    }

    @Override
    public ReturnData newHotel(HttpServletRequest request, HttpServletResponse response, Hotel hotel) {
        returnData = new ReturnData();
        returnData = providerHandler.newHotel(request, response, hotel);
        if(returnData.getStatusCode() == 1){
            returnData.setMessage(hotel.getHotelCode() + " added successfully!");
            LOGGER.info(returnData.getMessage());
            return returnData;
        }
        if(returnData.getStatusCode() == 0){
            returnData.setMessage(hotel.getHotelCode() + " already added!");
            LOGGER.warn(returnData.getMessage());
            return returnData;
        }
        else{
            returnData.setMessage("Exception");
            LOGGER.error("'newHotel' " + returnData.getMessage());
            return returnData;
        }
    }

    @Override
    public ReturnData updateHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode, Hotel hotel) {
        returnData = new ReturnData();
        returnData = providerHandler.updateHotel(request, response, hotelCode, hotel);
        if(returnData.getStatusCode() == 1){
            returnData.setMessage(hotelCode + " updated successfully!");
            LOGGER.info(returnData.getMessage());
            return returnData;
        }
        if(returnData.getStatusCode() == 0){
            returnData.setMessage(hotelCode + " did not found!");
            LOGGER.warn(returnData.getMessage());
            return returnData;
        }
        else{
            returnData.setMessage("Exception");
            LOGGER.error("'updateHotel' " + returnData.getMessage());
            return returnData;
        }
    }

    @Override
    public ReturnData deleteHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode) {
        returnData = new ReturnData();
        returnData = providerHandler.deleteHotel(request, response, hotelCode);
        if(returnData.getStatusCode() == 1){
            returnData.setMessage(hotelCode + " deleted successfully!");
            LOGGER.info(returnData.getMessage());
            return returnData;
        }
        if(returnData.getStatusCode() == 0){
            returnData.setMessage(hotelCode + " did not found!");
            LOGGER.warn(returnData.getMessage());
            return returnData;
        }
        else{
            returnData.setMessage("Exception");
            LOGGER.error("'deleteHotel' " + returnData.getMessage());
            return returnData;
        }
    }
}
