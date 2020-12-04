package com.example.androidtest.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidtest.R;
import com.example.androidtest.databinding.FragmentNewsBinding;
import com.example.androidtest.interfaces.FragmentSendDataListener;
import com.example.androidtest.interfaces.RecyclerViewItemClickListener;
import com.example.androidtest.models.AnswerModel;
import com.example.androidtest.models.ArticleResponseModel;
import com.example.androidtest.models.NewsResponseModel;
import com.example.androidtest.models.adapters.NewsRecyclerViewAdapter;
import com.example.androidtest.view.fragments.base.BaseFragment;
import com.example.androidtest.viewModel.NewsViewModel;

import java.util.List;


public class NewsFragment extends BaseFragment {

    private FragmentNewsBinding mFragmentNewsBinding;
    private NewsViewModel mNewsViewModel;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private FragmentSendDataListener mFragmentSendDataListener;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModels();
        initObservers();
        loadData();
        getActivity().setTitle("News");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentNewsBinding = FragmentNewsBinding.inflate(inflater, null, false);
        return mFragmentNewsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentSendDataListener) {
            mFragmentSendDataListener = (FragmentSendDataListener) context;
        }
    }

    private void loadData() {
        mNewsViewModel.getNewsResponse();
    }

    private void initViewModels() {
        mNewsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
    }


    private void initObservers() {
        mNewsViewModel.getNewsResponseModelsLiveData().observe(requireActivity(), new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel newsResponseModel) {
                configureRecyclerView(newsResponseModel);
                mNewsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
        mNewsViewModel.getAnswerModelsLiveData().observe(requireActivity(), new Observer<AnswerModel>() {
            @Override
            public void onChanged(AnswerModel answerModel) {
                showMessage();
            }
        });

    }

    private void showMessage() {
        Toast.makeText(requireActivity(), mNewsViewModel.getAnswerModelsLiveData().getValue().toString(), Toast.LENGTH_SHORT).show();
    }

    private void configureRecyclerView(NewsResponseModel newsResponseModel) {
        List<ArticleResponseModel> articleResponseModelList = newsResponseModel.getArticles();
        mFragmentNewsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(articleResponseModelList, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(ArticleResponseModel articleResponseModel) {
                mFragmentSendDataListener.onClick(articleResponseModel);
            }
        });
        mFragmentNewsBinding.recyclerView.setAdapter(mNewsRecyclerViewAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mFragmentSendDataListener != null) {
            mFragmentSendDataListener = null;
        }
    }

}