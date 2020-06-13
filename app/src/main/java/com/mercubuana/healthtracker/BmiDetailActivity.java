package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.model.BmiModel;
import com.mercubuana.healthtracker.realmhelper.BmiRealmHelper;

import java.util.Calendar;

public class BmiDetailActivity extends AppCompatActivity {
    Button btnHitung, btnUpdate, btnDelete;
    EditText edTinggi, edBobot;
    TextView tvBmi, tvStatus;
    BmiRealmHelper realmBmi;

    Double dblBobot, dblBmi, dblTinggi;
    String strStatus, strDate;
    int intImage;

    public static final String KEY_ID = "key_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_detail);

        realmBmi = new BmiRealmHelper(BmiDetailActivity.this);
        final int dataID = getIntent().getIntExtra(KEY_ID, 0);

        final BmiModel data = realmBmi.getDetail(dataID);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnHitung = findViewById(R.id.btnHitung);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        edTinggi = findViewById(R.id.edTinggi);
        edBobot = findViewById(R.id.edBobot);
        tvBmi = findViewById(R.id.tvBmi);
        tvStatus = findViewById(R.id.tvStatus);

        edTinggi.setText(data.getStrTinggi());
        edBobot.setText(data.getStrBobot());
        tvBmi.setText("BMI : "+data.getDblBmi().toString());
        tvStatus.setText(data.getStrStatus());

        Calendar calendar = Calendar.getInstance();
        String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
        String nowMonth = String.valueOf(calendar.get(Calendar.MONTH));
        String nowDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

        strDate = nowDay+"-"+nowMonth+"-"+nowYear;

        if(!data.getStrTanggal().equals(strDate)){
            btnUpdate.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
            btnHitung.setVisibility(View.GONE);
        }

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BmiTambahActivity.validateRequired(BmiDetailActivity.this, edTinggi, edBobot) == true) {
                    dblTinggi = Double.valueOf(edTinggi.getText().toString());
                    dblBobot = Double.valueOf(edBobot.getText().toString());

                    dblBmi = BmiTambahActivity.hitungBmi(dblTinggi, dblBobot);
                    strStatus = BmiTambahActivity.cariKategori(dblBmi);

                    tvBmi.setText("BMI : " + dblBmi.toString());
                    tvStatus.setText(strStatus);
                    btnUpdate.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dblTinggi = Double.valueOf(edTinggi.getText().toString());
                dblBobot = Double.valueOf(edBobot.getText().toString());

                dblBmi = BmiTambahActivity.hitungBmi(dblTinggi, dblBobot);
                strStatus = BmiTambahActivity.cariKategori(dblBmi);
                intImage = BmiTambahActivity.cariImage(dblBmi);

                BmiModel bmi = new BmiModel();
                bmi.setIntId(dataID);
                bmi.setStrTinggi(String.valueOf(dblTinggi));
                bmi.setStrBobot(String.valueOf(dblBobot));
                bmi.setDblBmi(dblBmi);
                bmi.setStrStatus(strStatus);
                bmi.setIntGambarStatus(intImage);
                bmi.setStrTanggal(data.getStrTanggal());

                realmBmi.updateData(bmi);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmBmi.deleteData(dataID);
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
