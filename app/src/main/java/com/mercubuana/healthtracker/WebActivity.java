package com.mercubuana.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.healthtracker.R;

import static com.mercubuana.healthtracker.konstanta.Konstanta.URL_ARTIKEL;

public class WebActivity extends AppCompatActivity {

    WebView web;
    ProgressDialog progress;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        web = findViewById(R.id.webView);

        progress = new ProgressDialog(WebActivity.this);
        progress.setMessage("Waiting......");
        progress.show();

        web.loadUrl(intent.getStringExtra(URL_ARTIKEL));
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view,url);
                progress.dismiss();
                getSupportActionBar().setTitle(web.getTitle());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if(web.canGoBack()){
            web.goBack();
        }else {
            super.onBackPressed();
        }
        return true;
    }
}
