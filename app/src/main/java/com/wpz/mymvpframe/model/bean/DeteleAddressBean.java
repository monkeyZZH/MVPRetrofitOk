package com.wpz.mymvpframe.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wpz on 2017/10/20 0020.
 * 类作用：
 */

public class DeteleAddressBean implements Serializable {

    /**
     * code : 1
     * msg : redis命中数据！
     * data : [{"id":76,"uid":1,"province":"上海市","city":"上海市","county":"闵行区","detail":"Dassdsdasd","is_default":1,"status":1,"create_time":"2017-10-24 14:40:48","update_time":"2017-10-24 14:40:48","uname":"Enemies","umobile":"17601680794"},{"id":71,"uid":1,"province":"北京市","city":"北京市","county":"东城区","detail":"Zaizheline ","is_default":0,"status":1,"create_time":"2017-10-24 11:30:52","update_time":"2017-10-24 11:30:52","uname":"Hahaha","umobile":"13931077890"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 76
         * uid : 1
         * province : 上海市
         * city : 上海市
         * county : 闵行区
         * detail : Dassdsdasd
         * is_default : 1
         * status : 1
         * create_time : 2017-10-24 14:40:48
         * update_time : 2017-10-24 14:40:48
         * uname : Enemies
         * umobile : 17601680794
         */

        private int id;
        private int uid;
        private String province;
        private String city;
        private String county;
        private String detail;
        private int is_default;
        private int status;
        private String create_time;
        private String update_time;
        private String uname;
        private String umobile;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUmobile() {
            return umobile;
        }

        public void setUmobile(String umobile) {
            this.umobile = umobile;
        }
    }
}