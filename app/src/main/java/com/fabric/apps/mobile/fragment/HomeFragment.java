package com.fabric.apps.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.model.ModelLoader;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.HomeExtendsActivity;
import com.fabric.apps.mobile.adapter.ProductHomeAdapter;
import com.fabric.apps.mobile.adapter.SlidingBannerAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
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
public class HomeFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private String color = "";
    SessionSharedPreferences sessionSharedPreferences;
    private SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.banner_view_pager)
    ViewPager bannerViewPager;

    @BindView(R.id.swipe_refres_layout)
    SwipeRefreshLayout swRefreslayout;

    @BindView(R.id.view_pager_indicator)
    DotsIndicator viewPagerIndicator;

    @BindView(R.id.view_for_you)
    TextView btViewForYou;

    @BindView(R.id.view_new_arrival)
    TextView btViewNewArr;

    @BindView(R.id.view_best_seller)
    TextView btViewBestSell;

    @BindView(R.id.for_you_list)
    RecyclerView forYouList;

    @BindView(R.id.best_seller_list)
    RecyclerView bestSellerList;

    @BindView(R.id.new_arrivals_list)
    RecyclerView newArrivalList;

    private int NUM_PAGES = 0;
    private int currentPage = 0;


    private ProductHomeAdapter homeAdapter;
    private List<ProductsItem> product_catalogs = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        swipeRefreshLayout.setOnRefreshListener(this);

        forYouList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        bestSellerList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newArrivalList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        
        initViewPager();
//        LoadJson();

        btViewForYou.setOnClickListener(this);
        btViewBestSell.setOnClickListener(this);
        btViewNewArr.setOnClickListener(this);

        return view;
    }

    private void LoadJson() {

        swipeRefreshLayout.setRefreshing(true);
       String token = sessionSharedPreferences.getAccessToken();
        Log.d("TAG", "isitoken: " +token);
        String key = "oa00000000app";
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IkpvaG4gRG9lIiwidXNlcm5hbWUiOiJhbGkiLCJwaG9uZU51bWJlciI6IjA4MjM3Nzk1NDAwOCIsInBhc3N3b3JkIjoiZTAxYzg1OGZiY2Q5MWEzZmE3ODVkMDZjNDY5YTMzYjEyOGU4MGY1ZiIsInNhbHQiOiIwNmQ0OTM5M2ZmOWYzNGFjNjA2YTkwZDdhY2M2ZWMwNGY0Y2MyODVkIiwiaW1hZ2UiOiIiLCJjcmVhdGVkQXQiOm51bGwsInVwZGF0ZWRBdCI6bnVsbCwiaWF0IjoxNTY2ODc1NjM1fQ.Nyqv36mgzwwPncCA2Eb4faYnj6SGF0YawlLduJYjnUY";

        ConfigRetrofit.provideApiService().getProduc(key,token).enqueue(new Callback<ResponseProduc>() {
            @Override
            public void onResponse(Call<ResponseProduc> call, Response<ResponseProduc> response) {
                if(response != null && response.body().getProducts() != null){
                    if (!product_catalogs.isEmpty()){
                        product_catalogs.clear();
                    }
                    product_catalogs = response.body().getProducts();
                    homeAdapter = new ProductHomeAdapter(getContext(),product_catalogs);
                    forYouList.setAdapter(homeAdapter);
                    bestSellerList.setAdapter(homeAdapter);
                    newArrivalList.setAdapter(homeAdapter);
                    homeAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);

                }else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "Isi nya Kosong  bor",Toast.LENGTH_SHORT).show();
                }

                Log.d("TAG", "Isi Body Cuy"+ response.body());
            }

            @Override
            public void onFailure(Call<ResponseProduc> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);

            }
        });
//        String color;
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Product_catalog_parent> call;
//        call = apiInterface.getProductparent();
//
//        call.enqueue(new Callback<Product_catalog_parent>() {
//            @Override
//            public void onResponse(Call<Product_catalog_parent> call, ResponseSignup<Product_catalog_parent> response) {
//
//
//                if (response.isSuccessful() && response.body().getProduct_catalog() != null){
//                    if (!product_catalogs.isEmpty()){
//                        product_catalogs.clear();
//                    }
//
//                    product_catalogs = response.body().getProduct_catalog();
//                    homeAdapter = new ProductHomeAdapter(getContext(),product_catalogs);
//                    forYouList.setAdapter(homeAdapter);
//                    bestSellerList.setAdapter(homeAdapter);
//                    newArrivalList.setAdapter(homeAdapter);
//
//                    homeAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(getActivity(), "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product_catalog_parent> call, Throwable t) {
//
//            }
//        });
    }


    private void initViewPager() {
        ArrayList<Banner> bannerArrayList = populateBanner();
        bannerViewPager.setAdapter(new SlidingBannerAdapter(getContext(), bannerArrayList));
        viewPagerIndicator.setViewPager(bannerViewPager);

        NUM_PAGES = bannerArrayList.size();

        Handler handler = new Handler();
        Runnable runnable = () -> {
            if (currentPage == NUM_PAGES){
                currentPage = 0;
            }

            bannerViewPager.setCurrentItem(currentPage++, true);
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
               handler.post(runnable);
            }
        }, 3000, 3000);

        bannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private ArrayList<Banner> populateBanner(){

        ArrayList<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Banner banner = new Banner();
            banner.bannerImg = R.drawable.banner;

            banners.add(banner);
        }

        return banners;

    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.view_for_you:
                intent = new Intent(getContext(), HomeExtendsActivity.class);
                intent.putExtra("Title", "Rekomendasi Untuk Kamu");
                startActivity(intent);
                break;
            case R.id.view_best_seller:
                intent = new Intent(getContext(), HomeExtendsActivity.class);
                intent.putExtra("Title", "Produk Terlaris");
                startActivity(intent);
                break;
            case R.id.view_new_arrival:
                intent = new Intent(getContext(), HomeExtendsActivity.class);
                intent.putExtra("Title", "Produk Terbaru");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {

        LoadJson();
    }

//    private void onLoadSwiperRefresh(final String data){
//        swipeRefreshLayout.post(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        LoadJson(data);
//                    }
//                }
//        );
//    }
}
