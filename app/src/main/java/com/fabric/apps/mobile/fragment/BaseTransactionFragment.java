package com.fabric.apps.mobile.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.TransaksiListAdapter;
import com.fabric.apps.mobile.connection.ApiInterface;
import com.fabric.apps.mobile.connection.ConfigRetrofit;

import com.fabric.apps.mobile.model.transaksiGetModel.ResponseGetModel;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseTransactionFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.transaction_list_pending)
    RecyclerView transaksilistPending;

    SessionSharedPreferences sessionSharedPreferences;
//    private TransaksiController transaksiController = new TransaksiController();

    private TransaksiListAdapter transaksiListAdapter;
//    private List<TransactionItemGetModel> transactionPostModels = new ArrayList<>();
    List<TransactionItemGetModel> mim;
    private List hasil = new ArrayList();

//    public static String status;

    public BaseTransactionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_list_pending, container, false);
        ButterKnife.bind(this, view);

        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        transaksilistPending.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

//        transaksiController.getAllTransaksi(transaksilistPending,getContext());

        LoadData();



        return view;
    }

    private void LoadData() {
        int id = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";

        ConfigRetrofit.provideApiService().getTransaksi(id,key,token).enqueue(new Callback<ResponseGetModel>() {
            @Override
            public void onResponse(Call<ResponseGetModel> call, Response<ResponseGetModel> response) {
                if (response.isSuccessful()) {
                    mim = response.body().getTransaction();

                    ArrayList<TransactionItemGetModel> lisa = new ArrayList<>();

                    for (int i = 0; i < mim.size(); i++) {
//                        String status = transactionItem.get(i).getStatus().equalsIgnoreCase("Sukses");
//                        Log.d("TAG", "statusnyaa " + mim.get(i).getStatus().equals("Sukses"));
                        if (mim.get(i).getStatus().equals("Pendding")) {
                            lisa.add(mim.get(i));
                            Log.d("TAG", "statusnyaa " + mim.get(i).getStatus());

                        }
//                        if (transactionItem.get(i).getStatus().equals("Sukses")) {
//                            lisa.add(transactionItem.get(i).getNoPesanan());
//                            lisa.add(transactionItem.get(i).getStatus());
//                            lisa.add(transactionItem.get(i).getTotalHarga());
//                            lisa.add(transactionItem.get(i).getDateOfTransaction());
//
////                            lisa.add(transactionItem.get(i));
//
//                            Log.d("TAG", "ayya " +lisa.get(i));
//
//
//                        }
                    }
                    transaksiListAdapter = new TransaksiListAdapter(getContext(),lisa);
                    transaksilistPending.setAdapter(transaksiListAdapter);
                    transaksiListAdapter.notifyDataSetChanged();



                }
            }
//
            @Override
            public void onFailure(Call<ResponseGetModel> call, Throwable t) {

            }
        });
//        ConfigRetrofit.provideApiService().getTransaksi(id,key,token).enqueue(new Callback<ResponseGetModel>() {
//            @Override
//            public void onResponse(Call<ResponseGetModel> call, Response<ResponseGetModel> response) {
//                if (response.isSuccessful()) {
//                    ResponseGetModel responseGetModel = response.body();
//                    transactionItemGetModels = Arrays.asList(responseGetModel.getTransaksi());
////                    for (int i = 0; i < itemGetModels.size(); i++) {
////                        Log.d("TAG", "isi Status" +itemGetModels.get(i).getStatus());
////                        transaksiListAdapter = new TransaksiListAdapter(getContext(),itemGetModels);
////                        transaksilistPending.setAdapter(transaksiListAdapter);
////                        transaksiListAdapter.notifyDataSetChanged();
////                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetModel> call, Throwable t) {
//
//            }
//        });
//        ConfigRetrofit.provideApiService().getTransaksi(id,key,token).enqueue(new Callback<ResponseGetModel>() {
//            @Override
//            public void onResponse(Call<ResponseGetModel> call, Response<ResponseGetModel> response) {
//                if (response.isSuccessful()) {
//                    transactionPostModels = response.body().getTransaction();
//                    transaksiListAdapter = new TransaksiListAdapter(getContext(),transactionPostModels);
//                    transaksilistPending.setAdapter(transaksiListAdapter);
//                    transaksiListAdapter.notifyDataSetChanged();
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetModel> call, Throwable t) {
//
//            }
//        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
