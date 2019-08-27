package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ShippingAddressDetailActivity;
import com.fabric.apps.mobile.model.ShippingAddress;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddressListAdapter extends RecyclerView.Adapter<ShippingAddressListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ShippingAddress> addressList;
    private String TAG;

    public ShippingAddressListAdapter(Context context, ArrayList<ShippingAddress> addressList, String TAG) {
        this.context = context;
        this.addressList = addressList;
        this.TAG = TAG;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shipping_address_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.addressName.setText(addressList.get(position).addressName);
        holder.addressPhoneNumber.setText(addressList.get(position).addressPhoneNumber);
        holder.addressDetail.setText(addressList.get(position).addressDetail);

        String cityDistrict = addressList.get(position).addressCity + ", " + addressList.get(position).addressDistrict;
        String poBox = "Kode Pos. " + addressList.get(position).addressPoBox;

        holder.addressCityDistrict.setText(cityDistrict);
        holder.addressProvince.setText(addressList.get(position).addressProvince);
        holder.addressPoBox.setText(poBox);

        if (addressList.get(position).isDefault){
            holder.addressDefault.setVisibility(View.VISIBLE);
        } else {
            holder.addressDefault.setVisibility(View.GONE);
        }

        if (TAG.equals("Show")){
            holder.wrapper.setOnClickListener(v -> {
                Intent intent = new Intent(context, ShippingAddressDetailActivity.class);
                intent.putExtra("TAG", "Edit");
                intent.putExtra("Name", addressList.get(position).addressName);
                intent.putExtra("PhoneNumber", addressList.get(position).addressPhoneNumber);
                intent.putExtra("Province", addressList.get(position).addressProvince);
                intent.putExtra("City", addressList.get(position).addressCity);
                intent.putExtra("District", addressList.get(position).addressDistrict);
                intent.putExtra("PostalCode", addressList.get(position).addressPoBox);
                intent.putExtra("Detailed", addressList.get(position).addressDetail);
                intent.putExtra("isDefault", addressList.get(position).isDefault);
                context.startActivity(intent);
            });
        } else if (TAG.equals("Change")){

        }
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.address_name)
        TextView addressName;

        @BindView(R.id.phone_number_address)
        TextView addressPhoneNumber;

        @BindView(R.id.detail_shipping_address)
        TextView addressDetail;

        @BindView(R.id.address_city_distrik)
        TextView addressCityDistrict;

        @BindView(R.id.address_province)
        TextView addressProvince;

        @BindView(R.id.address_po_box)
        TextView addressPoBox;

        @BindView(R.id.default_address)
        TextView addressDefault;

        @BindView(R.id.wrapper)
        RelativeLayout wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
