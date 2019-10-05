package com.example.bloodbankinventory.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.login;
import com.example.bloodbankinventory.shareprefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Button logout;
    private Context ctx;
    SharedPreferences sharedpref;
    shareprefs sh;
    public static final  String defaultValue = "true";
    TextView nama;


 @Override
 public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                          @Nullable Bundle savedInstanceState) {
     View v = inflater.inflate(R.layout.fragment_home, container, false);
     logout = (Button) v.findViewById(R.id.logout);


     logout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //Menghapus Status login dan kembali ke Login Activity
             shareprefs.clearLoggedInUser(getActivity().getBaseContext());
             startActivity(new Intent(getActivity().getBaseContext(), login.class));
             getActivity().finish();
         }
     });
     return v;
 }

    /*public void CheckSession(){

        Boolean Check = Boolean.valueOf(shareprefs.readSharedSetting(ctx,"BloodBankInventory","true"));

        Intent introIntent = new Intent(ctx, login.class);
        introIntent.putExtra("BloodBankInventory", Check);

        //The Value if you click on Login Activity and Set the value is FALSE and whe false the activity will be visible
        if (Check) {
            startActivity(introIntent);
            getActivity().finish();;
        } //If no the Main Activity not Do Anything
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);

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
        return super.onOptionsItemSelected(item);
    }*/
}

