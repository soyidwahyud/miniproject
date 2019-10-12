package com.example.bloodbankinventory.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbankinventory.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BarangAdapter  extends BaseAdapter {

    private ArrayList<Barang> barangList;
    Context context;
    LayoutInflater inflater;

    public BarangAdapter(ArrayList<Barang> barangList, Context context) {
        this.barangList = barangList;
        this.context = context;
        this.inflater= (LayoutInflater.from(context));
    }


    @Override
    public int getCount() {
        return barangList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i,View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.barang_item,null);
        TextView c = (TextView)convertView.findViewById(R.id.coombserum);
        TextView n = (TextView)convertView.findViewById(R.id.nacl);

        c.setText(barangList.get(i).getCoombserum());
        n.setText(barangList.get(i).getNacl());

        return convertView;
    }


    public BarangAdapter(ArrayList<Barang> barang) {

        barangList = barang;
    }


}
