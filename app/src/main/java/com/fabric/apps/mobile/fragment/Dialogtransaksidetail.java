package com.fabric.apps.mobile.fragment;

import android.app.Dialog;
import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dialogtransaksidetail extends DialogCartFragment {


    @BindView(R.id.tv_kurir_detail)
    TextView kurir;

    @BindView(R.id.tv_status_detail)
    TextView status;

    @BindView(R.id.tv_nopesanan_detail)
    TextView noPesanan;

    @BindView(R.id.tv_ongkir_detail)
    TextView Ongkir;

    @BindView(R.id.tv_bayar_total)
    TextView totalBayar;

    @BindView(R.id.tgl_transaksi_detail)
    TextView Tgl;

    public Dialogtransaksidetail() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_detailtransaksi, container, false);
//        ButterKnife.bind(this, view);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        if (bundle != null){

            kurir.setText(bundle.getString("typeongkir"));
            status.setText(bundle.getString("status"));
            totalBayar.setText(format.format(bundle.getInt("totalharga")));
            Ongkir.setText(format.format(bundle.getInt("shipingcost")));
            noPesanan.setText(bundle.getString("nopesanan"));
            Tgl.setText(bundle.getString("tgltransaksi"));


        }

        close.setOnClickListener(this);
        proccedCheckout.setOnClickListener(this);
        addMore.setOnClickListener(this);
        decrease.setOnClickListener(this);
        increase.setOnClickListener(this);

        return view;
    }

}
