package com.fabric.apps.mobile.contoller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fabric.apps.mobile.adapter.CatalogueListAdapter;
import com.fabric.apps.mobile.adapter.ProductHomeAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.productModel.ProductsItem;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductController {

    private final String TAG ="Produk";
    SessionSharedPreferences sessionSharedPreferences;
    private List<ProductsItem> productsItems = new ArrayList<>();

    //mengambil data produk di json
    public void ambilProduk(RecyclerView recyclerView,RecyclerView recyclerView1,RecyclerView recyclerView2, Context context, ViewGroup onNoData, ViewGroup onSuccess, ViewGroup onError, SwipeRefreshLayout refreshLayout){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        String id = sessionSharedPreferences.getID();
        Log.d("TAG", "isi idnya bro: " +id);
        String token = sessionSharedPreferences.getAccessToken();
        Log.d("TAG", "buaha: " +token);
        String key = "oa00000000app";
        ConfigRetrofit.provideApiService().getProduc(key,token).enqueue(new Callback<ResponseProduc>() {
            @Override
            public void onResponse(Call<ResponseProduc> call, Response<ResponseProduc> response) {
                if (response != null && response.body().getProducts() != null){
                    if (!productsItems.isEmpty()){
                        productsItems.clear();
                    }
                    productsItems = response.body().getProducts();
                    ProductHomeAdapter adapter = new ProductHomeAdapter(context,productsItems);
                    recyclerView.setAdapter(adapter);
                    recyclerView1.setAdapter(adapter);
                    recyclerView2.setAdapter(adapter);
                    onSuccess.setVisibility(View.VISIBLE);
                    onNoData.setVisibility(View.GONE);
                    onError.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                    adapter.notifyDataSetChanged();
                }else {
                    onSuccess.setVisibility(View.GONE);
                    onNoData.setVisibility(View.VISIBLE);
                    onError.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                    assert response.errorBody() != null;
                    Log.i(TAG, response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseProduc> call, Throwable t) {
                Log.e(TAG, "Something wrong happer", t);

                onSuccess.setVisibility(View.GONE);
                onNoData.setVisibility(View.GONE);
                onError.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
            }
        });
    }
}
