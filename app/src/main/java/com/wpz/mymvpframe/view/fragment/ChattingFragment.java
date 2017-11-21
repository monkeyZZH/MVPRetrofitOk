package com.wpz.mymvpframe.view.fragment;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseFragment;
import com.wpz.mymvpframe.presenter.ChattingPresenter;
import com.wpz.mymvpframe.view.iview.IChattingView;

/**
 * Created by wpz on 2017/10/12 0012.
 * 类作用：趣聊Fragment
 */

public class ChattingFragment extends BaseFragment<ChattingPresenter> implements IChattingView {
    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ChattingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fagment_chatting;
    }
}
