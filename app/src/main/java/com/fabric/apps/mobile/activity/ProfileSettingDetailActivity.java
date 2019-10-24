package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.fragment.EmailAddressFragment;
import com.fabric.apps.mobile.fragment.PasswordFragment;
import com.fabric.apps.mobile.fragment.PhoneNumberFragment;
import com.fabric.apps.mobile.fragment.ShippingAdressFragment;

import java.util.Objects;

public class ProfileSettingDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Fragment shippingAddress = new ShippingAdressFragment();
    private Fragment phoneNumber = new PhoneNumberFragment();
    private Fragment emailAddress = new EmailAddressFragment();
    private Fragment password = new PasswordFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting_detail);
        ButterKnife.bind(this);

        intent = getIntent();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        initFragment();
        setupContainer();

        Bundle bundle = new Bundle();
        bundle.putString("TAG", "Show");
        shippingAddress.setArguments(bundle);
    }

    private void setupContainer() {
        switch (intent.getStringExtra("TAG")){
            case "Shipping AddressDaftarModel":
                fragmentManager.beginTransaction().show(shippingAddress).commit();
                break;
            case "Phone Number":
                fragmentManager.beginTransaction().show(phoneNumber).commit();
                break;
            case "Email AddressDaftarModel":
                fragmentManager.beginTransaction().show(emailAddress).commit();
                break;
            case "Password":
                fragmentManager.beginTransaction().show(password).commit();
                break;
                default:
                    break;

        }
    }

    private void initFragment() {
        fragmentManager.beginTransaction().add(R.id.setting_container, shippingAddress)
                .hide(shippingAddress).commit();
        fragmentManager.beginTransaction().add(R.id.setting_container, phoneNumber)
                .hide(phoneNumber).commit();
        fragmentManager.beginTransaction().add(R.id.setting_container, emailAddress)
                .hide(emailAddress).commit();
        fragmentManager.beginTransaction().add(R.id.setting_container, password)
                .hide(password).commit();
    }
}
