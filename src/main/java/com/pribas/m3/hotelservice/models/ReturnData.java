package com.pribas.m3.hotelservice.models;

import com.mongodb.util.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class ReturnData {
    private List<Hotel> hotels;
    private Hotel hotel;
    private Integer statusCode;
    private String message;
    private User userInf;

    public static class User {
        private String RequestURL;
        private String RequestMethod;
        private String clientIP;
        private Integer responseCode;

        public String getRequestURL() {
            return RequestURL;
        }

        public void setRequestURL(String requestURL) {
            RequestURL = requestURL;
        }

        public String getRequestMethod() {
            return RequestMethod;
        }

        public void setRequestMethod(String requestMethod) {
            RequestMethod = requestMethod;
        }

        public String getClientIP() {
            return clientIP;
        }

        public void setClientIP(String clientIP) {
            this.clientIP = clientIP;
        }

        public Integer getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
        }
    }
    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUserInf() {
        return userInf;
    }

    public void setUserInf(User userInf) {
        this.userInf = userInf;
    }

    @Override
    public String toString() {
        return "ReturnData{" +
                "hotels=" + hotels +
                ", hotel=" + hotel +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", userInf=" + userInf +
                '}';
    }
}