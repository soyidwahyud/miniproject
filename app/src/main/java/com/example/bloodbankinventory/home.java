package com.example.bloodbankinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
    Button logout;
    private int waktu_loading=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = (Button) findViewById(R.id.logout);




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareprefs.saveSharedSetting(home.this, "BloodBankInventory", "true");

                Intent logout = new Intent(getApplicationContext(), login.class);
                startActivity(logout);
                finish();
            }
        });
        CheckSession();
    }
    public void CheckSession(){

        Boolean Check = Boolean.valueOf(shareprefs.readSharedSetting(this, "BloodBankInventory", "true"));

        Intent introIntent = new Intent(this, login.class);
        introIntent.putExtra("BloodBankInventory", Check);

        //The Value if you click on Login Activity and Set the value is FALSE and whe false the activity will be visible
        if (Check) {
            startActivity(introIntent);
            finish();
        } //If no the Main Activity not Do Anything
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
