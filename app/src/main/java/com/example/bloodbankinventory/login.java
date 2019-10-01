package com.example.bloodbankinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class login extends AppCompatActivity {

    String username="", password="";
    private EditText edituser, editpassword;
    private ImageButton login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edituser = findViewById(R.id.edituser);
        editpassword = findViewById(R.id.editpassword);
        login = (ImageButton)findViewById(R.id.login);
    }


    public void loginOnClick(View view) {
        if(edituser.getText().toString().equals("soyidwahyud") && editpassword.getText().toString().equals("soyid24")){
            //Toast.makeText(getApplicationContext(),"login successfully",Toast.LENGTH_SHORT).show();

            AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
            alertDialog.setTitle("Pesan");
            alertDialog.setMessage("Login berhasil");

            alertDialog.show();
            Intent home = new Intent(this,home.class);
            startActivity(home);
        }
        else{
            //Toast.makeText(getApplicationContext(),"wrong login",Toast.LENGTH_SHORT).show();

            AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
            alertDialog.setTitle("Pesan");
            alertDialog.setMessage("Login gagal");


            alertDialog.show();
        }
    }
}
