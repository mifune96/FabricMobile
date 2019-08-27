package com.fabric.apps.mobile.fragment;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.MainActivity;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.siginModel.ResponseLogin;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    SessionSharedPreferences sm;

    @BindView(R.id.input_nama_pengguna)
    EditText tvnamapengguna;

    @BindView(R.id.input_password)
    EditText tvpassword;

    @BindView(R.id.button_login)
    Button btLogin;



    public LoginFragment() {
        // Required empty public constructor
    }

    private void userlogin(){
        String namapengguna = tvnamapengguna.getText().toString();
        String pass = tvpassword.getText().toString();

        if (namapengguna.isEmpty()) {
            tvnamapengguna.setError("Nama Pengguna Tidak Boleh Kosong");
            tvnamapengguna.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            tvpassword.setError("Password Tidak Boleh Kosong");
            tvpassword.requestFocus();
            return;
        }


        ConfigRetrofit.provideApiService().sigIn(namapengguna,pass).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response != null && response.isSuccessful()) {
//                    String a = response.body().getCustomerSigin().getId();
                    String message = response.body().getMessage();
                    sm.setLogin(true);
                    sm.setAccessToken(response.body().getAccessToken());
                    sm.setID(response.body().getCustomerSigin().getId());
                    sm.setUSER_NAME(response.body().getCustomerSigin().getName());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    startActivity(new Intent(getContext(),MainActivity.class));
                } else {
                    Toast.makeText(getActivity(),"Gagal Login Gagal cek email atau passsword" , Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d("TAG", "Failed Connetion To" + t.toString());
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        sm = new SessionSharedPreferences(getActivity());

        btLogin.setOnClickListener(this);




        return view;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                userlogin();
                break;
        }
    }
}
