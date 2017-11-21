package com.wpz.mymvpframe.model.bean;

import java.util.List;

/**
 * Created by wpz on 2017/11/7 0007.
 * 类作用：
 */

public class RegisterPhoneNumBean {

    /**
     * code : 0
     * msg : 手机号已经注册！
     * data : []
     */

    private int code;
    private String msg;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
