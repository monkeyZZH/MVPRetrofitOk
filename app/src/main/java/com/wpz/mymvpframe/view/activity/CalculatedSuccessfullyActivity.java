package com.wpz.mymvpframe.view.activity;

import android.view.View;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.CalculatedSuccessfullyPresenter;
import com.wpz.mymvpframe.view.iview.ICalculatedSuccessfullyView;

/**
 * Created by wpz on 2017/11/13 0013.
 * 类作用：测量成功之后
 */

public class CalculatedSuccessfullyActivity extends BaseActivity<CalculatedSuccessfullyPresenter> implements ICalculatedSuccessfullyView, View.OnClickListener{
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new CalculatedSuccessfullyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calculated_successfully;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDatas() {

    }
}
