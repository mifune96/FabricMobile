package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.AccountSettingActivity;
import com.fabric.apps.mobile.activity.HelpActivity;
import com.fabric.apps.mobile.activity.InformationActivity;
import com.fabric.apps.mobile.activity.NotificationActivity;
import com.fabric.apps.mobile.activity.TransactionActivity;
import com.fabric.apps.mobile.model.Setting;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingListAdapter extends RecyclerView.Adapter<SettingListAdapter.ViewHolder> {

    private ArrayList<Setting> settingList = populateSetting();
    private Context context;

    public SettingListAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Setting> populateSetting() {
        ArrayList<Setting> settings = new ArrayList<>();
        int[] settingImg = {R.drawable.ic_chat
                ,  R.drawable.ic_shopping, R.drawable.ic_settings
                , R.drawable.ic_help, R.drawable.ic_information};
        int[] settingName = {R.string.chat, R.string.transaction
                , R.string.account_setting, R.string.help, R.string.information};

        for (int i = 0; i < settingName.length; i++){
            Setting setting = new Setting();
            setting.settingName = settingName[i];
            setting.settingImg = settingImg[i];
            setting.settingId = i;
            settings.add(setting);
        }

        return settings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.settingName.setText(settingList.get(position).settingName);
        holder.settingImage.setImageResource(settingList.get(position).settingImg);

        holder.wrapper.setOnClickListener(v -> {
            switch (settingList.get(position).settingId){
                case 0:
                    Intent openWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=6281368009882&text=Hi%20there,%20i%20love%20your%20product.%20Contact%20Me."));
                    context.startActivity(openWA);
                    break;
                case 1:
                    context.startActivity(new Intent(context, TransactionActivity.class));
                    break;
                case 2:
                    context.startActivity(new Intent(context, AccountSettingActivity.class));
                    break;
                case 3:
                    context.startActivity(new Intent(context, HelpActivity.class));
                    break;
                case 4:
                    context.startActivity(new Intent(context, InformationActivity.class));
                    break;
                    default:
                        break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.setting_image)
        ImageView settingImage;

        @BindView(R.id.setting_name)
        TextView settingName;

        @BindView(R.id.wrapper)
        RelativeLayout wrapper;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
