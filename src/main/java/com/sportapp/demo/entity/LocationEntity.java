package com.sportapp.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String locationId;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String streetNumber;
    @Column
    private String demaprtment;
    @Column
    private String country;
    @Column
    private float lat;
    @Column
    private float lang;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<TimeTableEntity> timeTableEntities;

    @ManyToMany
    @JoinTable(
            name = "main_typ_sport_location",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "main_typ_sport_id"))
    List<MainTypSportEntity> mainTypSports;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getDemaprtment() {
        return demaprtment;
    }

    public void setDemaprtment(String demaprtment) {
        this.demaprtment = demaprtment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLang() {
        return lang;
    }

    public void setLang(float lang) {
        this.lang = lang;
    }

    public List<TimeTableEntity> getTimeTableEntities() {
        return timeTableEntities;
    }

    public void setTimeTableEntities(List<TimeTableEntity> timeTableEntities) {
        this.timeTableEntities = timeTableEntities;
    }

    public List<MainTypSportEntity> getMainTypSports() {
        return mainTypSports;
    }

    public void setMainTypSports(List<MainTypSportEntity> mainTypSports) {
        this.mainTypSports = mainTypSports;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
