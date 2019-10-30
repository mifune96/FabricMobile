package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutListAdapter extends RecyclerView.Adapter<CheckoutListAdapter.ViewHolder>{
    private Context context;
    private List<CartItem> cartItems;
    public static int totalbayarnya;
    private TextView total;


    public CheckoutListAdapter(Context context, List<CartItem> cart_data) {
        this.context = context;
        this.cartItems = cart_data;
        this.setTotalBayar();

    }


    @NonNull
    @Override
    public CheckoutListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_list_item, parent, false);
        setTotalBayar();
        return new CheckoutListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutListAdapter.ViewHolder holder, int position) {

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        setTotalBayar();
        holder.productName.setText(cartItems.get(position).getProductCart().getName());
        holder.color.setText(cartItems.get(position).getProductCart().getWarna());
        holder.productPrice.setText(format.format(cartItems.get(position).getProductCart().getHarga()));
        holder.quantity.setText(Double.toString(cartItems.get(position).getPermeter()));

        if (!cartItems.get(position).getProductCart().getImage().isEmpty()){
            Glide.with(context).load(cartItems.get(position).getProductCart()
                    .getImage()).into(holder.productImage);
            holder.productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            holder.productImage.setImageResource(R.drawable.default_image_placeholder);
        }

    }


    public double setTotalBayar(){
        Double price = 0.0;
        for (CartItem c : cartItems){
            price += (c.getPermeter() * c.getProductCart().getHarga());
        }
        return price;
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_produk)
        ImageView productImage;

        @BindView(R.id.tv_namaproduk)
        TextView productName;

        @BindView(R.id.tv_warna)
        TextView color;

        @BindView(R.id.tv_hargaproduk)
        TextView productPrice;

        @BindView(R.id.tv_qty)
        TextView quantity;



        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
