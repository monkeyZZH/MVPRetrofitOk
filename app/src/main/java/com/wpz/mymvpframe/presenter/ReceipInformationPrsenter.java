package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IReceiptInformationView;

/**
 * Created by wpz on 2017/10/20 0020.
 * 类作用：
 */

public class ReceipInformationPrsenter extends BasePresenter<IReceiptInformationView> {
    //请求数据
    public void getMyAddData(Context context){
        HttpUtils.getData(RetrofitApi.getServer().getAdderssData(),new ObserverApi<ReceiptInformationBean>(context) {
            @Override
            public void onSuccess(ReceiptInformationBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    //请求数据
    public void getDeleteAddData(Context context,int id) {
        HttpUtils.getData(RetrofitApi.getServer().getDeleteAddressData(id), new ObserverApi<ReceiptInformationBean>(context) {
            @Override
            public void onSuccess(ReceiptInformationBean o) {
                getView().getDeleteAddData(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
