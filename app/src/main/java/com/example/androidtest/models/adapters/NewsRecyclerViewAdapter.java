package com.example.androidtest.models.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidtest.databinding.RecycerViewBinding;
import com.example.androidtest.interfaces.RecyclerViewItemClickListener;
import com.example.androidtest.models.ArticleResponseModel;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.RecyclerViewViewHolder> {

    private static RecycerViewBinding mRecyclerViewBinding;
    private final List<ArticleResponseModel> mArticleResponseModels;
    private RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public NewsRecyclerViewAdapter(List<ArticleResponseModel> mArticleResponseModels, RecyclerViewItemClickListener listener) {
        this.mArticleResponseModels = mArticleResponseModels;
        this.mRecyclerViewItemClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mRecyclerViewBinding = RecycerViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecyclerViewViewHolder(mRecyclerViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewViewHolder holder, int position) {
        ArticleResponseModel articleResponseModel = mArticleResponseModels.get(position);
        holder.bind(articleResponseModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerViewItemClickListener.onClick(articleResponseModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticleResponseModels.size();
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public RecyclerViewViewHolder(@NonNull RecycerViewBinding recycerViewBinding) {
            super(recycerViewBinding.getRoot());
        }

        public void bind(ArticleResponseModel articleResponseModel) {
            mRecyclerViewBinding.setArticle(articleResponseModel);
            getImage(articleResponseModel);
        }

        public void getImage(ArticleResponseModel articleResponseModel) {
            Glide.with(mRecyclerViewBinding.getRoot())
                    .load(articleResponseModel.getUrlToImage())
                    .into(mRecyclerViewBinding.recImageView);

        }
    }

    public void setListener(RecyclerViewItemClickListener listener) {
        this.mRecyclerViewItemClickListener = listener;
    }
}
