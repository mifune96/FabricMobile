//package com.fabric.apps.mobile.contoller;
//
//import android.content.Context;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.fabric.apps.mobile.adapter.TransaksiListAdapter;
//import com.fabric.apps.mobile.connection.ConfigRetrofit;
//import com.fabric.apps.mobile.model.transaksiGetModel.ResponseGetModel;
//import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;
//import com.fabric.apps.mobile.utils.SessionSharedPreferences;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class TransaksiController {
//
//    SessionSharedPreferences sessionSharedPreferences;
//    private List<TransactionItemGetModel> transactionPostModels = new ArrayList<>();
//
//    public void getAllTransaksi(RecyclerView recyclerView, Context context) {
//        sessionSharedPreferences = new SessionSharedPreferences(context);
//        int id = sessionSharedPreferences.getID();
//        String token = sessionSharedPreferences.getAccessToken();
//        String key = "oa00000000app";
//        ConfigRetrofit.provideApiService().getTransaksi(id, key, token).enqueue(new Callback<ResponseGetModel>() {
//            @Override
//            public void onResponse(Call<ResponseGetModel> call, Response<ResponseGetModel> response) {
//                if (response != null && response.body().getTransaction() != null) {
//                    if (transactionPostModels.isEmpty()) {
//                        transactionPostModels.clear();
//                    }
//                    transactionPostModels = response.body().getTransaction();
//
//
////                    List<TransactionItemGetModel> mm = response.body().getTransaction();
//
////
//                    for (int i = 0; i < transactionPostModels.size(); i++) {
//                        if (transactionPostModels.get(i).getStatus().equals("Pendding")) {
//                            TransaksiListAdapter adapter = new TransaksiListAdapter(context, transactionPostModels);
//                            recyclerView.setAdapter(adapter);
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//
//                } else {
//                    Toast.makeText(context, "Gagal Get AllDataTransaksi", Toast.LENGTH_LONG).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseGetModel> call, Throwable t) {
////                Log.d(TAG, "onFailureGetAllTransaksi : " + t.toString());
//            }
//        });
//
//    }
//
//}
