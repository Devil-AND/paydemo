package com.project.zhengpengfei20180306.view;

import com.project.zhengpengfei20180306.bean.CartBean;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface CartView {
    void showCartDataBean(CartBean cartBean);//显示购物车数据

    void priceAndNum(double round);//结算

    void updateCartData();
}
