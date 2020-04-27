package com.sportapp.demo.dto;


public class LocationPostDto {

    private String userId;
    private String mainTypSportEntityId;
    private String city;
    private String country;
    private String department;
    private String street;
    private String streetNumber;
    private short hour;

    public LocationPostDto() {
    }

    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMainTypSportEntityId() {
        return mainTypSportEntityId;
    }

    public void setMainTypSportEntityId(String mainTypSportEntityId) {
        this.mainTypSportEntityId = mainTypSportEntityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
}
