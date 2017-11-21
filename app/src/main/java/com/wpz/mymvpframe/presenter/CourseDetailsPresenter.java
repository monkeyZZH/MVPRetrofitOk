package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.CourseDetailsBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.ICourseDrtailsView;

/**
 * Created by wpz on 2017/10/18 0018.
 * 类作用：
 */

public class CourseDetailsPresenter extends BasePresenter<ICourseDrtailsView> {
    //获取课程详情的借口
    public void getCourseDetailsData(Context context, int id) {
        HttpUtils.getData(RetrofitApi.getServer().getCourseDetailsData(id), new ObserverApi<CourseDetailsBean>(context) {
            @Override
            public void onSuccess(CourseDetailsBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
