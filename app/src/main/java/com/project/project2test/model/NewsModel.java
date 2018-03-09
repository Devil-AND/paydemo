package com.project.project2test.model;

import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.NewsBean;
import com.project.project2test.utils.LoadNetDataListener;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface NewsModel {
    void getNewsData(String url, LoadNetDataListener<NewsBean> loadNetDataListener);//获取新闻数据

    void getAndroidNewsData(String url, String page, LoadNetDataListener<AndroidNewsData> loadNetDataListener);//获取Android新闻数据
}
