package com.example.androidtest.view.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.androidtest.Constants;
import com.example.androidtest.managers.ITHFragmentManager;
import com.example.androidtest.R;
import com.example.androidtest.interfaces.FragmentSendDataListener;
import com.example.androidtest.models.ArticleResponseModel;
import com.example.androidtest.view.activity.base.BaseActivity;
import com.example.androidtest.view.fragments.NewsFragment;
import com.example.androidtest.view.fragments.WebSiteShowFragment;

public class MainActivity extends BaseActivity implements FragmentSendDataListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureFragment(NewsFragment.newInstance());
    }

    @Override
    public void onClick(ArticleResponseModel articleResponseModel) {
        WebSiteShowFragment webSiteShowFragment = new WebSiteShowFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SEND_DATA_FROM_NEWS_FRAGMENT_KAY, articleResponseModel.getUrl());
        webSiteShowFragment.setArguments(bundle);
        configureFragmentWithFragmentName(webSiteShowFragment, Constants.SHOW_NEWS_FRAGMENT_TAG);
    }

    private void configureFragment(Fragment fragment) {
        ITHFragmentManager.addFragment(R.id.fragmentContainer, getSupportFragmentManager(), fragment);
    }

    private void configureFragmentWithFragmentName(Fragment fragment, String name) {
        ITHFragmentManager.addFragment(R.id.fragmentContainer,
                getSupportFragmentManager(),
                fragment,
                name);
    }
}