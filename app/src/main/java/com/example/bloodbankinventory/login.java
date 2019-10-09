package com.example.bloodbankinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.inputmethod.EditorInfo;
import androidx.fragment.app.Fragment;
import java.util.List;

import com.example.bloodbankinventory.fragment.HomeFragment;

import java.util.prefs.Preferences;

public class login extends AppCompatActivity {
    String username="", password="";
    SessionManager session ;
    private CheckBox checkBoxRememberMe;
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
                    alertDialog.setMessage("Login Berhasil");
                    alertDialog.show();
                    razia();
                } else {
                    //Toast.makeText(getApplicationContext(),"wrong login",Toast.LENGTH_SHORT).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
                    alertDialog.setTitle("Pesan");
                    alertDialog.setMessage("Login gagal");
                    alertDialog.show();
                }
            }
        });
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        if (!new shareprefs(login.this).isUserLogedOut()) {
            startHomeActivity();
        }
    }
        private void razia(){
            // Reset errors.
            edituser.setError(null);
            editpassword.setError(null);

            // Store values at the time of the login attempt.
            String username = edituser.getText().toString();
            String password = editpassword.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // Check for a valid password, if the user entered one.
            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                editpassword.setError(getString(R.string.invalid_password));
                focusView = editpassword;
                cancel = true;
            }

            // Check for a valid email address.
            if (TextUtils.isEmpty(username)) {
                edituser.setError(getString(R.string.invalid_username));
                focusView = edituser;
                cancel = true;
            } else if (!isEmailValid(username)) {
                edituser.setError(getString(R.string.failed_user));
                focusView = edituser;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            }else {
                // save data in local shared preferences
                if (checkBoxRememberMe.isChecked())
                    saveLoginDetails(username, password);
                startHomeActivity();
            }

            /* Jika cancel true, variable fokus mendapatkan fokus */
            //if (cancel) fokus.requestFocus();
            //else masuk();
        }
            private void startHomeActivity() {
                Intent intent = new Intent(this, home.class);
                startActivity(intent);
                finish();

                //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                //fragmentTransaction.replace(R.id.dynamic_fragment_placeholder,new HomeFragment());
                //fragmentTransaction.commit();

            }

        /*private void masuk(){
            shareprefs.setLoggedInUser(getBaseContext(),shareprefs.getRegisteredUser(getBaseContext()));
            shareprefs.setLoggedInStatus(getBaseContext(),true);

            AlertDialog alertDialog = new AlertDialog.Builder(login.this).create();
            alertDialog.setTitle("Pesan");
            alertDialog.setMessage("Login berhasil");
            alertDialog.show();
            startActivity(new Intent(getBaseContext(),home.class));finish();

            /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.loginForm, new HomeFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }*/
    private void saveLoginDetails(String username, String password) {
        new shareprefs(login.this).saveLoginDetails(username, password);
    }

    private boolean cekPassword(String password){
        return password.equals("soyid24");
    }

    private boolean cekUser(String user){
        return user.equals("soyidwahyud");
    }
    private boolean isEmailValid(String username) {
        //TODO: Replace this with your own logic
        return username.equals("soyidwahyud");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

