package com.fabric.apps.mobile.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.MainActivity;
import com.fabric.apps.mobile.adapter.CatalogueListAdapter;
import com.fabric.apps.mobile.adapter.ProductHomeAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.productModel.ProductsItem;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.model.siginModel.ResponseLogin;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class CatalogueFragment extends Fragment {

    @BindView(R.id.catalogue_list)
    RecyclerView rvcatalogueList;

    SessionSharedPreferences sessionSharedPreferences;

    private CatalogueListAdapter catalogueListAdapter;
    private List<ProductsItem> productList = new ArrayList<>();

    public CatalogueFragment() {
//         Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_catalogue, container, false);
        ButterKnife.bind(this, view);

        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvcatalogueList.setLayoutManager(layoutManager);
        LoadJson();
        return view;
    }

    private void LoadJson() {

        String token = sessionSharedPreferences.getAccessToken();
        Log.d("TAG", "isitoken: " +token);
        String key = "oa00000000app";
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwibmFtZSI6IkpvaG4gRG9lIiwidXNlcm5hbWUiOiJhbGkiLCJwaG9uZU51bWJlciI6IjA4MjM3Nzk1NDAwOCIsInBhc3N3b3JkIjoiZTAxYzg1OGZiY2Q5MWEzZmE3ODVkMDZjNDY5YTMzYjEyOGU4MGY1ZiIsInNhbHQiOiIwNmQ0OTM5M2ZmOWYzNGFjNjA2YTkwZDdhY2M2ZWMwNGY0Y2MyODVkIiwiaW1hZ2UiOiIiLCJjcmVhdGVkQXQiOm51bGwsInVwZGF0ZWRBdCI6bnVsbCwiaWF0IjoxNTY2ODc1NjM1fQ.Nyqv36mgzwwPncCA2Eb4faYnj6SGF0YawlLduJYjnUY";

        ConfigRetrofit.provideApiService().getProduc(key,token).enqueue(new Callback<ResponseProduc>() {
            @Override
            public void onResponse(Call<ResponseProduc> call, Response<ResponseProduc> response) {
                if(response != null && response.body().getProducts() != null){
                    if (!productList.isEmpty()){
                        productList.clear();
                    }
                    productList = response.body().getProducts();
                    catalogueListAdapter = new CatalogueListAdapter(getContext(), productList);
                    rvcatalogueList.setAdapter(catalogueListAdapter);
                }

                Log.d("TAG", "Isi Body Cuy"+ response.body());
            }

            @Override
            public void onFailure(Call<ResponseProduc> call, Throwable t) {

            }
        });

//        ConfigRetrofit.provideApiService().getProduc().enqueue(new Callback<ResponseProduc>() {
//            @Override
//            public void onResponse(Call<ResponseProduc> call, Response<ResponseProduc> response) {
//                Log.d("TAG","product"+response.body());
//
//                if (response != null && response.isSuccessful()) {
//                    Log.d("TAG","product"+response.body());
//
////                    productList = response.body().getProducts();
////                    catalogueListAdapter = new CatalogueListAdapter(getContext(), productList);
////                    rvcatalogueList.setAdapter(catalogueListAdapter);
////
////                    catalogueListAdapter.notifyDataSetChanged();
////                    String a = response.body().getCus1tomerSigin().getId();
////                    String message = response.body().getMessage();
////                    sm.setLogin(true);
////                    sm.setAccessToken(response.body().getAccessToken());
////                    sm.setID(response.body().getCustomerSigin().getId());
////                    sm.setUSER_NAME(response.body().getCustomerSigin().getName());
////
////                    Intent intent = new Intent(getActivity(), MainActivity.class);
////                    startActivity(intent);
////                    startActivity(new Intent(getContext(),MainActivity.class));
//                } else {
//                    Toast.makeText(getActivity(),"Gagal Login Gagal cek email atau passsword" , Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<ResponseProduc> call, Throwable t) {
//                Log.d("TAG", "Failed Connetion To" + t.toString());
//            }
//        });
    }


}
