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
import com.fabric.apps.mobile.model.signupModel.ResponseSignup;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    SessionSharedPreferences sessionSharedPreferences;

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



    public SignUpFragment() {
        // Required empty public constructor
    }


    //insert to auth
    private void signupinsert(){

        String username = tvusername.getText().toString();
        String nama =  tvnama.getText().toString();
        String notlp = tvnotlp.getText().toString();
        String pass = tvpassword.getText().toString();
        String pass2 = tvpassword2.getText().toString();

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

        ConfigRetrofit.provideApiService().Signup(nama,username,notlp,pass,pass2)
                .enqueue(new Callback<ResponseSignup>() {
                    @Override
                    public void onResponse(Call<ResponseSignup> call, Response<ResponseSignup> response) {
                        if (response != null && response.isSuccessful()){
                            Log.d("TAG", "Sukses Daftar ");
                            String token = response.body().getAccessToken();
                            int id = response.body().getCustomerSignup().getId();
                            sessionSharedPreferences.saveSPString(SessionSharedPreferences.AccessToken, token);
                            sessionSharedPreferences.saveSPInt("" + SessionSharedPreferences.ID, id); //dah jdi string
                            sessionSharedPreferences.saveSPBoolean(SessionSharedPreferences.IS_LOGIN,true);

                            startActivity(new Intent(getContext(),MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            getActivity().finish();
//                            Intent intent = new Intent(getActivity(), MainActivity.class);
//                            startActivity(intent);
//                            startActivity(new Intent(getContext(),MainActivity.class));
                        } else {
                            Toast.makeText(getActivity(),"Gagal MendaftarCek data diri" , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseSignup> call, Throwable t) {

                    }
                });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());

        BtnSignup.setOnClickListener(this);

        return view;

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
