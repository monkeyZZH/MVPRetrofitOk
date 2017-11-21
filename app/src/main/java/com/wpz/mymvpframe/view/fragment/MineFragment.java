package com.wpz.mymvpframe.view.fragment;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseFragment;
import com.wpz.mymvpframe.presenter.MinePresenter;
import com.wpz.mymvpframe.view.iview.IMainView;

/**
 * Created by wpz on 2017/10/12 0012.
 * 类作用：我的Fragment
 */

public class MineFragment extends BaseFragment<MinePresenter> implements IMainView{
    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new MinePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fagment_mine;
    }
}
