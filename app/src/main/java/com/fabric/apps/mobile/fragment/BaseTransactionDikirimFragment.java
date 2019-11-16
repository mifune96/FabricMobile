package com.fabric.apps.mobile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.TransaksiListDikirimAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.transaksiGetModel.ResponseGetModel;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseTransactionDikirimFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.transaction_list_dikirim)
    RecyclerView transaksilistDikirim;

    SessionSharedPreferences sessionSharedPreferences;
    private TransaksiListDikirimAdapter transaksiListDikirimAdapter;
    List<TransactionItemGetModel> lol;

    public BaseTransactionDikirimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_list_dikirim, container, false);
        ButterKnife.bind(this, view);

        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());
        transaksilistDikirim.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

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
                    lol = response.body().getTransaction();

                    ArrayList<TransactionItemGetModel> sukses = new ArrayList<>();

                    for (int i = 0; i < lol.size(); i++) {
                        if (lol.get(i).getStatus().equalsIgnoreCase("Dikirim")) {
                            sukses.add(lol.get(i));
//                            Log.d("TAG", "statusnyaa " + sukses.get(i).getStatus());

                        }
                    }
                    transaksiListDikirimAdapter = new TransaksiListDikirimAdapter(getContext(),sukses);
                    transaksilistDikirim.setAdapter(transaksiListDikirimAdapter);
                    transaksiListDikirimAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetModel> call, Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
