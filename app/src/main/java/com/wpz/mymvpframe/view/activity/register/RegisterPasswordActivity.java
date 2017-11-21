package com.wpz.mymvpframe.view.activity.register;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.wpz.mymvpframe.model.bean.RegisterPasswordBean;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterPasswordPresenter;
import com.wpz.mymvpframe.view.iview.IRegisterPasswordView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：注册，输入密码，和获取验证码
 */

public class RegisterPasswordActivity extends BaseActivity<RegisterPasswordPresenter> implements IRegisterPasswordView,View.OnClickListener {

    private ImageView register_password_title_return;
    private TextView register_password_next_text;
    private TextView register_password_verification_text;
    private TextView register_password_verification_voice;
    private TextView register_password_title_right_text;
    private EditText register_password_password;
    private EditText register_password_verification;
    private CheckBox register_password_visible_checkbox;
    private TextView register_password_phono;
    private String phonoId;
    private String verification;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //用户手机号
        register_password_phono = (TextView) findViewById(R.id.register_password_phono);

        Bundle bundle = this.getIntent().getExtras();
        phonoId = bundle.getString("PhonoId");
        String phonoIds = phonoId.substring(0, 3) + "****" + phonoId.substring(7, 11);
        register_password_phono.setText("您的手机号码为" + phonoIds);
        Toast.makeText(mContext, phonoId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onsuccess(Object o) {
        RegisterPasswordBean RegisterPasswordBean = (RegisterPasswordBean) o;
        String msg = RegisterPasswordBean.getMsg();
        Toast.makeText(mContext, msg + "", Toast.LENGTH_SHORT).show();
        int code = RegisterPasswordBean.getCode();
        if (code == 1){
            startActivity(new Intent(this,RegisterAvatarNameActivity.class));
        }
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterPasswordPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_password;
    }

    @Override
    protected void initView() {
        //后退
        register_password_title_return = (ImageView) findViewById(R.id.register_password_title_return);
        //下一步
        register_password_next_text = (TextView) findViewById(R.id.register_password_next_text);
        //获取验证码
        register_password_verification_text = (TextView) findViewById(R.id.register_password_verification_text);
        //申请语音验证
        register_password_verification_voice = (TextView) findViewById(R.id.register_password_verification_voice);
        //登陆
        register_password_title_right_text = (TextView) findViewById(R.id.register_password_title_right_text);
        //密码
        register_password_password = (EditText) findViewById(R.id.register_password_password);
        //验证码
        register_password_verification = (EditText) findViewById(R.id.register_password_verification);
        //可见与不可见
        register_password_visible_checkbox = (CheckBox) findViewById(R.id.register_password_visible_checkbox);
        //图片
        ImageView register_password_beijing_img = (ImageView) findViewById(R.id.register_password_beijing_img);
        register_password_beijing_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {
        register_password_title_return.setOnClickListener(this);
        register_password_next_text.setOnClickListener(this);
        register_password_verification_text.setOnClickListener(this);
        register_password_verification_voice.setOnClickListener(this);
        register_password_verification_voice.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        register_password_title_right_text.setOnClickListener(this);
        register_password_visible_checkbox.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_password_title_return:
                finish();
                break;
            case R.id.register_password_next_text:
                verification = register_password_verification.getText().toString();
                password = register_password_password.getText().toString();
                mPresenter.getCheckVerificationData(this,phonoId,verification,password);
                break;
            case R.id.register_password_verification_text:
                mPresenter.getVerificationData(this,phonoId);
                break;
            case R.id.register_password_verification_voice:
                //语音验证
                break;
            case R.id.register_password_title_right_text:
                //登陆
                break;
            case R.id.register_password_visible_checkbox:
                if (register_password_visible_checkbox.isChecked()){
                    Log.e("asdasdasdsd",register_password_visible_checkbox.isChecked() + "");
                    register_password_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else {
                    Log.e("asdasdasdsd",register_password_visible_checkbox.isChecked() + "");
                    register_password_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT );
                }
                break;
        }
    }

    @Override
    public void getVerificationData(Object o) {

    }
}
