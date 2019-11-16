package com.fabric.apps.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.DetailTransaksi;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.fragment.Dialogtransaksidetail;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiListSelesaiAdapter extends RecyclerView.Adapter<TransaksiListSelesaiAdapter.ViewHolder> {

    private Context context;
    private List<TransactionItemGetModel> transactionItemGetModels;


    public TransaksiListSelesaiAdapter(Context context, List<TransactionItemGetModel> transactionItemGetModels) {
        this.context = context;
        this.transactionItemGetModels = transactionItemGetModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi_seleseai, parent, false);
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

        holder.selesai.setOnClickListener(v ->{

            Intent intent = new Intent(context, DetailTransaksi.class);
            intent.putExtra("nopesanan", transactionItemGetModels.get(position).getNoPesanan());
            intent.putExtra("status", transactionItemGetModels.get(position).getStatus());
            intent.putExtra("totalharga", transactionItemGetModels.get(position).getTotalHarga());
            intent.putExtra("tgltransaksi", transactionItemGetModels.get(position).getDateOfTransaction());
            intent.putExtra("typeongkir", transactionItemGetModels.get(position).getTypeOfOngkir());
            intent.putExtra("shipingcost",transactionItemGetModels.get(position).getShippingCosts());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return transactionItemGetModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_nopesanan_selesai)
        TextView noPesanan;

        @BindView(R.id.tv_lihatdetail_selesai)
        TextView selesai;

        @BindView(R.id.tv_hargaproduk_selesai)
        TextView hargaProduk;

        @BindView(R.id.tv_tanggal_selesai)
        TextView tglPesanan;


        @BindView(R.id.tv_status_selesai)
        TextView status;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
