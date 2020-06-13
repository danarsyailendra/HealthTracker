package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.konstanta.Konstanta;
import com.mercubuana.healthtracker.model.WaterModel;
import com.mercubuana.healthtracker.realmhelper.WaterRealmHelper;

import java.util.Calendar;

public class WaterDetailActivity extends AppCompatActivity {

    Button btnSimpan,btnDelete;
    EditText edAir;
    WaterRealmHelper realmWater;

    Double dblAir;
    String strTanggal;
    int intImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final int dataID = getIntent().getIntExtra(Konstanta.KEY_ID,0);
        realmWater = new WaterRealmHelper(WaterDetailActivity.this);
        final WaterModel data = realmWater.getDetail(dataID);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnDelete = findViewById(R.id.btnDelete);
        edAir = findViewById(R.id.edAir);

        edAir.setText(data.getStrLiter());

        Calendar calendar = Calendar.getInstance();
        String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
        String nowMonth = String.valueOf(calendar.get(Calendar.MONTH));
        String nowDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        strTanggal = nowDay+"-"+nowMonth+"-"+nowYear;

        if(!data.getStrTanggal().equals(strTanggal)){
            btnDelete.setVisibility(View.GONE);
            btnSimpan.setVisibility(View.GONE);
        }

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(WaterTambahActivity.validateRequired(WaterDetailActivity.this,edAir) == true){
                    dblAir = Double.valueOf(edAir.getText().toString());

                    intImage = WaterTambahActivity.cariImage(dblAir);
                    strTanggal = data.getStrTanggal();

                    WaterModel water = new WaterModel();
                    water.setIntId(dataID);
                    water.setStrLiter(String.valueOf(dblAir));
                    water.setStrTanggal(strTanggal);
                    water.setIntGambarStatus(intImage);

                    realmWater.updateData(water);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmWater.deleteData(dataID);
                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
