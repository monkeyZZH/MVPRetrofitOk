package com.wpz.mymvpframe.view.activity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.LandingBean;
import com.wpz.mymvpframe.presenter.MainPresenter;
import com.wpz.mymvpframe.view.bdbkview.RegisterDialog;
import com.wpz.mymvpframe.view.fragment.ChattingFragment;
import com.wpz.mymvpframe.view.fragment.FindFragment;
import com.wpz.mymvpframe.view.fragment.MineFragment;
import com.wpz.mymvpframe.view.fragment.TrainingFragment;
import com.wpz.mymvpframe.view.iview.IMainView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView, View.OnClickListener {
    private RadioButton main_radiobtn_training;
    private RadioButton main_radiobtn_find;
    private RadioButton main_radiobtn_chatting;
    private RadioButton main_radiobtn_mine;
    private TrainingFragment trainingFragment;
    private FindFragment findFragment;
    private ChattingFragment chattingFragment;
    private MineFragment mineFragment;

//    private Button mButton;
//    private TextView mTextview;
//    private ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDefultView();
        RegisterDialog.Builder builder = new RegisterDialog.Builder(this);
        builder.setMessage("这个就是自定义的提示框");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();

    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        main_radiobtn_training = (RadioButton) findViewById(R.id.main_radiobtn_training);//训练
        //定义底部标签图片大小和位置
        Drawable drawable_xunlian = getResources().getDrawable(R.drawable.selector_home);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_xunlian.setBounds(0, 0, 40, 70);
        //设置图片在文字的哪个方向
        main_radiobtn_training.setCompoundDrawables(null, drawable_xunlian, null, null);

        main_radiobtn_find = (RadioButton) findViewById(R.id.main_radiobtn_find);//发现
        //定义底部标签图片大小和位置
        Drawable drawable_faxian = getResources().getDrawable(R.drawable.selector_faxian);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_faxian.setBounds(0, 0, 60, 60);
        //设置图片在文字的哪个方向
        main_radiobtn_find.setCompoundDrawables(null, drawable_faxian, null, null);

        main_radiobtn_chatting = (RadioButton) findViewById(R.id.main_radiobtn_chatting);//趣聊
        //定义底部标签图片大小和位置
        Drawable drawable_quliao = getResources().getDrawable(R.drawable.selector_quliao);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_quliao.setBounds(0, 0, 60, 60);
        //设置图片在文字的哪个方向
        main_radiobtn_chatting.setCompoundDrawables(null, drawable_quliao, null, null);

        main_radiobtn_mine = (RadioButton) findViewById(R.id.main_radiobtn_mine);//我的
        //定义底部标签图片大小和位置
        Drawable drawable_mine = getResources().getDrawable(R.drawable.selector_mine);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_mine.setBounds(0, 0, 60, 60);
        //设置图片在文字的哪个方向
        main_radiobtn_mine.setCompoundDrawables(null, drawable_mine, null, null);

        initEvents();

//        mButton = (Button) findViewById(R.id.button);
//        mButton.setOnClickListener(this);
//        mTextview = (TextView) findViewById(R.id.textview);
//        mImageview = (ImageView) findViewById(R.id.imageview);
    }


    private void initEvents() {
        main_radiobtn_training.setOnClickListener(this);//训练
        main_radiobtn_find.setOnClickListener(this);//发现
        main_radiobtn_chatting.setOnClickListener(this);//趣聊
        main_radiobtn_mine.setOnClickListener(this);//我的
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.button:
//                mPresenter.getMainData(mContext);
//                break;
            case R.id.main_radiobtn_training:
                select(0);
                break;
            case R.id.main_radiobtn_find:
                select(1);
                break;
            case R.id.main_radiobtn_chatting:
                select(2);
                break;
            case R.id.main_radiobtn_mine:
                select(3);
                break;
        }
    }
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (trainingFragment != null) {
            fragmentTransaction.hide(trainingFragment);

        }
        if (findFragment != null) {
            fragmentTransaction.hide(findFragment);

        }
        if (chattingFragment != null) {
            fragmentTransaction.hide(chattingFragment);

        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);

        }

    }


    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();  //获得Fragment管理器
        FragmentTransaction ft = fm.beginTransaction(); //开启一个事务

        hidtFragment(ft);   //隐藏Fragment的方法，先判断fragment是否为空，如果不为空则隐藏Fragment

        switch (i) {//点击切换fragment,如果fragment为空，则创建，如果不为空，就显示
            case 0:
                if (trainingFragment == null) {
                    trainingFragment = new TrainingFragment();
                    ft.add(R.id.FramLaout, trainingFragment);
                } else {
                    ft.show(trainingFragment);
                }
                break;
            case 1:
                if (findFragment == null) {
                    findFragment = new FindFragment();
                    ft.add(R.id.FramLaout, findFragment);
                } else {
                    ft.show(findFragment);
                }
                break;
            case 2:
                if (chattingFragment == null) {
                    chattingFragment = new ChattingFragment();
                    ft.add(R.id.FramLaout, chattingFragment);
                } else {
                    ft.show(chattingFragment);
                }
                break;
            case 3:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    ft.add(R.id.FramLaout, mineFragment);
                } else {
                    ft.show(mineFragment);
                }
                break;
        }
        ft.commit();   //提交事务
    }
    private void initDefultView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        trainingFragment = new TrainingFragment();
        ft.add(R.id.FramLaout, trainingFragment);
        ft.commit();
    }

    @Override
    protected void initDatas() {

    }


    @Override
    public void onsuccess(Object o) {
        LandingBean landingBean = (LandingBean) o;
//        MainBean mainBean = (MainBean) o;
//        mTextview.setText(mainBean.getResult().getAdvs().get(0).getPic());
//        Glide.with(this).load(mainBean.getResult().getAdvs().get(0).getPic()).into(mImageview);
        Toast.makeText(mContext, landingBean.getCode() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Object o) {

    }
}
