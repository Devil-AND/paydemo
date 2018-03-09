package com.project.project2test.utils;


import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.CartBean;
import com.project.project2test.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface Request_Interface {
    @GET("toutiao/index?type=top&key=597b4f9dcb50e051fd725a9ec54d6653")
    Observable<NewsBean> getNewsData();

    @GET("api/data/Android/10/{page}")
    Observable<AndroidNewsData> getAndroidNewsData(@Path("page") String page);

    @GET("product/getCarts?source=android&uid=12248")
    Observable<CartBean> getCartListData();


}
