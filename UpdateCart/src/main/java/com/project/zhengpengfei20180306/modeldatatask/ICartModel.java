package com.project.zhengpengfei20180306.modeldatatask;

import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.bean.UpdateCartBean;
import com.project.zhengpengfei20180306.utils.LoadNetListener;

import java.util.List;
import java.util.Map;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface ICartModel {
    void getCartData(String url, LoadNetListener<CartBean> loadNetListener);//加载购物车数据

    void updateCartData(String url, Map<String, String> params, LoadNetListener<UpdateCartBean> loadNetListener);//更新购物车

    void deleteCartData(String url, String pid, LoadNetListener<UpdateCartBean> loadNetListener);//删除购物车

    double settleAcount(List<CartBean.DataBean> list);
}
