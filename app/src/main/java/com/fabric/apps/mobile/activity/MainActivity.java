package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.AddressController;
import com.fabric.apps.mobile.contoller.TransaksiController;
import com.fabric.apps.mobile.fragment.CartFragment;
import com.fabric.apps.mobile.fragment.CatalogueFragment;
import com.fabric.apps.mobile.fragment.FavoriteFragment;
import com.fabric.apps.mobile.fragment.HomeFragment;
import com.fabric.apps.mobile.fragment.ProfileFragment;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SessionSharedPreferences sm;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView mBotNavBar;

    @BindView(R.id.button_search)
    Button searchButton;

    private static String TAG;
    private Fragment homeFragment = new HomeFragment();
    private Fragment catalogFragment = new CatalogueFragment();
    private Fragment cartFragment = new CartFragment();
    private Fragment profileFragment = new ProfileFragment();
    private Fragment activeFragment = homeFragment;

    private AddressController addressController = new AddressController();
    private TransaksiController transaksiController = new TransaksiController();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sm = new SessionSharedPreferences(this);

        addressController.getAddress(this);


        TAG = "Home";


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(R.string.nav_home_toolbar_title);

//        searchButton.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.VISIBLE);

        initFragment();

        mBotNavBar.setOnNavigationItemSelectedListener(itemSelectedListener);
        searchButton.setOnClickListener(this);
    }



    private void initFragment(){
        fragmentManager.beginTransaction().add(R.id.main_container, homeFragment, "Home").commit();
        fragmentManager.beginTransaction().add(R.id.main_container, cartFragment,"Cart")
                .hide(cartFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, catalogFragment,"Catalog")
                .hide(catalogFragment).commit();
        fragmentManager.beginTransaction().add(R.id.main_container, profileFragment,"Profile")
                .hide(profileFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener = menuItem -> {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                TAG = "Home";
                setBottomNavBar(TAG);
                return true;
            case R.id.nav_catalog:
                TAG = "Catalog";
                setBottomNavBar(TAG);
                return true;
            case R.id.nav_cart:
                TAG = "Cart";
                setBottomNavBar(TAG);
                return true;
            case R.id.nav_user:
                TAG = "Profile";
                setBottomNavBar(TAG);
                return true;
            default:
                return false;
        }
    };

    private void setBottomNavBar(String tag){

        switch (tag){
            case "Home":
                fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
                activeFragment = homeFragment;
                Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(R.string.nav_home_toolbar_title);
//                searchButton.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                break;
            case "Catalog":
                fragmentManager.beginTransaction().hide(activeFragment).show(catalogFragment).commit();
                activeFragment = catalogFragment;
                Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(getString(R.string.nav_catalogue_toolbar_title));
//                searchButton.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                break;
            case "Cart":
                fragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit();
                activeFragment = cartFragment;
                Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
                getSupportActionBar().setTitle(R.string.nav_cart_toolbar_title);
//                searchButton.setVisibility(View.GONE);
                toolbar.setVisibility(View.VISIBLE);
                break;
            case "Profile":
                fragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit();
                activeFragment = profileFragment;
                Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
                toolbar.setVisibility(View.GONE);
//                searchButton.setVisibility(View.GONE);
                break;
                default:
                    break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()){
//            case R.id.menu_notif:
//                startActivity(new Intent(this, NotificationActivity.class));
//                return true;
            case R.id.menu_chat:
                Intent openWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6281368009882&text=Hi%20there,%20i%20love%20your%20product.%20Contact%20Me."));
                startActivity(openWA);
                return true;
                default:
                    return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setBottomNavBar(TAG);
    }

    boolean doubleBackPressed = false;

    @Override
    public void onBackPressed() {
        if (doubleBackPressed){
            super.onBackPressed();
        }

        this.doubleBackPressed = true;
        Toast.makeText(this, "Ketuk Dua Kali Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackPressed = false, 2000);
    }
}
