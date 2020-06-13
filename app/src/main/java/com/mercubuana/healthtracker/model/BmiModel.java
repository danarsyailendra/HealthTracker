package com.mercubuana.healthtracker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class BmiModel extends RealmObject {
    private int intGambarStatus;
    private Double dblBmi;

    @PrimaryKey
    private int intId;

    private String strTinggi,strBobot,strStatus,strTanggal;

    public int getIntGambarStatus() {
        return intGambarStatus;
    }

    public void setIntGambarStatus(int intGambarStatus) {
        this.intGambarStatus = intGambarStatus;
    }

    public String getStrTinggi() {
        return strTinggi;
    }

    public void setStrTinggi(String strTinggi) {
        this.strTinggi = strTinggi;
    }

    public String getStrBobot() {
        return strBobot;
    }

    public void setStrBobot(String strBobot) {
        this.strBobot = strBobot;
    }

    public String getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public String getStrTanggal() {
        return strTanggal;
    }

    public void setStrTanggal(String strTanggal) {
        this.strTanggal = strTanggal;
    }

    public Double getDblBmi() {
        return dblBmi;
    }

    public void setDblBmi(Double dblBmi) {
        this.dblBmi = dblBmi;
    }
}
