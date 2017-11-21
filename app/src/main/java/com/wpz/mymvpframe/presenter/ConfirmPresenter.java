package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.ConfirmBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IConfirmView;

/**
 * @类作用:
 * @author: 王鹏智
 * @Date: 2017/10/14  15:07
 * <p>
 * 思路：
 */


public class ConfirmPresenter extends BasePresenter<IConfirmView> {
    //获取课程详情的借口
    public void getConfirmData(Context context, int plan_id,int address,String remark,int pay_method,int coupon,String name) {
        HttpUtils.getData(RetrofitApi.getServer().getConfirmData(plan_id,address,remark,pay_method,coupon,name), new ObserverApi<ConfirmBean>(context) {
            @Override
            public void onSuccess(ConfirmBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
