package com.wpz.mymvpframe.view.activity.register;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.resisterorlogin.RegisterAvatarNamePresenter;
import com.wpz.mymvpframe.view.activity.MainActivity;
import com.wpz.mymvpframe.view.iview.IRegisterAvatarNameView;

/**
 * Created by wpz on 2017/11/4 0004.
 * 类作用：注册成功后，上传头像和起名
 */

public class RegisterAvatarNameActivity extends BaseActivity<RegisterAvatarNamePresenter> implements IRegisterAvatarNameView, View.OnClickListener {

    private TextView register_avatar_yes;

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterAvatarNamePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_avatar_name;
    }

    @Override
    protected void initView() {
        //确定
        register_avatar_yes = (TextView) findViewById(R.id.register_avatar_yes);

        //图片
        ImageView register_avatar_beijing_img = (ImageView) findViewById(R.id.register_avatar_beijing_img);
        register_avatar_beijing_img.setAlpha(0.8f);
    }

    @Override
    protected void initDatas() {
        register_avatar_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_avatar_yes:
                Intent intent = new Intent(this, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("yes",);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

}
