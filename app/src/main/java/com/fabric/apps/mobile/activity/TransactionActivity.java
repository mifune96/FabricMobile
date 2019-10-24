package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.ViewPagerAdapter;
import com.fabric.apps.mobile.fragment.TransactionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class TransactionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.transaction_tab)
    TabLayout transactionTab;

    @BindView(R.id.transaction_pager)
    ViewPager transactionPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Transaksi");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        setupTabLayout();
        setupViewPager();

        transactionTab.setupWithViewPager(transactionPager);
    }

    private void setupViewPager() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new TransactionFragment(), "Membayar");
        pagerAdapter.addFragment(new TransactionFragment(), "Dikirim");
        pagerAdapter.addFragment(new TransactionFragment(), "Diterima");
        pagerAdapter.addFragment(new TransactionFragment(), "Selesai");
        pagerAdapter.addFragment(new TransactionFragment(), "Dibatalkan");
        transactionPager.setAdapter(pagerAdapter);
    }

    private void setupTabLayout() {
        transactionTab.addTab(transactionTab.newTab().setTag("To Pay"));
        transactionTab.addTab(transactionTab.newTab().setTag("To Ship"));
        transactionTab.addTab(transactionTab.newTab().setTag("To Receive"));
        transactionTab.addTab(transactionTab.newTab().setTag("Completed"));
        transactionTab.addTab(transactionTab.newTab().setTag("Cancelled"));
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        menu.findItem(R.id.menu_notif).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_chat:
                Intent openWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6281368009882&text=Hi%20there,%20i%20love%20your%20product.%20Contact%20Me."));
                startActivity(openWA);
                return true;
                default:
                    return false;
        }
    }
}
