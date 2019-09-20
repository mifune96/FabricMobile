package com.fabric.apps.mobile.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.contoller.AddressController;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_address)
    TextView userAddress;

    @BindView(R.id.user_phone_number)
    TextView userPhoneNumber;

    @BindView(R.id.change_shipping_adress)
    TextView changeAddress;

    @BindView(R.id.payment_method_info)
    TextView paymentInfo;

    @BindView(R.id.service_provider)
    LinearLayout serviceWrapper;

    @BindView(R.id.product_list)
    RecyclerView productList;

    @BindView(R.id.service_image)
    ImageView serviceImage;

    @BindView(R.id.service_name)
    TextView serviceName;

    @BindView(R.id.total_product_price)
    TextView totalPrice;

    @BindView(R.id.service_price)
    TextView servicePrice;

    @BindView(R.id.total_payment)
    TextView totalPayment;

    @BindView(R.id.place_order)
    Button placeOrder;

    @BindView(R.id.product_image)
    ImageView productImage;

    @BindView(R.id.product_name)
    TextView productName;

    @BindView(R.id.product_price)
    EasyMoneyTextView productPrice;

    @BindView(R.id.product_quantity)
    TextView productQuantity;

    @BindView(R.id.increase_product)
    ImageButton increaseProduct;

    @BindView(R.id.decrease_product)
    ImageButton decreaseProduct;

    @BindView(R.id.button_remove)
    ImageView removeButton;

    @BindView(R.id.cart_item)
    ViewGroup productDetail;

    @BindView(R.id.progress_bar)
    SpinKitView progressBar;

    private CartController cartController = new CartController();
    private Intent intent;
    private final int REQUEST_ADDRESS_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        intent = getIntent();
        String TAG = intent.getStringExtra("TAG");

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Atur Pesanan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

//        cartController.addcart(productList,progressBar,this,i);
        productList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        getMainAddress();
        initCheckoutFromProduct();

//        changeAddress.setOnClickListener(this);
//        if (TAG.equals("Product")){
//            progressBar.setVisibility(View.VISIBLE);
//            initCheckoutFromProduct();
//            progressBar.setVisibility(View.GONE);
//        } else if (TAG.equals("Cart")){
//            progressBar.setVisibility(View.VISIBLE);
//            initCheckoutFromCart();
//            progressBar.setVisibility(View.GONE);
//        }
//
//        changeAddress.setOnClickListener(this);
//        serviceWrapper.setOnClickListener(this);
//        paymentInfo.setOnClickListener(this);

    }
    private void getMainAddress() {
        AddressController addressController = new AddressController();
//        addressController.getMainAddress(1, this, userName, userAddress, userPhoneNumber);
    }

    private void initCheckoutFromCart() {


    }

    private void initCheckoutFromProduct() {
        productName.setText(intent.getStringExtra("product_name"));
        productPrice.setText(intent.getStringExtra("product_price"));
        productPrice.setCurrency("Rp");
        productPrice.showCommas();
        productPrice.showCurrencySymbol();

        if (!intent.getStringExtra("product_image").isEmpty()){
            Glide.with(this).load(intent.getStringExtra("product_image")).into(productImage);
        } else {
            productImage.setImageResource(R.drawable.default_image_placeholder);
        }

        removeButton.setVisibility(View.GONE);
        productDetail.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_shipping_adress:
            Intent intent = new Intent(this, ProfileSettingDetailActivity.class);
            intent.putExtra("TAG", "Shipping Address");
            intent.putExtra("Func", "GET");
            startActivityForResult(intent, REQUEST_ADDRESS_CODE);
            break;
            case R.id.payment_method_info:
                break;
            case R.id.service_provider:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADDRESS_CODE || resultCode == RESULT_OK && data != null){
            userName.setText(data.getStringExtra("Name"));
            userAddress.setText(data.getStringExtra("Description"));
            userPhoneNumber.setText(data.getStringExtra("PhoneNumber"));
        }
    }
}
