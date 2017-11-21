package com.wpz.mymvpframe.view.fragment.training;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextPaint;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.base.BaseFragment;
import com.wpz.mymvpframe.model.bean.MainBean;
import com.wpz.mymvpframe.presenter.training.BdbkPresenter;
import com.wpz.mymvpframe.view.activity.BindScalesActivity;
import com.wpz.mymvpframe.view.activity.DetailsActivity;
import com.wpz.mymvpframe.view.activity.register.RegisterLoginActivity;
import com.wpz.mymvpframe.view.adapter.TrainingExpandableListViewAdapter;
import com.wpz.mymvpframe.view.bdbkview.ScaleRulerView;
import com.wpz.mymvpframe.view.bdbkview.scale.NumericWheelAdapter;
import com.wpz.mymvpframe.view.bdbkview.scale.widget.WheelView;
import com.wpz.mymvpframe.view.iview.IBdbkView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by wpz on 2017/10/13 0013.
 * 类作用：训练多个详情页
 */

public class BdbkFragment extends BaseFragment<BdbkPresenter> implements IBdbkView, View.OnClickListener {

    private ImageView training_bonus_journey_image;
    private Button training_bonus_journey_btn;
    private LinearLayout training_bdbk_course;
    private TextView training_bonus_journey_title_text;
    private com.wpz.mymvpframe.view.bdbkview.MyExpandableListView training_bdbk_expandableListView;
    private TextView training_countdown;
    private Button training_btn_choose_result;
    private RadioButton training_radio_man;
    private RadioButton training_radio_woman;
    private TextView training_ed_text;
    private ScaleRulerView training_btn_choose_result1;
    private ScaleRulerView training_btn_choose_result2;
    private TextView training_tv_user_height_value;
    private TextView training_tv_left_number;
    private TextView training_tv_conter_number;
    private TextView training_tv_right_number;
    private TextView training_tv_user_weight_value;
    private TextView training_tv_left_weight;
    private TextView training_tv_conter_weight;
    private TextView training_tv_right_weight;
    private WheelView whView, whData, whMonth;  //年 月 日
    private AlertDialog builder;
    private NumericWheelAdapter numericWheelAdapter;
    private float mHeight = 170;
    private float mMaxHeight = 220;
    private float mMinHeight = 100;
    private float mWeight = 60.0f;
    private float mMaxWeight = 200;
    private float mMinWeight = 25;
    private String sYear;
    private String sMonth;
    private String sData;
    private static int START_YEAR = 1950, END_YEAR = 2100;
    private RadioGroup training_radio_group;
    private Button bt_cancel;
    private Button bt_sure;
    private LinearLayout training_potential;
    private LinearLayout training_scale;
    private ImageView training_recipe_img;

    private List<MainBean.DataBean.CourseBean> data;
    private Button bt_cancel1;
    private Button bt_sure1;
    private Button jianzhi_return;
    private LinearLayout training_potential1;
    private LinearLayout bdbk_unpaid;
    private LinearLayout training_bdbk_unbalanced_scales;
    private RelativeLayout unbalanced_scales_scales;
    private TextView unbalanced_scales_title_text;
    private ImageView unbalanced_scales_img;
    private TextView unbalanced_scales_introduction_text;
    private TextView unbalanced_scales_name_text;
    private TextView training_recipe_img_small_title3_text;
    private TextView training_recipe_img_small_title2_text;
    private TextView training_recipe_img_small_title1_text;
    private TextView training_recipe_img_title_text;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getBdbkData(mContext);

        //没购买课程等。界面
        bdbk_unpaid = (LinearLayout) getActivity().findViewById(R.id.bdbk_Unpaid);
        //课程界面
        training_bdbk_course = (LinearLayout) getActivity().findViewById(R.id.training_bdbk_course);
        //潜力界面
        training_potential = (LinearLayout) getActivity().findViewById(R.id.training_potential);
        //刻度尺界面
        training_scale = (LinearLayout) getActivity().findViewById(R.id.training_Scale);

        //训练小标题
        training_bonus_journey_title_text = (TextView) getActivity().findViewById(R.id.training_Bonus_journey_title_text);
        training_bonus_journey_image = (ImageView) getActivity().findViewById(R.id.training_Bonus_journey_image);//参加训练的背景
        training_bonus_journey_btn = (Button) getActivity().findViewById(R.id.training_Bonus_journey_btn);//参加训练
        //课程集合
        training_bdbk_expandableListView = (com.wpz.mymvpframe.view.bdbkview.MyExpandableListView) getActivity().findViewById(R.id.training_bdbk_expandableListView);
        training_countdown = (TextView) getActivity().findViewById(R.id.training_Countdown);
        //食谱
        training_recipe_img = (ImageView) getActivity().findViewById(R.id.training_recipe_img);
        training_recipe_img.setAlpha(0.7f);

