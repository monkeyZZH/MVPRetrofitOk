package com.wpz.mymvpframe.api;

import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.model.bean.ArticleDetailsBean;
import com.wpz.mymvpframe.model.bean.ConfirmBean;
import com.wpz.mymvpframe.model.bean.CourseDetailsBean;
import com.wpz.mymvpframe.model.bean.DetailsBean;
import com.wpz.mymvpframe.model.bean.LandingBean;
import com.wpz.mymvpframe.model.bean.MainBean;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.model.bean.RegisterPasswordBean;
import com.wpz.mymvpframe.model.bean.RegisterPhoneNumBean;
import com.wpz.mymvpframe.model.bean.VideoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by wpz on 2017/5/17.
 *
 * 接口
 */

public interface Api {

    //登录
    @POST(Content.LANDING)
    Observable<LandingBean> getLandData(@Query("username") String username,
                                        @Query("password") String password);



    //验证码
    @POST(Content.CHECK_PHONE)
    Observable<RegisterPhoneNumBean> getPhoneData(@Query("mobile") String mobile);

    //验证码
    @POST(Content.VERIFICATION_CODE)
    Observable<RegisterPasswordBean> getVerificationData(@Query("mobile") String mobile);

    //检查验证码
    @POST(Content.CHECK_VERIFICATION)
    Observable<RegisterPasswordBean> getCheckVerificationData(@Query("mobile") String mobile,
                                                              @Query("verify") String verify,
                                                              @Query("password") String password);

    // 首页面一级列表请求方法
    @GET(Content.CART_LIST)
    Observable<MainBean> getbdbkData();
    //获取订单计划详情
    @POST(Content.PAGE_DETAILS)
    Observable<DetailsBean> getDetatlsData(@Query("id") int id);

    //获取用户地址
    @POST(Content.USER_ADDRESS)
    Observable<ReceiptInformationBean> getAdderssData();
    //添加用户地址
    @POST(Content.ADD_ADDRESS)
    Observable<ReceiptInformationBean> getAddAddressData(@Query("uname") String uname,
                                                         @Query("umobile" ) String umobile,
                                                         @Query("province") String province,
                                                         @Query("city") String city,
                                                         @Query("county") String county,
                                                         @Query("detail") String detail,
                                                         @Query("is_default") int is_default);
    //修改用户地址
    @POST(Content.MODIFY_ADDRESS)
    Observable<ReceiptInformationBean> getModifyAddressData(@Query("id") int id,
                                                            @Query("uname") String uname,
                                                            @Query("umobile" ) String umobile,
                                                            @Query("province") String province,
                                                            @Query("city") String city,
                                                            @Query("county") String county,
                                                            @Query("detail") String detail,
                                                            @Query("is_default") int is_default);
    //删除用户地址
    @POST(Content.DELETE_ADDRESS)
    Observable<ReceiptInformationBean> getDeleteAddressData(@Query("id") int id);

    //课程详情页
    @POST(Content.COURSE_DETAILS)
    Observable<CourseDetailsBean> getCourseDetailsData(@Query("id") int id);

    //课程详情页
    @POST(Content.ARTICLE_DETAILS)
    Observable<ArticleDetailsBean> getArticleDetailsData(@Query("id") int id);


    //创建订单
    @POST(Content.CREATE_ORDER)
    Observable<ConfirmBean> getConfirmData(@Query("plan_id") int plan_id,
                                           @Query("address") int address,
                                           @Query("remark") String remark,
                                           @Query("pay_method") int pay_method,
                                           @Query("coupon") int coupon,
                                           @Query("name") String name);
    @GET(Content.VIDEO)
    Observable<VideoBean> getVideoData(@Query("videoId") String videoId);
}
