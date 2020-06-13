package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.response.ResponseRegister;
import com.mercubuana.healthtracker.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnDaftar;
    RadioGroup rdgGender;
    RadioButton rdbChecked;
    EditText edEmail, edNama, edPassword, edKonfirmasiPassword;
    String email, nama, password, gender,strKonformasiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnDaftar = findViewById(R.id.btnDaftar);
        rdgGender = findViewById(R.id.rdgGender);
        edEmail = findViewById(R.id.edEmail);
        edNama = findViewById(R.id.edNama);
        edPassword = findViewById(R.id.edPassword);
        edKonfirmasiPassword = findViewById(R.id.edKonfirmasiPassword);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = new ProgressDialog(RegisterActivity.this);
                progress.setMessage("Waiting...");
                progress.show();

                email = edEmail.getText().toString();
                nama = edNama.getText().toString();
                password = edPassword.getText().toString();
                int selectedId = rdgGender.getCheckedRadioButtonId();
                switch (selectedId){
                    case R.id.rdbLaki:
                        gender = "1";
                        break;
                    case R.id.rdbPerempuan:
                        gender = "2";
                        break;
                }
                if(validateRequired() == true) {
                    if (validatePassword() == true) {
                        Call<ResponseRegister> request = RetrofitConfig.getApiService().register(email, nama, password, gender);
                        request.enqueue(new Callback<ResponseRegister>() {
                            @Override
                            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                                progress.dismiss();
                                Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                if (response.body().isStatus() == true) {
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                                progress.dismiss();
                                Toast.makeText(RegisterActivity.this, "Koneksi Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progress.dismiss();
                    }
                }else {
                    progress.dismiss();
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean validatePassword(){
        boolean validate = true;

        password = edPassword.getText().toString();
        strKonformasiPassword = edKonfirmasiPassword.getText().toString();

        if(!password.equals(strKonformasiPassword)){
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        return validate;
    }

    private boolean validateRequired(){
        boolean validate = true;

        email = edEmail.getText().toString();
        nama = edNama.getText().toString();
        password = edPassword.getText().toString();
        strKonformasiPassword = edKonfirmasiPassword.getText().toString();
        int selectedId = rdgGender.getCheckedRadioButtonId();

        if(email.isEmpty() || nama.isEmpty() || password.isEmpty() || strKonformasiPassword.isEmpty() || selectedId == -1 ){
            Toast.makeText(this, "Ada yang kosong", Toast.LENGTH_SHORT).show();
            validate = false;
        }

        return validate;
    }
}
