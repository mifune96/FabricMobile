package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductHistoryAdapter extends RecyclerView.Adapter<ProductHistoryAdapter.ViewHolder> {

    private Context context;
    private List<ResponseProduc> product_catalogs;

    public ProductHistoryAdapter(Context context, List<ResponseProduc> product_catalogs) {
        this.context = context;
        this.product_catalogs = product_catalogs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_history_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.productName.setText(product_catalogs.get(position).getProduct().getNameProduct());
//        holder.productPrice.setText(product_catalogs.get(position).getPrice());

//        Glide.with(context).load(product_catalogs.get(position).getProduct().getImageUrl()).into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
