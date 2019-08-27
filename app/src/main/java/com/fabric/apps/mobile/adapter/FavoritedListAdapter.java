package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritedListAdapter extends RecyclerView.Adapter<FavoritedListAdapter.ViewHolder> {

    private Context context;
    private List<ResponseProduc> productArrayList;

    public FavoritedListAdapter(Context context, List<ResponseProduc> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorited_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.productName.setText(productArrayList.get(position).getProduct().getNameProduct());
//        holder.productPrice.setText("Rp. "+productArrayList.get(position).getPrice());
//
//        Glide.with(context).load(productArrayList.get(position).getProduct().getImageUrl()).into(holder.productImage);

        holder.remove.setOnClickListener(v -> {
            productArrayList.remove(position);
            notifyDataSetChanged();
        });

        holder.wrapper.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
//            intent.putExtra("product_name", productArrayList.get(position).getProduct().getNameProduct());
//            intent.putExtra("product_price", productArrayList.get(position).getPrice());
//            intent.putExtra("product_image", productArrayList.get(position).getProduct().getImageUrl());
//            intent.putExtra("product_description", productArrayList.get(position).getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.button_remove)
        FloatingActionButton remove;

        @BindView(R.id.product_wrapper)
        LinearLayout wrapper;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
