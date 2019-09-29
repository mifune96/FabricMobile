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
    public static Integer idcart, idcustomer, idproduk;
    public static Double permeter=0.0;
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

//        totalPayment = getTotakPayment();

        holder.productName.setText(cartItems.get(position).getProductCart().getName());
        holder.productPrice.setText(Integer.toString(cartItems.get(position).getProductCart().getHarga()));
        holder.productPrice.setCurrency("Rp");
        holder.productPrice.showCommas();
        holder.productPrice.showCurrencySymbol();
        holder.quantity.setText(Double.toString(cartItems.get(position).getPermeter()));
        int s = cartItems.get(position).getProductCart().getStok();
        idcart = cartItems.get(position).getId();
        idcustomer = cartItems.get(position).getCustomerId();

        setTotalBayar();
//        permeter = cartItems.get(position).getPermeter();


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
            idproduk = cartItems.get(position).getProductId();
            permeter = Double.parseDouble(holder.quantity.getText().toString());
            if (cartItems.get(position).getPermeter() < cartItems.get(position).getProductCart().getStok()) {
                permeter += 0.5;
                cartController.updateCart(context);
                holder.quantity.setText("" + permeter);
            }
//
//
//
//
//
////            Integer price = 0;
////            double  qt = 0.5;
////            qty = Double.parseDouble(holder.quantity.getText().toString());//1
////            price=cartItems.get(position).getProductCart().getHarga();//1000
////            qty += 0.5f;//1.5
////            qt = 0.5;
////            qt *= price;
////            holder.quantity.setText("" + qty);
////            qty *= price;//1.5*1000
////            holder.productPrice.setText("Rp." +qty);
////
////            totalPayment += qt;
////
////            total.setText("Rp. " + totalPayment);
        });

        /*
        ketika menekan button -
         */
        holder.decrease.setOnClickListener(v -> {
            permeter = Double.parseDouble(holder.quantity.getText().toString());
            idproduk = cartItems.get(position).getProductId();
            if (cartItems.get(position).getPermeter() > 0.5) {
                permeter -= 0.5;
                cartController.updateCart(context);
                holder.quantity.setText(""+permeter);

            }
//
////            qty = Double.parseDouble(holder.quantity.getText().toString());
////            Double qt = 0.5;
////            if (qty > 1){
////                int barder=cartItems.get(position).getProductCart().getHarga();
////                qty -= 0.5f;
////                holder.quantity.setText(""+qty);
////                qty *= barder;
////                qt *= barder;
////                holder.productPrice.setText("Rp. "+qty);
////                totalPayment -= qt;
////                total.setText("Rp. " + totalPayment);
////            } else {
////                qty = 1.0;
////                holder.quantity.setText("" + qty);
////            }
        });

    }

    private void setTotalBayar(){
        Double price = 0.0;
        for (CartItem c : cartItems){
            price = price + (c.getPermeter() * c.getProductCart().getHarga());
        }
        totalPayment = price;
    }


    //    public Integer getTotakPayment(){
//        int price = 0;
//
//        for (int i = 0; i < cartItems.size(); i++){
//            price += cartItems.get(i).getProductCart().getHarga();
//        }
//        return price;
//    }
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