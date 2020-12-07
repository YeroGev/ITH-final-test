package com.example.androidtest.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidtest.Constants;
import com.example.androidtest.R;
import com.example.androidtest.view.fragments.base.BaseFragment;


public class WebSiteShowFragment extends BaseFragment {

    private WebView mWebView;
    private String mUrl;

    public static WebSiteShowFragment newInstance() {
        return new WebSiteShowFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().getString(Constants.SEND_DATA_FROM_NEWS_FRAGMENT_KAY)!= null) {
            mUrl = getArguments().getString(Constants.SEND_DATA_FROM_NEWS_FRAGMENT_KAY);
        }
        getActivity().setTitle(mUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_site_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findWebView(view);
        configureWebView(mUrl);
    }

    private void findWebView(View view) {
        mWebView = view.findViewById(R.id.webView);
    }

    public static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void configureWebView(String url) {
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
    }
}