package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private TextView total;
    private int qty, totalPayment;
    private CartController cartController = new CartController();

    public CartListAdapter(Context context, List<CartItem> cart_data) {
        this.context = context;
        this.cartItems = cart_data;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.productName.setText(cartItems.get(position).getProductCart().getName());
        holder.productPrice.setText(Integer.toString(cartItems.get(position).getProductCart().getHarga()));
        holder.productPrice.setCurrency("Rp");
        holder.productPrice.showCommas();
        holder.productPrice.showCurrencySymbol();
        holder.quantity.setText("1.0");

        if (!cartItems.get(position).getProductCart().getImage().isEmpty()){
            Glide.with(context).load(cartItems.get(position).getProductCart()
                    .getImage()).into(holder.productImage);
            holder.productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            holder.productImage.setImageResource(R.drawable.default_image_placeholder);
        }

        holder.remove.setOnClickListener(v -> {
//            cartController.deleteCart(cartItems.get(position).getId(), context);
            cartItems.remove(position);
            notifyDataSetChanged();
        });

        holder.increase.setOnClickListener(v -> {
            qty++;
            holder.quantity.setText("" + qty);
        });

        holder.decrease.setOnClickListener(v -> {
            qty = Integer.parseInt(holder.quantity.getText().toString());

            if (qty > 1){
                qty--;
                holder.quantity.setText("" + qty);
            } else {
                qty = 1;
                holder.quantity.setText("" + qty);
            }
        });

    }
    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        EasyMoneyTextView productPrice;

        @BindView(R.id.button_remove)
        ImageView remove;

        @BindView(R.id.increase_product)
        ImageButton increase;

        @BindView(R.id.decrease_product)
        ImageButton decrease;

        @BindView(R.id.product_quantity)
        TextView quantity;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}