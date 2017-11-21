package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IAddAddressView;

/**
 * Created by wpz on 2017/10/16 0016.
 * 类作用：
 */

public class AddAddressPresenter extends BasePresenter<IAddAddressView> {

//                address, a, name, phone, Province, City, County);
    public void getAddAddressData(Context context, String detail, int is_default, String uname, String umobie, String province, String city, String county) {
        HttpUtils.getData(RetrofitApi.getServer().getAddAddressData(uname, umobie, province, city, county, detail, is_default), new ObserverApi<ReceiptInformationBean>(context) {
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
