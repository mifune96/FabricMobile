package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.FavoritedListAdapter;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.ArrayList;
import java.util.List;

public class FavoritedActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.favorited_list)
    RecyclerView favoriteList;

    private FavoritedListAdapter favoritedListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ResponseProduc> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorited);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favorit");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        layoutManager = new LinearLayoutManager(FavoritedActivity.this);
        favoriteList.setLayoutManager(layoutManager);

//        LoadJson();
    }

//    private void LoadJson() {
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Product_catalog_parent> call;
//        call = apiInterface.getProductparent();
//        call.enqueue(new Callback<Product_catalog_parent>() {
//            @Override
//            public void onResponse(Call<Product_catalog_parent> call, ResponseSignup<Product_catalog_parent> response) {
//                if (response.isSuccessful() && response.body().getProduct_catalog() != null){
//                    if (!productArrayList.isEmpty()){
//                        productArrayList.clear();
//                    }
//
//                    productArrayList = response.body().getProduct_catalog();
//                    favoritedListAdapter = new FavoritedListAdapter(FavoritedActivity.this,productArrayList);
//                    favoriteList.setAdapter(favoritedListAdapter);
//                    favoritedListAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(FavoritedActivity.this, "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product_catalog_parent> call, Throwable t) {
//
//            }
//        });
//    }
    }


