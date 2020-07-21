package com.example.smallexease.model;

import com.example.smallexease.api.ApiService;
import com.example.smallexease.bean.ListBean;
import com.example.smallexease.callback.ListCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImpListModel implements ListModel {
    @Override
    public void getData(final ListCallBack listCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiService.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ListBean> observable = apiService.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        listCallBack.onSuccess(listBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listCallBack.onFail("网络错误:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
