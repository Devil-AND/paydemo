package com.project.project2test.view;

import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.NewsBean;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface NewsView {
    void showNewsToView(NewsBean newsBean);//展示数据到页面

    void showAndroidNewsToView(AndroidNewsData androidNewsData);//展示数据到页面
}
