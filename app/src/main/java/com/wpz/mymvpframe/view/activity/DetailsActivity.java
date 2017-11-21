package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.DetailsBean;
import com.wpz.mymvpframe.presenter.DetailsPresenter;
import com.wpz.mymvpframe.view.iview.IDetailsView;

/**
 * @类作用:
 * @author: 王鹏智
 * @Date: 2017/10/13  22:20
 * <p>
 * 思路：商品详情页
 */


public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetailsView,View.OnClickListener{

    private ImageView details_image;
    private ImageView title_return;
    private TextView details_btn;
    private RelativeLayout details_service;
    private String name;
    private int id = 9;
    private String price;
    private String img;
    private String desc;
    private int address_id;
    private String province;
    private String city;
    private String county;
    private String detail;
    private String uname;
    private String umobile;
    private boolean address;

    @Override
    public void onsuccess(Object o) {
        DetailsBean detailsBean = (DetailsBean) o;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(Content.BASE_URL + detailsBean.getData().getGimg())
                .apply(options)
                .into(details_image);
        name = detailsBean.getData().getName();
        price = detailsBean.getData().getPrice();
        img = detailsBean.getData().getImg();
        desc = detailsBean.getData().getDesc();

        address_id = detailsBean.getData().getAddress().getId();
        province = detailsBean.getData().getAddress().getProvince();
        city = detailsBean.getData().getAddress().getCity();
        county = detailsBean.getData().getAddress().getCounty();
        detail = detailsBean.getData().getAddress().getDetail();
        uname = detailsBean.getData().getAddress().getUname();
        umobile = detailsBean.getData().getAddress().getUmobile();

        Toast.makeText(mContext, this.name + price + img + desc, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title_text = (TextView) findViewById(R.id.title_left_text);
        title_text.setText("商品详情页");
        mPresenter.getDetailsData(this,id);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new DetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        details_image = (ImageView) findViewById(R.id.details_image);//长图
        title_return = (ImageView) findViewById(R.id.title_left_return);//返回
        title_return.setOnClickListener(this);
        details_btn = (TextView) findViewById(R.id.Details_btn);//立即参与
        details_btn.setOnClickListener(this);
        details_service = (RelativeLayout) findViewById(R.id.details_service);//客服
        details_service.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_left_return:
                finish();
                break;
            case R.id.Details_btn:
                Intent intent = new Intent(DetailsActivity.this, ConfirmActivity.class);
                /* 通过Bundle对象存储需要传递的数据 */
                Bundle bundle = new Bundle();
                /*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
                bundle.putString("Name", name);
                bundle.putString("Price", price);
                bundle.putString("Img", img);
                bundle.putString("Desc", desc);
//                if ()
                bundle.putInt("address",address_id);
                bundle.putString("province",province);
                bundle.putString("city",city);
                bundle.putString("county",county);
                bundle.putString("detail",detail);
                bundle.putString("uname",uname);
                bundle.putString("umobile",umobile);
                /*把bundle对象assign给Intent*/
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
        }
    }
}
