package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;

/**
 * Created by wpz on 2017/10/26 0026.
 * 类作用：
 */

public class ModifyPresenter extends BasePresenter {
    public void getModifyData(Context context,int id,String uname, String umobie, String province, String city, String county,String detail, int is_default){
        HttpUtils.getData(RetrofitApi.getServer().getModifyAddressData(id, uname, umobie, province, city, county, detail, is_default), new ObserverApi<ReceiptInformationBean>(context) {
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
}
