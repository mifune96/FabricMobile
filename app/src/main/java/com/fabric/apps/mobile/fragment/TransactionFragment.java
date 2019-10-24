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
public class TransactionFragment extends Fragment {


    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
        * Disini nanti kan pake recyclerview
        * tinggal pake parameter aja tiap dia ganti tab parameternya berubah
        * jadi isinya ikutan berubah
        * make TAG lagi
        * ga harus TAG, itu cuma nama variabel doang
        * */
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

}
