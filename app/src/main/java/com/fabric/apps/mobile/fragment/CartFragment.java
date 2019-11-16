package com.fabric.apps.mobile.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.cart_list)
    RecyclerView cartList;

    @BindView(R.id.checkout)
    Button checkoutButton;

    @BindView(R.id.total_payment)
    TextView totalPayment;

    @BindView(R.id.successGroup)
    ViewGroup onSuccess;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.errorGroup)
    ViewGroup onError;

    @BindView(R.id.errorState)
    ViewGroup errorState;

    @BindView(R.id.reload)
    Button reload;



    SessionSharedPreferences sessionSharedPreferences;
    private CartListAdapter cartListAdapter;

    private CartController cartController = new CartController();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        cartList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        checkoutButton.setOnClickListener(this);
        reload.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(onRefreshListener);
        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);

            onRefreshListener.onRefresh();
        });

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkout:
                startActivity(new Intent(getContext(), CheckoutActivity.class));
                break;
            case R.id.reload:
                refreshLayout.post(() -> {
                    refreshLayout.setRefreshing(true);

                    onRefreshListener.onRefresh();
                });
                break;
        }
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            cartController.ambilCart(cartList,getContext(),onError,onSuccess,errorState,refreshLayout,totalPayment);
        }
    };
}