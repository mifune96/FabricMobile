package com.fabric.apps.mobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTransaksi  extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    private String mStatus,mKurir,mTanggal, mNopesanan;
    private int mTotalnayar,mOngkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dialog_detailtransaksi);
        ButterKnife.bind(this);
        initView();

    }

    private void initView(){
        Intent intent = getIntent();

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail Transaksi");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        mKurir = getIntent().getStringExtra("typeongkir");
        mStatus = getIntent().getStringExtra("status");
        mTanggal = getIntent().getStringExtra("tgltransaksi");
        mNopesanan = getIntent().getStringExtra("nopesanan");
        mTotalnayar = getIntent().getIntExtra("totalharga",0);
        mOngkir = getIntent().getIntExtra("shipingcost",0);

        kurir.setText(mKurir);
        status.setText(mStatus);
        Tgl.setText(mTanggal);
        noPesanan.setText(mNopesanan);
        Ongkir.setText(format.format(mOngkir));
        totalBayar.setText(format.format(mTotalnayar));

    }
}
