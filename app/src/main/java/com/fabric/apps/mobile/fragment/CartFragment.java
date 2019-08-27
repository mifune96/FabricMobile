package com.fabric.apps.mobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.model.cartModel.Cart_data;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.cart_list)
    RecyclerView cartList;

    @BindView(R.id.checkout)
    Button checkoutButton;

    @BindView(R.id.total_payment)
    TextView totalPayment;

    private CartListAdapter cartListAdapter;
    private List<Cart_data> productList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
//        LoadJson();
        cartList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        checkoutButton.setOnClickListener(this);
        return view;
    }

//    private void LoadJson() {
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Cart_parent> call;
//        call = apiInterface.getCartparent(2);
//
//        call.enqueue(new Callback<Cart_parent>() {
//            @Override
//            public void onResponse(Call<Cart_parent> call, ResponseSignup<Cart_parent> response) {
//                if (response.isSuccessful() && response.body().getCart_data() != null){
//                    if (!productList.isEmpty()){
//                        productList.clear();
//                    }
//
//                    productList = response.body().getCart_data();
//                    cartListAdapter = new CartListAdapter(getContext(),productList, totalPayment);
//                    cartList.setAdapter(cartListAdapter);
//
//                    cartListAdapter.notifyDataSetChanged();
//                    totalPayment.setText("Rp. " + cartListAdapter.getTotalPayment().intValue());
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
//
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkout:
                startActivity(new Intent(getContext(), CheckoutActivity.class));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        LoadJson();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        LoadJson();
    }
}