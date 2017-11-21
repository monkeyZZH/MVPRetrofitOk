package com.wpz.mymvpframe.presenter.resisterorlogin;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.RegisterPasswordBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IRegisterPasswordView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：校验手机，密码，验证码
 */

public class RegisterPasswordPresenter extends BasePresenter<IRegisterPasswordView> {
    //获取课程详情的借口
    public void getCheckVerificationData(Context context, String mobile, String verify, String password) {
        HttpUtils.getData(RetrofitApi.getServer().getCheckVerificationData(mobile,verify,password), new ObserverApi<RegisterPasswordBean>(context) {
            @Override
            public void onSuccess(RegisterPasswordBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }

    //获取课程详情的借口
    public void getVerificationData(Context context, String mobile) {
        HttpUtils.getData(RetrofitApi.getServer().getVerificationData(mobile), new ObserverApi<RegisterPasswordBean>(context) {
            @Override
            public void onSuccess(RegisterPasswordBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
