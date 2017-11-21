package com.wpz.mymvpframe.view.activity.register;

import android.content.Intent;
import android.graphics.Paint;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.LandingBean;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterLoginPresenter;
import com.wpz.mymvpframe.view.activity.MainActivity;
import com.wpz.mymvpframe.view.iview.IRegisterLoginView;

/**
 * Created by wpz on 2017/11/6 0006.
 * 类作用：登陆页面
 */

public class RegisterLoginActivity extends BaseActivity<RegisterLoginPresenter> implements IRegisterLoginView, View.OnClickListener {

    private TextView register_password_title_right_text;
    private ImageView register_password_title_return;
    private EditText register_login_phono;
    private EditText register_login_password;
    private CheckBox register_login_password_visible_checkbox;
    private TextView register_login_retrieve_password;
    private TextView register_password_next_text;

    @Override
    public void onsuccess(Object o) {
        LandingBean landingBean = (LandingBean) o;
        int code = landingBean.getCode();
        Toast.makeText(mContext, code + "", Toast.LENGTH_SHORT).show();
        if (code == 1){
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterLoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_login;
    }

    @Override
    protected void initView() {
        //注册
        register_password_title_right_text = (TextView) findViewById(R.id.register_password_title_right_text);
        //返回
        register_password_title_return = (ImageView) findViewById(R.id.register_password_title_return);

        //用户账号（手机号）
        register_login_phono = (EditText) findViewById(R.id.register_login_phono);
        //用户密码
        register_login_password = (EditText) findViewById(R.id.register_login_password);
        //密码是否可见
        register_login_password_visible_checkbox = (CheckBox) findViewById(R.id.register_login_password_visible_checkbox);
        //找回密码
        register_login_retrieve_password = (TextView) findViewById(R.id.register_login_retrieve_password);
        //登陆
        register_password_next_text = (TextView) findViewById(R.id.register_password_next_text);
        //背景
        ImageView register_login_beijing_img = (ImageView) findViewById(R.id.register_login_beijing_img);
        register_login_beijing_img.setAlpha(0.2f);
    }

    @Override
    protected void initDatas() {
        register_login_retrieve_password.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        register_password_next_text.setOnClickListener(this);
        register_password_title_right_text.setOnClickListener(this);
        register_login_password_visible_checkbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_password_next_text:
                String num = register_login_phono.getText().toString();
                String pasw = register_login_password.getText().toString();
                mPresenter.getConfirmData(this,num,pasw);
                break;
            case R.id.register_password_title_right_text:
                startActivity(new Intent(this,RegisterOrLoginActivity.class));
                finish();
                break;
            case R.id.register_login_password_visible_checkbox:
            if (register_login_password_visible_checkbox.isChecked()){
                Log.e("asdasdasdsd",register_login_password_visible_checkbox.isChecked() + "");
                register_login_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }else {
                Log.e("asdasdasdsd",register_login_password_visible_checkbox.isChecked() + "");
                register_login_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );
            }
            break;
        }
    }

}
