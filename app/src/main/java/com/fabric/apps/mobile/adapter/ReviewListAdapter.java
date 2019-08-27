package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.Review;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Review> reviewList;

    public ReviewListAdapter(Context context, ArrayList<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reviewerName.setText(reviewList.get(position).reviewerName);
        holder.reviewDate.setText(reviewList.get(position).reviewDate);
        holder.rating.setRating(reviewList.get(position).rating);
        holder.review.setText(reviewList.get(position).review);

        Glide.with(context).load(reviewList.get(position).reviewerImage).into(holder.reviewerImage);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.reviewer_image)
        CircleImageView reviewerImage;

        @BindView(R.id.reviewer_name)
        TextView reviewerName;

        @BindView(R.id.review_date)
        TextView reviewDate;

        @BindView(R.id.rating_bar)
        AppCompatRatingBar rating;

        @BindView(R.id.review)
        TextView review;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
