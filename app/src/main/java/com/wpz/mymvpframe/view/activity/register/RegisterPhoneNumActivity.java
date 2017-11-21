package com.wpz.mymvpframe.view.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.RegisterPhoneNumBean;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterPhoneNumPresenter;
import com.wpz.mymvpframe.utils.VerificationUtil;
import com.wpz.mymvpframe.view.iview.IRegisterPhoneNumView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：输入手机号和第三方登录
 */

public class RegisterPhoneNumActivity extends BaseActivity<RegisterPhoneNumPresenter> implements IRegisterPhoneNumView, View.OnClickListener {

    private ImageView register_title_left;
    private TextView register_title_right_text;
    private EditText register_phone;
    private CheckBox register_checkBox;
    private TextView register_protocol;
    private TextView register_register_text;
    private ImageView register_phone_q_q;
    private ImageView register_phone_xinlang;
    private ImageView register_phone_weixin;
    private String phone;

    @Override
    public void onsuccess(Object o) {
        RegisterPhoneNumBean RegisterPhoneNumBean = (RegisterPhoneNumBean)o;
        int code = RegisterPhoneNumBean.getCode();
        if (code == 1){
            Intent intent = new Intent(this, RegisterPasswordActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PhonoId", phone);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if (code == 0){
            String msg = RegisterPhoneNumBean.getMsg();
            Toast.makeText(mContext,msg , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterPhoneNumPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_phone_num;
    }

    @Override
    protected void initView() {
        //返回
        register_title_left = (ImageView) findViewById(R.id.register_title_left);
        //登陆
        register_title_right_text = (TextView) findViewById(R.id.register_title_right_text);
        //手机号
        register_phone = (EditText) findViewById(R.id.register_phone);
        //同意条款
        register_checkBox = (CheckBox) findViewById(R.id.register_checkBox);
        //条款的点击事件
        register_protocol = (TextView) findViewById(R.id.register_protocol);
        //下一步
        register_register_text = (TextView) findViewById(R.id.register_register_text);
        //QQ
        register_phone_q_q = (ImageView) findViewById(R.id.register_phone_q_q);
        //新浪
        register_phone_xinlang = (ImageView) findViewById(R.id.register_phone_xinlang);
        //微信
        register_phone_weixin = (ImageView) findViewById(R.id.register_phone_weixin);
        //图片
        ImageView register_phone_num_beijing_img = (ImageView) findViewById(R.id.register_phone_num_beijing_img);
        register_phone_num_beijing_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {
        // 对EditText内容的实时监听
        register_phone.addTextChangedListener(new TextWatcher() {

            // 第二个执行
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {
//                System.out.println("onTextChanged:" + "start:" + start + "before:" + before + "count:" + count);
                Log.e("ceshi","onTextChanged:" + "start:" + start + "before:" + before + "count:" + count);
            }

            // 第一个执行
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
//                System.out.println("beforeTextChanged:" + "start:" + start + "count:" + count + "after:" + after);
                Log.e("ceshi","beforeTextChanged:" + "start:" + start + "count:" + count + "after:" + after);
            }

            // 第三个执行
            @Override
            public void afterTextChanged(Editable s) { // Edittext中实时的内容
//                System.out.println("afterTextChanged:" + s);
                Log.e("ceshi",s + "");
                if (s == null){
                    View v = findViewById(R.id.register_register_text);//找到你要设透明背景的layout 的id
                    v.getBackground().setAlpha(204);//0~255透明度值
//                    register_register_text.setTextColor(this.getResources().getColor(R.color.touming));
                }else {
                    View v = findViewById(R.id.register_register_text);//找到你要设透明背景的layout 的id
                    v.getBackground().setAlpha(255);//0~255透明度值
//                    register_register_text.setTextColor(this.getResources().getColor(R.color.White));
                }

            }
        });



//        register_register_text.setBackgroundColor(Color.argb(55, 420, 640, 98)); //背景透明度
//        register_register_text.setTextColor(Color.argb(343, 447, 64, 45));   //文字透明度

        register_title_left.setOnClickListener(this);
        register_title_right_text.setOnClickListener(this);
        register_protocol.setOnClickListener(this);
        register_register_text.setOnClickListener(this);
        register_phone_q_q.setOnClickListener(this);
        register_phone_xinlang.setOnClickListener(this);
        register_phone_weixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_title_left:
                finish();
                break;
            case R.id.register_title_right_text:
                startActivity(new Intent(this,RegisterLoginActivity.class));
                finish();
                break;
            case R.id.register_protocol:
                startActivity(new Intent(this,RegisterProtocolActivity.class));
                break;
            case R.id.register_register_text:
                phone = register_phone.getText().toString();
                boolean validTelNumber = VerificationUtil.isValidTelNumber(phone);
                if (validTelNumber){
                    mPresenter.getPhoneData(this,phone);
                }else {
                    Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_phone_q_q:

                break;
            case R.id.register_phone_xinlang:

                break;
            case R.id.register_phone_weixin:

                break;
        }
    }

}
