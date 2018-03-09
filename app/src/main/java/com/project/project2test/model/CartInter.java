package com.project.project2test.model;

import com.project.project2test.bean.CartBean;
import com.project.project2test.utils.LoadNetDataListener;

import java.util.List;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface CartInter {
    //查询购物车
    void checkCartList(String url, LoadNetDataListener<CartBean> loadNetDataListener);

    double getSum(List<CartBean.DataBean> list, int num);
}
