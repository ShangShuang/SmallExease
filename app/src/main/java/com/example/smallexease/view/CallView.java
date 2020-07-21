package com.example.smallexease.view;

import com.example.smallexease.bean.ListBean;

public interface CallView {
    void onSuccess(ListBean listBean);

    void onFail(String error);
}
