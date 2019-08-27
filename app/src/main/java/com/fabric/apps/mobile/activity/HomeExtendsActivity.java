package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.ExtendListAdapter;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeExtendsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.extend_title)
    TextView title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.extendList)
    RecyclerView rvextendList;

    private List<ResponseProduc> productList = new ArrayList<>();
    private ExtendListAdapter extendListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_extends);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());
        rvextendList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("Title"));

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
//                    if (!productList.isEmpty()){
//                        productList.clear();
//                    }
//
//                    productList = response.body().getProduct_catalog();
//                    extendListAdapter = new ExtendListAdapter(HomeExtendsActivity.this,productList);
//                    rvextendList.setAdapter(extendListAdapter);
//                    extendListAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(HomeExtendsActivity.this, "Gak Ada Hasil Bro", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Product_catalog_parent> call, Throwable t) {
//
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public void onClick(View v) {
    }
}
