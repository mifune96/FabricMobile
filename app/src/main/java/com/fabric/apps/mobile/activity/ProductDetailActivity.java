package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.contoller.CartController;
import com.fabric.apps.mobile.fragment.DialogCartFragment;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;
import com.github.ybq.android.spinkit.SpinKitView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_produk_gambar)
    ImageView productImage;

    @BindView(R.id.tv_produk_nama)
    TextView productName;

    @BindView(R.id.tv_produk_harga)
    TextView productPrice;

    @BindView(R.id.tv_produk_deskripsi)
    TextView productDescription;

    @BindView(R.id.tv_produk_warna)
    TextView productColor;

    @BindView(R.id.tv_produk_bahan)
    TextView productMaterial;

    @BindView(R.id.tv_produk_stok)
    TextView productStock;

    @BindView(R.id.tv_produk_code)
    TextView productCode;

    @BindView(R.id.product_review)
    RecyclerView reviewList;

    @BindView(R.id.button_chat)
    ImageButton buttonChat;

    @BindView(R.id.button_buy_now)
    Button buttonBuyNow;

    @BindView(R.id.progress_bar)
    SpinKitView progressBar;

    private String mImg,mProductname,mDescription,mProductcode,mProductmaterial,mProductcolor;
    private int mProductprice,mProductstock;
    private int mProductid;
    SessionSharedPreferences preferences;
    private CartController cartController = new CartController();
    public static int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        preferences = new SessionSharedPreferences(this);
        initView();


    }
    private void initView(){
        Intent intent = getIntent();

        Locale locale = new Locale("in", "ID");

        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(intent.getStringExtra("product_name"));
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        mProductname = getIntent().getStringExtra("product_name");
        mProductid = getIntent().getIntExtra("id",0); // kalo int pake defaultValue
        mProductprice = getIntent().getIntExtra("product_price",0);
        mProductstock = getIntent().getIntExtra("product_stok",0);
        mProductcode = getIntent().getStringExtra("product_code");
        mProductmaterial = getIntent().getStringExtra("product_material");
        mProductcolor = getIntent().getStringExtra("product_color");
        mDescription = getIntent().getStringExtra("product_description");
        mImg = getIntent().getStringExtra("product_image");
        //progressBar.animate();

        if (!mImg.isEmpty()){
            Glide.with(this).load(mImg).into(productImage);
            productImage.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            productImage.setImageResource(R.drawable.default_image_placeholder);
        }

        productName.setText(mProductname);
        productPrice.setText(format.format(mProductprice));
        productDescription.setText(mDescription);
        productCode.setText(mProductcode);
        productColor.setText(mProductcolor);
        productStock.setText(mProductstock+ " Meter");
        productMaterial.setText(mProductmaterial);
        ID = mProductid;



        buttonBuyNow.setOnClickListener(this);
        buttonChat.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.menu_cart);

        View actionView = menuItem.getActionView();
        TextView cartBadge = actionView.findViewById(R.id.cart_badge);

        if (cartBadge != null){
            cartController.getCartCount(cartBadge, this);
        }

//        setupBadge();

        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
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
            case R.id.button_chat:
                Intent openWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6285299503504&text=Hi%20there,%20i%20love%20your%20product.%20Contact%20Me."));
                startActivity(openWA);
                break;
            case R.id.button_buy_now:
                Bundle bundle = new Bundle();
                bundle.putInt("product_id", mProductid);
                bundle.putString("product_name", productName.getText().toString());
                bundle.putInt("product_price", mProductprice);
                bundle.putString("product_image", mImg);
                bundle.putString("product_color", mProductcolor);

                DialogFragment dialogFragment = new DialogCartFragment();
                dialogFragment.setArguments(bundle);// setbundelnya
                dialogFragment.show(getSupportFragmentManager(),"Cart Dialog");
                break;
            default:
                break;
        }
    }


    private void addCart() {
        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                , WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        List<CartItem> getResult = new ArrayList<>();

        CartItem cart = new CartItem();
        cartController.addcart(cart,progressBar,this,this,getResult);

    }
}
