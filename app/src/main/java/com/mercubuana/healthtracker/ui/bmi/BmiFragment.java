package com.mercubuana.healthtracker.ui.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.healthtracker.R;
import com.mercubuana.healthtracker.BmiTambahActivity;
import com.mercubuana.healthtracker.adapter.BmiAdapter;
import com.mercubuana.healthtracker.model.BmiModel;
import com.mercubuana.healthtracker.realmhelper.BmiRealmHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BmiFragment extends Fragment {

    List<BmiModel> dataBmi = new ArrayList<>();
    RecyclerView recycler;
    BmiRealmHelper realm;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bmi, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BmiTambahActivity.class));
            }
        });

        /*BmiModel bmi =new BmiModel();
        bmi.setIntGambarStatus(R.drawable.checkmark);
        bmi.setStrTinggi("Tinggi : 160cm");
        bmi.setStrBobot("Bobot : 50kg");
        bmi.setStrStatus("Ideal");
        bmi.setStrTanggal("17-05-2020");

        for (int i = 0; i < 20; i++) {
            dataBmi.add(bmi);
        }*/

        realm = new BmiRealmHelper(getContext());
        dataBmi =realm.showData();

        recycler = root.findViewById(R.id.recyclerView);
        recycler.setAdapter(new BmiAdapter(getContext(),dataBmi));
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(),1));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        dataBmi = realm.showData();
        recycler.setAdapter(new BmiAdapter(getContext(),dataBmi));
    }
}
