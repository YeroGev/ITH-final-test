package com.example.androidtest.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtest.models.NewsResponseModel;
import com.example.androidtest.repository.NewsRepository;
import com.example.androidtest.repository.NewsRepositoryImpl;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    private final NewsRepository mNewsRepository;

    private final CompositeDisposable mCompositeDisposable;

    private final MutableLiveData<NewsResponseModel> mNewsResponseModelMutableLiveData = new MutableLiveData<>();


    public NewsViewModel() {
        mNewsRepository = new NewsRepositoryImpl();
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getNewsResponse() {
        mCompositeDisposable.add(mNewsRepository.getNewsResponseModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<NewsResponseModel>() {
                    @Override
                    public void onNext(@NonNull NewsResponseModel newsResponseModel) {
                        mNewsResponseModelMutableLiveData.setValue(newsResponseModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public LiveData<NewsResponseModel> getNewsResponseModelsLiveData() {
        return mNewsResponseModelMutableLiveData;
    }

    private void dispose() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        dispose();
    }
}
