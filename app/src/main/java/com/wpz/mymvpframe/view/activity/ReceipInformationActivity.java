package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.presenter.ReceipInformationPrsenter;
import com.wpz.mymvpframe.view.adapter.RecripInformListAdapter;
import com.wpz.mymvpframe.view.bdbkview.SideslipListView;
import com.wpz.mymvpframe.view.iview.IReceiptInformationView;

import java.util.List;

//import com.solo.library.ISlide;
//import com.solo.library.OnClickSlideItemListener;

/**
 * Created by wpz on 2017/10/20 0020.
 * 类作用：我的收货地址页
 */

public class ReceipInformationActivity extends BaseActivity<ReceipInformationPrsenter> implements IReceiptInformationView, View.OnClickListener{

    private TextView title_text;
    private SideslipListView receipt_information_list;
    private List<ReceiptInformationBean.DataBean> data;
    private ImageView title_left_return;
    private TextView myadd_add_add;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title_text = (TextView) findViewById(R.id.title_left_text);
        title_text.setText("我的收货地址");
        mPresenter.getMyAddData(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getMyAddData(this);
    }

    @Override
    public void onsuccess(Object o) {
        final ReceiptInformationBean receiptInformationBean = (ReceiptInformationBean) o;
        data = receiptInformationBean.getData();
        Toast.makeText(mContext, receiptInformationBean.getMsg(), Toast.LENGTH_SHORT).show();
        RecripInformListAdapter recripInformListAdapter = new RecripInformListAdapter(this,data,receipt_information_list);
        receipt_information_list.setAdapter(recripInformListAdapter);
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ReceipInformationPrsenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receipt_information;
    }

    @Override
    protected void initView() {
        title_left_return = (ImageView) findViewById(R.id.title_left_return);
        myadd_add_add = (TextView) findViewById(R.id.myadd_add_add);//新建收货地址
        myadd_add_add.setOnClickListener(this);
        title_left_return.setOnClickListener(this);
        //收货列表
        receipt_information_list = (SideslipListView) findViewById(R.id.receipt_information_list);
        receipt_information_list.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initDatas() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left_return:
                finish();
                break;
            case R.id.myadd_add_add:
                startActivity(new Intent(this, AddAddressActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void getDeleteAddData(Object o) {

    }
}
