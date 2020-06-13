package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.model.BmiModel;
import com.mercubuana.healthtracker.realmhelper.BmiRealmHelper;

import java.text.DecimalFormat;
import java.util.Calendar;

public class BmiTambahActivity extends AppCompatActivity {

    Button btnHitung, btnSimpan;
    static EditText edTinggi;
    static EditText edBobot;
    TextView tvBmi, tvStatus;
    BmiRealmHelper realmBmi;

    Double dblBobot, dblBmi,dblTinggi;
    String strStatus,strDate;
    int intImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bmi);

        realmBmi = new BmiRealmHelper(BmiTambahActivity.this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnHitung = findViewById(R.id.btnHitung);
        btnSimpan = findViewById(R.id.btnSimpan);
        edTinggi = findViewById(R.id.edTinggi);
        edBobot = findViewById(R.id.edBobot);
        tvBmi = findViewById(R.id.tvBmi);
        tvStatus = findViewById(R.id.tvStatus);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRequired(BmiTambahActivity.this, edTinggi, edBobot) == true) {
                    dblTinggi = Double.valueOf(edTinggi.getText().toString());
                    dblBobot = Double.valueOf(edBobot.getText().toString());

                    dblBmi = hitungBmi(dblTinggi, dblBobot);
                    strStatus = cariKategori(dblBmi);

                    tvBmi.setText("BMI : " + dblBmi.toString());
                    tvStatus.setText(strStatus);
                    btnSimpan.setVisibility(View.VISIBLE);
                }
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRequired(BmiTambahActivity.this,edTinggi,edBobot) == true) {
                    dblTinggi = Double.valueOf(edTinggi.getText().toString());
                    dblBobot = Double.valueOf(edBobot.getText().toString());

                    dblBmi = hitungBmi(dblTinggi, dblBobot);
                    strStatus = cariKategori(dblBmi);
                    intImage = cariImage(dblBmi);

                    Calendar calendar = Calendar.getInstance();
                    String nowYear = String.valueOf(calendar.get(Calendar.YEAR));
                    String nowMonth = String.valueOf(calendar.get(Calendar.MONTH));
                    String nowDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

                    strDate = nowDay+"-"+nowMonth+"-"+nowYear;

                    BmiModel bmi = new BmiModel();
                    bmi.setIntId((int) realmBmi.getNextId());
                    bmi.setStrTinggi(String.valueOf(dblTinggi));
                    bmi.setStrBobot(String.valueOf(dblBobot));
                    bmi.setDblBmi(dblBmi);
                    bmi.setStrStatus(strStatus);
                    bmi.setIntGambarStatus(intImage);
                    bmi.setStrTanggal(strDate);

                    realmBmi.insertData(bmi);
                    finish();
                }
            }
        });

    }

    public static int cariImage(Double dblBmi) {
        int intImage = 0;
        if (dblBmi < 17.0) {
            intImage = R.drawable.danger;
        } else if (dblBmi >= 17.0 && dblBmi <= 18.4) {
            intImage = R.drawable.warning;
        } else if (dblBmi >= 18.5 && dblBmi <= 25) {
            intImage = R.drawable.checkmark;
        } else if (dblBmi >= 25.1 && dblBmi <= 27) {
            intImage = R.drawable.warning;
        } else {
            intImage = R.drawable.danger;
        }
        return intImage;
    }

    public static boolean validateRequired(Context context, EditText edTinggi, EditText edBobot) {
        boolean validate = true;
        if(edTinggi.getText().toString().isEmpty() || edBobot.getText().toString().isEmpty()){
            Toast.makeText(context, "Ada yang kosong", Toast.LENGTH_SHORT).show();
            validate = false;
        }
        return validate;
    }

    public static String cariKategori(Double dblBmi) {
        String strStatus;
        if (dblBmi < 17.0) {
            strStatus = "Kurus, kekurangan berat badan berat";
        } else if (dblBmi >= 17.0 && dblBmi <= 18.4) {
            strStatus = "Kurus, kekurangan berat badan ringan";
        } else if (dblBmi >= 18.5 && dblBmi <= 25) {
            strStatus = "Normal";
        } else if (dblBmi >= 25.1 && dblBmi <= 27) {
            strStatus = "Gemuk, kelebihan berat badan ringan";
        } else {
            strStatus = "Gemuk, kelebihan berat badan berat";
        }
        return strStatus;
    }

    public static Double hitungBmi(Double dblTinggi, Double dblBobot) {
        Double dblBmi = (dblBobot) / ((dblTinggi / 100) * (dblTinggi / 100));
        return Double.valueOf(new DecimalFormat("#.##").format(dblBmi));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
