package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.productModel.ProductsItem;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ViewHolder> {

    private Context context;
    private List<ProductsItem> productList;

    public ProductHomeAdapter(Context context, List<ProductsItem> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(productList.get(position).getName());

        Glide.with(context)
                .load(productList.get(position)
                       .getImage()).into(holder.productImage);

        holder.wrapper.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("id", productList.get(position).getId());
            intent.putExtra("product_name", productList.get(position).getName());
            intent.putExtra("product_price", productList.get(position).getHarga());
            intent.putExtra("product_image", productList.get(position).getImage());
            intent.putExtra("product_stok",productList.get(position).getStok());
            intent.putExtra("product_description", productList.get(position).getDeskripsi());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_wrapper)
        RelativeLayout wrapper;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
