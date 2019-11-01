package com.fabric.apps.mobile.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.MainActivity;
import com.fabric.apps.mobile.adapter.KotaRajaongkirListAdapter;
import com.fabric.apps.mobile.adapter.ProvinceRajaongkirListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.daftarModel.ResponseDaftarModel;
import com.fabric.apps.mobile.model.kotaModel.ResponseKotaModel;
import com.fabric.apps.mobile.model.kotaModel.ResultsItemKotaModel;
import com.fabric.apps.mobile.model.provinsirajaongkirModel.ResponseProvinceModel;
import com.fabric.apps.mobile.model.provinsirajaongkirModel.ResultsItemProvinceModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    SessionSharedPreferences sessionSharedPreferences;
    List<ResultsItemProvinceModel> resultsItems;
    List<ResultsItemKotaModel> resultsItemKotaModels;
    private ProvinceRajaongkirListAdapter adapter;
    private KotaRajaongkirListAdapter adpter_kota;

    @BindView(R.id.input_nama_pengguna)
    EditText tvusername;

    @BindView(R.id.input_nama)
    EditText tvnama;

    @BindView(R.id.input_nomer_telpon)
    EditText tvnotlp;

    @BindView(R.id.input_password)
    EditText tvpassword;

    @BindView(R.id.input_password_again)
    EditText tvpassword2;

    @BindView(R.id.button_signup)
    Button BtnSignup;

    @BindView(R.id.sp_input_provinsi)
    Spinner Spnprovinsi;

    @BindView(R.id.sp_input_kota)
    Spinner Spnkota;

    public static String idSpinnerProvonsi;
    public static String idSpinnerKota;
    Context mContext;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());

        BtnSignup.setOnClickListener(this);
        LoaddataProvinsi();
        LoaddataKota();

        return view;

    }

    private void LoaddataProvinsi() {
        ConfigRetrofit.provideApiService().getProvince().enqueue(new Callback<ResponseProvinceModel>() {
            @Override
            public void onResponse(Call<ResponseProvinceModel> call, Response<ResponseProvinceModel> response) {
                if (response.isSuccessful()){
                    resultsItems = response.body().getRajaongkir().getResults();
                    initdatakeSpinner();
                } else {
                    Toast.makeText(getActivity(), "Data Provinsi tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinceModel> call, Throwable t) {
                Log.d("TAG", "onFailure: " );
            }
        });
    }

    private void LoaddataKota() {

        ConfigRetrofit.provideApiService().getKota(idSpinnerProvonsi).enqueue(new Callback<ResponseKotaModel>() {
            @Override
            public void onResponse(Call<ResponseKotaModel> call, Response<ResponseKotaModel> response) {
                if (response.isSuccessful()){
                    resultsItemKotaModels = response.body().getRajaongkir().getResults();
                    initdatakotaSpinner();

                } else {
                    Toast.makeText(getActivity(), "Data Kota tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaModel> call, Throwable t) {
                Log.d("TAG", "onFailure: " );
            }
        });
    }


    private void initdatakeSpinner() {
        adapter = new ProvinceRajaongkirListAdapter(getContext(),resultsItems);
        adapter.notifyDataSetChanged();
        Spnprovinsi.setAdapter(adapter);

        Spnprovinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResultsItemProvinceModel current = (ResultsItemProvinceModel) parent.getItemAtPosition(position);
                idSpinnerProvonsi = current.getProvinceId();
                LoaddataKota();
                Log.d("TAG", "Idnya bos "+ idSpinnerProvonsi);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initdatakotaSpinner() {
        adpter_kota = new KotaRajaongkirListAdapter(getContext(), resultsItemKotaModels);
        adpter_kota.notifyDataSetChanged();
        Spnkota.setAdapter(adpter_kota);

        Spnkota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ResultsItemKotaModel currentkota = (ResultsItemKotaModel) parent.getItemAtPosition(position);
                idSpinnerKota = currentkota.getCityId();
                Log.d("TAG", "Id Kota " +idSpinnerKota);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //insert to auth
    private void signupinsert(){

        String username = tvusername.getText().toString();
        String nama =  tvnama.getText().toString();
        String notlp = tvnotlp.getText().toString();
        String pass = tvpassword.getText().toString();
        String pass2 = tvpassword2.getText().toString();
        String idkota = idSpinnerKota;
        String idprovinsi = idSpinnerProvonsi;

        if (username.isEmpty()) {
            tvusername.setError("Nama Pengguna Tidak Boleh Kosong");
            tvusername.requestFocus();
            return;

        }  if (nama.isEmpty()) {
            tvnama.setError("Nama Tidak Boleh Kosong");
            tvnama.requestFocus();
            return;

        } if (notlp.isEmpty()) {
            tvnotlp.setError("Nomer Telpon Tidak Boleh Kosong");
            tvnotlp.requestFocus();
            return;

        }
         if (pass.isEmpty()) {
            tvpassword.setError("Password Tidak Boleh Kosong");
            tvpassword.requestFocus();
            return;

        } if (pass.length() < 6) {
            tvpassword.setError("Password Minimal 6 Karakter");
            tvpassword.requestFocus();
            return;
        } if (pass2.isEmpty()){
            tvpassword2.setError("Konfirmasi Password Tidak Boleh Kosong");
            tvpassword2.requestFocus();
            return;
        } if (!pass2.equals(pass)){
             tvpassword2.setError("Password Tidak Sama");
             tvpassword2.requestFocus();
             return;
        }

         ConfigRetrofit.provideApiService().Signup(nama,username,notlp,pass,pass2,idprovinsi,idkota).enqueue(new Callback<ResponseDaftarModel>() {
             @Override
             public void onResponse(Call<ResponseDaftarModel> call, Response<ResponseDaftarModel> response) {
                 if (response.isSuccessful()){
                     String token = response.body().getAccessToken();
                            int id = response.body().getCustomerCreate().getId();
                            sessionSharedPreferences.saveSPString(SessionSharedPreferences.AccessToken, token);
                            sessionSharedPreferences.saveSPInt(SessionSharedPreferences.ID, id); //dah jdi string
                            sessionSharedPreferences.saveSPBoolean(SessionSharedPreferences.IS_LOGIN,true);

                            startActivity(new Intent(getContext(), MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            getActivity().finish();

                 } else{

                 }
             }

             @Override
             public void onFailure(Call<ResponseDaftarModel> call, Throwable t) {

             }
         });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:
                signupinsert();
                break;
        }
    }

}
