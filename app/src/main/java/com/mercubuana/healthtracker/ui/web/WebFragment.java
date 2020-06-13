package com.mercubuana.healthtracker.ui.web;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.WebActivity;

import static com.mercubuana.healthtracker.konstanta.Konstanta.URL_ARTIKEL;


public class WebFragment extends Fragment {

    Button btnIndex,btnKesehatan,btnGelas,btnObesitas;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

// Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_web, container, false);

        btnIndex = root.findViewById(R.id.btnIndex);
        btnKesehatan = root.findViewById(R.id.btnKesehatan);
        btnGelas = root.findViewById(R.id.btnGelas);
        btnObesitas = root.findViewById(R.id.btnObesitas);

        btnIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(URL_ARTIKEL,"https://www.alodokter.com/pemahaman-seputar-indeks-massa-tubuh");
                startActivity(intent);
            }
        });

        btnKesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(URL_ARTIKEL,"https://k-link.co.id/id/pentingnya-menjaga-berat-badan-ideal-untuk-kesehatan/");
                startActivity(intent);
            }
        });

        btnGelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(URL_ARTIKEL,"https://www.dokter.id/berita/mengapa-kita-harus-minum-8-gelas-perhari");
                startActivity(intent);
            }
        });

        btnObesitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra(URL_ARTIKEL,"https://www.halodoc.com/agar-sehat-benarkah-orang-butuh-minum-8-gelas-sehari-");
                startActivity(intent);
            }
        });

        return root;
    }
}
