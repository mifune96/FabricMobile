package com.fabric.apps.mobile.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.ProvinsirajaongkirModel.ResultsItemProvinceModel;

import static com.fabric.apps.mobile.R.layout.item_province;


import java.util.List;

public class AddressRajaongkirListAdapter extends ArrayAdapter<ResultsItemProvinceModel> {

    public AddressRajaongkirListAdapter(Context context, List<ResultsItemProvinceModel> resultsItems){
        super(context,0,resultsItems);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position, View convertView, ViewGroup parent){
        //todo 21 biarkan merah
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(item_province, parent, false);
//            convertView = LayoutInflater.from(getContext()).inflate(item_province, parent, false);
        }
        TextView province_nama = convertView.findViewById(R.id.tv_namaprovinsi);
        //item sama dengan todo20
        ResultsItemProvinceModel current = getItem(position);

        if (current!=null){
            province_nama.setText(current.getProvince());
        }

        return convertView;
    }

}
