package com.example.androidtest.view.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidtest.databinding.FragmentNewsBinding;
import com.example.androidtest.interfaces.RecyclerViewItemClickListener;
import com.example.androidtest.models.ResponseModel;
import com.example.androidtest.models.ArticleResponseModel;
import com.example.androidtest.models.NewsResponseModel;
import com.example.androidtest.view.adapters.NewsRecyclerViewAdapter;
import com.example.androidtest.view.fragments.base.BaseFragment;
import com.example.androidtest.viewModel.NewsViewModel;


public class NewsFragment extends BaseFragment {

    private FragmentNewsBinding mFragmentNewsBinding;
    private NewsViewModel mNewsViewModel;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private RecyclerViewItemClickListener mFragmentSendDataListener;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModels();
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
        configureRecyclerView();
        initObservers();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RecyclerViewItemClickListener) {
            mFragmentSendDataListener = (RecyclerViewItemClickListener) context;
        }
    }

    private void loadData() {
        mNewsViewModel.getNewsResponse();
    }

    private void initViewModels() {
        mNewsViewModel = new ViewModelProvider(getActivity()).get(NewsViewModel.class);
    }

    private void initObservers() {
        mNewsViewModel.getNewsResponseModelsLiveData().observe(this, new Observer<NewsResponseModel>() {
            @Override
            public void onChanged(NewsResponseModel newsResponseModel) {
                setRecyclerViewItems(newsResponseModel);
            }
        });

        mNewsViewModel.getAnswerModelsLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseModel>() {
            @Override
            public void onChanged(ResponseModel responseModel) {
                showMessage();
            }
        });
    }

    private void showMessage() {
        Toast.makeText(requireActivity(), mNewsViewModel.getAnswerModelsLiveData().getValue().toString(), Toast.LENGTH_SHORT).show();
    }

    private void configureRecyclerView() {
        mFragmentNewsBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
        mFragmentNewsBinding.recyclerView.setAdapter(mNewsRecyclerViewAdapter);
    }

    private void setRecyclerViewItems(NewsResponseModel newsResponseModel){
        mNewsRecyclerViewAdapter.setArticleResponseModels(newsResponseModel.getArticles());
        mNewsRecyclerViewAdapter.setRecyclerViewItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onClick(ArticleResponseModel articleResponseModel) {
                mFragmentSendDataListener.onClick(articleResponseModel);
            }
        });
        mNewsRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mFragmentSendDataListener != null) {
            mFragmentSendDataListener = null;
        }
    }
}