package com.fabric.apps.mobile.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fabric.apps.mobile.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPembayaran extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tambahGambar)
    ImageView ivTambahgambar;

    @BindView(R.id.btn_kirim)
    Button kirim;

    @BindView(R.id.tv_totalpembayaran)
    TextView totalHarga;

    private StorageReference mStorageRef;
    Uri imageuri;

    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Halaman Pembayaran");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);


        total = getIntent().getIntExtra("total_bayar",0);
        totalHarga.setText(format.format(total));

        mStorageRef = FirebaseStorage.getInstance().getReference();

        ivTambahgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ActivityPembayaran.this).compress(512).start();
            }
        });

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageuri == null){
                    Toast.makeText(ActivityPembayaran.this, "Gambar Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    uploadimage();

                }
            }
        });
    }

    private void uploadimage() {
        StorageReference ref = mStorageRef.child(Calendar.getInstance().getTimeInMillis() + ".jpg");

        ref.putFile(imageuri).continueWithTask(upload -> {
            if (!upload.isSuccessful()) {
                throw new Exception();
            }
            return ref.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, task.getResult().toString(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this,TransactionActivity.class));
                finish();

                // get url make ini task.getResult().toString()
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            imageuri=data.getData();
            ivTambahgambar.setImageURI(imageuri);
            ivTambahgambar.setScaleType(ImageView.ScaleType.CENTER);
        }
    }
}
