package com.wpz.mymvpframe.presenter.resisterorlogin;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.RegisterPhoneNumBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IRegisterPhoneNumView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：
 */

public class RegisterPhoneNumPresenter extends BasePresenter<IRegisterPhoneNumView>{

    //请求数据
    public void getPhoneData(final Context context, String mobile){
        HttpUtils.getData(RetrofitApi.getServer().getPhoneData(mobile),new ObserverApi<RegisterPhoneNumBean>(context) {
            @Override
            public void onSuccess(RegisterPhoneNumBean o) {
                    getView().onsuccess(o);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
