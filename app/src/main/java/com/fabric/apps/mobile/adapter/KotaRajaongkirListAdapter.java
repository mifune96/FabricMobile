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
import com.fabric.apps.mobile.model.kotaModel.ResponseKotaModel;
import com.fabric.apps.mobile.model.kotaModel.ResultsItemKotaModel;
import com.fabric.apps.mobile.model.provinsirajaongkirModel.ResultsItemProvinceModel;

import java.util.List;

import static com.fabric.apps.mobile.R.layout.item_city;

public class KotaRajaongkirListAdapter extends ArrayAdapter<ResultsItemKotaModel> {

    public KotaRajaongkirListAdapter(Context context, List<ResultsItemKotaModel> resultsItemKotaModels) {
        super(context, 0, resultsItemKotaModels);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        //todo 21 biarkan merah
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(item_city, parent, false);

        }
        TextView kota_nama = convertView.findViewById(R.id.tv_namacity);
        //item sama dengan todo20
        ResultsItemKotaModel current = getItem(position);

        if (current != null) {
            kota_nama.setText(current.getCityName());
        }

        return convertView;
    }
}

