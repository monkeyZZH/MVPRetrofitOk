package com.wpz.mymvpframe.bluetooth.wby;

import com.wpz.mymvpframe.bluetooth.bleprofile.BleManagerCallbacks;
import com.wpz.mymvpframe.bluetooth.entity.BodyFatData;
import com.wpz.mymvpframe.bluetooth.entity.WeightData;
import com.wpz.mymvpframe.bluetooth.utils.AicareBleConfig;

public interface WBYManagerCallbacks extends BleManagerCallbacks {

    void getWeightData(WeightData weightData);

    void getSettingStatus(AicareBleConfig.SettingStatus status);

    void getResult(int index, String result);

    void getFatData(boolean isHistory, BodyFatData bodyFatData);
}
