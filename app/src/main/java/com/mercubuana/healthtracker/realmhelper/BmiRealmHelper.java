package com.mercubuana.healthtracker.realmhelper;

import android.content.Context;
import android.widget.Toast;

import com.mercubuana.healthtracker.model.BmiModel;
import com.mercubuana.healthtracker.module.BmiModule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

public class BmiRealmHelper {
    private Context context;
    private Realm realm;

    public BmiRealmHelper(Context context) {
        this.context = context;
        Realm.init(context);

        RealmConfiguration bmiRealConfiguration = new RealmConfiguration.Builder()
                .name("bmi.realm")
                .modules(new BmiModule())
                .build();

        realm = Realm.getInstance(bmiRealConfiguration);
    }

    public void insertData(BmiModel bmi){
        realm.beginTransaction();
        realm.copyToRealm(bmi);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });

        realm.close();
    }

    public List<BmiModel> showData(){
        return realm.where(BmiModel.class).findAll();
    }

    public long getNextId(){
        if (realm.where(BmiModel.class).count() != 0){
            long id = realm.where(BmiModel.class).max("intId").longValue();
            return id+1;
        }else {
            return 1;
        }
    }

    public BmiModel getDetail(Integer intId){
        BmiModel data = realm.where(BmiModel.class).equalTo("intId",intId).findFirst();
        return data;
    }

    public void updateData(BmiModel bmi){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(bmi);
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data Berhasil Diupdate", Toast.LENGTH_SHORT).show();
            }
        });
        realm.commitTransaction();
        realm.close();
    }

    public void deleteData(Integer intId){
        realm.beginTransaction();
        BmiModel bmi = realm.where(BmiModel.class).equalTo("intId",intId).findFirst();
        bmi.deleteFromRealm();
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }
}
