package com.fabric.apps.mobile.fragment;


import android.app.Dialog;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.ArrayList;
import java.util.List;
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

    @BindView(R.id.product_name)
    TextView productName;

    @BindView(R.id.product_price)
    EasyMoneyTextView productPrice;

    @BindView(R.id.button_remove)
    ImageView buttonRemove;

    @BindView(R.id.product_quantity)
    TextView productQuantity;

    private int id;

//    private CartListAdapter cartListAdapter;
//    private List<Cart_data> product_catalogs2 = new ArrayList<>();

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

        Bundle bundle = this.getArguments();
        if (bundle != null){
            if (!Objects.requireNonNull(bundle.getString("product_image")).isEmpty()){
                Glide.with(this).load(bundle.getString("product_image")).into(productImage);
            } else {
                productImage.setImageResource(R.drawable.default_image_placeholder);
            }

            productName.setText(bundle.getString("product_name"));
            productPrice.setText(Integer.toString(bundle.getInt("product_price")));
            productQuantity.setText(bundle.getString("product_length"));
            id = bundle.getInt("id");
        }

        close.setOnClickListener(this);
        proccedCheckout.setOnClickListener(this);
        addMore.setOnClickListener(this);

        return view;
    }

    // ngambil data dari bundle tdi
    private void getDetail(){
        Bundle bundle = getArguments(); // bisa lu inisialisasi dlu atau lngsung getArguments() kaya intent bisa langsung getintent ok cuk gw solat dulu ty ty
        subTotal.setText(getArguments().getInt("id")); // misalnya begini cuk
    }

//    private void LoadJson() {
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Cart_parent> call;
//        call = apiInterface.getCartparent(getId());
//        call.enqueue(new Callback<Cart_parent>() {
//            @Override
//            public void onResponse(Call<Cart_parent> call, ResponseSignup<Cart_parent> response) {
//                if (response.isSuccessful() && response.body().getCart_data() != null){
//                    if (!product_catalogs2.isEmpty()){
//                        product_catalogs2.clear();
//                    }
//
//                    product_catalogs2 = response.body().getCart_data();
//                    cartListAdapter = new CartListAdapter(getContext(),product_catalogs2, subTotal);
//                    rvcartList.setAdapter(cartListAdapter);
//
//                    cartListAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getActivity(), "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cart_parent> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_close:
                dismiss();
                break;
            case R.id.add_more:
                dismiss();
                break;
            case R.id.proceed_to_checkout:
                startActivity(new Intent(getContext(), CheckoutActivity.class));
                break;
        }
    }
}
