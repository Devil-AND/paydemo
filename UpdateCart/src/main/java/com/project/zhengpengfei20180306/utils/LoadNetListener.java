package com.project.zhengpengfei20180306.utils;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadNetListener<T> {
    void loadSuccess(T data);//请求成功

    void loadFailed(T data);//请求失败
}
