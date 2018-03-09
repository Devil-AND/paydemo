package com.project.zhengpengfei20180306.utils;

import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.bean.PosterBean;
import com.project.zhengpengfei20180306.bean.UpdateCartBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface Request_Interface {
    @GET("ad/getAd")
    Observable<PosterBean> getPosterData();

    @GET("ad/getAd")
    Observable<PosterBean> getMiaoShaData();

    @GET("product/getCarts?source=android&uid=71")
    Observable<CartBean> getCartDataBean();

    //更新购物车
    @GET("product/updateCarts")
    Observable<UpdateCartBean> updateCartData(@QueryMap Map<String, String> params);

    @GET("product/deleteCart?uid=71&")
    Observable<UpdateCartBean> deleteCartData(@Query("pid") String pid);


}
