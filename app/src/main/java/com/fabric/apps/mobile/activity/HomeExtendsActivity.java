package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.ExtendListAdapter;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeExtendsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.extend_title)
    TextView title;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.extendList)
    RecyclerView rvextendList;

    private List<ResponseProduc> productList = new ArrayList<>();
    private ExtendListAdapter extendListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_extends);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());
        rvextendList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        Intent intent = getIntent();

        title.setText(intent.getStringExtra("Title"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public void onClick(View v) {
    }
}
