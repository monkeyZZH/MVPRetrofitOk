package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.BindScalesPresenter;
import com.wpz.mymvpframe.view.iview.IBindScalesView;

/**
 * Created by wpz on 2017/10/16 0016.
 * 类作用：绑定体脂秤
 */

public class BindScalesActivity extends BaseActivity<BindScalesPresenter> implements IBindScalesView,View.OnClickListener {

    private TextView bindscales_bind_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title_text = (TextView) findViewById(R.id.title_left_text);
        title_text.setText("ibody智能体脂秤");
    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new BindScalesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_scales;
    }

    @Override
    protected void initView() {
        bindscales_bind_btn = (TextView) findViewById(R.id.bindscales_bind_btn);
    }

    @Override
    protected void initDatas() {
        bindscales_bind_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bindscales_bind_btn:
                startActivity(new Intent(mContext,ScaleDataActivity.class));
                break;
        }

    }
}
