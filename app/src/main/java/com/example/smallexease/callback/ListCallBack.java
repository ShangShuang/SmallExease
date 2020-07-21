package com.example.smallexease.callback;

import com.example.smallexease.bean.ListBean;

public interface ListCallBack {
    void onSuccess(ListBean listBean);

    void onFail(String error);
}
