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
import com.fabric.apps.mobile.model.cekkurirModel.CostItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.CostsItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.ResponseKurirModel;
import com.fabric.apps.mobile.model.kotaModel.ResultsItemKotaModel;

import java.util.Currency;
import java.util.List;

import static com.fabric.apps.mobile.R.layout.item_kurir;
import static com.fabric.apps.mobile.R.layout.checkout_detail;

public class KurirListAdapter extends ArrayAdapter<CostsItemKurirModel>{

  public KurirListAdapter(Context context, List<CostsItemKurirModel> itemKurirModels){
      super(context,0,itemKurirModels);
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
            convertView = LayoutInflater.from(getContext()).inflate(item_kurir, parent,false);

        }
        TextView kurir_tipe = convertView.findViewById(R.id.tv_kurir);
        TextView estimasi = convertView.findViewById(R.id.tv_estimasis);

        //item sama dengan todo20
        CostsItemKurirModel current = getItem(position);



        if (current != null) {
            kurir_tipe.setText(current.getService());
//            bayar.setText("Rp."+ current.getCost().get(position).getValue());
//
            List<CostItemKurirModel> mom = current.getCost();
            for (int i = 0; i<mom.size();i++){
                estimasi.setText(current.getCost().get(i).getEtd()+" Hari");
            }


        }

        return convertView;
    }
}
