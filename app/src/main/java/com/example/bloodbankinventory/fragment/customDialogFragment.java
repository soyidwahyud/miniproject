package com.example.bloodbankinventory.fragment;

import androidx.fragment.app.DialogFragment;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.utils.BarangCRUD;

/**
 * A simple {@link Fragment} subclass.
 */
public class customDialogFragment extends DialogFragment {

    private static final String TAG = "inputdialog";

    public customDialogListener mOnInputSelected;

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
        final EditText nama = getDialog().findViewById(R.id.nameEditText);
        final EditText data = getDialog().findViewById(R.id.inputEditText);
        Button add =getDialog().findViewById(R.id.addBtn);
        Button update = getDialog().findViewById(R.id.updateBtn);
        Button delete = getDialog().findViewById(R.id.deleteBtn);



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


                        mOnInputSelected.sendInput3(newName);
                        mOnInputSelected.sendInput4(newInput);
                    }
                }else
                {
                    Toast.makeText(getActivity(), "Nama Barang dan Data Barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                getDialog().show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
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

                    HistoryFragment h = (HistoryFragment) getActivity().getSupportFragmentManager().findFragmentByTag("HistoryFragment");
                    h.inputnama.setText(barang);
                    h.inputdata.setText(jumlah);
                    //mOnInputSelected.sendInput(barang);
                    //mOnInputSelected.sendInput2(jumlah);
                }
                else
                {
                    Toast.makeText(getActivity(), "Nama Barang dan Data Barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                getDialog().dismiss();
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

    public static customDialogFragment newInstance(String title) {
        customDialogFragment cus = new customDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        cus.setArguments(args);
        return cus;
    }
    public interface customDialogListener{

        void sendInput(String barang);
        void sendInput2(String jumlah);
        void sendInput3(String newName);
        void sendInput4(String newInput);
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
        try{
            mOnInputSelected = (customDialogListener) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }

}
