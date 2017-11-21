package com.wpz.mymvpframe.view.activity.register;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterProtocolPresenter;
import com.wpz.mymvpframe.view.iview.IRegisterProtocolView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：条款页
 */

public class RegisterProtocolActivity extends BaseActivity<RegisterProtocolPresenter> implements IRegisterProtocolView,View.OnClickListener {

    private ImageView register_protocol_title_return;
    private TextView register_protocol_know;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterProtocolPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_protocol;
    }

    @Override
    protected void initView() {
        //知道了
        register_protocol_know = (TextView) findViewById(R.id.register_protocol_know);
        //返回按钮
        register_protocol_title_return = (ImageView) findViewById(R.id.register_protocol_title_return);
    }

    @Override
    protected void initDatas() {
        register_protocol_know.setOnClickListener(this);
        register_protocol_title_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_protocol_know:
                finish();
                break;
            case R.id.register_protocol_title_return:
                finish();
                break;
        }
    }

}
