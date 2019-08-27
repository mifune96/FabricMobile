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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.EditProfileActivity;
import com.fabric.apps.mobile.adapter.SettingListAdapter;

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


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        wrapper.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), EditProfileActivity.class));
        });

        return view;
    }

    private void initRecyclerView() {
        settingList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        settingList.setAdapter(new SettingListAdapter(getContext()));
    }
}
