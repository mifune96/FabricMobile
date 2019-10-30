package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.TransaksiController;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.transaction_list)
    RecyclerView transaksilist;

    SessionSharedPreferences sessionSharedPreferences;
    private TransaksiController transaksiController = new TransaksiController();

    public static TransactionFragment newInstance() {
        return new TransactionFragment();
    }

    public TransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        ButterKnife.bind(this, view);

        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        transaksilist.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        transaksiController.getAllTransaksi(transaksilist,getContext());


        /*
        * Disini nanti kan pake recyclerview
        * tinggal pake parameter aja tiap dia ganti tab parameternya berubah
        * jadi isinya ikutan berubah
        * make TAG lagi
        * ga harus TAG, itu cuma nama variabel doang
        * */
        return view;
    }



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
