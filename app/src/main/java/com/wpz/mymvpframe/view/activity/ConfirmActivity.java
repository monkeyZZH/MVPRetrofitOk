package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.ConfirmBean;
import com.wpz.mymvpframe.presenter.ConfirmPresenter;
import com.wpz.mymvpframe.view.activity.zhifubao.AuthResult;
import com.wpz.mymvpframe.view.activity.zhifubao.PayResult;
import com.wpz.mymvpframe.view.iview.IConfirmView;

import java.util.Map;

/**
 * @类作用:
 * @author: 王鹏智
 * @Date: 2017/10/14  14:32
 * <p>
 * 思路：计划确认页面
 */


public class ConfirmActivity extends BaseActivity<ConfirmPresenter> implements IConfirmView,View.OnClickListener {

    private TextView confirm_payables;
    private TextView confirm_pay;
    private ImageView title_return;
//    private String total_amount;
    private CheckBox confirm_buy_scale_check;
    private RelativeLayout confirm_new_address;
    private CheckBox confirm_pay_weixin_check;
    private CheckBox confirm_pay_zhifubao_check;
    private LinearLayout confirm_scale_layout;
    private ImageView confirm_commodity_img;

    private TextView confirm_commodity_name;
    private TextView confirm_commodity_details;
    private TextView confirm_commodity_money;
    private String name;
    private String price;
    private String img;
    private String desc;
    private EditText confirm_remarks_edit;





    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2017110109661149";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ConfirmActivity.this, PaymentDuccessfulActivity.class);
                        Bundle bundle = new Bundle();
                        String img = order_detail.getImg();
                        String name = order_detail.getName();
                        String desc = order_detail.getDesc();
                        String price = order_detail.getPrice();
                        String City = order_detail.getAddress().getCity();
                        String County = order_detail.getAddress().getCounty();
                        String Detail = order_detail.getAddress().getDetail();
                        String Province = order_detail.getAddress().getProvince();
                        String Uname = order_detail.getAddress().getUname();
                        String Umobile = order_detail.getAddress().getUmobile();

                        bundle.putString("img",img);
                        bundle.putString("name",name);
                        bundle.putString("desc",desc);
                        bundle.putString("price",price);
                        bundle.putString("City",City);
                        bundle.putString("County",County);
                        bundle.putString("Detail",Detail);
                        bundle.putString("Province",Province);
                        bundle.putString("Uname",Uname);
                        bundle.putString("Umobile",Umobile);
                        bundle.putString("Order",order);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(mContext,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(mContext,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private RelativeLayout confirm_default_address;
    private String order;
    private ConfirmBean.DataBean.OrderDetailBean order_detail;
    private int address_id;
    private String province;
    private String city;
    private String county;
    private String detail;
    private String uname;
    private String umobile;
    private TextView confirm_address_text;
    private TextView confirm_name_phono_text;
    private TextView confirm_name_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView title_text = (TextView) findViewById(R.id.title_left_text);
        title_text.setText("Bodybank计划参与确认");
         /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();

        /*获取Bundle中的数据，注意类型和key*/
        name = bundle.getString("Name");
        price = bundle.getString("Price");
        img = bundle.getString("Img");
        desc = bundle.getString("Desc");
        address_id = bundle.getInt("address_id");
        province = bundle.getString("province");
        city = bundle.getString("city");
        county = bundle.getString("county");
        detail = bundle.getString("detail");
        uname = bundle.getString("uname");
        umobile = bundle.getString("umobile");
        confirm_default_address.setVisibility(View.VISIBLE);
        confirm_new_address.setVisibility(View.GONE);
        confirm_address_text.setText(province + city + county + detail);
        confirm_name_text.setText(uname);
        confirm_name_phono_text.setText( umobile);
        initData();
    }

    private void initData() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(Content.BASE_URL + img)
                .apply(options)
                .into(confirm_commodity_img);
        confirm_commodity_name.setText(name);
        confirm_commodity_details.setText(desc);
        confirm_payables.setText(price);
        confirm_commodity_money.setText(price);
    }

    @Override
    public void onsuccess(Object o) {
        ConfirmBean ConfirmBean = (ConfirmBean)o;
        String info = ConfirmBean.getData().getSign();

        final String orderInfo = info;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ConfirmActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
        order = ConfirmBean.getData().getOrder_id();
        order_detail = ConfirmBean.getData().getOrder_detail();

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ConfirmPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm;
    }

