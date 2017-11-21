package com.wpz.mymvpframe.view.activity.register;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterRetrievePasswordPhonePresenter;
import com.wpz.mymvpframe.view.iview.IRegisterRetrievePasswordPhoneView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：手机号登陆，和第三方登录
 */

public class RegisterRetrievePasswordPhoneActivity extends BaseActivity<RegisterRetrievePasswordPhonePresenter> implements IRegisterRetrievePasswordPhoneView, View.OnClickListener {

    private EditText register_retrieve_phone;
    private TextView register_retrieve_login_text;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterRetrievePasswordPhonePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_retrieve_password_phone;
    }

    @Override
    protected void initView() {
        //手机号
        register_retrieve_phone = (EditText) findViewById(R.id.register_retrieve_phone);
        //登陆
        register_retrieve_login_text = (TextView) findViewById(R.id.register_retrieve_login_text);
        //图片
        ImageView register_reset_password_beijing_img = (ImageView) findViewById(R.id.register_reset_password_beijing_img);
        register_reset_password_beijing_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {

    }

}
