package com.example.bloodbankinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import java.util.List;

import com.example.bloodbankinventory.fragment.HomeFragment;

import java.util.prefs.Preferences;

public class login extends AppCompatActivity {

    String username="", password="";
    SessionManager session ;
    private EditText edituser, editpassword;
    private ImageButton login;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        edituser = findViewById(R.id.edituser);
        editpassword = findViewById(R.id.editpassword);
        login = (ImageButton) findViewById(R.id.login);

        editpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edituser.getText().toString().equals("soyidwahyud") && editpassword.getText().toString().equals("soyid24")) {
                    //Toast.makeText(getApplicationContext(),"login successfully",Toast.LENGTH_SHORT).show();
                    //session.createLoginSession("soyidwahyud", "soyid24");
                    //SessionManager.checkLogin(SessionManager.IS_LOGIN, true);

                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Pesan");
                    alertDialog.setMessage("Login berhasil");
                    alertDialog.show();
                    razia();



            /*if (SessionManager.isLoggedIn()){
                startActivity(new Intent(this, home.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }*/
                } else {
                    //Toast.makeText(getApplicationContext(),"wrong login",Toast.LENGTH_SHORT).show();

                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Pesan");
                    alertDialog.setMessage("Login gagal");


                    alertDialog.show();
                }
            }
        });
    }
        private void razia(){
            /* Mereset semua Error dan fokus menjadi default */
            edituser.setError(null);
            editpassword.setError(null);
            View fokus = null;
            boolean cancel = false;

            /* Mengambil text dari form User dan form Password dengan variable baru bertipe String*/
            String user = edituser.getText().toString();
            String password = editpassword.getText().toString();

            /* Jika form user kosong atau TIDAK memenuhi kriteria di Method cekUser() maka, Set error
             *  di Form User dengan menset variable fokus dan error di Viewnya juga cancel menjadi true*/
            if (TextUtils.isEmpty(user)){
                edituser.setError("This field is required");
                fokus = edituser;
                cancel = true;
            }else if(!cekUser(user)){
                edituser.setError("This Username is not found");
                fokus = edituser;
                cancel = true;
            }

            /* Sama syarat percabangannya dengan User seperti di atas. Bedanya ini untuk Form Password*/
            if (TextUtils.isEmpty(password)){
                editpassword.setError("This field is required");
                fokus = editpassword;
                cancel = true;
            }else if (!cekPassword(password)){
                editpassword.setError("This password is incorrect");
                fokus = editpassword;
                cancel = true;
            }

            /* Jika cancel true, variable fokus mendapatkan fokus */
            if (cancel) fokus.requestFocus();
            else masuk();
        }

        private void masuk(){
            shareprefs.setLoggedInUser(getBaseContext(),shareprefs.getRegisteredUser(getBaseContext()));
            shareprefs.setLoggedInStatus(getBaseContext(),true);
            startActivity(new Intent(getBaseContext(),home.class));finish();

            /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.loginForm, new HomeFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
        }


    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences */
    private boolean cekPassword(String password){
        return password.equals("soyid24");
    }

    /** True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user){
        return user.equals("soyidwahyud");
    }

}

