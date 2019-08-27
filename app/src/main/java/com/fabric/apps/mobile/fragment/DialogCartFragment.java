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

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.model.cartModel.Cart_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogCartFragment extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.button_close)
    ImageView close;

    @BindView(R.id.cart_list)
    RecyclerView rvcartList;

    @BindView(R.id.sub_total)
    TextView subTotal;

    @BindView(R.id.proceed_to_checkout)
    Button procedCheckout;

    @BindView(R.id.add_more)
    Button addMore;

    private CartListAdapter cartListAdapter;
    private List<Cart_data> product_catalogs2 = new ArrayList<>();

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

        rvcartList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
//        LoadJson();

        close.setOnClickListener(this);
        procedCheckout.setOnClickListener(this);
        addMore.setOnClickListener(this);

        return view;
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
