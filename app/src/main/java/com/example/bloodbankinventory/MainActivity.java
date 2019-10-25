package com.example.bloodbankinventory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.bloodbankinventory.fragment.HistoryFragment;
import com.example.bloodbankinventory.fragment.HomeFragment;
import com.example.bloodbankinventory.fragment.customDialogFragment;
import com.example.bloodbankinventory.utils.BarangCRUD;

public class MainActivity extends AppCompatActivity implements HistoryFragment.OnFragmentInteractionListener, customDialogFragment.OnFragmentInteractionListener {
    private HistoryFragment historyFragment;
    private customDialogFragment CustomDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyFragment = new HistoryFragment();
        CustomDialogFragment = new customDialogFragment();

        Intent login =new Intent(getApplicationContext(), login.class);
        startActivity(login);
        finish();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickedBarang(BarangCRUD barang) {

    }

    @Override
    public void addNewBarang(BarangCRUD barang) {
        historyFragment.newBarang(barang);
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.frame_content, historyFragment)
        .commit();
        }

    @Override
    public void updateBarang(BarangCRUD barang) {

    }

    @Override
    public void btnAdd() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new HistoryFragment())
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void btnOpen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new customDialogFragment())
                .addToBackStack(null)
                .commit();
    }

    //@Override
    //public void onSave(int jumlah, String nama) {

    //}
}
