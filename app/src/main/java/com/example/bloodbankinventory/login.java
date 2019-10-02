package com.example.bloodbankinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class login extends AppCompatActivity {

    String username="", password="";
    SessionManager session ;
    private EditText edituser, editpassword;
    private ImageButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        edituser = findViewById(R.id.edituser);
        editpassword = findViewById(R.id.editpassword);
        login = (ImageButton)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edituser.getText().toString().equals("soyidwahyud") && editpassword.getText().toString().equals("soyid24")){
                    //Toast.makeText(getApplicationContext(),"login successfully",Toast.LENGTH_SHORT).show();
                    //session.createLoginSession("soyidwahyud", "soyid24");
                    //SessionManager.checkLogin(SessionManager.IS_LOGIN, true);
                    shareprefs.saveSharedSetting(login.this, "BloodBankInventory", "false");
                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Pesan");
                    alertDialog.setMessage("Login berhasil");
                    alertDialog.show();

                    Intent home = new Intent(getApplicationContext(),home.class);
                    startActivity(home);
                    finish();
            /*if (SessionManager.isLoggedIn()){
                startActivity(new Intent(this, home.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }*/
                }
                else{
                    //Toast.makeText(getApplicationContext(),"wrong login",Toast.LENGTH_SHORT).show();

                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Pesan");
                    alertDialog.setMessage("Login gagal");


                    alertDialog.show();
                }
            }
        });

    }



}
