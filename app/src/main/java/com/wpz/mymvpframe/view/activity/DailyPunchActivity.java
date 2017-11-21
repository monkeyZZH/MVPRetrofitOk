package com.wpz.mymvpframe.view.activity;

import android.view.View;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.DailyPunchPresenter;
import com.wpz.mymvpframe.view.iview.IDailyPunchView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wpz on 2017/10/30 0030.
 * 类作用：每日打卡
 */

public class DailyPunchActivity extends BaseActivity<DailyPunchPresenter> implements IDailyPunchView,View.OnClickListener {

    private TextView daily_punch_date;
    private TextView daily_punch_time;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new DailyPunchPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_daily_punch;
    }

    @Override
    protected void initView() {
        //年月日星期
        daily_punch_date = (TextView) findViewById(R.id.daily_punch_date);
        //时分
        daily_punch_time = (TextView) findViewById(R.id.daily_punch_time);

    }

    @Override
    protected void initDatas() {
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd EEEE");
        daily_punch_date.setText(format.format(date));
        format=new SimpleDateFormat("HH:mm");
        daily_punch_time.setText(format.format(date));

    }

    @Override
    public void onClick(View v) {

    }

}
