package com.wpz.mymvpframe.view.activity;

import android.view.View;
import android.widget.ImageView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.PunchCardPresenter;
import com.wpz.mymvpframe.view.iview.IPunchCardView;

/**
 * Created by wpz on 2017/10/31 0031.
 * 类作用：每日打卡评价页面
 */

public class PunchCardActivity extends BaseActivity<PunchCardPresenter> implements IPunchCardView, View.OnClickListener {

    private int num = 0;
    private ImageView punch_card_general_img;
    private ImageView punch_card_little_tired_img;
    private ImageView punch_card_small_cool_img;
    private ImageView punch_card_super_tired_img;
    private ImageView punch_card_super_cool_img;

    @Override
    protected void createPresenter() {
        mPresenter = new PunchCardPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_punch_card;
    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void initView() {
        //一般
        punch_card_general_img = (ImageView) findViewById(R.id.punch_card_general_img);
        //小累
        punch_card_little_tired_img = (ImageView) findViewById(R.id.punch_card_little_tired_img);
        //小爽
        punch_card_small_cool_img = (ImageView) findViewById(R.id.punch_card_small_cool_img);
        //超累
        punch_card_super_tired_img = (ImageView) findViewById(R.id.punch_card_super_tired_img);
        //超爽
        punch_card_super_cool_img = (ImageView) findViewById(R.id.punch_card_super_cool_img);
        initEvents();
    }

    private void initEvents() {
        //一般
        punch_card_general_img.setOnClickListener(this);
        //小累
        punch_card_little_tired_img.setOnClickListener(this);
        //小爽
        punch_card_small_cool_img.setOnClickListener(this);
        //超累
        punch_card_super_tired_img.setOnClickListener(this);
        //超爽
        punch_card_super_cool_img.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.punch_card_general_img:
                if (num == 0) {
                    punch_card_general_img.setImageResource(R.mipmap.general_true_2x);
                    num += 1;
                } else if (num == 1) {
                    punch_card_general_img.setImageResource(R.mipmap.general_true_2x);

                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_false_2x);
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_false_2x);
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_false_2x);
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_false_2x);
                }
                break;
            case R.id.punch_card_little_tired_img:
                if (num == 0) {
                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_true_2x);
                    num += 1;
                } else if (num == 1) {
                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_true_2x);

                    punch_card_general_img.setImageResource(R.mipmap.general_false_2x);
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_false_2x);
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_false_2x);
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_false_2x);
                }
                break;
            case R.id.punch_card_small_cool_img:
                if (num == 0) {
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_true_2x);
                    num += 1;
                } else if (num == 1) {
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_true_2x);

                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_false_2x);
                    punch_card_general_img.setImageResource(R.mipmap.general_false_2x);
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_false_2x);
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_false_2x);
                }
                break;
            case R.id.punch_card_super_tired_img:
                if (num == 0) {
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_true_2x);
                    num += 1;
                } else if (num == 1) {
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_true_2x);

                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_false_2x);
                    punch_card_general_img.setImageResource(R.mipmap.general_false_2x);
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_false_2x);
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_false_2x);
                }
                break;
            case R.id.punch_card_super_cool_img:
                if (num == 0) {
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_true_2x);
                    num += 1;
                } else if (num == 1) {
                    punch_card_super_cool_img.setImageResource(R.mipmap.super_cool_true_2x);

                    punch_card_little_tired_img.setImageResource(R.mipmap.little_tired_false_2x);
                    punch_card_general_img.setImageResource(R.mipmap.general_false_2x);
                    punch_card_super_tired_img.setImageResource(R.mipmap.super_tired_false_2x);
                    punch_card_small_cool_img.setImageResource(R.mipmap.small_cool_false_2x);
                }
                break;
        }
    }

}
