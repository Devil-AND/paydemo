package com.project.zhengpengfei20180306.modeldatatask;

import com.project.zhengpengfei20180306.bean.PosterBean;
import com.project.zhengpengfei20180306.utils.LoadNetListener;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface IHomeModel {
    //加载轮播数据
    void loadPoster(String url, LoadNetListener<PosterBean> loadNetListener);

    //开启秒杀
    void loadMiaoSha(String url, LoadNetListener<PosterBean> loadNetListener);
}
