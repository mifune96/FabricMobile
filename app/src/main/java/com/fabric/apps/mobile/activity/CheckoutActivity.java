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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.adapter.CheckoutListAdapter;
import com.fabric.apps.mobile.adapter.KurirListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.contoller.AddressController;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.model.cekkurirModel.CostItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.CostsItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.ResponseKurirModel;
import com.fabric.apps.mobile.model.profileModel.CustomerProfilModel;
import com.fabric.apps.mobile.model.transaksiPostModel.ResponseTransaksiPostModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CheckoutActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

    @BindView(R.id.iv_produk)
    ImageView productImage;

    @BindView(R.id.tv_namaproduk)
    TextView productName;

    @BindView(R.id.tv_hargaproduk)
    TextView productPrice;

    @BindView(R.id.tv_qty)
    TextView productQuantity;

    @BindView(R.id.progress_bar)
    SpinKitView progressBar;

    @BindView(R.id.sp_tipekurir)
    Spinner spkurir;

    @BindView(R.id.et_note)
    EditText note;

    private List<CostsItemKurirModel> costsItemKurirModels;
    private KurirListAdapter kurirListAdapter;


    private CheckoutListAdapter checkoutListAdapter;
    SessionSharedPreferences preferences;
    private List<CartItem> cartItems;
    public static String kurir;
    public static int total_ongkir;
    public static int totalbelanja;
    public static int HargaAkhir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);



        preferences = new SessionSharedPreferences(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Atur Pesanan");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        productList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        placeOrder.setOnClickListener(this);

        initradio();


        initCheckoutFromProduct();
        Log.d("TAG", "dari controller "+AddressController.addressid);

    }

    public void onRadioButtonClicked(View view){
        boolean cekradio = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rb_jne:
                if (cekradio) {
                   kurir = "jne";
                   initradio();
                }
                    break;

            case R.id.rb_pos:
                if (cekradio) {
                    kurir = "pos";
                    initradio();
                }
                    break;

            case R.id.rb_tiki:
                if (cekradio) {
                    kurir = "tiki";
                    initradio();
                }
                    break;
        }
    }

    private void initradio(){
    int id = preferences.getID();
    ConfigRetrofit.provideApiService().getKurir(id, kurir).enqueue(new Callback<ResponseKurirModel>() {
        @Override
        public void onResponse(Call<ResponseKurirModel> call, Response<ResponseKurirModel> response) {
            if (response.isSuccessful()) {
                costsItemKurirModels = response.body().getCosts();
                initDataSpinner();

                } else {
                Toast.makeText(CheckoutActivity.this, "Gagal Post Kurir", Toast.LENGTH_LONG).show();
              }
            }

        @Override
        public void onFailure(Call<ResponseKurirModel> call, Throwable t) {
            Log.d("TAG", "onFailure: " +t.toString());
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
                Locale locale = new Locale("in", "ID");

                NumberFormat format = NumberFormat.getCurrencyInstance(locale);

                CostsItemKurirModel cur = (CostsItemKurirModel) parent.getItemAtPosition(position);
                List<CostItemKurirModel> mom = cur.getCost();
                for (int i = 0; i<mom.size();i++){
                    total_ongkir = mom.get(i).getValue();
                   servicePrice.setText(format.format(total_ongkir));

                    HargaAkhir = totalbelanja+total_ongkir;

                   totalPayment.setText(format.format(HargaAkhir));

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initCheckoutFromProduct() {
        int id = preferences.getID();
        String token = preferences.getAccessToken();
        String key = "oa00000000app";
        ConfigRetrofit.provideApiService().getCart(id,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                if (response.isSuccessful()) {
                    Locale locale = new Locale("in", "ID");

                    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                    cartItems = response.body().getCart();
                    checkoutListAdapter = new CheckoutListAdapter(CheckoutActivity.this, cartItems);
                    totalPrice.setText(format.format(checkoutListAdapter.setTotalBayar()));
                    totalbelanja = (int) checkoutListAdapter.setTotalBayar();

                    productList.setAdapter(checkoutListAdapter);
                    checkoutListAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(CheckoutActivity.this, "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                Log.d("TAG", "onFailure: " +t.toString());
            }
        });

    }

    private void initDatatransaksi(){
        int id = preferences.getID();
        int idaddress = AddressController.addressid;
        String token = preferences.getAccessToken();
        String key = "oa00000000app";


        String edNote = note.getText().toString();

        if (edNote.isEmpty()) {
            note.setError("Catatan Untuk penjual tdk Boleh Kosong");
            note.requestFocus();
            return;
        }

        ConfigRetrofit.provideApiService().postTransaksi(key,token,id,idaddress,HargaAkhir,kurir,edNote,total_ongkir).enqueue(new Callback<ResponseTransaksiPostModel>() {
            @Override
            public void onResponse(Call<ResponseTransaksiPostModel> call, Response<ResponseTransaksiPostModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(CheckoutActivity.this, "Transaksi Berhasil", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(CheckoutActivity.this, "Transaksi Gagal", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksiPostModel> call, Throwable t) {
                Log.d("TAG", "onFailureTransaksi: " +t.toString());
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.place_order:
                initDatatransaksi();
                startActivity(new Intent(this,ActivityPembayaran.class));
                break;
        }
    }

}
