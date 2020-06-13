package com.mercubuana.healthtracker.ui.water;

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
import com.mercubuana.healthtracker.WaterTambahActivity;
import com.mercubuana.healthtracker.adapter.WaterAdapter;
import com.mercubuana.healthtracker.model.WaterModel;
import com.mercubuana.healthtracker.realmhelper.WaterRealmHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WaterFragment extends Fragment {

    List<WaterModel> dataWater = new ArrayList<>();
    RecyclerView recycler;
    WaterRealmHelper realm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_water, container, false);
        FloatingActionButton fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), WaterTambahActivity.class));
            }
        });

        /*WaterModel water = new WaterModel();
        water.setIntGambarStatus(R.drawable.full_glass);
        water.setStrLiter("2,1");
        water.setStrTanggal("01-06-2020");

        for (int i = 0; i < 20; i++) {
            dataWater.add(water);
        }*/

        realm = new WaterRealmHelper(getContext());
        dataWater = realm.showData();

        recycler = root.findViewById(R.id.recyclerView);
        recycler.setAdapter(new WaterAdapter(getContext(),dataWater));
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getContext(),1));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        dataWater = realm.showData();
        recycler.setAdapter(new WaterAdapter(getContext(),dataWater));
    }
}
