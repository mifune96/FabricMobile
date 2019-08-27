package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.fabric.apps.mobile.R;

import java.util.Objects;

public class ShippingAddressDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.input_name)
    EditText inputName;

    @BindView(R.id.input_phone_number)
    EditText inputPhoneNumber;

    @BindView(R.id.input_province)
    EditText inputProvince;

    @BindView(R.id.input_city)
    EditText inputCity;

    @BindView(R.id.input_district)
    EditText inputDistrict;

    @BindView(R.id.input_postal_code)
    EditText inputPostalCode;

    @BindView(R.id.input_detailed_address)
    EditText inputDetailedCode;

    @BindView(R.id.switch_default)
    SwitchCompat switchDefault;

    @BindView(R.id.button_save)
    Button buttonSave;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_shipping_address_detail);
        ButterKnife.bind(this);

        intent = getIntent();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        initToolbar();
        initView();
    }

    private void initView() {
        if (intent.getStringExtra("TAG").equals("Edit")){
            inputName.setText(intent.getStringExtra("Name"));
            inputPhoneNumber.setText(intent.getStringExtra("PhoneNumber"));
            inputProvince.setText(intent.getStringExtra("Province"));
            inputCity.setText(intent.getStringExtra("City"));
            inputDistrict.setText(intent.getStringExtra("District"));
            inputPostalCode.setText(intent.getStringExtra("PostalCode"));
            inputDetailedCode.setText(intent.getStringExtra("Detailed"));
            switchDefault.setChecked(intent.getBooleanExtra("isDefault", false));
        }
    }

    private void initToolbar() {
        switch (intent.getStringExtra("TAG")){
            case "Add":
                Objects.requireNonNull(getSupportActionBar()).setTitle("Tamabah Alamat");
                break;
            case "Edit":
                Objects.requireNonNull(getSupportActionBar()).setTitle("Ubah Alamat");
                break;
                default:
                    break;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (intent.getStringExtra("TAG").equals("Add"))
            menu.findItem(R.id.menu_delete).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_address_menu, menu);
        return true;
    }
}
