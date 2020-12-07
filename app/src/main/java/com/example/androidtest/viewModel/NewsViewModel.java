package com.example.androidtest.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtest.enums.ResponseType;
import com.example.androidtest.models.ResponseModel;
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
    private final MutableLiveData<ResponseModel> mResponseTypeMutableLiveData = new MutableLiveData<>();

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
                        ResponseModel responseModel = new ResponseModel(ResponseType.ERROR,e.getMessage());
                        mResponseTypeMutableLiveData.setValue(responseModel);
                    }

                    @Override
                    public void onComplete() {
                        ResponseModel responseModel = new ResponseModel(ResponseType.COMPLETE,"");
                        mResponseTypeMutableLiveData.setValue(responseModel);
                    }
                }));
    }

    public LiveData<NewsResponseModel> getNewsResponseModelsLiveData() {
        return mNewsResponseModelMutableLiveData;
    }

    public LiveData<ResponseModel> getAnswerModelsLiveData() {
        return mResponseTypeMutableLiveData;
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
