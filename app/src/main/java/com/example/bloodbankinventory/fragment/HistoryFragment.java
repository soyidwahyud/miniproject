package com.example.bloodbankinventory.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.shareprefs;
import com.example.bloodbankinventory.utils.Barang;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    JSONObject saved;
    Button delete;
    private static final String BARANG_KEY = "barang";
    SharedPreferences.OnSharedPreferenceChangeListener mListener;
    private static final String TAG = "HistoryFragment";


    public HistoryFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        sharedPreferences= getActivity().getSharedPreferences("text", Context.MODE_PRIVATE);
        Log.d("Testing",sharedPreferences.getString("saved",""));
        try{
            saved = new JSONObject(sharedPreferences.getString("saved",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        delete = view.findViewById(R.id.delete);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new Adapter());

        delete.setOnClickListener(this::deleteAllValue);
        mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                switch (key){
                    case BARANG_KEY:{
                        String barang = sharedPreferences.getString("key","N/A");
                        Log.d(TAG,"onSharedPreferencesChanged: " +key + " : " + barang);
                        break;
                    }
                }
            }
        };
        sharedPreferences = getActivity().getSharedPreferences("text",Context.MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(mListener);

        return view;
    }

    private void deleteAllValue(View view){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("delete",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(getContext(),"Value Remove", Toast.LENGTH_SHORT).show();
    }
    public class Adapter extends RecyclerView.Adapter<Adapter.Holder>{


        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.barang_item,parent,false);
            Holder holder = new Holder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            try {
                holder.c.setText(saved.getString("saved" + position));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return saved.length();
        }

        public class Holder extends RecyclerView.ViewHolder {

            TextView c;
            TextView n;

            public Holder(@NonNull View itemView) {
                super(itemView);
                c=itemView.findViewById(R.id.barang1);

            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_history);
    }



}
