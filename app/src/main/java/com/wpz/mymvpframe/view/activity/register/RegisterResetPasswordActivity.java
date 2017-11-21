package com.wpz.mymvpframe.view.activity.register;

import android.graphics.Paint;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterResetPasswordPresenter;
import com.wpz.mymvpframe.view.iview.IRegisterResetPasswordView;

/**
 * Created by wpz on 2017/11/6 0006.
 * 类作用：重置密码
 */

public class RegisterResetPasswordActivity extends BaseActivity<RegisterResetPasswordPresenter> implements IRegisterResetPasswordView, View.OnClickListener {

    private EditText register_reset_password_verification;
    private EditText register_reset_password;
    private TextView register_reset_password_verification_text;
    private CheckBox register_reset_password_visible_checkbox;
    private TextView register_reset_password_verification_voice;
    private TextView register_reset_password_yes_text;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterResetPasswordPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_reset_password;
    }

    @Override
    protected void initView() {
        //重置密码
        register_reset_password = (EditText) findViewById(R.id.register_reset_password);
        //输入验证码
        register_reset_password_verification = (EditText) findViewById(R.id.register_reset_password_verification);
        //获取验证码
        register_reset_password_verification_text = (TextView) findViewById(R.id.register_reset_password_verification_text);
        //可见与不可见标识
        register_reset_password_visible_checkbox = (CheckBox) findViewById(R.id.register_reset_password_visible_checkbox);
        //获取语音验证码
        register_reset_password_verification_voice = (TextView) findViewById(R.id.register_reset_password_verification_voice);
        //确定
        register_reset_password_yes_text = (TextView) findViewById(R.id.register_reset_password_yes_text);
        //图片
        ImageView register_reset_password_beijing_img = (ImageView) findViewById(R.id.register_reset_password_beijing_img);
        register_reset_password_beijing_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {
        register_reset_password_verification_text.setOnClickListener(this);
        register_reset_password_visible_checkbox.setOnClickListener(this);
        register_reset_password_verification_voice.setOnClickListener(this);
        register_reset_password_verification_voice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        register_reset_password_yes_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_reset_password_verification_text:
                //获取验证码
                break;
            case R.id.register_reset_password_visible_checkbox:

                if (register_reset_password_visible_checkbox.isChecked()){
                    Log.e("asdasdasdsd",register_reset_password_visible_checkbox.isChecked() + "");
                    register_reset_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    Log.e("asdasdasdsd",register_reset_password_visible_checkbox.isChecked() + "");
                    register_reset_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );
                }
                break;
            case R.id.register_reset_password_verification_voice:
                //语音验证码
                break;
            case R.id.register_reset_password_yes_text:
                //确定
                break;
        }
    }

}
