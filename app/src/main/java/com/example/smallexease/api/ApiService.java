package com.example.smallexease.api;

import com.example.smallexease.bean.ListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String baseUrl = "http://static.owspace.com/";

    @GET("?c=api&a=getList&page_id=0")
    Observable<ListBean> getData();
}
