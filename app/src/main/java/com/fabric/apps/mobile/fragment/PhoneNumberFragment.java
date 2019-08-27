package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabric.apps.mobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneNumberFragment extends Fragment {


    public PhoneNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number, container, false);
    }

}
