package com.mercubuana.healthtracker.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LoginModel extends RealmObject {
    private String strUserId,strUserName,strUserEmail,strUserPass,strUserGender;

    @PrimaryKey
    private int intId;

    public int getIntId() {
        return intId;
    }

    public void setIntId(int intId) {
        this.intId = intId;
    }
    public String getStrUserId() {
        return strUserId;
    }

    public void setStrUserId(String strUserId) {
        this.strUserId = strUserId;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrUserEmail() {
        return strUserEmail;
    }

    public void setStrUserEmail(String strUserEmail) {
        this.strUserEmail = strUserEmail;
    }

    public String getStrUserPass() {
        return strUserPass;
    }

    public void setStrUserPass(String strUserPass) {
        this.strUserPass = strUserPass;
    }

    public String getStrUserGender() {
        return strUserGender;
    }

    public void setStrUserGender(String strUserGender) {
        this.strUserGender = strUserGender;
    }


}
