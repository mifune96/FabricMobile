package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.ProductController;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    CarouselView carouselView;
    int [] sampleimage = {R.drawable.banner, R.drawable.banner, R.drawable.banner};

    @BindView(R.id.new_arrivals_list)
    RecyclerView newArrivalList;

    @BindView(R.id.carouselView)
    CarouselView caroselnya;

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

//        bannerController.initBannerPager(bannerViewPager,getContext(), viewPagerIndicator);
        reload.setOnClickListener(this);
        carouselView = caroselnya;
        carouselView.setPageCount(sampleimage.length);
        carouselView.setImageListener(imageListener);

//        forYouList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        bestSellerList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        newArrivalList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        refreshLayout.setOnRefreshListener(onRefreshListener);
        refreshLayout.post(() -> {
            refreshLayout.setRefreshing(true);

            onRefreshListener.onRefresh();
        });
        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleimage[position]);
        }
    };

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
            productController.ambilProduk(bestSellerList,newArrivalList, getContext(), onError, onSuccess, errorState, refreshLayout);

        }
    };


}
