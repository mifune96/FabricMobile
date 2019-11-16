package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.DetailTransaksi;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiListDikirimAdapter extends RecyclerView.Adapter<TransaksiListDikirimAdapter.ViewHolder> {

    private Context context;
    private List<TransactionItemGetModel> transactionItemGetModelDikirim;


    public TransaksiListDikirimAdapter(Context context, List<TransactionItemGetModel> transaksi_data) {
        this.context = context;
        this.transactionItemGetModelDikirim = transaksi_data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi_dikirim, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        holder.noPesanan.setText(transactionItemGetModelDikirim.get(position).getNoPesanan());
        holder.status.setText(transactionItemGetModelDikirim.get(position).getStatus());
        holder.hargaProduk.setText(format.format(transactionItemGetModelDikirim.get(position).getTotalHarga()));
        holder.tglPesanan.setText(transactionItemGetModelDikirim.get(position).getDateOfTransaction());

        holder.selesai.setOnClickListener(v ->{

            Intent intent = new Intent(context, DetailTransaksi.class);
            intent.putExtra("nopesanan", transactionItemGetModelDikirim.get(position).getNoPesanan());
            intent.putExtra("status", transactionItemGetModelDikirim.get(position).getStatus());
            intent.putExtra("totalharga", transactionItemGetModelDikirim.get(position).getTotalHarga());
            intent.putExtra("tgltransaksi", transactionItemGetModelDikirim.get(position).getDateOfTransaction());
            intent.putExtra("typeongkir", transactionItemGetModelDikirim.get(position).getTypeOfOngkir());
            intent.putExtra("shipingcost",transactionItemGetModelDikirim.get(position).getShippingCosts());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return transactionItemGetModelDikirim.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_lihatdetail_dikirim)
        TextView selesai;


        @BindView(R.id.tv_nopesanan_dikirim)
        TextView noPesanan;

        @BindView(R.id.tv_hargaproduk_dikirim)
        TextView hargaProduk;

        @BindView(R.id.tv_tanggal_dikirim)
        TextView tglPesanan;


        @BindView(R.id.tv_status_dikirim)
        TextView status;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
