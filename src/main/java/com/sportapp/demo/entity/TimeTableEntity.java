package com.sportapp.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TimeTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String timeTableId;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn
    private LocationEntity location;


    public long getId() {
        return id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }
}
