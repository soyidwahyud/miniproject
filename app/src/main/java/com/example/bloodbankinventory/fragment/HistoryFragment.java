package com.example.bloodbankinventory.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

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

import com.example.bloodbankinventory.utils.BarangCRUD;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment implements customDialogFragment.OnInputSelected{

    ListView list;
    ArrayAdapter<String>adapter;
    BarangCRUD crud = new BarangCRUD();
    Dialog d;
    TextView inputnama, inputdata;
    public HistoryFragment(){

    }
    private static final String TAG = "HistoryFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        inputnama = view.findViewById(R.id.input_nama);
        inputdata = view.findViewById(R.id.input_barang);
        //list= (ListView)view.findViewById(R.id.lv);

        /*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i,view);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });*/
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: opening dialog");

                customDialogFragment dialog = new customDialogFragment();
                dialog.setTargetFragment(HistoryFragment.this, 1);
                dialog.show(getFragmentManager(), "inputdialog");

            }
        });

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.fragment_history);
    }


    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);

        inputnama.setText(input);
        inputdata.setText(input);
    }
}
