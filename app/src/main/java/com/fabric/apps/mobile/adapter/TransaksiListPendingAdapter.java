package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ActivityPembayaran;
import com.fabric.apps.mobile.activity.DetailTransaksi;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransaksiListPendingAdapter extends RecyclerView.Adapter<TransaksiListPendingAdapter.ViewHolder> {

    private Context context;
    private List<TransactionItemGetModel> transactionItemGetModels;


    public TransaksiListPendingAdapter(Context context, List<TransactionItemGetModel> transaksi_data){
        this.context = context;
        this.transactionItemGetModels = transaksi_data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transaksi_pending, parent, false);
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

        int harga = transactionItemGetModels.get(position).getTotalHarga();

        holder.btnBayar.setOnClickListener(v -> {
            Intent intent = new Intent(context, ActivityPembayaran.class);
            intent.putExtra("total_bayar", harga);
            context.startActivity(intent);
        });

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

        @BindView(R.id.tv_lihatdetail_pending)
        TextView selesai;


        @BindView(R.id.tv_nopesanan)
        TextView noPesanan;


        @BindView(R.id.tv_hargaproduk)
        TextView hargaProduk;

        @BindView(R.id.tv_tanggal)
        TextView tglPesanan;


        @BindView(R.id.tv_status)
        TextView status;
//
        @BindView(R.id.btn_bayar)
        Button btnBayar;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
