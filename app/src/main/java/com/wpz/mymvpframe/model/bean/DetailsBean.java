package com.wpz.mymvpframe.model.bean;

/**
 * Created by wpz on 2017/10/19 0019.
 * 类作用：
 */

public class DetailsBean {

    /**
     * code : 1
     * msg : 数据获取成功！
     * data : {"id":9,"name":"bodybank瘦身课程","desc":"21天完美蜕变，改变，无处不在！","img":"/bodybank/public/uploads/images/20171012/4d5de910a30ab1adf7f2c2357cb40ee8.png","price":"500.00","gimg":"10","bimg":"8","coupon":0,"fat_more":0,"address":{"id":98,"province":"北京市","city":"北京市","county":"东城区","detail":"详细地址","uname":"王鹏智","umobile":"15524992992","is_default":1}}
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
         * id : 9
         * name : bodybank瘦身课程
         * desc : 21天完美蜕变，改变，无处不在！
         * img : /bodybank/public/uploads/images/20171012/4d5de910a30ab1adf7f2c2357cb40ee8.png
         * price : 500.00
         * gimg : 10
         * bimg : 8
         * coupon : 0
         * fat_more : 0
         * address : {"id":98,"province":"北京市","city":"北京市","county":"东城区","detail":"详细地址","uname":"王鹏智","umobile":"15524992992","is_default":1}
         */

        private int id;
        private String name;
        private String desc;
        private String img;
        private String price;
        private String gimg;
        private String bimg;
        private int coupon;
        private int fat_more;
        private AddressBean address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGimg() {
            return gimg;
        }

        public void setGimg(String gimg) {
            this.gimg = gimg;
        }

        public String getBimg() {
            return bimg;
        }

        public void setBimg(String bimg) {
            this.bimg = bimg;
        }

        public int getCoupon() {
            return coupon;
        }

        public void setCoupon(int coupon) {
            this.coupon = coupon;
        }

        public int getFat_more() {
            return fat_more;
        }

        public void setFat_more(int fat_more) {
            this.fat_more = fat_more;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public static class AddressBean {
            /**
             * id : 98
             * province : 北京市
             * city : 北京市
             * county : 东城区
             * detail : 详细地址
             * uname : 王鹏智
             * umobile : 15524992992
             * is_default : 1
             */

            private int id;
            private String province;
            private String city;
            private String county;
            private String detail;
            private String uname;
            private String umobile;
            private int is_default;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getIs_default() {
                return is_default;
            }

            public void setIs_default(int is_default) {
                this.is_default = is_default;
            }
        }
    }
}
