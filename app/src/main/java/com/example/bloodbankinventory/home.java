package com.example.bloodbankinventory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.bloodbankinventory.fragment.Data_KeluarFragment;
import com.example.bloodbankinventory.fragment.Data_MasukFragment;
import com.example.bloodbankinventory.fragment.HistoryFragment;
import com.example.bloodbankinventory.fragment.HomeFragment;
import com.example.bloodbankinventory.fragment.LogOutFragment;
import com.example.bloodbankinventory.fragment.customDialogFragment;
import com.example.bloodbankinventory.utils.BarangCRUD;
import com.google.android.material.navigation.NavigationView;


public class home extends AppCompatActivity implements HistoryFragment.OnFragmentInteractionListener, customDialogFragment.OnFragmentInteractionListener {
    private Context ctx;
    private static final String TAG = "home";
    Button logout;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;

    private HistoryFragment historyFragment;
    private customDialogFragment CustomDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logout = (Button) findViewById(R.id.logout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        historyFragment = new HistoryFragment();
        CustomDialogFragment = new customDialogFragment();

        loadFragment(new HomeFragment());
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu2);
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);

        drawerToggle = setupDrawerToggle();
        final NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout.addDrawerListener(drawerToggle);
        setupNavDrawer(navigationView);


        this.getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        Fragment current = getCurrentFragment();
                        if(current instanceof HomeFragment){
                            navigationView.setCheckedItem(R.id.nav_home);
                        }
                        else if (current instanceof HistoryFragment) {
                            navigationView.setCheckedItem(R.id.nav_history);
                        }
                        else if(current instanceof LogOutFragment) {
                            navigationView.setCheckedItem(R.id.nav_logout);
                        }
                    }
                });

    }

    public Fragment getCurrentFragment() {
        return this.getSupportFragmentManager().findFragmentById(R.id.home);
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
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_history:
                        fragment = new HistoryFragment();
                        break;

                    case R.id.nav_logout:
                        fragment = new LogOutFragment();
                        break;
                    default:
                        fragment = new HistoryFragment();
                }
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();
                return loadFragment(fragment);
            }
        });
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
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
        //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        /*switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }*/
        if (item.getItemId() == android.R.id.home) {
            Log.i(TAG, "onOptionsItemSelected: Home Button Clicked");
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClickedBarang(BarangCRUD barang) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Data", barang);
        customDialogFragment dialog = new customDialogFragment();
        dialog.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, dialog)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void addNewBarang(BarangCRUD barang) {
        historyFragment.newBarang(barang);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, historyFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void updateBarang(BarangCRUD barang) {
        historyFragment.newBarang(barang);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, historyFragment)
                .commit();
    }


    /*@Override
    public void btnAdd() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new HistoryFragment())
                .addToBackStack(null)
                .commit();

        if (!historyFragment.isAdded()){
           FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frame_content,historyFragment,"HistoryFragment");
            transaction.commit();
            finish();
        }
    }*/

    @Override
    public void btnOpen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, new customDialogFragment())
                .addToBackStack(null)
                .commit();
    }
}
