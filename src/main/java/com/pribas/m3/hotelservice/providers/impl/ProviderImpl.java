package com.pribas.m3.hotelservice.providers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.pribas.m3.hotelservice.models.Hotel;
import com.pribas.m3.hotelservice.models.ReturnData;
import com.pribas.m3.hotelservice.providers.ProviderHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProviderImpl implements ProviderHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderImpl.class);
    private static List<Hotel> hotels;
    private static Hotel hotel;
    private static ReturnData returnData;
    private static ReturnData.User userInf;

    @Autowired
    MongoTemplate mongoTemplate;


    @Override
    public ReturnData getHotels(HttpServletRequest request, HttpServletResponse response) {
        hotels = new ArrayList<Hotel>();
        returnData = new ReturnData();
        returnData.setUserInf(userInformation(request, response));
        LOGGER.info(returnData.getUserInf().toString());
        LOGGER.debug("Hotels are listing.");
        try {
            hotels = mongoTemplate.findAll(Hotel.class, "hotels");
            returnData.setHotels(hotels);
            returnData.setStatusCode(1);
            return returnData;
        }catch (Exception ex){
            LOGGER.error("'providerHandler.getHotels' EXCEPTION!");
            ex.printStackTrace();
            returnData.setStatusCode(2);
            return returnData;
        }
    }

    @Override
    public ReturnData getHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode) {
        hotel = new Hotel();
        returnData = new ReturnData();
        returnData.setUserInf(userInformation(request, response));
        LOGGER.info(returnData.getUserInf().toString());
        LOGGER.debug("'" + hotelCode + "' is listing.");
        try{
            if(!checkHotelCode(hotelCode)){
                Query query = new Query(Criteria.where("hotelCode").is(hotelCode));
                hotel = mongoTemplate.findOne(query, Hotel.class, "hotels");
                returnData.setHotel(hotel);
                returnData.setStatusCode(1);

                return returnData;
            }else{
                returnData.setStatusCode(0);
                return returnData;
            }
        }catch (Exception ex){
            LOGGER.error("'providerHandler.getHotel(" + hotelCode + ")' EXCEPTION!");
            returnData.setStatusCode(2);
            return returnData;
        }
    }

    @Override
    public ReturnData newHotel(HttpServletRequest request, HttpServletResponse response, Hotel hotel) {
        returnData = new ReturnData();
        returnData.setUserInf(userInformation(request, response));
        LOGGER.info(returnData.getUserInf().toString());
        LOGGER.debug("'" + hotel.getHotelCode() + "' is adding.");
        try{
            if(checkHotelCode(hotel.getHotelCode())){
                String hotelString;
                hotelString = new ObjectMapper().writeValueAsString(hotel);
                DBObject hotelObj = (DBObject) JSON.parse(hotelString);
                mongoTemplate.getCollection("hotels").save(hotelObj);
                returnData.setStatusCode(1);
                returnData.setHotel(hotel);
                return returnData;
            }else{
                returnData.setStatusCode(0);
                return returnData;
            }
        }catch (Exception ex){
            LOGGER.error("'providerHandler.newHotel(" + hotel.toString() + ")' EXCEPTION!");
            ex.printStackTrace();
            returnData.setStatusCode(2);
            return returnData;
        }
    }

    @Override
    public ReturnData updateHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode, Hotel hotel) {
        returnData = new ReturnData();
        returnData.setUserInf(userInformation(request, response));
        LOGGER.info(returnData.getUserInf().toString());
        LOGGER.debug("'" + hotelCode + "' is updating.");
        try{
            if(!checkHotelCode(hotelCode) && !checkHotelCode(hotel.getHotelCode())){
                String hotelString = new ObjectMapper().writeValueAsString(hotel);
                DBObject hotelObj = (DBObject) JSON.parse(hotelString);
                mongoTemplate.getCollection("hotels").update((DBObject) JSON.parse
                        ("{'hotelCode' : '" + hotelCode + "'}"), hotelObj);
                returnData.setStatusCode(1);
                returnData.setHotel(hotel);
                return returnData;
            }else{
                returnData.setStatusCode(0);
                returnData.setHotel(hotel);
                return returnData;
            }
        }catch (Exception ex){
            LOGGER.error("'providerHandler.updateHotel(" + hotel.toString() + ")' EXCEPTION!");
            ex.printStackTrace();
            returnData.setStatusCode(2);
            return returnData;
        }
    }

    @Override
    public ReturnData deleteHotel(HttpServletRequest request, HttpServletResponse response, String hotelCode) {
        returnData = new ReturnData();
        returnData.setUserInf(userInformation(request, response));
        LOGGER.info(returnData.getUserInf().toString());
        LOGGER.debug("'" + hotelCode + "' is deleting.");
        try{
            if(!checkHotelCode(hotelCode)){
                Query query = new Query(Criteria.where("hotelCode").is(hotelCode));
                mongoTemplate.remove(query, "hotels");
                returnData = getHotels(request, response);
                return returnData;
            }else{
                returnData.setStatusCode(0);
                return returnData;
            }
        }catch (Exception ex){
            LOGGER.error("'providerHandler.deleteHotel(" + hotelCode + ")' EXCEPTION!");
            ex.printStackTrace();
            returnData.setStatusCode(2);
            return returnData;
        }
    }

    @Override
    public boolean checkHotelCode(String hotelCode) {
        Query query = new Query(Criteria.where("hotelCode").is(hotelCode));
        if (!mongoTemplate.find(query, Hotel.class, "hotels").isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ReturnData.User userInformation(HttpServletRequest request, HttpServletResponse response) {
        userInf = new ReturnData.User();
        userInf.setRequestURL(request.getRequestURL().toString());
        userInf.setRequestMethod(request.getMethod());
        userInf.setClientIP(request.getRemoteAddr());
        userInf.setResponseCode(response.getStatus());
        return userInf;
    }
}
