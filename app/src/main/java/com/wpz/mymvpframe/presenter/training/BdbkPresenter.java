package com.wpz.mymvpframe.presenter.training;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.MainBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IBdbkView;

/**
 * Created by wpz on 2017/10/13 0013.
 * 类作用：
 */

public class BdbkPresenter extends BasePresenter<IBdbkView> {
    //请求数据
    public void getBdbkData(Context context){
        HttpUtils.getData(RetrofitApi.getServer().getbdbkData(),new ObserverApi<MainBean>(context) {
            @Override
            public void onSuccess(MainBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

}
