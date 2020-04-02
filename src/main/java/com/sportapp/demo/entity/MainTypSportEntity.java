package com.sportapp.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MainTypSportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String MainTypSportEntityId;

    @Column
    private String mainNameOfSport;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainTypSport")
    private List<PreciseDisciplineEntity> mainTypSports;

    @ManyToMany(mappedBy = "mainTypSports")
    List<LocationEntity> locations;

    public List<PreciseDisciplineEntity> getMainTypSports() {
        return mainTypSports;
    }

    public void setMainTypSports(List<PreciseDisciplineEntity> mainTypSports) {
        this.mainTypSports = mainTypSports;
    }

    public List<LocationEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationEntity> locations) {
        this.locations = locations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMainTypSportEntityId() {
        return MainTypSportEntityId;
    }

    public void setMainTypSportEntityId(String mainTypSportEntityId) {
        MainTypSportEntityId = mainTypSportEntityId;
    }

    public String getMainNameOfSport() {
        return mainNameOfSport;
    }

    public void setMainNameOfSport(String mainNameOfSport) {
        this.mainNameOfSport = mainNameOfSport;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
