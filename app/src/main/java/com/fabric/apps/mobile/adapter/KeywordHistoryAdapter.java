package com.fabric.apps.mobile.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.Keyword;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class KeywordHistoryAdapter extends RecyclerView.Adapter<KeywordHistoryAdapter.ViewHolder> {

    private ArrayList<Keyword> keywordList;

    public KeywordHistoryAdapter(ArrayList<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.keyword_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.keywordName.setText(keywordList.get(position).keywordName);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.keyword_name)
        TextView keywordName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
