package com.example.bloodbankinventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.bloodbankinventory.fragment.Data_KeluarFragment;
import com.example.bloodbankinventory.fragment.Data_MasukFragment;
import com.example.bloodbankinventory.fragment.HistoryFragment;
import com.example.bloodbankinventory.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;


public class home extends AppCompatActivity {
    private Context ctx;
    Button logout;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    private int waktu_loading=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = (Button) findViewById(R.id.logout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout.addDrawerListener(drawerToggle);
        setupNavDrawer(navigationView);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke Login Activity
                shareprefs.clearLoggedInUser(getBaseContext());
                startActivity(new Intent(getBaseContext(), login.class));
                finish();
            }
        });
    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupNavDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_history:
                        fragment = new HistoryFragment();
                        break;
                    case R.id.nav_datam:
                        fragment = new Data_MasukFragment();
                        break;
                    case R.id.nav_datak:
                        fragment = new Data_KeluarFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_content, fragment).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    public void CheckSession(){

        Boolean Check = Boolean.valueOf(shareprefs.readSharedSetting(ctx,"BloodBankInventory","true"));

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

    @SuppressLint("WrongConstant")
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
        switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
