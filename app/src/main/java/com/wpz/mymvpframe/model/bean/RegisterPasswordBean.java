package com.wpz.mymvpframe.model.bean;

/**
 * Created by wpz on 2017/11/6 0006.
 * 类作用：
 */

public class RegisterPasswordBean {

    /**
     * code : 1
     * msg : 注册成功！
     * data : {"uid":121528,"avatar":"","username":"15524992992","nickname":"","last_login_time":1509967214,"last_login_ip":"3755985128"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 121528
         * avatar :
         * username : 15524992992
         * nickname :
         * last_login_time : 1509967214
         * last_login_ip : 3755985128
         */

        private int uid;
        private String avatar;
        private String username;
        private String nickname;
        private int last_login_time;
        private String last_login_ip;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(int last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }
    }
}
