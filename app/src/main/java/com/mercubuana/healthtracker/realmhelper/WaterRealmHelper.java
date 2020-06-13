package com.mercubuana.healthtracker.realmhelper;

import android.content.Context;
import android.widget.Toast;

import com.mercubuana.healthtracker.model.WaterModel;
import com.mercubuana.healthtracker.module.WaterModule;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;

public class WaterRealmHelper {
    private Context context;
    private Realm realm;

    public WaterRealmHelper(Context context) {
        this.context = context;
        Realm.init(context);

        RealmConfiguration waterRealConfiguration = new RealmConfiguration.Builder()
                .name("water.realm")
                .modules(new WaterModule())
                .build();

        realm = Realm.getInstance(waterRealConfiguration);
    }

    public void insertData(WaterModel water){
        realm.beginTransaction();
        realm.copyToRealm(water);
        realm.commitTransaction();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm realm) {
                Toast.makeText(context, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }

    public long getNextId(){
        if (realm.where(WaterModel.class).count() != 0){
            long id = realm.where(WaterModel.class).max("intId").longValue();
            return id+1;
        }else {
            return 1;
        }
    }

    public List<WaterModel> showData(){
        return realm.where(WaterModel.class).findAll();
    }

    public WaterModel getDetail(Integer intId){
        WaterModel data = realm.where(WaterModel.class).equalTo("intId",intId).findFirst();
        return data;
    }

    public void updateData(WaterModel water){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(water);
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
        WaterModel water = realm.where(WaterModel.class).equalTo("intId",intId).findFirst();
        water.deleteFromRealm();
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
