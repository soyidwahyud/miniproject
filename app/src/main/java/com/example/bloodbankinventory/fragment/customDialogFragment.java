package com.example.bloodbankinventory.fragment;


import android.content.Context;
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
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.utils.BarangCRUD;

/**
 * A simple {@link Fragment} subclass.
 */
public class customDialogFragment extends DialogFragment {

    private static final String TAG = "inputdialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    private EditText nama, data;
    Button addButton, update, delete;
    BarangCRUD crud = new BarangCRUD();
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

        getDialog().setContentView(R.layout.inputdialog);
        getDialog().setTitle("Input Data");
        final EditText nama = view.findViewById(R.id.nameEditText);
        final EditText data = view.findViewById(R.id.inputEditText);
        addButton =view.findViewById(R.id.addButton);
        Button update = view.findViewById(R.id.updateBtn);
        Button delete = view.findViewById(R.id.deleteBtn);

         addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: add data");
                String barang=nama.getText().toString();
                String jumlah = data.getText().toString();
                if(barang.length()>0 && barang != null && jumlah.length()>0 && jumlah != null)
                {
                    crud.save(barang,jumlah);
                    nama.setText("");
                    data.setText("");
                    mOnInputSelected.sendInput(barang);
                    mOnInputSelected.sendInput(jumlah);
                }
                else
                {
                    Toast.makeText(getActivity(), "Nama Barang dan Data Barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                getDialog().show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: update data");
                String newName=nama.getText().toString();
                String newInput = data.getText().toString();
                if(newName.length()>0 && newName != null && newInput.length()>0 && newInput !=null)
                {

                    if(crud.update(pos,newName,newInput))
                    {
                        nama.setText(newName);
                        data.setText(newInput);


                        mOnInputSelected.sendInput(newName);
                        mOnInputSelected.sendInput(newInput);
                    }
                }else
                {
                    Toast.makeText(getActivity(), "Nama Barang dan Data Barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                getDialog().show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
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


        /*if(pos== -1)
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }

}
