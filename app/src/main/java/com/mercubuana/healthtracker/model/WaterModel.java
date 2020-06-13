package com.mercubuana.healthtracker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WaterModel extends RealmObject {
    @PrimaryKey
    private int intId;

    private int intGambarStatus;

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }

    public int getIntGambarStatus() {
        return intGambarStatus;
    }

    public void setIntGambarStatus(int intGambarStatus) {
        this.intGambarStatus = intGambarStatus;
    }

    public String getStrTanggal() {
        return strTanggal;
    }

    public void setStrTanggal(String strTanggal) {
        this.strTanggal = strTanggal;
    }
    private String strTanggal,strLiter;

    public String getStrLiter() {
        return strLiter;
    }

    public void setStrLiter(String strLiter) {
        this.strLiter = strLiter;
    }
}
