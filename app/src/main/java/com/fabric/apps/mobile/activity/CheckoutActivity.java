package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.model.cartModel.Cart_data;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener{

    SessionSharedPreferences session;

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
    RecyclerView rvproductList;

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

    private CartListAdapter cartListAdapter;
    private List<Cart_data> cart_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Atur Pesanan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        session = new SessionSharedPreferences(this.getApplicationContext());
        rvproductList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        changeAddress.setOnClickListener(this);

//        LoadJson();
    }


//    private void LoadJson() {
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Cart_parent> call;
//        call = apiInterface.getCartparent(2);
//        call.enqueue(new Callback<Cart_parent>() {
//            @Override
//            public void onResponse(Call<Cart_parent> call, ResponseSignup<Cart_parent> response) {
//            if (response.isSuccessful() && response.body().getCart_data() != null){
//                if (!cart_data.isEmpty()){
//                    cart_data.clear();
//                }
//
//                cart_data = response.body().getCart_data();
//                cartListAdapter = new CartListAdapter(CheckoutActivity.this,cart_data, totalPrice);
//                rvproductList.setAdapter(cartListAdapter);
//                cartListAdapter.notifyDataSetChanged();
//                totalPrice.setText("Rp. "+session.getJumlah().intValue());
////                totalPrice.setText("Rp. " + cartListAdapter.getTotalPayment().intValue());
//            } else {
//                Toast.makeText(CheckoutActivity.this, "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
//            }
//            }
//
//            @Override
//            public void onFailure(Call<Cart_parent> call, Throwable t) {
//
//            }
//        });
//
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_shipping_adress:
                Intent intent = new Intent(this, ChangeAddressActivity.class);
                intent.putExtra("TAG", "Change");
                startActivity(intent);
                break;
        }
    }
}
