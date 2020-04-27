package com.sportapp.demo.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class TimeTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String timeTableId;

    @Column
    private short hour;

    @Column
    private Calendar date;

    @ManyToOne
    @JoinColumn
    private LocationEntity location;

    public long getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(String timeTableId) {
        this.timeTableId = timeTableId;
    }

    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
