package com.example.bloodbankinventory.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.shareprefs;
import com.example.bloodbankinventory.utils.Barang;
import com.example.bloodbankinventory.utils.BarangAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    public String information;
    private Gson gson;
    private shareprefs share;
    private RecyclerView RecyclerView;
    ArrayList<Barang> barangList;
    private BarangAdapter adapter;
    private RecyclerView.LayoutManager layout;


    public HistoryFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        gson = new Gson();
        RecyclerView = view.findViewById(R.id.recyclerview);
        RecyclerView.setHasFixedSize(true);
        layout = new LinearLayoutManager(getActivity());
        adapter = new BarangAdapter(barangList);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_history);
    }
    public void setInformation(String information) {
        this.information = information;
    }


}
