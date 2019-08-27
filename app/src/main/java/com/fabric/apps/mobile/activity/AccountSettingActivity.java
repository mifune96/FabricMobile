package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.AccountSettingListAdapter;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.Objects;

public class AccountSettingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.account_setting_list)
    RecyclerView settingList;

    @BindView(R.id.button_logout)
    Button btLogout;
    SessionSharedPreferences sessionSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);
        sessionSharedPreferences = new SessionSharedPreferences(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pengaturan Akun");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        settingList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        settingList.setAdapter(new AccountSettingListAdapter(this));

        btLogout.setOnClickListener(v -> {
            sessionSharedPreferences.logout();
            startActivity(new Intent(this, Login_Signup_Activity.class));
            finish();
        });
    }
}
