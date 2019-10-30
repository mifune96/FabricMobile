package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.ProductDetailActivity;
import com.fabric.apps.mobile.model.productModel.ProductsItem;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogueListAdapter extends RecyclerView.Adapter<CatalogueListAdapter.ViewHolder> {

    private Context context;
    private List<ProductsItem> productList;
    public static int IDcart;

    public CatalogueListAdapter(Context context, List<ProductsItem> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.catalogue_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        holder.productName.setText(productList.get(position).getName());
        holder.productPrice.setText(format.format(productList.get(position).getHarga()));

        int id = productList.get(position).getId();
        IDcart = id;
//        String folder = "http://192.168.100.55:8080/catalog/";


        if (!productList.get(position).getImage().isEmpty()){
            Glide.with(context).load(productList.get(position).getImage()).into(holder.productImage);
            holder.productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            holder.productImage.setImageResource(R.drawable.default_image_placeholder);
        }

        holder.favorite.setOnClickListener(v -> {
            holder.favorite.setImageResource(R.drawable.ic_favorite_fill);
        });

        holder.wrapper.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("id", productList.get(position).getId());
            intent.putExtra("product_name", productList.get(position).getName());
            intent.putExtra("product_price", productList.get(position).getHarga());
            intent.putExtra("product_image", productList.get(position).getImage());
            intent.putExtra("product_stok",productList.get(position).getStok());
            intent.putExtra("product_color",productList.get(position).getWarna());
            intent.putExtra("product_material",productList.get(position).getMaterial());
            intent.putExtra("product_code",productList.get(position).getCode());
            intent.putExtra("product_description", productList.get(position).getDeskripsi());
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

        @BindView(R.id.favorite_button)
        FloatingActionButton favorite;

        @BindView(R.id.product_wrapper)
        LinearLayout wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
