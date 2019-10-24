package com.fabric.apps.mobile.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.List;
import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
//
//    @BindView(R.id.user_name)
//    TextView userName;

//    @BindView(R.id.user_address)
//    TextView userAddress;
//
//    @BindView(R.id.user_phone_number)
//    TextView userPhoneNumber;
//
//    @BindView(R.id.change_shipping_adress)
//    TextView changeAddress;
//
//    @BindView(R.id.payment_method_info)
//    TextView paymentInfo;

    @BindView(R.id.service_provider)
    LinearLayout serviceWrapper;

    @BindView(R.id.product_list)
    RecyclerView productList;

    @BindView(R.id.rb_jne)
    RadioButton rbJne;

    @BindView(R.id.rb_pos)
    RadioButton rbPos;

    @BindView(R.id.rb_tiki)
    RadioButton rbTiki;

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
    TextView productPrice;

    @BindView(R.id.product_quantity)
    TextView productQuantity;

    @BindView(R.id.increase_product)
    ImageButton increaseProduct;

    @BindView(R.id.decrease_product)
    ImageButton decreaseProduct;

    @BindView(R.id.button_remove)
    ImageView removeButton;

    @BindView(R.id.progress_bar)
    SpinKitView progressBar;

    private CartController cartController = new CartController();
    private Intent intent;
    private CartListAdapter cartListAdapter;
    SessionSharedPreferences preferences;
    private List<CartItem> cartItems;
    private final int REQUEST_ADDRESS_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        intent = getIntent();
        String TAG = intent.getStringExtra("TAG");
        preferences = new SessionSharedPreferences(this);

        removeButton.setVisibility(View.GONE);
        increaseProduct.setVisibility(View.GONE);
        decreaseProduct.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Atur Pesanan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

//        cartController.addcart(productList,progressBar,this,i);
        productList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


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


    private void initCheckoutFromCart() {


    }

    private void initCheckoutFromProduct() {
        int id = preferences.getID();
        String token = preferences.getAccessToken();
        String key = "oa00000000app";
        ConfigRetrofit.provideApiService().getCart(id,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                if (response.isSuccessful()) {
                    cartItems = response.body().getCart();
                    cartListAdapter = new CartListAdapter(CheckoutActivity.this, cartItems, totalPrice);
                    productList.setAdapter(cartListAdapter);
                    cartListAdapter.notifyDataSetChanged();
                    removeButton.setVisibility(View.GONE);
                    increaseProduct.setVisibility(View.GONE);
                    decreaseProduct.setVisibility(View.GONE);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Gagal bro", Toast.LENGTH_LONG).show();
            }
        });
//        productName.setText(intent.getStringExtra("product_name"));
//        productPrice.setText(intent.getStringExtra("product_price"));
//
//
//        if (!intent.getStringExtra("product_image").isEmpty()){
//            Glide.with(this).load(intent.getStringExtra("product_image")).into(productImage);
//        } else {
//            productImage.setImageResource(R.drawable.default_image_placeholder);
//        }



//        productDetail.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.service_provider:
                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_ADDRESS_CODE || resultCode == RESULT_OK && data != null){
////            userName.setText(data.getStringExtra("Name"));
////            userAddress.setText(data.getStringExtra("Description"));
////            userPhoneNumber.setText(data.getStringExtra("PhoneNumber"));
//        }
//    }
}
