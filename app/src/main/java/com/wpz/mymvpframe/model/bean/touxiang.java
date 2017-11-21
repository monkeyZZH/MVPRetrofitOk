package com.wpz.mymvpframe.model.bean;

import java.util.List;

/**
 * Created by wpz on 2017/11/13 0013.
 * 类作用：
 */

public class touxiang {

    /**
     * code : 1
     * msg : 上传成功！
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
