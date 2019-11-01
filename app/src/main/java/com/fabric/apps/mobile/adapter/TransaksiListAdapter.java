package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiListAdapter extends RecyclerView.Adapter<TransaksiListAdapter.ViewHolder> {

    private Context context;
    private List<TransactionItemGetModel> transactionItemGetModels;


    public TransaksiListAdapter(Context context, List<TransactionItemGetModel> transaksi_data){
        this.context = context;
        this.transactionItemGetModels = transaksi_data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        holder.noPesanan.setText(transactionItemGetModels.get(position).getNoPesanan());
        holder.status.setText(transactionItemGetModels.get(position).getStatus());
        holder.hargaProduk.setText(format.format(transactionItemGetModels.get(position).getTotalHarga()));
        holder.tglPesanan.setText(transactionItemGetModels.get(position).getDateOfTransaction());



    }

    @Override
    public int getItemCount() {
        return transactionItemGetModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nopesanan)
        TextView noPesanan;

        @BindView(R.id.tv_lihatdetail)
        TextView lihatDetail;

        @BindView(R.id.tv_hargaproduk)
        TextView hargaProduk;

        @BindView(R.id.tv_tanggal)
        TextView tglPesanan;


        @BindView(R.id.tv_status)
        TextView status;

        @BindView(R.id.btn_bayar)
        Button btnBayar;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
