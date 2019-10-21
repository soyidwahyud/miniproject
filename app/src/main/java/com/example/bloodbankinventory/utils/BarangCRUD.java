package com.example.bloodbankinventory.utils;
import java.util.ArrayList;
public class BarangCRUD {
    private ArrayList<String> baraang =new ArrayList<>();
    private ArrayList<String> jumlaah  = new ArrayList<>();

    public void save(String barang, String jumlah)

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
    }
}
