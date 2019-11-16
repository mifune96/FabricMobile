package com.fabric.apps.mobile.contoller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.adapter.CartListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartController {

    private final String TAG ="Cart";
    SessionSharedPreferences sessionSharedPreferences;
    private List<CartItem> cartItems = new ArrayList<>();

    public void ambilCart (RecyclerView recyclerView, Context context, ViewGroup errorGroup
            , ViewGroup onSuccess, ViewGroup errorState, SwipeRefreshLayout refreshLayout, TextView total){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        int id = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        Log.d("ID Session", "ID NYA BRO: " + id);
        ConfigRetrofit.provideApiService().getCart(id,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                cartItems = response.body().getCart();
                CartListAdapter adapter = new CartListAdapter(context, cartItems, total);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (cartItems.isEmpty()) {
                    cartItems.clear();
                    onSuccess.setVisibility(View.GONE);
                    errorGroup.setVisibility(View.VISIBLE);
                    errorState.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                }else {
                    onSuccess.setVisibility(View.VISIBLE);
                    errorGroup.setVisibility(View.GONE);
                    errorState.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
//                    Log.i(TAG, response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                onSuccess.setVisibility(View.GONE);
                errorGroup.setVisibility(View.GONE);
                errorState.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    public void addDialogcart(int produkid, double qty){
        int idcostumer = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        ConfigRetrofit.provideApiService().postCart(key,token,idcostumer,produkid,qty).enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if (response.isSuccessful()){

                    Log.d(TAG, "Berhasil");
                } else {
                    Log.d(TAG, "Gagal");
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Log.d(TAG, "Gagal");
            }
        });
    }
    public void addcart(CartItem cart, SpinKitView progressBar, Activity activity, Context context, List<CartItem> cartResponse){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        int idcostumer = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        double qty = 1.0;

        int idpro = ProductDetailActivity.ID;

        Log.d("TAG", "id barangnya" +idpro);
        ConfigRetrofit.provideApiService().postCart(key,token,idcostumer,idpro,qty).enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if (response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                    Log.i(TAG, "mantap: " + response.body());

                    Log.i(TAG, response.body().toString());
                } else {
                    Toast.makeText(context, "Gagal Memasukkan Produk", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onError: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Toast.makeText(context, "Oops, something went wrong.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    public void deletCart(int cartId, Context context){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
//        Integer idm = CartListAdapter.idcart;
        ConfigRetrofit.provideApiService().deletCart(cartId,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Berhasil Menghapus Barang", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gagal Menghapus Barang", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onError: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                Toast.makeText(context, "Oops, something when wrong!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateCart (int idcart,int idcustumer,int idproduk,double permeter,Context context){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        Log.d(TAG, "PERMETER :" +permeter);
        ConfigRetrofit.provideApiService().updateCart(idcart,key,token,idcustumer,idproduk,permeter).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                if (response.isSuccessful()){
                    Toast.makeText(context, "Berhasil Mengupdate QTY", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gagal Mengupdate QTY", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onError: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
//                Toast.makeText(context, "Oops, something when wrong!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getCartCount(TextView cardBadge, Context context){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        int id = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";

        ConfigRetrofit.provideApiService().getCart(id,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {
                if (response.body().getCart().size() > 0){
                    int size = response.body().getCart().size();
                    String cartCount = String.valueOf(Math.min(size, 99));
                    cardBadge.setText(cartCount);

                    if (cardBadge.getVisibility() != View.VISIBLE){
                        cardBadge.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (cardBadge.getVisibility() != View.GONE){
                        cardBadge.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
