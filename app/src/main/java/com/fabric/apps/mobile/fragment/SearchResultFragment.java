package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.CatalogueListAdapter;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {

    @BindView(R.id.product_result_list)
    RecyclerView rvproductList;

    private CatalogueListAdapter catalogueListAdapter;
    private List<ResponseProduc> productList = new ArrayList<>();

    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        ButterKnife.bind(this, view);

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
//                    if (!productList.isEmpty()){
//                        productList.clear();
//                    }
//
//                    productList = response.body().getProduct_catalog();
//                    catalogueListAdapter = new CatalogueListAdapter(getContext(),productList);
//                    rvproductList.setAdapter(catalogueListAdapter);
//
//                    catalogueListAdapter.notifyDataSetChanged();
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

}
