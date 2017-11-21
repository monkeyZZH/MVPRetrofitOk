package com.wpz.mymvpframe.model.bean;

/**
 * Created by wpz on 2017/11/7 0007.
 * 类作用：
 */

public class ConfirmBean {

    /**
     * code : 1
     * data : {"order_detail":{"address":{"city":"北京市","county":"崇文区","detail":"没谁了","id":80,"is_default":1,"province":"北京市","umobile":"666666666666","uname":"还有谁"},"bimg":"8","coupon":0,"desc":"21天完美蜕变，改变，无处不在！","fat_more":0,"gimg":"10","id":9,"img":"/bodybank/public/uploads/images/20171012/4d5de910a30ab1adf7f2c2357cb40ee8.png","name":"bodybank瘦身课程","price":"500.00"},"order_id":"20171108142732521025","sign":"alipay_sdk=alipay-sdk-php-20161101&app_id=2017110109661149&biz_content=%7B%22body%22%3A%22%5Cu6d4b%5Cu8bd5%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu6d4b%5Cu8bd5%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%2220171108142732521025%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.muzhivote.cn%2Fapi%2Fpay%2FAlipay%2FaliPayBack&sign_type=RSA2&timestamp=2017-11-08+14%3A27%3A33&version=1.0&sign=MTCWzLGVrLwcz39uuYhmldsVJxx23PZC52oVcdLoEUu8Wobh1cHt1xjGyEzdkzdtbTmMcfNn0iJrWZrjCsF1DdO3%2F5WTriAhm%2BobA%2FAFGzF5VhQWupHEjna3pfoPiXILKQ6DfdWXfUSU4V%2BbkVO%2Fg0V8xW0u7EqMYgmRaVSM7p%2BjlfiZQoTzEz75DuNBTMrgUuutR0f%2F9jr0b8h87QxFLT4NrYnOyQCVMEKPcp3staqs4eZgmJHkJJOAaAKBRTUKp5OgvbjXzgq5lnsIlTjvf6Ow9chA1TbTGApa9%2FW6UbN%2BmDqt62By2wuH4%2B9apwGk4qPAmnWIvUwBUg4RRtadYA%3D%3D"}
     * msg : 数据获取成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * order_detail : {"address":{"city":"北京市","county":"崇文区","detail":"没谁了","id":80,"is_default":1,"province":"北京市","umobile":"666666666666","uname":"还有谁"},"bimg":"8","coupon":0,"desc":"21天完美蜕变，改变，无处不在！","fat_more":0,"gimg":"10","id":9,"img":"/bodybank/public/uploads/images/20171012/4d5de910a30ab1adf7f2c2357cb40ee8.png","name":"bodybank瘦身课程","price":"500.00"}
         * order_id : 20171108142732521025
         * sign : alipay_sdk=alipay-sdk-php-20161101&app_id=2017110109661149&biz_content=%7B%22body%22%3A%22%5Cu6d4b%5Cu8bd5%5Cu5546%5Cu54c1%22%2C%22subject%22%3A%22%5Cu6d4b%5Cu8bd5%5Cu5546%5Cu54c1%22%2C%22out_trade_no%22%3A%2220171108142732521025%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.muzhivote.cn%2Fapi%2Fpay%2FAlipay%2FaliPayBack&sign_type=RSA2&timestamp=2017-11-08+14%3A27%3A33&version=1.0&sign=MTCWzLGVrLwcz39uuYhmldsVJxx23PZC52oVcdLoEUu8Wobh1cHt1xjGyEzdkzdtbTmMcfNn0iJrWZrjCsF1DdO3%2F5WTriAhm%2BobA%2FAFGzF5VhQWupHEjna3pfoPiXILKQ6DfdWXfUSU4V%2BbkVO%2Fg0V8xW0u7EqMYgmRaVSM7p%2BjlfiZQoTzEz75DuNBTMrgUuutR0f%2F9jr0b8h87QxFLT4NrYnOyQCVMEKPcp3staqs4eZgmJHkJJOAaAKBRTUKp5OgvbjXzgq5lnsIlTjvf6Ow9chA1TbTGApa9%2FW6UbN%2BmDqt62By2wuH4%2B9apwGk4qPAmnWIvUwBUg4RRtadYA%3D%3D
         */

        private OrderDetailBean order_detail;
        private String order_id;
        private String sign;

        public OrderDetailBean getOrder_detail() {
            return order_detail;
        }

        public void setOrder_detail(OrderDetailBean order_detail) {
            this.order_detail = order_detail;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public static class OrderDetailBean {
            /**
             * address : {"city":"北京市","county":"崇文区","detail":"没谁了","id":80,"is_default":1,"province":"北京市","umobile":"666666666666","uname":"还有谁"}
             * bimg : 8
             * coupon : 0
             * desc : 21天完美蜕变，改变，无处不在！
             * fat_more : 0
             * gimg : 10
             * id : 9
             * img : /bodybank/public/uploads/images/20171012/4d5de910a30ab1adf7f2c2357cb40ee8.png
             * name : bodybank瘦身课程
             * price : 500.00
             */

            private AddressBean address;
            private String bimg;
            private int coupon;
            private String desc;
            private int fat_more;
            private String gimg;
            private int id;
            private String img;
            private String name;
            private String price;

            public AddressBean getAddress() {
                return address;
            }

            public void setAddress(AddressBean address) {
                this.address = address;
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getFat_more() {
                return fat_more;
            }

            public void setFat_more(int fat_more) {
                this.fat_more = fat_more;
            }

            public String getGimg() {
                return gimg;
            }

            public void setGimg(String gimg) {
                this.gimg = gimg;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public static class AddressBean {
                /**
                 * city : 北京市
                 * county : 崇文区
                 * detail : 没谁了
                 * id : 80
                 * is_default : 1
                 * province : 北京市
                 * umobile : 666666666666
                 * uname : 还有谁
                 */

                private String city;
                private String county;
                private String detail;
                private int id;
                private int is_default;
                private String province;
                private String umobile;
                private String uname;

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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getIs_default() {
                    return is_default;
                }

                public void setIs_default(int is_default) {
                    this.is_default = is_default;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getUmobile() {
                    return umobile;
                }

                public void setUmobile(String umobile) {
                    this.umobile = umobile;
                }

                public String getUname() {
                    return uname;
                }

                public void setUname(String uname) {
                    this.uname = uname;
                }
            }
        }
    }
}
