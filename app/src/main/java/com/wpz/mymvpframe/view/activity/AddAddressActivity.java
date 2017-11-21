package com.wpz.mymvpframe.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.AddAddressPresenter;
import com.wpz.mymvpframe.view.iview.IAddAddressView;

import ch.ielse.view.SwitchView;

/**
 * Created by wpz on 2017/10/16 0016.
 * 类作用：新建收货地址类
 */

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements IAddAddressView, View.OnClickListener {

    private EditText addaddress_name_edit;
    private EditText addaddress_phone_edit;
    private EditText addaddress_address_edit;
    private TextView addaddress_save_btn;
    private ImageView title_return;
    private SwitchView addaddress_default_btn;
    private TextView addaddress_address_text;
    private String detail;//详细地址
    private int is_default;//是否默认
    private String uname;//用户名
    private String umobie;//用户联系方式
    private String Province;//省
    private String City;//市
    private String County;//县(区)

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title_text = (TextView) findViewById(R.id.title_left_text);
        title_text.setText("新增收货地址");
    }
    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new AddAddressPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_addaddress;
    }

    @Override
    protected void initView() {
        title_return = (ImageView) findViewById(R.id.title_left_return);//返回按钮
        addaddress_name_edit = (EditText) findViewById(R.id.addaddress_name_edit);//联系人姓名
        addaddress_phone_edit = (EditText) findViewById(R.id.addaddress_phone_edit);//联系人电话
        addaddress_address_edit = (EditText) findViewById(R.id.addaddress_Address_edit);//详细地址
        addaddress_save_btn = (TextView) findViewById(R.id.addaddress_save_btn);//保存按钮
        //设置默认的按钮
        addaddress_default_btn = (SwitchView) findViewById(R.id.addaddress_default_btn);
        //设置地址信息
        addaddress_address_text = (TextView) findViewById(R.id.addaddress_address_text);
        initEvents();
    }

    private void initEvents() {
        title_return.setOnClickListener(this);//返回按钮
        addaddress_save_btn.setOnClickListener(this);//保存按钮
        addaddress_default_btn.setOnClickListener(this);
        addaddress_address_text.setOnClickListener(this);//设置地址信息
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

            case R.id.addaddress_save_btn:
                String name = addaddress_name_edit.getText().toString();
                String phone = addaddress_phone_edit.getText().toString();
                String address = addaddress_address_edit.getText().toString();
                Toast.makeText(mContext, "姓名：" + name + "\n电话：" + phone + "\n省:"+ Province + "\n市:"
                        + City + "\n县:" + County +  "\n地址:" + address + "\n默认状态：" +
                        addaddress_default_btn.isOpened(), Toast.LENGTH_SHORT).show();
                int a = 0;
                if (addaddress_default_btn.isOpened()){
                    a = 1;
                }
                mPresenter.getAddAddressData(this, address, a, name, phone, Province, City, County);
                startActivity(new Intent(this,ReceipInformationActivity.class));
                finish();
                break;

            case R.id.addaddress_default_btn:
                if (addaddress_default_btn.isOpened()){
                Toast.makeText(getApplicationContext(), "设置为默认", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "不设置为默认", Toast.LENGTH_SHORT).show();
            }
                break;
            case R.id.addaddress_address_text:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    selectAddress();//调用CityPicker选取区域
                }
                break;
        }
    }
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(AddAddressActivity.this)
                .textSize(14)
//                .titleBackgroundColor("#FFFFFF")
                // .titleTextColor("#696969")
                .confirTextColor("#1585FF")
                .cancelTextColor("#1585FF")
                .province("北京市")
                .city("北京市")
                .district("东城区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(5)
                .itemPadding(15)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                Province = province.trim();
                City = city.trim();
                County = district.trim();
                addaddress_address_text.setText(province.trim()+ "-" + city.trim()+ "-" + district.trim());
            }
        });
    }
}
