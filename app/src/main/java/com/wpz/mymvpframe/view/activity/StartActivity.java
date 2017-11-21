package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.api.RetrofitApi;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.LandingBean;
import com.wpz.mymvpframe.presenter.StartPresenter;
import com.wpz.mymvpframe.view.activity.register.RegisterLoginActivity;
import com.wpz.mymvpframe.view.iview.IStartView;

import java.util.List;

import okhttp3.Cookie;

/**
 * Created by wpz on 2017/10/12 0012.
 * 类作用：启动页
 */

public class StartActivity extends BaseActivity<StartPresenter> implements IStartView, View.OnClickListener {

    private TextView start_text;
    private Button start_btn_just;
    private Intent intent;
    private boolean isCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Cookie> cookies = RetrofitApi.getCookies();
        for (int i = 0; i < cookies.size(); i++) {
            String name = cookies.get(i).name();
            Log.e("Cookies", name);
            Log.e("Cookies", cookies.get(i) + "");
        }
        Log.e("Cookies", cookies.size() + "");
        Log.e("Cookies", "没了");
    }

    @Override
    protected void createPresenter() {
        mPresenter = new StartPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        start_text = (TextView) findViewById(R.id.start_text);
        start_btn_just = (Button) findViewById(R.id.start_btn_just);
        start_btn_just.setOnClickListener(this);

        new Handler(new Handler.Callback() {

            private int a;

            @Override
            public boolean handleMessage(Message msg) {
                if (isCheck) {
                    return true;
                } else {
                    List<Cookie> cookies = RetrofitApi.getCookies();
                    a = 0;
                    for (int i = 0; i < cookies.size(); i++) {
                        String name = cookies.get(i).name();
                        if (name != null) {
                            a = 1;
                        } else {
                            a = 0;
                        }
                        Log.e("Cookies", name);
                        Log.e("Cookies", cookies.get(i) + "");
                    }
                    Log.e("Cookies", cookies.size() + "");
                    Log.e("Cookies", "没了");

                    if (a == 1) {

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), RegisterLoginActivity.class));
                        finish();
                    }
                    return false;
                }
            }
        }).sendEmptyMessageDelayed(0, 3000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn_just:
                isCheck = true;
                List<Cookie> cookies = RetrofitApi.getCookies();
                int a = 0;
                for (int i = 0; i < cookies.size(); i++) {
                    String name = cookies.get(i).name();
                    if (name != null) {
                        a = 1;
                    } else {
                        a = 0;
                    }
                    Log.e("Cookies", name);
                    Log.e("Cookies", cookies.get(i) + "");
                }
                Log.e("Cookies", cookies.size() + "");
                Log.e("Cookies", "没了");

                if (a == 1) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), RegisterLoginActivity.class));
                    finish();
                }
                break;
        }
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onsuccess(Object o) {
        LandingBean LandingBean = (LandingBean) o;
        String msg = LandingBean.getMsg();
        Log.e("aaaa", msg);
//        RetrofitApi.clear();
    }

    @Override
    public void onError(Object o) {

    }


}
