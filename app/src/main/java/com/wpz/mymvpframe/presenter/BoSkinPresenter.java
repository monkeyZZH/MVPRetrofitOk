package com.wpz.mymvpframe.presenter;

import android.content.Context;

import com.wpz.mymvpframe.api.ObserverApi;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BasePresenter;
import com.wpz.mymvpframe.model.bean.VideoBean;
import com.wpz.mymvpframe.model.httputils.HttpUtils;
import com.wpz.mymvpframe.view.iview.IBoSkinView;

/**
 * name:周振辉
 * 时间：2017/11/13 19:13
 * 类描述：
 */

public class BoSkinPresenter extends BasePresenter<IBoSkinView> {

    //获取视频的借口
    public void getVideoData(Context context, String videoId) {
        HttpUtils.getData(RetrofitApi.getServer().getVideoData(videoId), new ObserverApi<VideoBean>(context) {
            @Override
            public void onSuccess(VideoBean o) {
                getView().onsuccess(o);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
