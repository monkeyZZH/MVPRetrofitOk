package com.wpz.mymvpframe.view.activity;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.bluetooth.bleprofile.BleProfileService;
import com.wpz.mymvpframe.bluetooth.bleprofile.BleProfileServiceReadyActivity;
import com.wpz.mymvpframe.bluetooth.entity.BodyFatData;
import com.wpz.mymvpframe.bluetooth.entity.User;
import com.wpz.mymvpframe.bluetooth.entity.WeightData;
import com.wpz.mymvpframe.bluetooth.utils.AicareBleConfig;
import com.wpz.mymvpframe.bluetooth.utils.L;
import com.wpz.mymvpframe.bluetooth.utils.ParseData;
import com.wpz.mymvpframe.bluetooth.wby.WBYService;
import com.wpz.mymvpframe.presenter.OnTheScalePresenter;
import com.wpz.mymvpframe.view.bdbkview.XCRoundImageView;
import com.wpz.mymvpframe.view.iview.IOnTheScaleView;

/**
 * Created by wpz on 2017/11/9 0009.
 * 类作用：链接设备
 */

public class OnTheScaleActivity extends BleProfileServiceReadyActivity<WBYService.WBYBinder,OnTheScalePresenter> implements IOnTheScaleView, View.OnClickListener {

    private TextView on_the_scale_text;
    private XCRoundImageView on_the_scale_name_img;
    private TextView on_the_scale_name_text;
    private TextView on_the_scale_time_text;
    private static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    private static final int REQUEST_CODE_LOCATION_SETTINGS = 2;


