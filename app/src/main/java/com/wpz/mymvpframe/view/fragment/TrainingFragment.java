package com.wpz.mymvpframe.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseFragment;
import com.wpz.mymvpframe.presenter.TrainingPresenter;
import com.wpz.mymvpframe.view.activity.ReceipInformationActivity;
import com.wpz.mymvpframe.view.adapter.TrainingTabPagerAdapter;
import com.wpz.mymvpframe.view.iview.ITrainingView;

/**
 * Created by wpz on 2017/10/12 0012.
 * 类作用：训练Fragment
 */

public class TrainingFragment extends BaseFragment<TrainingPresenter> implements ITrainingView,View.OnClickListener{


    private ViewPager mViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private ImageView training_calendar;
    private ImageView training_title;
    private ImageView training_search;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TrainingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fagment_training;
    }
    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        ViewPager mViewPager= (ViewPager) getActivity().findViewById(R.id.viewPager);
        TrainingTabPagerAdapter myFragmentPagerAdapter = new TrainingTabPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        TabLayout mTabLayout = (TabLayout) getActivity().findViewById(R.id.training_tab);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
//        two = mTabLayout.getTabAt(1);

        //设置Tab的图标，假如不需要则把下面的代码删去
//        one.setIcon(R.mipmap.ic_launcher);
//        two.setIcon(R.mipmap.ic_launcher);

        //日历
        training_calendar = (ImageView) getActivity().findViewById(R.id.training_calendar);
        //标题头
        training_title = (ImageView) getActivity().findViewById(R.id.training_title);
        //搜索
        training_search = (ImageView) getActivity().findViewById(R.id.training_search);
        training_calendar.setOnClickListener(this);
        training_search.setOnClickListener(this);

    }
    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.training_calendar:
                break;
            case R.id.training_search:
                startActivity(new Intent(mContext, ReceipInformationActivity.class));

                break;
        }
    }
}
