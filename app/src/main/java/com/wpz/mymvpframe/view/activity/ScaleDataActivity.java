package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.ScaleDataPresenter;
import com.wpz.mymvpframe.view.bdbkview.scale.NumericWheelAdapter;
import com.wpz.mymvpframe.view.bdbkview.scale.widget.WheelView;
import com.wpz.mymvpframe.view.iview.IScaleDataView;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by wpz on 2017/11/9 0009.
 * 类作用：体脂秤需要添加的信息
 */

public class ScaleDataActivity extends BaseActivity<ScaleDataPresenter> implements IScaleDataView, View.OnClickListener {

    private RadioGroup scaledata_radio_group;
    private RadioButton scale_data_male;
    private RadioButton scale_data_female;
    private ImageView title_left_return;
    private RelativeLayout scale_data_birthday;
    private RelativeLayout scale_data_height;
    private WheelView whView, whData, whMonth;  //年 月 日
    private AlertDialog builder;
    private Button bt_cancel1;
    private Button bt_sure1;
    private NumericWheelAdapter numericWheelAdapter;
    private String sYear;
    private String sMonth;
    private String sData;
    private static int START_YEAR = 1950, END_YEAR = 2100;
    private TextView scale_data_birthday_text;
    private TextView scale_data_height_text;
    private TextView scale_data_yes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView viewById = (TextView) findViewById(R.id.title_left_text);
        viewById.setText("添加Bodybank计划参与者");
    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ScaleDataPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scale_data;
    }

    @Override
    protected void initView() {
        //后退
        title_left_return = (ImageView) findViewById(R.id.title_left_return);
        scaledata_radio_group = (RadioGroup) findViewById(R.id.scaledata_radio_group);
        //选男
        scale_data_male = (RadioButton) findViewById(R.id.scale_data_male);
        //选女
        scale_data_female = (RadioButton) findViewById(R.id.scale_data_female);
        //选择生日
        scale_data_birthday = (RelativeLayout) findViewById(R.id.scale_data_birthday);
        //选择身高
        scale_data_height = (RelativeLayout) findViewById(R.id.scale_data_height);
        //生日
        scale_data_birthday_text = (TextView) findViewById(R.id.scale_data_birthday_text);
        //身高
        scale_data_height_text = (TextView) findViewById(R.id.scale_data_height_text);
        //确定
        scale_data_yes = (TextView) findViewById(R.id.scale_data_yes);
    }

    @Override
    protected void initDatas() {
        scale_data_male.setOnClickListener(this);
        scale_data_female.setOnClickListener(this);
        scale_data_birthday.setOnClickListener(this);
        scale_data_height.setOnClickListener(this);
        scale_data_yes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scale_data_male:
                //选男
                break;
            case R.id.scale_data_female:
                //选女
                break;
            case R.id.scale_data_birthday:
                showTime();
                break;
            case R.id.scale_data_height:
                //选身高
                break;
            case R.id.bt_cancel: //取消
                builder.dismiss();
                break;
            case R.id.bt_sure: //确定
                setYears();
                break;
            case R.id.scale_data_yes:
                startActivity(new Intent(this,ScanningScalesActivity.class));
                break;
        }
    }


    /**
     * 初始化月份
     */
    private void initMonth() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, 12, "%02d");
        numericWheelAdapter.setLabel(" 月");
        numericWheelAdapter.setTextColor(Color.BLACK);
        whMonth.setViewAdapter(numericWheelAdapter);
        whMonth.setCyclic(true);
    }
    /**
     * 显示全部日期
     */
    private void showTime() {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;  //通过Calendar算出的月数要+1
        int curDate = c.get(Calendar.DATE);
        builder = new AlertDialog.Builder(this).create();
        builder.show();
        Window window = builder.getWindow();
        window.setContentView(R.layout.wheelview);
        window.getDecorView().setPadding(0,0,0,0);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM); //设置弹出时位置
        window.setWindowAnimations(R.style.AnimBottom); //设置弹出时动画
        //取消
        bt_cancel1 = (Button) window.findViewById(R.id.bt_cancel);
        bt_cancel1.setOnClickListener(this);
        //确定
        bt_sure1 = (Button) window.findViewById(R.id.bt_sure);
        bt_sure1.setOnClickListener(this);
        whView = (WheelView) window.findViewById(R.id.years);
        whMonth = (WheelView) window.findViewById(R.id.month); //月份
        initMonth();
        whData = (WheelView) window.findViewById(R.id.data);  //天数
        inData(curMonth, curDate);
        whMonth.setCurrentItem(curMonth - 1); // 设置当前时间
        whData.setCurrentItem(curDate - 1);
        whMonth.setVisibleItems(6);
        whData.setVisibleItems(6);
        whView.setCurrentItem(curYear - 1950);
        whView.setVisibleItems(6);
        //滑动
        numericWheelAdapter = new NumericWheelAdapter(this, 1950, curYear);
        numericWheelAdapter.setLabel(" 年");
        whView.setViewAdapter(numericWheelAdapter);
        whView.setCyclic(true);
    }

    /**
     * 将年份传入上面
     */
    public void setYears() {
        String parten = "00";
        DecimalFormat decimal = new DecimalFormat(parten);
        //获取滑动后的年份
        sYear = whView.getCurrentItem() + START_YEAR + "";
        //获取滑动后的月份
        sMonth = decimal.format(whMonth.getCurrentItem() + 1);
        //获取滑动后的日期
        sData = decimal.format(whData.getCurrentItem() + 1);
        //将获取到的数据返回到textView中
        scale_data_birthday_text.setText(sYear + " 年" + sMonth + " 月" + sData + "日");
        //销毁弹框
        builder.dismiss();
    }
    /**
     * 获取天数
     */
    private void inData(int year, int month) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(this, 1, getDay(year, month), "%02d");
        numericWheelAdapter.setLabel(" 日");
        whData.setViewAdapter(numericWheelAdapter);
        whData.setCyclic(true);
    }
    /**
     * 天数
     */
    private int getDay(int year, int month) {
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        } if (month == 2) {
            return  flag ? 29 : 28;
        }
        return month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ?31: 30;
    }
}
