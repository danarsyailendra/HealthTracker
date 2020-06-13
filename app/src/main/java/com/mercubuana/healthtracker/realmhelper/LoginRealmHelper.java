package com.mercubuana.healthtracker.realmhelper;

import android.content.Context;

import com.mercubuana.healthtracker.model.LoginModel;
import com.mercubuana.healthtracker.module.LoginModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginRealmHelper {
    private Context context;
    private Realm realm;

    public LoginRealmHelper(Context context){
        this.context = context;
        Realm.init(context);
        //realm = Realm.getDefaultInstance();
        RealmConfiguration loginRealConfiguration = new RealmConfiguration.Builder()
                .name("login.realm")
                .modules(new LoginModule())
                .build();

        realm = Realm.getInstance(loginRealConfiguration);
    }

    public void login(LoginModel login){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(login);
        realm.commitTransaction();
        realm.close();
    }

    public LoginModel cekLogged(Integer intId){
        LoginModel data = realm.where(LoginModel.class).equalTo("intId",intId).findFirst();
        return data;
    }

    public LoginModel getGender(Integer intId){
        LoginModel data = realm.where(LoginModel.class).equalTo("Intid",intId).findFirst();
        return data;
    }

}