    private AnimationSet animationSet;
    private XCRoundImageView measurement_water_wave;
    private WBYService.WBYBinder binder;
    private User user;
    private byte unit = AicareBleConfig.UNIT_KG;
    private RelativeLayout scale_data_calculate;
    private RelativeLayout scale_data_connection;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!ensureBLESupported()) {
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!isBLEEnabled()) {
            showBLEDialog();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//如果 API level 是大于等于 23(Android 6.0) 时
            //判断是否具有权限
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //判断是否需要向用户解释为什么需要申请该权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Toast.makeText(this, "自Android 6.0开始需要打开位置权限才可以搜索到Ble设备", Toast.LENGTH_SHORT).show();
                }
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_ACCESS_COARSE_LOCATION);
            }
        }
        if (isScanning()){

        }
        startScan();
    }

    //允许修改权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_ACCESS_COARSE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户允许改权限，0表示允许，-1表示拒绝 PERMISSION_GRANTED = 0， PERMISSION_DENIED = -1
                //permission was granted, yay! Do the contacts-related task you need to do.
                //这里进行授权被允许的处理
            } else {
                //permission denied, boo! Disable the functionality that depends on this permission.
                //这里进行权限被拒绝的处理
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    //定位权限
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_LOCATION_SETTINGS) {
            if (isLocationEnable(this)) {
                //定位已打开的处理
            } else {
                //定位依然没有打开的处理
            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Location service if enable
     *
     * @param context
     * @return location is enable if return true, otherwise disable.
     */
    public static final boolean isLocationEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean networkProvider = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (networkProvider || gpsProvider) return true;
        return false;
    }

    @Override
    public void onsuccess(Object o) {

    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new OnTheScalePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_on_the_scale;
    }

    @Override
    protected void initView() {
        //用户头像
        on_the_scale_name_img = (XCRoundImageView) findViewById(R.id.on_the_scale_name_img);
        //用户名字
        on_the_scale_name_text = (TextView) findViewById(R.id.on_the_scale_name_text);
        //上下午
        on_the_scale_time_text = (TextView) findViewById(R.id.on_the_scale_time_text);
        //文字的平移
        on_the_scale_text = (TextView) findViewById(R.id.on_the_scale_text);
        final TranslateAnimation animation = new TranslateAnimation(0, 0, 65, 0);
        animation.setDuration(800);//设置动画持续时间
        animation.setRepeatCount(100);//设置重复次数
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        on_the_scale_text.setAnimation(animation);

        //计算
        scale_data_calculate = (RelativeLayout) findViewById(R.id.scale_data_calculate);
        //连接
        scale_data_connection = (RelativeLayout) findViewById(R.id.scale_data_connection);

        measurement_water_wave = (XCRoundImageView) findViewById(R.id.measurement_water_wave);
    }

    //获取设备名，设备唯一标识
    @Override
    protected void getAicareDevice(BluetoothDevice device, int rssi) {
        Toast.makeText(this, "device =====" + device.getName() + "rssi =====" + rssi, Toast.LENGTH_SHORT).show();
        Log.e("I'm here","能不能找到呢！？！？！？" + "device =====" + device.getName() + "rssi =====" + rssi + "唯一标识" + device.getAddress());
        startConncet(device.getAddress());
        SwitchThepage();
    }

    public void SwitchThepage(){
        scale_data_calculate.setVisibility(View.VISIBLE);
        scale_data_connection.setVisibility(View.GONE);
    }

    @Override
    public void onStateChanged(String deviceAddress, int state) {
        super.onStateChanged(deviceAddress, state);
        switch (state) {
            case BleProfileService.STATE_CONNECTED:
//                Toast.makeText(this, "连接成功", Toast.LENGTH_SHORT).show();
                Log.e("=====","连接成功");
                break;
            case BleProfileService.STATE_DISCONNECTED:
//                Toast.makeText(this, "断开连接", Toast.LENGTH_SHORT).show();
                Log.e("=====","断开连接");
                break;
            case BleProfileService.STATE_SERVICES_DISCOVERED:
//                Toast.makeText(this, "发现服务", Toast.LENGTH_SHORT).show();
                Log.e("=====","发现服务");
                break;
            case BleProfileService.STATE_INDICATION_SUCCESS:
                Log.e("=====","使能成功");
//                stopScan();
                break;
        }
    }

    @Override
    protected void initDatas() {

        //创建一个AnimationSet对象，参数为Boolean型，
        //true表示使用Animation的interpolator，false则是使用自己的
        animationSet = new AnimationSet(true);
        //参数1：x轴的初始值
        //参数2：x轴收缩后的值
        //参数3：y轴的初始值
        //参数4：y轴收缩后的值
        //参数5：确定x轴坐标的类型
        //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        //参数7：确定y轴坐标的类型
        //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1, 8, 1, 8,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1500);
        animationSet.addAnimation(scaleAnimation);
        //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        //设置动画执行的时间
        alphaAnimation.setDuration(1500);
        //将alphaAnimation对象添加到AnimationSet当中
        animationSet.addAnimation(alphaAnimation);
        //使用ImageView的startAnimation方法执行动画
        animationSet.setFillAfter(true);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                measurement_water_wave.startAnimation(animationSet);
            }
        });
        measurement_water_wave.startAnimation(animationSet);
        user = new User(0, 1, 24, 180, 0, 0);
    }

    @Override
    public void onClick(View v) {

    }
    //体重
    private void setWeighDataText(String weight) {
//        tv_weight.setText(getString(R.string.weight, weight));
        Toast.makeText(this, weight, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onGetWeightData(final WeightData weightData) {
        L.e("体重体重", weightData.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (unit) {
                    case AicareBleConfig.UNIT_KG:
                        setWeighDataText(String.valueOf(weightData.getWeight()));
                        break;
                    case AicareBleConfig.UNIT_LB:
                        setWeighDataText(String.valueOf(ParseData.kg2lb(weightData.getWeight())));
                        break;
                    case AicareBleConfig.UNIT_ST:
                        setWeighDataText(ParseData.kg2st(weightData.getWeight()));
                        break;
                    case AicareBleConfig.UNIT_JIN:
                        setWeighDataText(String.valueOf(ParseData.kg2jin(weightData.getWeight())));
                        break;
                }
                if (weightData.getTemp() != Double.MAX_VALUE) {
//                    tv_temp.setText(getString(R.string.temp, String.valueOf(weightData.getTemp())));
                    Toast.makeText(OnTheScaleActivity.this, "温度" + String.valueOf(weightData.getTemp()), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onGetSettingStatus(AicareBleConfig.SettingStatus status) {
        L.e("不知道是啥", "SettingStatus = " + status);
        switch (status) {
            case NORMAL:
//                showInfo(getString(R.string.settings_status, getString(R.string.normal)));
                break;
            case LOW_POWER:
//                showInfo(getString(R.string.settings_status, getString(R.string.low_power)));
                break;
            case LOW_VOLTAGE:
//                showInfo(getString(R.string.settings_status, getString(R.string.low_voltage)));
                break;
            case ERROR:
//                showInfo(getString(R.string.settings_status, getString(R.string.error)));
                break;
            case TIME_OUT:
//                showInfo(getString(R.string.settings_status, getString(R.string.time_out)));
                break;
            case UNSTABLE:
//                showInfo(getString(R.string.settings_status, getString(R.string.unstable)));
                break;
            case SET_UNIT_SUCCESS:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_unit_success)));
                break;
            case SET_UNIT_FAILED:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_unit_failed)));
                break;
            case SET_TIME_SUCCESS:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_time_success)));
                break;
            case SET_TIME_FAILED:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_time_failed)));
                break;
            case SET_USER_SUCCESS:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_user_success)));
                Log.e("???","???");
                break;
            case SET_USER_FAILED:
