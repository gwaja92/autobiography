package com.gwaja92.android.autobiography;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String getstr = getArguments().getString("send");


        Toast.makeText(getActivity(), getstr, Toast.LENGTH_LONG).show();


        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView nameTextView = view.findViewById(R.id.myNameInSettings);


        nameTextView.setText(getstr);

        // Inflate the layout for this fragment
        return view;





    }

}