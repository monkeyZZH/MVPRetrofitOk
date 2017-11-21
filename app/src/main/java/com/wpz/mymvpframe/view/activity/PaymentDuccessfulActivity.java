package com.wpz.mymvpframe.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.presenter.PaymentSuccessfulPresenter;
import com.wpz.mymvpframe.view.iview.IPaymentSuccessfulView;

/**
 * Created by wpz on 2017/10/16 0016.
 * 类作用：支付成功页面
 */

public class PaymentDuccessfulActivity extends BaseActivity<PaymentSuccessfulPresenter> implements IPaymentSuccessfulView,View.OnClickListener {

    private TextView payment_order_number;
    private ImageView payment_duccessful_commodity_img;
    private TextView payment_duccessful_commodity_name;
    private TextView payment_duccessful_commodity_details;
    private TextView payment_duccessful_commodity_money;
    private TextView payment_duccessful_commodity_Quantity;
    private TextView payment_shipping_address;
    private TextView payment_contact_num;
    private TextView payment_contact_name;
    private Button payment_successful_btn;
    private TextView payment_successful_details;
    private TextView payment_successful_text;
    private ImageView payment_successful_img;
    private TextView payment_successful_end_btn;

    private RelativeLayout payment_scales;
    private String name;
    private String price;
    private String img;
    private String order;
    private String desc;
    private String city;
    private String county;
    private String detail;
    private String province;
    private String uname;
    private String umobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title_text = (TextView) findViewById(R.id.title_right_text);
        title_text.setText("支付成功");
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
         /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        img = bundle.getString("img");
        name = bundle.getString("name");
        desc = bundle.getString("desc");
        price = bundle.getString("price");
        city = bundle.getString("City");
        county = bundle.getString("County");
        detail = bundle.getString("Detail");
        province = bundle.getString("Province");
        uname = bundle.getString("Uname");
        umobile = bundle.getString("Umobile");
        order = bundle.getString("Order");
        payment_duccessful_commodity_name.setText(name);
        payment_duccessful_commodity_details.setText(desc);
        payment_duccessful_commodity_money.setText("¥" + price);
        Glide.with(this)
                .load(Content.BASE_URL + img)
                .apply(options)
                .into(payment_duccessful_commodity_img);
        payment_shipping_address.setText(province + city + county + detail);
        payment_contact_num.setText("收件人电话:" + umobile);
        payment_contact_name.setText("收件人姓名:" + uname);
        payment_order_number.setText("订单号:" + order);
    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new PaymentSuccessfulPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment_duccessful;
    }

    @Override
    protected void initView() {
        //支付成功后点击的完成按钮
        payment_successful_end_btn = (TextView) findViewById(R.id.payment_successful_end_btn);
        //订单号
        payment_order_number = (TextView) findViewById(R.id.payment_order_number);
        //套餐图片
        payment_duccessful_commodity_img = (ImageView) findViewById(R.id.payment_duccessful_commodity_img);
        //套餐名字
        payment_duccessful_commodity_name = (TextView) findViewById(R.id.payment_duccessful_commodity_name);
        //套餐标语
        payment_duccessful_commodity_details = (TextView) findViewById(R.id.payment_duccessful_commodity_details);
        //套餐金额
        payment_duccessful_commodity_money = (TextView) findViewById(R.id.payment_duccessful_commodity_money);
        //套餐数量
        payment_duccessful_commodity_Quantity = (TextView) findViewById(R.id.payment_duccessful_commodity_Quantity);
        //收件人地址
        payment_shipping_address = (TextView) findViewById(R.id.payment_shipping_address);
        //收件人联系电话
        payment_contact_num = (TextView) findViewById(R.id.payment_contact_num);
        //收件人姓名
        payment_contact_name = (TextView) findViewById(R.id.payment_contact_name);
        //支付成功后下面的btn（原为餐饮计划，先睹为快）
        payment_successful_btn = (Button) findViewById(R.id.payment_successful_btn);
        //支付成功详细内容
        payment_successful_details = (TextView) findViewById(R.id.payment_successful_details);
        //支付成功提示语
        payment_successful_text = (TextView) findViewById(R.id.payment_successful_text);
        //支付成功显示的图片
        payment_successful_img = (ImageView) findViewById(R.id.payment_successful_img);
        //判断是否购买了体脂秤
        payment_scales = (RelativeLayout) findViewById(R.id.payment_scales);
        initEvents();
    }

    private void initEvents() {
        payment_successful_end_btn.setOnClickListener(this);
        payment_successful_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //看餐饮计划的按钮
            case R.id.payment_successful_btn:
                finish();
                break;
            //完成按钮
            case R.id.payment_successful_end_btn:
                finish();
                break;
        }
    }

    @Override
    protected void initDatas() {


    }
}
