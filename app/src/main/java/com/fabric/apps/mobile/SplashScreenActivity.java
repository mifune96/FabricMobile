package com.fabric.apps.mobile;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.fabric.apps.mobile.activity.Login_Signup_Activity;
import com.fabric.apps.mobile.activity.MainActivity;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.logo)
    TextView logo;

    SessionSharedPreferences sessionSharedPreferences;

    private static final long ANIMATION_DURATION_MILISECONDS = 1500;
    private static final long DELAY_DURATION_MILISECONDS = 2500;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SplashScreenActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        sessionSharedPreferences = new SessionSharedPreferences(this);



            Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(ANIMATION_DURATION_MILISECONDS);
            animatorSet.setInterpolator(mInterpolator);

            ValueAnimator mScaleUpX = ObjectAnimator.ofFloat(logo, "scaleX", 0.0f, 1.0f);
            ValueAnimator mScaleUpY = ObjectAnimator.ofFloat(logo, "scaleY", 0.0f, 1.0f);

            animatorSet.play(mScaleUpX).with(mScaleUpY);
            animatorSet.start();


            new Handler().postDelayed(() -> {
                if (sessionSharedPreferences.getIS_LOGIN()) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                } else
                    startActivity(new Intent(this, Login_Signup_Activity.class));
                    finish();
            }, DELAY_DURATION_MILISECONDS);
        }

    }

