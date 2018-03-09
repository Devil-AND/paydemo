package com.project.project2test.utils;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface IPresenter<T> {
    void attach(T view);
    void detach();
}
