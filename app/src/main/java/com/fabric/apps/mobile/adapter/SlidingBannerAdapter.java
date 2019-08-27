package com.fabric.apps.mobile.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.model.Banner;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

public class SlidingBannerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Banner> bannerList;
    private LayoutInflater layoutInflater;

    public SlidingBannerAdapter(Context context, ArrayList<Banner> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;

        container.removeView(view);
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View bannerLayout = layoutInflater.inflate(R.layout.sliding_banner_layout, container, false);
        ImageView bannerImg = bannerLayout.findViewById(R.id.banner_img);

        bannerImg.setImageResource(bannerList.get(position).bannerImg);

        container.addView(bannerLayout, 0);

        return bannerLayout;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @Nullable
    @Override
    public Parcelable saveState() {
        return null;
    }
}
