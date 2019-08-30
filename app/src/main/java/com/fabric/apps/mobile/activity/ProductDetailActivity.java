package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.ReviewListAdapter;
import com.fabric.apps.mobile.fragment.DialogCartFragment;
import com.fabric.apps.mobile.model.Review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.product_image)
    ImageView productImage;

    @BindView(R.id.product_name)
    TextView productName;

    @BindView(R.id.product_price)
    TextView productPrice;

    @BindView(R.id.product_stock)
    TextView productStok;

    @BindView(R.id.product_description)
    TextView productDescription;

    @BindView(R.id.product_review)
    RecyclerView reviewList;

    @BindView(R.id.button_chat)
    ImageButton buttonChat;

    @BindView(R.id.button_add_to_cart)
    ImageButton buttonAddToCart;

    @BindView(R.id.button_buy_now)
    Button buttonBuyNow;

    private String mImg,mProductname,mDescription;
    private int mProductprice,mProductstock;
    private int mProductid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("product_name"));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        mProductname = intent.getStringExtra("product_name");
        mProductid = intent.getIntExtra("id",0);
        mProductprice = intent.getIntExtra("product_price",0);
        mProductstock = intent.getIntExtra("product_stok",0);
        mDescription = intent.getStringExtra("product_description");
        mImg = intent.getStringExtra("product_image");

        Glide.with(this)
                .load(mImg)
                .into(productImage);


        productName.setText(mProductname);
        productPrice.setText("Rp. "+mProductprice);
        productStok.setText("Stok :"+mProductstock);
        productDescription.setText(mDescription);

        Log.d("TAG", "ISINYA" +mProductid);

        buttonAddToCart.setOnClickListener(this);
        buttonBuyNow.setOnClickListener(this);

        initRecyclerView();
    }

//    private void InsertToCart() {
//        int sidproduct = mProductid;
//        int sidcostumer = 2;
//        int slengthpermeter = 11;
//        String snote = "Mantap Bosss";
//
//        ApiInterface apiInterface =  ConfigRetrofit.getClient().create(ApiInterface.class);
//        Call<Cart_data> call = apiInterface.postCart(sidproduct, sidcostumer,snote, slengthpermeter);
//        call.enqueue(new Callback<Cart_data>() {
//            @Override
//            public void onResponse(Call<Cart_data> call, ResponseSignup<Cart_data> response) {
//                if (response.isSuccessful())
//                {
//
//                    Toast.makeText(ProductDetailActivity.this,"Berhasil", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cart_data> call, Throwable t) {
//                Toast.makeText(ProductDetailActivity.this,"Gagal Post", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    private void initRecyclerView() {
        ArrayList<Review> reviews = populateReview();

        reviewList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        reviewList.setAdapter(new ReviewListAdapter(this, reviews));
    }

    private ArrayList<Review> populateReview() {
        ArrayList<Review> reviewArrayList = new ArrayList<>();

        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String formatDate = dateFormat.format(date);

        for (int i = 0; i < 3; i++){
            Review review = new Review();
            review.setReviewerName("John");
            review.setReviewDate(formatDate);
            review.setReviewerImage(R.drawable.user_profile);
            review.setReview("Barangnya bagus,mantap");
            review.setRating(4.5f);

            reviewArrayList.add(review);
        }

        return reviewArrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cart:
                startActivity(new Intent(this, CartActivity.class));
                return true;
            case R.id.menu_favorited:
                item.setIcon(R.drawable.ic_favorite_fill);
                return true;

                default:
                    return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add_to_cart:
                DialogFragment dialogFragment = new DialogCartFragment();
                dialogFragment.show(getSupportFragmentManager(),"Cart Dialog");
                break;
            case R.id.button_buy_now:
                startActivity(new Intent(this, CheckoutActivity.class));
//                InsertToCart();
                break;
        }
    }
}
