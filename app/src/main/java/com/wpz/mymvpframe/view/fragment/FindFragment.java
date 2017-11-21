package com.wpz.mymvpframe.view.fragment;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseFragment;
import com.wpz.mymvpframe.presenter.FindPresenter;
import com.wpz.mymvpframe.view.iview.IFindView;

/**
 * Created by wpz on 2017/10/12 0012.
 * 类作用：发现Fragment
 */

public class FindFragment extends BaseFragment<FindPresenter> implements IFindView {
    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new FindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fagment_find;
    }
}
