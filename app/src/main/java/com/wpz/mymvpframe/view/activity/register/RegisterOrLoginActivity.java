package com.wpz.mymvpframe.view.activity.register;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterOrLoginPresenter;
import com.wpz.mymvpframe.view.iview.IRegisterOrLoginView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：注册第一个页
 */

public class RegisterOrLoginActivity extends BaseActivity<RegisterOrLoginPresenter> implements IRegisterOrLoginView, View.OnClickListener {

    private TextView register_register_text;
    private TextView register_login_text;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterOrLoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_or_login;
    }

    @Override
    protected void initView() {
        //注册按钮{
        register_register_text = (TextView) findViewById(R.id.register_register_text);
        //登陆按钮
        register_login_text = (TextView) findViewById(R.id.register_login_text);
        register_register_text.setOnClickListener(this);
        register_login_text.setOnClickListener(this);
        register_login_text.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        //图片
        ImageView register_or_login_beijing_img = (ImageView) findViewById(R.id.register_or_login_beijing_img);
        register_or_login_beijing_img.setAlpha(0.8f);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_register_text:
                startActivity(new Intent(this,RegisterPhoneNumActivity.class));
                finish();
                break;
            case R.id.register_login_text:
                startActivity(new Intent(this,RegisterLoginActivity.class));
                finish();
                break;
        }
    }

}
