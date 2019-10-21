package com.example.bloodbankinventory.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.shareprefs;
import com.example.bloodbankinventory.utils.Barang;
//import com.example.bloodbankinventory.utils.BarangAdapter;
import com.google.gson.Gson;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Data_MasukFragment extends Fragment {
    private shareprefs share;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Button add, clear,delete;

    private RecyclerView.LayoutManager layout;
    private ListView ListView;
    private RecyclerView RecyclerView;
    JSONObject saved = new JSONObject();
    EditText coomb, nacl, tabung, pipet, hands,masker,gelas;
    Barang barang;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data__masuk, container, false);

        //buildRecyclerView(view);
        coomb = view.findViewById(R.id.editCoomb1);
        nacl = view.findViewById(R.id.editNacl1);
        tabung = view.findViewById(R.id.editTabung1);
        pipet = view.findViewById(R.id.editPipet1);
        hands = view.findViewById(R.id.editHands1);
        masker = view.findViewById(R.id.editMasker1);
        gelas = view.findViewById(R.id.editGlass1);
        add = view.findViewById(R.id.addButton);
        clear = view.findViewById(R.id.clearButton);

        init(view);
        Intent intent = getActivity().getIntent();
        if(intent.getIntExtra("position",-1)!=1){
            try{
                String c = coomb.getText().toString();
                String n = coomb.getText().toString();
                if(!sharedPreferences.getString("saved","").equals(""))
                    saved = new JSONObject(sharedPreferences.getString("saved",""));
                coomb.setText(saved.getString("saved" + intent.getIntExtra("position",0)));
                nacl.setText(saved.getString("saved" + intent.getIntExtra("position",0)));
                c = saved.getString("saved" + + intent.getIntExtra("position",0));
                n = saved.getString("saved" + + intent.getIntExtra("position",0));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = coomb.getText().toString();
                String n = coomb.getText().toString();
                if(c.isEmpty()&&n.isEmpty()){
                    Toast.makeText(getContext(),"enter text please",Toast.LENGTH_SHORT).show();
                }
                else{
                    coomb.setText("");
                    nacl.setText("");
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c = coomb.getText().toString();
                String n = nacl.getText().toString();
                if(!c.equals("")){
                        try {
                            if(!sharedPreferences.getString("saved","").equals(""))
                            saved = new JSONObject(sharedPreferences.getString("saved",""));
                        saved.put("saved" + saved.length(),c);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    Log.d("testing",saved+"");
                    editor.putString("saved",saved.toString());
                    editor.apply();
                    coomb.setText("");

                    Fragment fragment = null;
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_content, new HistoryFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    }
                else if(!n.equals("")){
                    try {
                        if(!sharedPreferences.getString("saved","").equals(""))
                            saved = new JSONObject(sharedPreferences.getString("saved",""));

                        saved.put("saved" + saved.length(),n);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("testing",saved+"");
                    editor.putString("saved",saved.toString());
                    editor.apply();
                    coomb.setText("");

                    Fragment fragment = null;
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_content, new HistoryFragment());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });



        return view;
    }
    private void deleteAllValue(View view){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("delete",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(getContext(),"Value Remove", Toast.LENGTH_SHORT).show();
    }
    private void init(View view){
        sharedPreferences = getActivity().getSharedPreferences("text", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        coomb = view.findViewById(R.id.editCoomb1);
        nacl = view.findViewById(R.id.editNacl1);
        add = view.findViewById(R.id.addButton);
        clear = view.findViewById(R.id.clearButton);
    }



    /*private void setInsertButton(View view){
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
        }*/
    /*private void buildRecyclerView(View view) {
        RecyclerView =(RecyclerView) view.findViewById(R.id.recyclerview1);
        RecyclerView.setHasFixedSize(true);
        layout = new LinearLayoutManager(getContext());
        adapter = new BarangAdapter(barangList);
    }*/
    /*private void buildListView(View view){
        ListView = (ListView)view.findViewById(R.id.listview1);
        ListView.setHasTransientState(true);
        layout = new LinearLayoutManager(getContext());
        adapter = new BarangAdapter(barangList);
    }*/

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_datam);
    }


}
