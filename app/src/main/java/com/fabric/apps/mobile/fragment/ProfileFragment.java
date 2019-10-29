package com.fabric.apps.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.EditProfileActivity;
import com.fabric.apps.mobile.activity.Login_Signup_Activity;
import com.fabric.apps.mobile.adapter.SettingListAdapter;
import com.fabric.apps.mobile.connection.ConfigRetrofit;
import com.fabric.apps.mobile.model.profileModel.ResponseProfilModel;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.user_image)
    CircleImageView userImage;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.setting_list)
    RecyclerView settingList;

    @BindView(R.id.profile_wrapper)
    RelativeLayout wrapper;

    @BindView(R.id.button_logout)
    Button btnLogout;

    SessionSharedPreferences sessionSharedPreferences;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        sessionSharedPreferences = new SessionSharedPreferences(this.getActivity());

        LoadProfil();
        initRecyclerView();

        wrapper.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), EditProfileActivity.class));
        });

        btnLogout.setOnClickListener(v -> {
            sessionSharedPreferences.logout();
            startActivity(new Intent(getContext(), Login_Signup_Activity.class));
            getActivity().finish();
        });

        return view;
    }

    private void LoadProfil() {
        int idcostumer = sessionSharedPreferences.getID();
        String token = sessionSharedPreferences.getAccessToken();
        String key = "oa00000000app";

        ConfigRetrofit.provideApiService().getProfil(idcostumer,key,token).enqueue(new Callback<ResponseProfilModel>() {
            @Override
            public void onResponse(Call<ResponseProfilModel> call, Response<ResponseProfilModel> response) {
                if (response.isSuccessful()){

                    Log.d("TAG", "Isi Respon profil " +response.body().getCustomer());
                    userName.setText(response.body().getCustomer().getName());
                }
            }

            @Override
            public void onFailure(Call<ResponseProfilModel> call, Throwable t) {

            }
        });
    }

    private void initRecyclerView() {
        settingList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        settingList.setAdapter(new SettingListAdapter(getContext()));
    }


}
