package com.wpz.mymvpframe.application;

/**
 * Created by wpz on 2017/5/17.
 */

public class Content {

    public static final String BASE_URL = "https://www.muzhivote.cn/";//URL     正式域名：http://www.ubodybank.com
        public static final String LANDING = "api/user/user/signin";//用户登录
//    public static final String REGISTRATION = "api/user/user/register";//用户注册
    public static final String touxiang = "api/user/user/userInfoUpdate";//上传头像
    public static final String CHECK_PHONE = "/api/user/user/checkMobile";//检查手机号
    public static final String VERIFICATION_CODE = "api/user/user/getVerify";//验证码
    public static final String CHECK_VERIFICATION = "api/user/user/checkverify";//检查验证码
    public static final String SIGN_OUT = "api/user/user/signout";//退出登录
    public static final String CART_LIST = "api/index/index/index";   //用户没有参与中的计划
    public static final String PAGE_DETAILS = "api/order/order/detail"; //获取订单计划详情   //?id=id
    public static final String CREATE_ORDER = "api/order/order/buildOrder";//创建订单   //plan_id int 是 计划ID //address int 是 地址ID //remark string 否 订单备注//pay_method	int	是 支付方式//coupon	int	否 优惠ID
    public static final String USER_ADDRESS = "api/index/adress/getAdress";//获取用户地址
    public static final String ADD_ADDRESS = "api/index/adress/addAdress";//添加用户地址
    public static final String MODIFY_ADDRESS = "api/index/adress/editAdress";//修改用户地址
    public static final String DELETE_ADDRESS = "api/index/adress/delAdress";//删除用户地址
    public static final String COURSE_DETAILS = "api/index/Course/detail";//课程详情页
    public static final String VIDEO = "api/index/course/getAlivideoInfo";//视频请求页
    public static final String ARTICLE_DETAILS = "api/index/article/detail";//文章详情页
}
