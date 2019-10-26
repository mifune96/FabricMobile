package com.fabric.apps.mobile.activity;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.adapter.KurirListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.model.cekkurirModel.CostItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.CostsItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.ResponseKurirModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
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

    @BindView(R.id.sp_tipekurir)
    Spinner spkurir;

    private List<CostsItemKurirModel> costsItemKurirModels;
    private List<ResponseKurirModel> responkurir;
    private List<CostItemKurirModel> kuriritem;
    private KurirListAdapter kurirListAdapter;


    private CartController cartController = new CartController();
    private Intent intent;
    private CartListAdapter cartListAdapter;
    SessionSharedPreferences preferences;
    private List<CartItem> cartItems;
    private final int REQUEST_ADDRESS_CODE = 100;
    public static String kurir;
    public static int harga;


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

//        initradio();

//        spkurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String harga = parent.getItemAtPosition(position).toString();
//                Toast.makeText(CheckoutActivity.this, "" + harga, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Atur Pesanan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

//        servicePrice.setText("Rp. "+harga);


        productList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        initCheckoutFromProduct();

    }

    public void onRadioButtonClicked(View view){
        boolean cekradio = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rb_jne:
                if (cekradio) {
                   kurir = "jne";
                   initradio();
//                   servicePrice.setText("Rp. "+harga);
                }
                    break;

            case R.id.rb_pos:
                if (cekradio)
                    kurir = "pos";
                    initradio();
//                    servicePrice.setText("Rp. "+harga);
                    break;

            case R.id.rb_tiki:
                if (cekradio)
                    kurir = "tiki";
                    initradio();
//                    servicePrice.setText("Rp. "+harga);
                    break;
        }
    }



private void initradio(){
    int id = preferences.getID();

    ConfigRetrofit.provideApiService().getKurir(id,kurir).enqueue(new Callback<ResponseKurirModel>() {
        @Override
        public void onResponse(Call<ResponseKurirModel> call, Response<ResponseKurirModel> response) {
            costsItemKurirModels = response.body().getCosts();

//            ArrayAdapter<String> apterbgs = new ArrayAdapter<>()

           initDataSpinner();
        }

        @Override
        public void onFailure(Call<ResponseKurirModel> call, Throwable t) {

        }
    });
}

    private void initDataSpinner() {
        kurirListAdapter = new KurirListAdapter(this,costsItemKurirModels);

        kurirListAdapter.notifyDataSetChanged();
        spkurir.setAdapter(kurirListAdapter);

        spkurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CostsItemKurirModel cur = (CostsItemKurirModel) parent.getItemAtPosition(position);

                servicePrice.setText(cur.getCost().get(position).getValue());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        spkurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    CostsItemKurirModel cost = (CostsItemKurirModel) parent.getItemAtPosition(position);
//                    //servicePrice.setText("Rp. " + cost.getCost().get(position));
//                    Toast.makeText(CheckoutActivity.this, "" + cost.getCost().get(position).getValue(), Toast.LENGTH_SHORT).show();
//                } catch (IndexOutOfBoundsException e) {
//                    Toast.makeText(CheckoutActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
////                String price;
////                switch (position) {
////                    case 0:
////                        price = costsItemKurirModels.get(0).getCost().get(position).getValue().toString();
////                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
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
