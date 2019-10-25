package com.example.bloodbankinventory.fragment;


import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.DialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.shareprefs;
import com.example.bloodbankinventory.utils.Barang;

import com.example.bloodbankinventory.utils.BarangAdapter;
import com.example.bloodbankinventory.utils.BarangCRUD;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    private OnFragmentInteractionListener listener;

    TextView inputnama, inputdata;

    private String[] idbarang ={"1","2","3","4"};
    private String[] nama={"Coomb serum", "Pasteur pipet", "Hands Schone", "Object Glass"};
    private String[] jumlah={"12","22","14","31"};
    public HistoryFragment(){

    }
    private static final String TAG = "HistoryFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        //inputnama = view.findViewById(R.id.input_nama);
        //inputdata = view.findViewById(R.id.input_jumlah);

        ListView list= view.findViewById(R.id.liist);

        BarangAdapter barangAdapter = new BarangAdapter(this.getContext(),idbarang,nama, jumlah);
        list.setAdapter(barangAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BarangCRUD barang = new BarangCRUD(Integer.valueOf(idbarang[position]), nama[position], Integer.valueOf(jumlah[position]));
                listener.onClickedBarang(barang);
            }
        });
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.btnOpen();

            }
        });
        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                customDialogFragment dialog = new customDialogFragment();
                dialog.setTargetFragment(HistoryFragment.this, 1);
                dialog.show(getFragmentManager(), "inputdialog");
            }
        });*/
        return view;
    }
    public  void updateBarang(BarangCRUD barang, int pos, String newName, String newJumlah){

        ArrayList<String>id = new ArrayList<>(Arrays.asList(idbarang));
        ArrayList<String> namaa = new ArrayList<>(Arrays.asList(nama));
        ArrayList<String> jumlaah = new ArrayList<>(Arrays.asList(jumlah));

        namaa.remove(barang.getNama());
        namaa.add(pos, newName);

        jumlaah.remove(barang.getJumlah());
        jumlaah.add(pos,String.valueOf(barang.getJumlah()));


    }

    public void newBarang(BarangCRUD barang){
        ArrayList<String>id = new ArrayList<>(Arrays.asList(idbarang));
        ArrayList<String> namaa = new ArrayList<>(Arrays.asList(nama));
        ArrayList<String> jumlaah = new ArrayList<>(Arrays.asList(jumlah));
        id.add(String.valueOf(id.size()+1));
        namaa.add(barang.getNama());
        jumlaah.add(String.valueOf(barang.getJumlah()));
        this.idbarang = id.toArray(new String[id.size()]);
        this.nama = namaa.toArray(new String[namaa.size()]);
        this.jumlah = jumlaah.toArray(new String[jumlaah.size()]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_history);
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void onClickedBarang(BarangCRUD barang);

        void addNewBarang(BarangCRUD barang);

        void btnAdd();
        void btnOpen();
    }



}
