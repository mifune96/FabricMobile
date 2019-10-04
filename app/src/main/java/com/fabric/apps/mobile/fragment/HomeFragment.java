package com.fabric.apps.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.ModelLoader;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.HomeExtendsActivity;
import com.fabric.apps.mobile.adapter.ProductHomeAdapter;
import com.fabric.apps.mobile.adapter.SlidingBannerAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.contoller.BannerController;
import com.fabric.apps.mobile.contoller.ProductController;
import com.fabric.apps.mobile.model.Banner;
import com.fabric.apps.mobile.model.productModel.ProductsItem;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

@BindView(R.id.banner_view_pager)
ViewPager bannerViewPager;

    @BindView(R.id.view_pager_indicator)
    DotsIndicator viewPagerIndicator;

    @BindView(R.id.for_you_list)
    RecyclerView forYouList;

    @BindView(R.id.new_arrivals_list)
    RecyclerView newArrivalList;

    @BindView(R.id.best_seller_list)
    RecyclerView bestSellerList;


    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.successGroup)
    ViewGroup onSuccess;

    @BindView(R.id.errorGroup)
    ViewGroup onError;

    @BindView(R.id.errorState)
    ViewGroup errorState;

    @BindView(R.id.reload)
    Button reload;

    private BannerController bannerController = new BannerController();
    private ProductController productController = new ProductController();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        bannerController.initBannerPager(bannerViewPager,getContext(), viewPagerIndicator);
        reload.setOnClickListener(this);

        forYouList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        bestSellerList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        newArrivalList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        refreshLayout.setOnRefreshListener(onRefreshListener);
        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);

            onRefreshListener.onRefresh();
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.reload){
            refreshLayout.post(() -> {
                refreshLayout.setRefreshing(true);

                onRefreshListener.onRefresh();
            });
        }
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            productController.ambilProduk(forYouList,bestSellerList,newArrivalList, getContext(), onError, onSuccess, errorState, refreshLayout);

        }
    };


}
