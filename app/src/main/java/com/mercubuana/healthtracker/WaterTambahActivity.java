package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.model.WaterModel;
import com.mercubuana.healthtracker.realmhelper.WaterRealmHelper;

import java.util.Calendar;

public class WaterTambahActivity extends AppCompatActivity {

    EditText edAir;
    Button btnSimpan;
    Double dblAir;
    int intImage;
    String strDate;
    WaterRealmHelper waterRealmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_water);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        waterRealmHelper = new WaterRealmHelper(WaterTambahActivity.this);
        edAir = findViewById(R.id.edAir);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRequired(WaterTambahActivity.this,edAir) == true){
                    dblAir = Double.valueOf(edAir.getText().toString());

                    intImage = cariImage(dblAir);

                    Calendar calendar = Calendar.getInstance();
                    String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
                    String nowMonth = String.valueOf(calendar.get(Calendar.MONTH));
                    String nowDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

                    strDate = nowDay+"-"+nowMonth+"-"+nowYear;

                    WaterModel water = new WaterModel();
                    water.setIntId((int) waterRealmHelper.getNextId());
                    water.setStrLiter(String.valueOf(dblAir));
                    water.setStrTanggal(strDate);
                    water.setIntGambarStatus(intImage);

                    waterRealmHelper.insertData(water);
                    finish();
                }
            }
        });
    }

    public static boolean validateRequired(Context context, EditText edAir) {
        boolean validate = true;
        if(edAir.getText().toString().isEmpty()){
            Toast.makeText(context, "Ada yang kosong", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }

    public static int cariImage(Double dblAir){
        int intImage = 0;
        if(dblAir < 1000.0){
            intImage = R.drawable.empty_glass;
        }else if (dblAir >=1000.0 && dblAir < 2000.0){
            intImage = R.drawable.half_glass;
        }else {
            intImage = R.drawable.full_glass;
        }

        return intImage;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
