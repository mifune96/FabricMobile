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
import com.fabric.apps.mobile.model.cartModel.Cart_data;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private Context context;
    private List<Cart_data> cart_data;
    private TextView total;
    private Double qty, totalPayment;
    SessionSharedPreferences session;

    //session = new SessionSharedPreferences(context); bukannya ini di taeo di fragmen ya kak ? ga tau sy harusnya di on create ahaha wkwkwkw
    public CartListAdapter(Context context, List<Cart_data> cart_data, TextView total) {
        this.context = context;
        this.cart_data = cart_data;
        this.total = total;
    }

    public CartListAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false);

//        int totalharga=0;
//        for (int i =0; i<cart_data.size();i++){
//            totalharga+=cart_data.get(i).getProduct_catalog().getPrice();
//        }
        session = new SessionSharedPreferences(context);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        totalPayment = getTotalPayment();

//        holder.productName.setText(cart_data.get(position).getProduct_catalog().getProduct().getNameProduct());
//        holder.productPrice.setText("Rp. "+ cart_data.get(position).getProduct_catalog().getPrice().intValue());
        holder.quantity.setText("1.0");


//        Glide.with(context).load(cart_data.get(position).getProduct_catalog().getProduct().getImageUrl()).into(holder.productImage);

        holder.remove.setOnClickListener(v -> {
//            delete(cart_data.get(position).getId(),context);
            cart_data.remove(position);
            notifyDataSetChanged();
        });

        //ini ketika nekan button +
        holder.increase.setOnClickListener(v -> {
            Double price = 0.0, qt = 0.0;
            qty = Double.parseDouble(holder.quantity.getText().toString());//1

            Toast.makeText(context, "qty : "+ qty, Toast.LENGTH_SHORT).show();
//            Double barder=cart_data.get(position).getProduct_catalog().getPrice().doubleValue();//1000
//            Toast.makeText(context, "barder : "+ barder, Toast.LENGTH_SHORT).show();
            qty += 0.5f;//1.5
            qt = 0.5;
//            qt *= barder;
            holder.quantity.setText("" + qty);
//            qty *= barder;//1.5*1000


            holder.productPrice.setText("Rp. "+qty.intValue());

            totalPayment += qt;

            total.setText("Rp. " + totalPayment.intValue());
//            session.setJumlah(totalPayment.floatValue());
            //mau simmpen nilai qty sama barder di shared preferences, cuma kan ini recyclerview jadi dia datanya lebih, sy coba pakai array di shared preferences tapi ga tau gmn panggilnya
            //session.setQty(qty.floatValue()[0],"array");
        });

        //ini ketika nekan button -
        holder.decrease.setOnClickListener(v -> {
            qty = Double.parseDouble(holder.quantity.getText().toString());
            Double qt = 0.5;

            if (qty > 1){
//                Double barder=cart_data.get(position).getProduct_catalog().getPrice().doubleValue();
                qty -= 0.5f;
                holder.quantity.setText(qty.toString().trim());
//                qty *= barder;
//                qt *= barder;
                holder.productPrice.setText("Rp. "+qty.intValue());
                totalPayment -= qt;
                total.setText("Rp. " + totalPayment.intValue());
//                session.setJumlah(totalPayment.floatValue());

            } else {
                qty = 1.0;
                holder.quantity.setText("" + qty);
            }
        });

        holder.wrapper.setOnClickListener(v -> {
            //Panggil ID
        });
    }

    public Double getTotalPayment(){
        Double price = 0.0;

        for (int i = 0; i < cart_data.size(); i++){
//            price += cart_data.get(i).getProduct_catalog().getPrice().doubleValue();
        }

        return price;
    }

//    private void delete(int id, Context context){
//        ApiInterface apiInterface = ConfigRetrofit.getClient().create(ApiInterface.class);
//
//        Call<Cart_data> call;
//        call = apiInterface.deleteCart(id);
//        call.enqueue(new Callback<Cart_data>() {
//            @Override
//            public void onResponse(Call<Cart_data> call, ResponseSignup<Cart_data> response) {
//                if (response.isSuccessful()){
//                    Toast.makeText(context,"Berhasil", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cart_data> call, Throwable t) {
//                Toast.makeText(context,"Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }


    @Override
    public int getItemCount() {
        return cart_data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_name)
        TextView productName;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.button_remove)
        ImageView remove;

        @BindView(R.id.increase_product)
        ImageButton increase;

        @BindView(R.id.decrease_product)
        ImageButton decrease;

        @BindView(R.id.product_quantity)
        TextView quantity;

        @BindView(R.id.product_wrapper)
        ViewGroup wrapper;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}