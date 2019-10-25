package com.example.bloodbankinventory.utils;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
public class BarangCRUD implements Parcelable {
    private int id;
    private int jumlah = 0;
    private String nama;
    private ArrayList<String> baraang =new ArrayList<>();
    private ArrayList<String> jumlaah  = new ArrayList<>();

    //protected BarangCRUD(Parcel in) {
    //    baraang = in.createStringArrayList();
    //    jumlaah = in.createStringArrayList();
    //}

    /*public static final Creator<BarangCRUD> CREATOR = new Creator<BarangCRUD>() {
        @Override
        public BarangCRUD createFromParcel(Parcel in) {
            return new BarangCRUD(in);
        }

        @Override
        public BarangCRUD[] newArray(int size) {
            return new BarangCRUD[size];
        }
    };*/

    /*public void save(String barang, String jumlah)

    {
        baraang.add(barang);
        jumlaah.add(jumlah);
    }

    public ArrayList<String> getBaraang()
    {
        return baraang;
    }
    public ArrayList<String>getJumlaah()
    {
        return jumlaah;
    }

    public Boolean update(int position,String newBarang, String newJumlah)
    {
        try {
            baraang.remove(position);
            baraang.add(position,newBarang);

            jumlaah.remove(position);
            jumlaah.add(position,newJumlah);

            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(int position)
    {
        try {
            baraang.remove(position);
            jumlaah.remove(position);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }*/
    public BarangCRUD(){

    }
    public BarangCRUD(int id, String nama,int jumlah) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringList(baraang);
        //dest.writeStringList(jumlaah);
        dest.writeInt(this.id);
        dest.writeString(this.nama);
        dest.writeInt(this.jumlah);

    }
    protected BarangCRUD(Parcel in) {
           this.id  = in.readInt();
           this.nama = in.readString();
           this.jumlah = in.readInt();
        }


    public static final Parcelable.Creator<BarangCRUD> CREATOR = new Parcelable.Creator<BarangCRUD>() {
        @Override
        public BarangCRUD createFromParcel(Parcel source) {
            return new BarangCRUD(source);
        }

        @Override
        public BarangCRUD[] newArray(int size) {
            return new BarangCRUD[size];
        }
    };
}
