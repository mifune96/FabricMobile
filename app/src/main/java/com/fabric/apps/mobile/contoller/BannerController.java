package com.fabric.apps.mobile.contoller;

import android.content.Context;
import android.os.Handler;

import androidx.viewpager.widget.ViewPager;

import com.fabric.apps.mobile.R;
import com.fabric.apps.mobile.adapter.SlidingBannerAdapter;
import com.fabric.apps.mobile.model.Banner;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private int NUM_PAGE = 0;
    private int currentPage = 0;

    // inisialisasi banner pada home fragment
    public void initBannerPager(ViewPager viewPager, Context context, DotsIndicator dotsIndicator){
        ArrayList<Banner> banners = populateBanner();
        viewPager.setAdapter(new SlidingBannerAdapter(context, banners));
        dotsIndicator.setViewPager(viewPager);

        NUM_PAGE = banners.size();

        Handler handler = new Handler();
        Runnable runnable = () -> {
            if (currentPage == NUM_PAGE){
                currentPage = 0;
            }

            viewPager.setCurrentItem(currentPage++, true);
        };

        //membuat viewpager swipe otomatis
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 3000);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //mengumpulkan data yang akan ditampilkan pada banner
    private ArrayList<Banner> populateBanner() {
        ArrayList<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            Banner banner = new Banner();
            banner.bannerImg = R.drawable.banner;

            banners.add(banner);
        }

        return banners;
    }
}
