package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.fragment.SearchHistoryFragment;
import com.fabric.apps.mobile.fragment.SearchResultFragment;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_search)
    EditText inputSearch;

    @BindView(R.id.button_filter)
    Button filter;

    @BindView(R.id.button_sort)
    Button sort;

    @BindView(R.id.button_reset)
    ImageView reset;

    @BindView(R.id.tool_wrapper)
    LinearLayout wrapper;

    private Fragment historyFragment = new SearchHistoryFragment();
    private Fragment resultFragment = new SearchResultFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        initFragment();

        inputSearch.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                fragmentManager.beginTransaction().hide(historyFragment).show(resultFragment).commit();
                wrapper.setVisibility(View.VISIBLE);
                return true;
            }

            return false;
        });

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reset.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reset.setOnClickListener(v -> {
            fragmentManager.beginTransaction().hide(resultFragment).show(historyFragment).commit();
            inputSearch.setText("");
            inputSearch.clearFocus();
            wrapper.setVisibility(View.GONE);
            reset.setVisibility(View.INVISIBLE);
        });
    }

    private void initFragment() {
        fragmentManager.beginTransaction().add(R.id.search_container, historyFragment).commit();
        fragmentManager.beginTransaction().add(R.id.search_container, resultFragment)
                .hide(resultFragment).commit();
    }
}
