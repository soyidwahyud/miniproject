package com.example.bloodbankinventory.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbankinventory.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BarangAdapter  extends ArrayAdapter<String> {
    String []id;
    String []nama;
    String []jumlah;

    public BarangAdapter(Context context, String[] idbarang, String[] nama, String[] jumlah){
        super(context, R.layout.barang, nama);
        this.id = idbarang;
        this.nama = nama;
        this.jumlah = jumlah;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.barang, parent, false);

        TextView nama = convertView.findViewById(R.id.textNama);
        TextView jumlah = convertView.findViewById(R.id.textJumlah);

        nama.setText(this.nama[position]);
        jumlah.setText(this.jumlah[position]);


        return convertView;
    }
}