package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.WindowManager;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.ViewPagerAdapter;
import com.fabric.apps.mobile.fragment.LoginFragment;
import com.fabric.apps.mobile.fragment.SignUpFragment;
import com.google.android.material.tabs.TabLayout;

public class Login_Signup_Activity extends AppCompatActivity {

    @BindView(R.id.login_signup_tab)
    TabLayout tabLayout;

    @BindView(R.id.login_signup_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login__signup_);
        ButterKnife.bind(this);
        
        setupTabLayout();
        setupViewPager();

        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager() {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new LoginFragment(), "Masuk");
        pagerAdapter.addFragment(new SignUpFragment(), "Daftar");
        viewPager.setAdapter(pagerAdapter);
    }

    private void setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setTag("Masuk"));
        tabLayout.addTab(tabLayout.newTab().setTag("Daftar"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
