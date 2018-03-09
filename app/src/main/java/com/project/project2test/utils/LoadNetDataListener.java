package com.project.project2test.utils;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadNetDataListener<T> {
    void loadSuccess(T netData);//请求成功

    void loadFailed(T netData);//请求失败
}