    @Override
    protected void initView() {

        //套餐名字
        confirm_commodity_name = (TextView) findViewById(R.id.confirm_commodity_name);
        //套餐介绍
        confirm_commodity_details = (TextView) findViewById(R.id.confirm_commodity_Details);
        //套餐金额
        confirm_commodity_money = (TextView) findViewById(R.id.confirm_commodity_money);
        confirm_commodity_img = (ImageView) findViewById(R.id.confirm_commodity_img);//套餐图片
        //有收货地址
        confirm_default_address = (RelativeLayout) findViewById(R.id.confirm_default_address);
        //地址
        confirm_address_text = (TextView) findViewById(R.id.confirm_address_text);
        //收货人姓名
        confirm_name_text = (TextView) findViewById(R.id.confirm_name_text);
        //收货人电话
        confirm_name_phono_text = (TextView) findViewById(R.id.confirm_name_phono_text);
        //新增收货地址
        confirm_new_address = (RelativeLayout) findViewById(R.id.confirm_new_address);
        //总金额的Text
        confirm_payables = (TextView) findViewById(R.id.confirm_payables);
        //支付按钮
        confirm_pay = (TextView) findViewById(R.id.confirm_pay);
        title_return = (ImageView) findViewById(R.id.title_left_return);//返回按钮
        confirm_buy_scale_check = (CheckBox) findViewById(R.id.confirm_buy_scale_check);//是否购买体脂秤的Check
        //微信支付点击按钮
        confirm_pay_weixin_check = (CheckBox) findViewById(R.id.confirm_pay_weixin_check);
        //支付宝支付点击按钮
        confirm_pay_zhifubao_check = (CheckBox) findViewById(R.id.confirm_pay_zhifubao_check);
        confirm_scale_layout = (LinearLayout) findViewById(R.id.confirm_scale_layout);//是否半价购买体脂秤
        //备注
        confirm_remarks_edit = (EditText) findViewById(R.id.confirm_Remarks_edit);
        initEvents();
    }

    private void initEvents() {
        confirm_new_address.setOnClickListener(this);//新增收货地址
        confirm_buy_scale_check.setOnClickListener(this);//是否购买体脂秤的Check
        confirm_pay.setOnClickListener(this);//支付按钮
        title_return.setOnClickListener(this);//返回按钮
        confirm_pay_weixin_check.setOnClickListener(this);//微信支付点击按钮
        confirm_pay_zhifubao_check.setOnClickListener(this);//支付宝支付点击按钮
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
            case R.id.confirm_new_address:
                startActivity(new Intent(ConfirmActivity.this,AddAddressActivity.class));
                break;
            case R.id.confirm_default_address:
                startActivity(new Intent(ConfirmActivity.this,ReceipInformationActivity.class));
                break;
            case R.id.confirm_pay:
                Toast.makeText(mContext, name + price + img + "++++订单号++++" +  order, Toast.LENGTH_SHORT).show();

                mPresenter.getConfirmData(this,9,address_id,"asd",2,0,desc);

//                Intent intent = new Intent(ConfirmActivity.this, PaymentDuccessfulActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("Name",name);
//                bundle.putString("Price",price);
//                bundle.putString("Img",img);
//                bundle.putString("Order",order);
//                intent.putExtras(bundle);
//                startActivity(intent);
                break;
            case R.id.confirm_buy_scale_check:
                boolean checked = confirm_buy_scale_check.isChecked();
                if (checked){
//                    int a = Integer.getInteger(total_amount);
//                    a+=68;
//                    confirm_payables.setText(a + "");
                }else {
//                    int a = Integer.getInteger(total_amount);
//                    a-=68;
//                    confirm_payables.setText(a + "");
                }
                break;
            case R.id.confirm_pay_weixin_check:
                    confirm_pay_zhifubao_check.setChecked(false);
                break;
            case R.id.confirm_pay_zhifubao_check:
                    confirm_pay_weixin_check.setChecked(false);
                break;
        }
    }



//    private class getImageCacheAsyncTask extends AsyncTask<String, Void, File> {
//        private final Context context;
//
//        public getImageCacheAsyncTask(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        protected File doInBackground(String... params) {
//            String imgUrl = params[0];
//            try {
//                return Glide.with(context)
//                        .load(imgUrl)
//                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
//                        .get();
//            } catch (Exception ex) {
//                return null;
//            }
//        }
//
//        @Override
//        protected void onPostExecute(File result) {
//            if (result == null) {
//                return;
//            }
//            //此path就是对应文件的缓存路径
//            String path = result.getPath();
//            Log.e("path", path);
//            Bitmap bmp = BitmapFactory.decodeFile(path);
//            confirm_commodity_img.setImageBitmap(bmp);
//        }
//    }
}
