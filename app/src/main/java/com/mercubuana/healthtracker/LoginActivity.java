package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.realmhelper.LoginRealmHelper;
import com.mercubuana.healthtracker.model.LoginModel;
import com.mercubuana.healthtracker.response.ResponseLogin;
import com.mercubuana.healthtracker.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button btnRegister,btnLogin;
    EditText edEmail,edPassword;
    String strEmail,strPassword;
    LoginRealmHelper realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        realm = new LoginRealmHelper(LoginActivity.this);

        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);

        SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
        int logged = sharedPref.getInt(getString(R.string.login_email), 0);

        if(logged == 1){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
                progress.setMessage("Waiting...");
                progress.show();

                strEmail = edEmail.getText().toString();
                strPassword = edPassword.getText().toString();

                Call<ResponseLogin> request  = RetrofitConfig.getApiService().login(strEmail,strPassword);
                request.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        progress.dismiss();
                        if(response.body().isStatus() == true){
                            SharedPreferences sharedPref = LoginActivity.this.getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt(getString(R.string.login_email), 1);
                            editor.commit();

                            LoginModel login =new LoginModel();
                            login.setIntId(99999);
                            login.setStrUserId(response.body().getData().getUserId());
                            login.setStrUserEmail(response.body().getData().getUserEmail());
                            login.setStrUserName(response.body().getData().getUserName());
                            login.setStrUserPass(response.body().getData().getUserPass());
                            login.setStrUserGender(response.body().getData().getUserGender());
                            realm.login(login);

                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        progress.dismiss();
                        Toast.makeText(LoginActivity.this, "Koneksi Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
