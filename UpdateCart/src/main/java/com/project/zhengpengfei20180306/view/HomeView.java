package com.project.zhengpengfei20180306.view;

import com.project.zhengpengfei20180306.bean.PosterBean;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:view视图层
 * Detail:
 */

public interface HomeView {
    void loadPosterData(PosterBean posterBean);//加载轮播数据

    void loadMiaoShaData(PosterBean posterBean);//秒杀
}
