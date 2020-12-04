package com.example.androidtest.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.example.androidtest.Constants;
import com.example.androidtest.R;
import com.example.androidtest.databinding.ActivitySplashBinding;
import com.example.androidtest.databinding.FragmentNewsBinding;
import com.example.androidtest.view.activity.base.BaseActivity;
import com.example.androidtest.viewModel.NewsViewModel;

public class SplashActivity extends BaseActivity {

    private ActivitySplashBinding mActivitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
        setContentView(mActivitySplashBinding.getRoot());
        getSplashImage();
        openProgram();
        getSupportActionBar().hide();
    }

    private void getSplashImage() {
        Glide.with(this)
                .load(R.drawable.splash_image)
                .into(mActivitySplashBinding.splashImage);
    }

    private void openProgram() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openMain();
            }
        }, Constants.SPLASH_SLEEP_TIME);
    }

    private void initViewBinding() {
        mActivitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater(), null, false);
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}