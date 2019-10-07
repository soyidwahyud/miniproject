package com.example.bloodbankinventory.fragment;


import android.content.Intent;
import android.media.tv.TvInputService;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankinventory.R;
import com.example.bloodbankinventory.SessionManager;
import com.example.bloodbankinventory.login;
import com.example.bloodbankinventory.shareprefs;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogOutFragment extends Fragment {
    //private TextView logout;
    //SessionManager session;
    //private Session session;
    private shareprefs session;
    private Button logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_log_out, container, false);

        logout = (Button) rootView.findViewById(R.id.logout);


        //session = new shareprefs();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareprefs p=new shareprefs(getContext());
                boolean ans=p.Logout();

                if(ans){
                    Intent login = new Intent(getActivity(),login.class);
                    startActivity(login);
                    getActivity().finish();
                }
                else {
                    Toast.makeText(getContext(),"Cannot Log out",Toast.LENGTH_SHORT);
                }
            }
        });
        return rootView;
    }
}