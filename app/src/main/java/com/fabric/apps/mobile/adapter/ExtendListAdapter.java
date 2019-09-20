package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.CheckoutActivity;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ExtendListAdapter extends RecyclerView.Adapter<ExtendListAdapter.ViewHolder> {

    private Context context;
    private List<ResponseProduc> productList;

    public ExtendListAdapter(Context context, List<ResponseProduc> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.extend_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.productName.setText(productList.get(position).getProductCart().getNameProduct());
//        holder.productPrice.setText(productList.get(position).getPrice());

//        Glide.with(context)
//                .load(productList.get(position)
////                        .getProductCart().getImageUrl()).into(holder.productImage);

        boolean isFavorited = true;

        holder.favorite.setOnClickListener(v -> {
            holder.favorite.setImageResource(R.drawable.ic_favorite_fill);
        });

        holder.buyNow.setOnClickListener(v -> {
            context.startActivity(new Intent(context, CheckoutActivity.class));
        });

        holder.wrapper.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
//            intent.putExtra("id",productList.get(position).getId());
//            intent.putExtra("product_name", productList.get(position).getProductCart().getNameProduct());
//            intent.putExtra("product_price", productList.get(position).getPrice());
//            intent.putExtra("product_image", productList.get(position).getProductCart().getImageUrl());
//            intent.putExtra("product_description", productList.get(position).getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.button_buy_now)
        Button buyNow;

        @BindView(R.id.favorite_button)
        ImageButton favorite;

        @BindView(R.id.product_wrapper)
        RelativeLayout wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
