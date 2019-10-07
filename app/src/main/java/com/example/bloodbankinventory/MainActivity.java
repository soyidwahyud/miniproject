package com.example.bloodbankinventory;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;

import com.example.bloodbankinventory.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;
    String isLogin;

    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(getApplicationContext(), login.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }

}
