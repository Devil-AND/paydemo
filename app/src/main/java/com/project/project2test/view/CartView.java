package com.project.project2test.view;

import com.project.project2test.bean.CartBean;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface CartView {
    void loadCartListData(CartBean cartBean);

    void priceAndNum(double sum, int num);
}