//                showInfo(getString(R.string.settings_status, getString(R.string.set_user_failed)));
                break;
            case UPDATE_USER_LIST_SUCCESS:
//                showInfo(getString(R.string.settings_status, getString(R.string.update_user_list_success)));
                break;
            case UPDATE_USER_LIST_FAILED:
//                showInfo(getString(R.string.settings_status, getString(R.string.update_user_list_failed)));
                break;
            case UPDATE_USER_SUCCESS:
//                showInfo(getString(R.string.settings_status, getString(R.string.update_user_success)));
                break;
            case UPDATE_USER_FAILED:
//                showInfo(getString(R.string.settings_status, getString(R.string.update_user_failed)));
                break;
            case NO_HISTORY:
//                showInfo(getString(R.string.settings_status, getString(R.string.no_history)));
                break;
            case HISTORY_START_SEND:
//                showInfo(getString(R.string.settings_status, getString(R.string.history_start_send)));
                break;
            case HISTORY_SEND_OVER:
//                showInfo(getString(R.string.settings_status, getString(R.string.history_send_over)));
                break;
            case NO_MATCH_USER:
//                showInfo(getString(R.string.settings_status, getString(R.string.no_match_user)));
                break;
            case ADC_MEASURED_ING:
//                showInfo(getString(R.string.settings_status, getString(R.string.adc_measured_ind)));
                break;
            case ADC_ERROR:
//                showInfo(getString(R.string.settings_status, getString(R.string.adc_error)));
                break;
            case UNKNOWN:
//                showInfo(getString(R.string.settings_status, getString(R.string.unknown)));
                break;
        }
    }
    //阻抗
    @Override
    public void onGetResult(int index, String result) {
        Log.e("阻抗", "index = " + index + "; result = " + result);
        Toast.makeText(mContext, "阻抗 阻抗：" + result + "", Toast.LENGTH_SHORT).show();
        switch (index) {
            case WBYService.BLE_VERSION:
//                showInfo(getString(R.string.ble_version, result));
                Log.e("阻抗BLE版本",result);
                break;
            case WBYService.USER_ID:
//                showInfo(getString(R.string.user_id, result));
                Log.e("阻抗用户编号",result);
                break;
            case WBYService.MCU_DATE:
//                showInfo(getString(R.string.mcu_date, result));
                Log.e("阻抗测量日期",result);
                break;
            case WBYService.MCU_TIME:
//                showInfo(getString(R.string.mcu_time, result));
                Log.e("阻抗测量时间",result);
                break;
            case WBYService.ADC:
//                showInfo(getString(R.string.adc, result));
                Log.e("阻抗 阻抗：",result);
                break;
        }
    }
    //体质参数
    @Override
    public void onGetFatData(boolean isHistory, final BodyFatData bodyFatData) {
        Log.e("!!!!!!!!", "isHistory = " + isHistory + "; BodyFatData = " + bodyFatData.toString());
        if (isHistory) {
//            showInfo(getString(R.string.history_data, bodyFatData.toString()));
            Log.e("!!!!!!!!历史数据：" , bodyFatData.toString());
        } else {
//            showInfo(getString(R.string.body_fat_data, bodyFatData.toString()));
            Log.e("!!!!!!!!体脂数据：" , bodyFatData.toString());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    seek_bar_weight.setProgress((int) (bodyFatData.getWeight() * 10));
                    if (bodyFatData.getAdc() != 0) {
//                        seek_bar_adc.setProgress(bodyFatData.getAdc());
                        Log.e("!!!!!!!!ADC",bodyFatData.getAdc() + "");
                    }
                }
            });
            if (isDeviceConnected()) {
                binder.updateUser(user);
            }
        }
        s = bodyFatData.toString();
        gogogo();
    }
    public void gogogo(){
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,CalculatedSuccessfullyActivity.class));
        finish();
    }
    @Override
    protected void onServiceBinded(WBYService.WBYBinder binder) {
        this.binder = binder;
        binder.syncUser(user);
    }

    @Override
    protected void onServiceUnbinded() {
        this.binder = null;
    }

    @Override
    public void onError(String msg, int errorCode) {

    }
}
