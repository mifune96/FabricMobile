package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.KeywordHistoryAdapter;
import com.fabric.apps.mobile.adapter.ProductHistoryAdapter;
import com.fabric.apps.mobile.model.Keyword;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchHistoryFragment extends Fragment {

    @BindView(R.id.keyword_list)
    RecyclerView keywordList;

    @BindView(R.id.product_list)
    RecyclerView productList;

    private ProductHistoryAdapter productHistoryAdapter;
    private List<ResponseProduc> product_catalogs = new ArrayList<>();

    public SearchHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_history, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
//        LoadJson();

        return view;
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
//                    if (!product_catalogs.isEmpty()){
//                        product_catalogs.clear();
//                    }
//
//                    product_catalogs = response.body().getProduct_catalog();
//                    productHistoryAdapter = new ProductHistoryAdapter(getContext(),product_catalogs);
//                    productList.setAdapter(productHistoryAdapter);
//
//                    productHistoryAdapter.notifyDataSetChanged();
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
//    }

    private void initRecyclerView() {
        ArrayList<Keyword> keywords = populateKeyword();

        keywordList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        keywordList.setAdapter(new KeywordHistoryAdapter(keywords));

    }


    private ArrayList<Keyword> populateKeyword() {
        ArrayList<Keyword> keywords = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Keyword keyword = new Keyword();
            keyword.keywordName = "Brokat";

            keywords.add(keyword);
        }

        return keywords;
    }

}
