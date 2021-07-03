package com.gwaja92.android.autobiography;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SettingFragment extends Fragment {

    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String name = sharedPref.getString("sharedName", "");


        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView nameTextView = view.findViewById(R.id.myNameInSettings);
        nameTextView.setText(name);

        // Inflate the layout for this fragment
        return view;


    }

}