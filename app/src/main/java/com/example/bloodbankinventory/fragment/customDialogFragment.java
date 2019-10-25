package com.example.bloodbankinventory.fragment;

import androidx.fragment.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.utils.Barang;
import com.example.bloodbankinventory.utils.BarangCRUD;

/**
 * A simple {@link Fragment} subclass.
 */
public class customDialogFragment extends DialogFragment {

    private static final String TAG = "inputdialog";

    //public customDialogListener mOnInputSelected;
    private OnFragmentInteractionListener listener;

    private int id;
    private String namaa;
    private int jumlah;

    private EditText nama, data;
    Button addButton, update, delete;

    ArrayAdapter<String>adapter;
    ListView list;
    int pos;

    public customDialogFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.inputdialog, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            BarangCRUD barang = bundle.getParcelable("Data");
            this.id = barang.getId();
            this.namaa = barang.getNama();
            this.jumlah = barang.getJumlah();
        }
        //BarangCRUD barang = bundle.getParcelable("Data");


        final EditText nama = view.findViewById(R.id.nameEditText);
        final EditText data = view.findViewById(R.id.inputEditText);

        nama.setText(this.namaa);
        data.setText(String.valueOf(this.jumlah));

        Button add =view.findViewById(R.id.addBtn);
        Button update = view.findViewById(R.id.updateBtn);
        //Button delete = view.findViewById(R.id.deleteBtn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarangCRUD barang = new BarangCRUD();
                barang.setId(0);
                barang.setNama(nama.getText().toString());
                barang.setJumlah(Integer.parseInt(data.getText().toString()));
                listener.addNewBarang(barang);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNama = nama.getText().toString();
                //nt
            }
        });
        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: delete data");
                if( crud.delete(pos))
                {
                    nama.setText("");
                    data.setText("");

                }
                getDialog().show();
            }
        });


        if(pos== -1)
        {
            add.setEnabled(true);
            update.setEnabled(false);
            delete.setEnabled(false);
        }
        else {
            add.setEnabled(true);
            update.setEnabled(true);
            delete.setEnabled(true);
            nama.setText(crud.getBaraang().get(pos));
            data.setText(crud.getJumlaah().get(pos));
        }*/
        return view;
    }

    public static customDialogFragment newInstance(String title) {
        customDialogFragment cus = new customDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        cus.setArguments(args);
        return cus;
    }

    /*public void sendBackResult(){
        customDialogListener listener = (customDialogListener) getTargetFragment();
        listener.sendInput(nama.getText().toString());
        listener.sendInput(data.getText().toString());
        listener.sendInput2();
        dismiss();
    }*/
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void addNewBarang(BarangCRUD barang);
        void updateBarang(BarangCRUD barang);
    }

}
