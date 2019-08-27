package com.fabric.apps.mobile.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ShippingAddressDetailActivity;
import com.fabric.apps.mobile.adapter.ShippingAddressListAdapter;
import com.fabric.apps.mobile.model.ShippingAddress;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShippingAdressFragment extends Fragment {

    @BindView(R.id.shipping_address_list)
    RecyclerView addressList;

    @BindView(R.id.add_new_address)
    Button addAddress;

    Bundle bundle = getArguments();

    public ShippingAdressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping_adress, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();
        addAddress.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ShippingAddressDetailActivity.class);
            intent.putExtra("TAG", "Add");
            startActivity(intent);
        });

        return view;
    }

    private void initRecyclerView() {
        ArrayList<ShippingAddress> shippingAddresses = populateAddress();

        addressList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        addressList.setAdapter(new ShippingAddressListAdapter(getContext(), shippingAddresses, bundle.getString("TAG")));
    }

    private ArrayList<ShippingAddress> populateAddress() {
        ArrayList<ShippingAddress> shippingAddresses = new ArrayList<>();

        for (int i = 0; i < 2; i++){
            ShippingAddress shippingAddress = new ShippingAddress();
            shippingAddress.addressName = "Roti John";
            shippingAddress.addressPhoneNumber = "+62 813 6800 9882";
            shippingAddress.addressDetail = "Jl. Nusantara No.15, Lk.05";
            shippingAddress.addressCity = "Kota Bandar Lampung";
            shippingAddress.addressDistrict = "Labuhan Ratu";
            shippingAddress.addressProvince = "Lampung";
            shippingAddress.addressPoBox = "35148";
            shippingAddress.isDefault = false;

            shippingAddresses.add(shippingAddress);
        }

        ShippingAddress shippingAddress = initDefault();
        shippingAddresses.add(shippingAddress);

        return shippingAddresses;
    }

    private ShippingAddress initDefault() {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.addressName = "Roti John";
        shippingAddress.addressPhoneNumber = "+62 813 6800 9882";
        shippingAddress.addressDetail = "Jl. Nusantara No.15, Lk.05";
        shippingAddress.addressCity = "Kota Bandar Lampung";
        shippingAddress.addressDistrict = "Labuhan Ratu";
        shippingAddress.addressProvince = "Lampung";
        shippingAddress.addressPoBox = "35148";
        shippingAddress.isDefault = true;

        return shippingAddress;
    }

}
