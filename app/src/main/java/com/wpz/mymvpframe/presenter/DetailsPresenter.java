package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.DetailsBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IDetailsView;

/**
 * @类作用:
 * @author: 王鹏智
 * @Date: 2017/10/13  22:24
 * <p>
 * 思路：
 */


public class DetailsPresenter extends BasePresenter<IDetailsView> {
    //请求数据
    public void getDetailsData(Context context, int id){
        HttpUtils.getData(RetrofitApi.getServer().getDetatlsData(id),new ObserverApi<DetailsBean>(context) {
            @Override
            public void onSuccess(DetailsBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
