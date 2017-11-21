package com.wpz.mymvpframe.presenter.resisterorlogin;

import android.content.Context;
import android.widget.Toast;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.LandingBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IRegisterLoginView;

/**
 * Created by wpz on 2017/11/6 0006.
 * 类作用：登陆
 */

public class RegisterLoginPresenter extends BasePresenter<IRegisterLoginView> {

    //请求数据
    public void getConfirmData(final Context context, String username, String password){
        HttpUtils.getData(RetrofitApi.getServer().getLandData(username,password),new ObserverApi<LandingBean>(context) {
            @Override
            public void onSuccess(LandingBean o) {
                if (o.getCode() == 1){
                    getView().onsuccess(o);
                }else {
                    Toast.makeText(context, o.getMsg() + "", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
