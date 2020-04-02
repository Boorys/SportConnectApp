package com.sportapp.demo.entity;

import javax.persistence.*;

@Entity
public class PreciseDisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String preciseDiscipline;

    @Column
    private String disciplineName;


    @ManyToOne
    @JoinColumn
    private MainTypSportEntity mainTypSport;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPreciseDiscipline() {
        return preciseDiscipline;
    }

    public void setPreciseDiscipline(String preciseDiscipline) {
        this.preciseDiscipline = preciseDiscipline;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public MainTypSportEntity getMainTypSport() {
        return mainTypSport;
    }

    public void setMainTypSport(MainTypSportEntity mainTypSport) {
        this.mainTypSport = mainTypSport;
    }
}
