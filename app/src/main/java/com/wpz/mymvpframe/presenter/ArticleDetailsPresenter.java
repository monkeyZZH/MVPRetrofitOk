package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.ArticleDetailsBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IArticleDetailsView;

/**
 * Created by wpz on 2017/11/1 0001.
 * 类作用：
 */

public class ArticleDetailsPresenter extends BasePresenter<IArticleDetailsView> {

    //获取课程详情的借口
    public void getArticleDetailsData(Context context, int id) {
        HttpUtils.getData(RetrofitApi.getServer().getArticleDetailsData(id), new ObserverApi<ArticleDetailsBean>(context) {
            @Override
            public void onSuccess(ArticleDetailsBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
