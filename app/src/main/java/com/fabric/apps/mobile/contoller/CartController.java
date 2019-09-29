package com.fabric.apps.mobile.contoller;

import android.app.Activity;
import android.content.Context;
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

    public void ambilCart (RecyclerView recyclerView, Context context, ViewGroup onError
            , ViewGroup onSuccess, ViewGroup errorState, SwipeRefreshLayout refreshLayout){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        int id = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        Log.d("ID Session", "ID NYA BRO: " + id);
        ConfigRetrofit.provideApiService().getCart(id,key,token).enqueue(new Callback<ResponseCart>() {
            @Override
            public void onResponse(Call<ResponseCart> call, Response<ResponseCart> response) {

                if (response != null && response.body().getCart() != null) {
                    if (cartItems.isEmpty()) {
                        cartItems.clear();
                    }
                    cartItems = response.body().getCart();
                    CartListAdapter adapter = new CartListAdapter(context, cartItems);
                    recyclerView.setAdapter(adapter);
                    onSuccess.setVisibility(View.VISIBLE);
                    onError.setVisibility(View.GONE);
                    errorState.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                }else {
                    onSuccess.setVisibility(View.VISIBLE);
                    onError.setVisibility(View.GONE);
                    errorState.setVisibility(View.GONE);
                    refreshLayout.setRefreshing(false);
                    Log.i(TAG, response.errorBody().toString());
                    }
            }

            @Override
            public void onFailure(Call<ResponseCart> call, Throwable t) {
                onSuccess.setVisibility(View.GONE);
                onError.setVisibility(View.GONE);
                errorState.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
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

                    Log.i(TAG, "Bgst: " + response.body());

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
        Integer idm = CartListAdapter.idcart;
        ConfigRetrofit.provideApiService().deletCart(idm,key,token).enqueue(new Callback<ResponseCart>() {
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

    public void updateCart (Context context){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";
        Integer idcart= CartListAdapter.idcart;
        Integer idcustumer = CartListAdapter.idcustomer;
        Integer idproduk= CartListAdapter.idproduk;
        Double permeter= CartListAdapter.permeter;
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
                Toast.makeText(context, "Oops, something when wrong!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
