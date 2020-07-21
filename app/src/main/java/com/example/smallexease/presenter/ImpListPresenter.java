package com.example.smallexease.presenter;

import com.example.smallexease.bean.ListBean;
import com.example.smallexease.callback.ListCallBack;
import com.example.smallexease.model.ImpListModel;
import com.example.smallexease.view.CallView;

public class ImpListPresenter implements ListPresenter, ListCallBack {
    private CallView callView;
    private ImpListModel impListModel;

    public ImpListPresenter(CallView callView) {
        this.callView = callView;
        impListModel = new ImpListModel();
    }

    @Override
    public void getData() {
        impListModel.getData(this);
    }

    @Override
    public void onSuccess(ListBean listBean) {
        callView.onSuccess(listBean);
    }

    @Override
    public void onFail(String error) {
        callView.onFail(error);
    }
}
