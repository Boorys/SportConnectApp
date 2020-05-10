package com.sportapp.demo.dto;

public class OpinionGetDto {


    private byte skill;

    private byte punctuality;

    private String generalComment;

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
}
