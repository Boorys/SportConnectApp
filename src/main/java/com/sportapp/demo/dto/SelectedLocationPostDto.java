package com.sportapp.demo.dto;

import java.util.Date;

public class SelectedLocationPostDto {

    private String street;
    private String streetNumber;
    private String city;
    private String mainNameOfSport;
    private Date date;
    private String firstName;
    private String lastName;

    public SelectedLocationPostDto(String street, String streetNumber, String city, String mainNameOfSport, Date date, String firstName, String lastName) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.mainNameOfSport = mainNameOfSport;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainNameOfSport() {
        return mainNameOfSport;
    }

    public void setMainNameOfSport(String mainNameOfSport) {
        this.mainNameOfSport = mainNameOfSport;
    }
}