        //未绑定秤
        training_bdbk_unbalanced_scales = (LinearLayout) getActivity().findViewById(R.id.training_bdbk_unbalanced_scales);
        //点击绑定体脂秤
        unbalanced_scales_scales = (RelativeLayout) getActivity().findViewById(R.id.unbalanced_scales_scales);
        //未绑定体脂秤的标题
        unbalanced_scales_title_text = (TextView) getActivity().findViewById(R.id.unbalanced_scales_title_text);
        //体脂秤图片
        unbalanced_scales_img = (ImageView) getActivity().findViewById(R.id.unbalanced_scales_img);
        //体脂秤名字
        unbalanced_scales_name_text = (TextView) getActivity().findViewById(R.id.unbalanced_scales_name_text);
        //体脂秤介绍
        unbalanced_scales_introduction_text = (TextView) getActivity().findViewById(R.id.unbalanced_scales_introduction_text);
        //食谱标题
        training_recipe_img_title_text = (TextView) getActivity().findViewById(R.id.training_recipe_img_title_text);
        //食谱标题内容1
        training_recipe_img_small_title1_text = (TextView) getActivity().findViewById(R.id.training_recipe_img_small_title1_text);
        //食谱标题内容2
        training_recipe_img_small_title2_text = (TextView) getActivity().findViewById(R.id.training_recipe_img_small_title2_text);
        //食谱标题内容3
        training_recipe_img_small_title3_text = (TextView) getActivity().findViewById(R.id.training_recipe_img_small_title3_text);
        //刻度尺
        initView();
        initEvents();
        initData();
    }
    //刻度尺控件
    private void initView() {
        //查看结果
        training_btn_choose_result = (Button) getActivity().findViewById(R.id.training_btn_choose_result);
        //男女选项
        training_radio_group = (RadioGroup) getActivity().findViewById(R.id.training_radio_group);
        //选男
        training_radio_man = (RadioButton) getActivity().findViewById(R.id.training_radio_man);
        //选女
        training_radio_woman = (RadioButton) getActivity().findViewById(R.id.training_radio_woman);
        //选择生日
        training_ed_text = (TextView) getActivity().findViewById(R.id.training_ed_text);
        //身高上边标注
        training_tv_user_height_value = (TextView) getActivity().findViewById(R.id.training_tv_user_height_value);
        //身高刻度尺
        training_btn_choose_result1 = (ScaleRulerView) getActivity().findViewById(R.id.training_scaleWheelView_height);
        //身高下左标注
        training_tv_left_number = (TextView) getActivity().findViewById(R.id.training_tv_left_number);
        //身高下中标注
        training_tv_conter_number = (TextView) getActivity().findViewById(R.id.training_tv_conter_number);
        //身高下右标注
        training_tv_right_number = (TextView) getActivity().findViewById(R.id.training_tv_right_number);
        //体重上标注
        training_tv_user_weight_value = (TextView) getActivity().findViewById(R.id.training_tv_user_weight_value);
        //体重刻度尺
        training_btn_choose_result2 = (ScaleRulerView) getActivity().findViewById(R.id.training_scaleWheelView_weight);
        //体重下左标注
        training_tv_left_weight = (TextView) getActivity().findViewById(R.id.training_tv_left_weight);
        //体重下中标注
        training_tv_conter_weight = (TextView) getActivity().findViewById(R.id.training_tv_conter_weight);
        //体重下右标注
        training_tv_right_weight = (TextView) getActivity().findViewById(R.id.training_tv_right_weight);
        //查看结果
        bt_cancel = (Button) getActivity().findViewById(R.id.bt_cancel);
        //查看结果
        bt_sure = (Button) getActivity().findViewById(R.id.bt_sure);


        //重新测量
        jianzhi_return = (Button) getActivity().findViewById(R.id.jianzhi_return);


        init();
    }

    private void initData() {
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/impaact.ttf");
        training_countdown.setTypeface(typeface);
        TextPaint tp = training_countdown.getPaint();
        tp.setFakeBoldText(true);
    }

    private void initEvents() {
        training_bonus_journey_btn.setOnClickListener(this);
        training_ed_text.setOnClickListener(this);
        training_btn_choose_result.setOnClickListener(this);
        training_recipe_img.setOnClickListener(this);
        jianzhi_return.setOnClickListener(this);
        unbalanced_scales_scales.setOnClickListener(this);
    }

    @Override
    public void onsuccess(Object o) {
        MainBean mainBean = (MainBean) o;
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        int code = mainBean.getCode();
        if (code == 200){
            bdbk_unpaid.setVisibility(View.GONE);
            training_bdbk_course.setVisibility(View.VISIBLE);
            Glide.with(getActivity())
                    .load(Content.BASE_URL + mainBean.getData().getSt_cover())
                    .apply(options)
                    .into(training_recipe_img);
            data = mainBean.getData().getCourse();
            TrainingExpandableListViewAdapter trainingExpandableListViewAdapter = new TrainingExpandableListViewAdapter(getActivity(),data);
            training_bdbk_expandableListView.setAdapter(trainingExpandableListViewAdapter);
            //默认展开
            training_bdbk_expandableListView.expandGroup(0);
        }else if(code == 605){
//            bdbk_unpaid.setVisibility(View.GONE);
//            training_bdbk_course.setVisibility(View.VISIBLE);
//            training_bdbk_expandableListView.setVisibility(View.GONE);
//            training_bdbk_unbalanced_scales.setVisibility(View.VISIBLE);
//            Glide.with(getActivity())
//                    .load(Content.BASE_URL + mainBean.getData().getSuggest().getCover())
//                    .apply(options)
//                    .into(training_recipe_img);
//            unbalanced_scales_title_text.setText(mainBean.getData().getBalance_data().getTitleX() + "\n" + mainBean.getData().getBalance_data().getDesc());
//            Glide.with(getActivity())
//                    .load(Content.BASE_URL + mainBean.getData().getBalance_data().getCover())
//                    .apply(options)
//                    .into(unbalanced_scales_img);
//            unbalanced_scales_name_text.setText(mainBean.getData().getBalance_data().getTitle_tag());
//            unbalanced_scales_introduction_text.setText(mainBean.getData().getBalance_data().getDesc_tag());
//            training_recipe_img_title_text.setText(mainBean.getData().getSuggest().getTitleX());
//            training_recipe_img_small_title1_text.setText(mainBean.getData().getSuggest().getTags().get(0));
//            training_recipe_img_small_title2_text.setText(mainBean.getData().getSuggest().getTags().get(1));
//            training_recipe_img_small_title3_text.setText(mainBean.getData().getSuggest().getTags().get(2));

        }else if (code == 602){
//            training_bdbk_course.setVisibility(View.GONE);
//            bdbk_unpaid.setVisibility(View.VISIBLE);
//            Glide.with(getActivity())
//                    .load(Content.BASE_URL + mainBean.getData().getBimg())
//                    .apply(options)
//                    .into(training_bonus_journey_image);
//            training_bonus_journey_btn.setText(mainBean.getData().getBtn_str());
//            training_bonus_journey_title_text.setText(mainBean.getData().getTitle());
        }else if(code == 300){
            startActivity(new Intent(mContext, RegisterLoginActivity.class));
        }
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getBdbkData(mContext);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new BdbkPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_training_bdbk;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.training_Bonus_journey_btn:
                Intent intent = new Intent(mContext, DetailsActivity.class);
                startActivity(intent);
                break;
            case R.id.training_ed_text:
                showTime();
                break;
            case R.id.bt_cancel: //取消
                builder.dismiss();
                break;
            case R.id.bt_sure: //确定
                setYears();
                break;
            case R.id.training_btn_choose_result:
                for (int i = 0; i < training_radio_group.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) training_radio_group.getChildAt(i);
                    if (rb.isChecked()) {
                        Toast.makeText(getActivity(), "身高： " + mHeight + " cm" + " 体重：" + mWeight
                                        + " kg" + "\n\r" + "生日：" + sYear + "-" + sMonth + "-" + sData
                                        + "\n\r" + "性别：" + rb.getText(),Toast.LENGTH_LONG).show();
                        training_scale.setVisibility(View.GONE);
                        training_potential.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.jianzhi_return:
                training_scale.setVisibility(View.VISIBLE);
                training_potential.setVisibility(View.GONE);
                break;
            case R.id.unbalanced_scales_scales:
                startActivity(new Intent(mContext, BindScalesActivity.class));
                break;
        }
    }

    /**
     * 初始化月份
     */
    private void initMonth() {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(getActivity(), 1, 12, "%02d");
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
        builder = new AlertDialog.Builder(getActivity()).create();
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
        numericWheelAdapter = new NumericWheelAdapter(getActivity(), 1950, curYear); //滑动
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
        training_ed_text.setText(sYear + " 年" + sMonth + " 月" + sData + "日");
        //销毁弹框
        builder.dismiss();
    }
    /**
     * 获取天数
     */
    private void inData(int year, int month) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(getActivity(), 1, getDay(year, month), "%02d");
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
    /**
     * 获取身高体重
     */
    private void init() {
        training_btn_choose_result1.initViewParam(mHeight, mMaxHeight, mMinHeight);
        training_btn_choose_result1.setValueChangeListener(new ScaleRulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                training_tv_user_height_value.setText((int) value + "");
                mHeight = value;
                training_tv_conter_number.setText((int) mHeight + ""); //获取当前指示器刻度值
                training_tv_left_number.setText((int) mHeight - 10 + ""); //获取左侧刻度值
                training_tv_left_number.setAlpha(0.5f);
                training_tv_right_number.setText((int) mHeight + 10 + "");  //获取右侧刻度值
                training_tv_right_number.setAlpha(0.5f);
            }
        });
        training_btn_choose_result2.initViewParam(mWeight, mMaxWeight, mMinWeight);
        training_btn_choose_result2.setValueChangeListener(new ScaleRulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                training_tv_user_weight_value.setText(value + "");
                mWeight = value;
                training_tv_conter_weight.setText((int) mWeight + ""); //获取当前指示器刻度值
                training_tv_left_weight.setText((int) mWeight - 10 + "");  //获取左侧刻度值
                training_tv_left_weight.setAlpha(0.5f);
                training_tv_right_weight.setText((int) mWeight + 10 + "");  //获取右侧刻度值
                training_tv_right_weight.setAlpha(0.5f);
            }
        });
    }
}
