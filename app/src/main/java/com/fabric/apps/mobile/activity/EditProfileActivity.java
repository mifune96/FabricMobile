package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.EditText;

import com.fabric.apps.mobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.user_image)
    CircleImageView userImage;

    @BindView(R.id.select_image)
    FloatingActionButton selectImage;

    @BindView(R.id.input_user_name)
    EditText inputName;

    @BindView(R.id.input_birth_date)
    EditText inputBirthDate;

    @BindView(R.id.gender_spinner)
    AppCompatSpinner genderSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile_menu, menu);
        return true;
    }
}
