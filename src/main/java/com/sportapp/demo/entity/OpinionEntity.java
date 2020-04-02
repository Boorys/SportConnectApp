package com.sportapp.demo.entity;

import org.w3c.dom.Text;
import javax.persistence.*;

@Entity
public class OpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String opinionId;

    @Column
    private byte skill;

    @Column
    private byte punctuality;

    @Column
    private String generalComment;

    @ManyToOne
    @JoinColumn
    private UserEntity user;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(String opinionId) {
        this.opinionId = opinionId;
    }

    public byte getSkill() {
        return skill;
    }

    public void setSkill(byte skill) {
        this.skill = skill;
    }

    public byte getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(byte punctuality) {
        this.punctuality = punctuality;
    }

    public String getGeneralComment() {
        return generalComment;
    }

    public void setGeneralComment(String generalComment) {
        this.generalComment = generalComment;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
