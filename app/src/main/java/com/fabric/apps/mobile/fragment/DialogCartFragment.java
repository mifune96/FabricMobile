package com.fabric.apps.mobile.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogCartFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.button_close)
    ImageView close;

    @BindView(R.id.sub_total)
    TextView subTotal;

    @BindView(R.id.proceed_to_checkout)
    Button proccedCheckout;

    @BindView(R.id.add_more)
    Button addMore;

    @BindView(R.id.product_image)
    ImageView productImage;

    @BindView(R.id.tv_namaproduk_dialog)
    TextView productName;

    @BindView(R.id.tv_hargaproduk_dialog)
    TextView productPrice;

    @BindView(R.id.tv_warna_dialog)
    TextView warna;

    @BindView(R.id.button_remove)
    ImageView buttonRemove;

    @BindView(R.id.increase_product)
    ImageButton increase;

    @BindView(R.id.decrease_product)
    ImageButton decrease;

    @BindView(R.id.product_quantity)
    TextView productQuantity;

    private int id;
    public double permeter = 1.0;

    private CartController cartController = new CartController();
    SessionSharedPreferences sessionSharedPreferences;


    public DialogCartFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_cart, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        buttonRemove.setVisibility(View.GONE);
        Bundle bundle = this.getArguments();

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        if (bundle != null){
            if (!Objects.requireNonNull(bundle.getString("product_image")).isEmpty()){
                Glide.with(this).load(bundle.getString("product_image")).into(productImage);
            } else {
                productImage.setImageResource(R.drawable.default_image_placeholder);
            }

            warna.setText(bundle.getString("product_color"));
            productName.setText(bundle.getString("product_name"));
            productPrice.setText(format.format(bundle.getInt("product_price")));
            subTotal.setText(format.format(bundle.getInt("product_price")));

            id = bundle.getInt("product_id");

        }

        close.setOnClickListener(this);
        proccedCheckout.setOnClickListener(this);
        addMore.setOnClickListener(this);
        decrease.setOnClickListener(this);
        increase.setOnClickListener(this);

        return view;
    }

    public void Adddialogcart(){
        int idcostumer = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        Log.d("TAG", "qtynya : " +token +id);

        ConfigRetrofit.provideApiService().postCart(key,token,idcostumer,id,permeter).enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Berhasil Menambahkan Barang", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "Berhasil memasukkan cardialog");
                } else {
//                    Toast.makeText(getActivity(), "Gagal Menambahkan Barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Log.d("TAG", "errormemasukkan barang : " +t.toString());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_close:
                refresh();
                break;

            case R.id.add_more:
                Adddialogcart();
                refresh();
                break;

            case R.id.proceed_to_checkout:
                Adddialogcart();
                refresh();
                startActivity(new Intent(getContext(), CheckoutActivity.class));
                break;

            case R.id.increase_product:
                Locale locale = new Locale("in", "ID");
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);

                Bundle bundle = this.getArguments();
                int harga = 0;
                harga = bundle.getInt("product_price");
                permeter = Double.parseDouble(productQuantity.getText().toString());
                permeter += 0.5;
                productQuantity.setText(Double.toString(permeter));
                harga *= permeter;
                Log.d("TAG", "isi permeter dalam onklik" +permeter);
                subTotal.setText(format.format(harga));

                break;

            case R.id.decrease_product:
                Locale locale2 = new Locale("in", "ID");
                NumberFormat format2 = NumberFormat.getCurrencyInstance(locale2);
                Bundle bundle2 = this.getArguments();
                permeter = Double.parseDouble(productQuantity.getText().toString());
                if (permeter > 1) {
                    int harga2 = 0;
                    harga2 = bundle2.getInt("product_price");
                    permeter -= 0.5;
                    productQuantity.setText(Double.toString(permeter));
                    harga2 *= permeter;
                    subTotal.setText(format2.format(harga2));
                }else {
                    permeter = 1.0;
                    productQuantity.setText(Double.toString(permeter));
                }
                break;
        }
    }

    public void refresh() {
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0,0);
        startActivity(intent);
        dismiss();
        getActivity().overridePendingTransition(0,0);
    }
}
