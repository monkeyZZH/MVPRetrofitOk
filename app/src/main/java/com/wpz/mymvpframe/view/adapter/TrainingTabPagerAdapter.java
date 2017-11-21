package com.wpz.mymvpframe.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wpz.mymvpframe.view.fragment.training.BdbkFragment;

/**
 * Created by wpz on 2017/10/13 0013.
 * 类作用：
 */

public class TrainingTabPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"BODYBANK训练"};
//    ,"Free训练"

    public TrainingTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new BdbkFragment();
        }
//        else if (position == 2) {
//            return new Fragment3();
//        }
//        else if (position == 3) {
//            return new Fragment4();
//        }
        return new BdbkFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
