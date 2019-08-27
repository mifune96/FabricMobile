package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.Notification;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Notification> notificationList;

    public NotificationListAdapter(Context context, ArrayList<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_list_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notifName.setText(notificationList.get(position).notifName);
        holder.notifMessage.setText(notificationList.get(position).notifMessage);

        Glide.with(context).load(notificationList.get(position).notifImage).into(holder.notifImage);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.notification_image)
        ImageView notifImage;

        @BindView(R.id.notification_name)
        TextView notifName;

        @BindView(R.id.notification_message)
        TextView notifMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
