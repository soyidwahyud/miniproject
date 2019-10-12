package com.example.bloodbankinventory.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.bloodbankinventory.MainActivity;
import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.shareprefs;
import com.example.bloodbankinventory.utils.Barang;
import com.example.bloodbankinventory.utils.BarangAdapter;
import com.google.gson.Gson;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;
import com.example.bloodbankinventory.fragment.HistoryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Data_MasukFragment extends Fragment {
    private Gson gson;
    private shareprefs share;
    private SharedPreferences sharedPreferences;
    private Button add, enter;
    ArrayList<Barang> barangList = new ArrayList<Barang>();
    private BarangAdapter adapter;
    private RecyclerView.LayoutManager layout;
    private ListView ListView;
    private RecyclerView RecyclerView;
    @BindView(R.id.editCoomb1) EditText coomb;
    @BindView(R.id.editNacl1)EditText nacl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data__masuk, container, false);

        //buildRecyclerView(view);
        ListView = view.findViewById(R.id.listview1);
        barangList.add(new Barang("12","14"));
        barangList.add(new Barang("34","12"));
        BarangAdapter barang = new BarangAdapter(barangList,getContext());
        ListView.setAdapter(barang);

        gson = new Gson();
        share = new shareprefs(getActivity().getApplicationContext());
        buildListView(view);
        loadData();

        setInsertButton(view);

        enter = (Button) view.findViewById(R.id.enterButton);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
        return view;
    }
    private void setInsertButton(View view){
        add =view.findViewById(R.id.addButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText c = (EditText)view.findViewById(R.id.editCoomb1);
                EditText n = (EditText)view.findViewById(R.id.editNacl1);
                insertBarang(c.getText().toString(),n.getText().toString());
                //barangList.add()
                //adapter.notifyDataSetChanged();
            }
        });
    }
    /*private void buildRecyclerView(View view) {
        RecyclerView =(RecyclerView) view.findViewById(R.id.recyclerview1);
        RecyclerView.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        adapter = new BarangAdapter(barangList);
    }*/
    private void buildListView(View view){
        ListView = (ListView)view.findViewById(R.id.listview1);
        ListView.setHasTransientState(true);
        layout = new LinearLayoutManager(getContext());
        adapter = new BarangAdapter(barangList);
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(barangList);
        editor.putString("Data list", json);
        editor.apply();
        Fragment fragment = null;
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Data list", null);
        Type type = new TypeToken<ArrayList<Barang>>() {}.getType();
        barangList = gson.fromJson(json, type);

        if (barangList == null) {
            barangList = new ArrayList<>();
        }
    }
    private void insertBarang(String barang1, String barang2) {
        barangList.add(new Barang(barang1, barang2));
        //adapter.notifyItemInserted(barangList.size());
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_datam);
    }

    //private void setInsertButton() {
    //
    //}

}
