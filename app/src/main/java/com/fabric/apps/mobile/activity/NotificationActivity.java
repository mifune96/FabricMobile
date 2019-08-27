package com.fabric.apps.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.NotificationListAdapter;
import com.fabric.apps.mobile.model.Notification;

import java.util.ArrayList;
import java.util.Objects;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.notification_list)
    RecyclerView notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Notifikasi");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<Notification> notifications = populateNotification();

        notificationList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        notificationList.setAdapter(new NotificationListAdapter(this, notifications));
    }

    private ArrayList<Notification> populateNotification() {
        ArrayList<Notification> notifications = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Notification notification = new Notification();
            notification.notifName = "Invoice";
            notification.notifMessage = "Bla Bla Bla";
            notification.notifImage = R.drawable.product_image;

            notifications.add(notification);
        }

        return notifications;
    }
}
