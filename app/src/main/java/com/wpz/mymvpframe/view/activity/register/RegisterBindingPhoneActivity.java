package com.wpz.mymvpframe.view.activity.register;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterBindingPhonePresenter;
import com.wpz.mymvpframe.view.iview.IRegisterBindingPhoneView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：绑定手机号页面
 */

public class RegisterBindingPhoneActivity extends BaseActivity<RegisterBindingPhonePresenter> implements IRegisterBindingPhoneView, View.OnClickListener {

    private ImageView register_binding_title_return;
    private TextView register_binding_protocol;
    private TextView register_binding_next_text;
    private EditText register_binding_phone;
    private CheckBox register_binding_checkBox;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterBindingPhonePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_binding_phone;
    }

    @Override
    protected void initView() {
        //返回
        register_binding_title_return = (ImageView) findViewById(R.id.register_binding_title_return);
        //下一步
        register_binding_next_text = (TextView) findViewById(R.id.register_binding_next_text);
        //手机号
        register_binding_phone = (EditText) findViewById(R.id.register_binding_phone);
        //同意条款
        register_binding_checkBox = (CheckBox) findViewById(R.id.register_binding_checkBox);
        //注册条款
        register_binding_protocol = (TextView) findViewById(R.id.register_binding_protocol);
        //图片
        ImageView register_bind_img = (ImageView) findViewById(R.id.register_bind_img);
        register_bind_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_binding_title_return:
                finish();
                break;
            case R.id.register_binding_next_text:
//                startActivity();
                break;
            case R.id.register_binding_protocol:

                break;
        }
    }

}
