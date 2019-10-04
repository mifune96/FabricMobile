package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private Context context;
    private List<CartItem> cartItems;
    private TextView total;
    private Double qty;
    public static Double totalPayment;
    private CartController cartController = new CartController();
    SessionSharedPreferences sessionSharedPreferences;

    public CartListAdapter(Context context, List<CartItem> cart_data,TextView total) {
        this.context = context;
        this.cartItems = cart_data;
        this.total = total;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        setTotalBayar();
        holder.productName.setText(cartItems.get(position).getProductCart().getName());
        holder.productPrice.setText(Integer.toString(cartItems.get(position).getProductCart().getHarga()));
        holder.productPrice.setCurrency("Rp");
        holder.productPrice.showCommas();

        holder.productPrice.showCurrencySymbol();
//        totalPayment = setTotalBayar();
        holder.quantity.setText(Double.toString(cartItems.get(position).getPermeter()));


        Log.d("TAG", "heheheh: " +total);
        if (!cartItems.get(position).getProductCart().getImage().isEmpty()){
            Glide.with(context).load(cartItems.get(position).getProductCart()
                    .getImage()).into(holder.productImage);
            holder.productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            holder.productImage.setImageResource(R.drawable.default_image_placeholder);
        }

        holder.remove.setOnClickListener(v -> {
            cartController.deletCart(cartItems.get(position).getId(), context);
            cartItems.remove(position);
            notifyDataSetChanged();
        });
        /*
        menekan button +
         */
        holder.increase.setOnClickListener(v -> {
            cartItems.get(position).setPermeter(
                    (cartItems.get(position
                    ).getPermeter() + 0.5));
            holder.quantity.setText(Double.toString(cartItems.get(position).getPermeter()));
            setTotalBayar();
            cartController.updateCart(cartItems.get(position).getId(),cartItems.get(position).getCustomerId(),
                    cartItems.get(position).getProductId(),cartItems.get(position).getPermeter(),context);
//
        });

        /*
        ketika menekan button -
         */
        holder.decrease.setOnClickListener(v -> {
            if (cartItems.get(position).getPermeter() > 1) {
                cartItems.get(position).setPermeter(
                        (cartItems.get(position
                        ).getPermeter() - 0.5));
                holder.quantity.setText(Double.toString(cartItems.get(position).getPermeter()));
                setTotalBayar();
                cartController.updateCart(cartItems.get(position).getId(),cartItems.get(position).getCustomerId(),
                        cartItems.get(position).getProductId(),cartItems.get(position).getPermeter(),context);
            }
        });

    }


    public void setTotalBayar(){
        Double price = 0.0;
        for (CartItem c : cartItems){
               price += (c.getPermeter() * c.getProductCart().getHarga());
        }
        Log.d("TAG", "setTotalBayar: " +price);
//        totalPayment = price;
        total.setText("Rp. " +price.intValue());

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