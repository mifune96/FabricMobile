package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.activity.EditProfileActivity;
import com.fabric.apps.mobile.activity.ProfileSettingDetailActivity;
import com.fabric.apps.mobile.model.Setting;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountSettingListAdapter extends RecyclerView.Adapter<AccountSettingListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Setting> settingList = populateSetting();

    public AccountSettingListAdapter(Context context) {
        this.context = context;
    }

    private ArrayList<Setting> populateSetting() {
        ArrayList<Setting> settings = new ArrayList<>();

        int[] settingName = {R.string.edit_profile, R.string.shipping_address, R.string.user_phone
                , R.string.email_address, R.string.password};

        for (int i = 0; i < settingName.length; i++){
            Setting setting = new Setting();
            setting.settingId = i;
            setting.settingName = settingName[i];

            settings.add(setting);
        }

        return settings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.account_setting_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Intent intent = new Intent(context, ProfileSettingDetailActivity.class);

        holder.settingName.setText(settingList.get(position).settingName);

        holder.wrapper.setOnClickListener(v -> {
            switch (settingList.get(position).settingId){
                case 0:
                    context.startActivity(new Intent(context, EditProfileActivity.class));
                    break;
                case 1:
                    intent.putExtra("TAG", "Shipping Address");
                    intent.putExtra("title", "Alamat Pengiriman");
                    context.startActivity(intent);
                    break;
                case 2:
                    intent.putExtra("TAG", "Phone Number");
                    intent.putExtra("title", "Nomor Telepon");
                    context.startActivity(intent);
                    break;
                case 3:
                    intent.putExtra("TAG", "Email Address");
                    intent.putExtra("title", "Alamat Email");
                    context.startActivity(intent);
                    break;
                case 4:
                    intent.putExtra("TAG", "Password");
                    intent.putExtra("title", "Password");
                    context.startActivity(intent);
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.account_setting_name)
        TextView settingName;

        @BindView(R.id.wrapper)
        RelativeLayout wrapper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
