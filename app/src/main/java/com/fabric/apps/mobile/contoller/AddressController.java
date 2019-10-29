package com.fabric.apps.mobile.contoller;

import android.content.Context;
import android.util.Log;

import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.addressModel.AddressItemModel;
import com.fabric.apps.mobile.model.addressModel.ResponseAddressModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressController {

    private final String TAG ="Address";
    SessionSharedPreferences sessionSharedPreferences;
    private List<AddressItemModel> addressItemModels = new ArrayList<>();
    public static int addressid; // ini kan gw buat global
//    private Context context;


    public void getAddress(Context context){
        sessionSharedPreferences = new SessionSharedPreferences(context);
        int idcostumer = sessionSharedPreferences.getID();
        String key = "oa00000000app";
        String token = sessionSharedPreferences.getAccessToken();

        ConfigRetrofit.provideApiService().getAddress(idcostumer,key,token).enqueue(new Callback<ResponseAddressModel>() {
            @Override
            public void onResponse(Call<ResponseAddressModel> call, Response<ResponseAddressModel> response) {
                if (response.isSuccessful()){
                    List<AddressItemModel> adrres = response.body().getAddress();

                    for (int i=0; i < adrres.size();i++){

                        addressid = adrres.get(i).getId();
                        Log.d(TAG, "idcontroller " +addressid);


                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseAddressModel> call, Throwable t) {

            }
        });
    }

}

