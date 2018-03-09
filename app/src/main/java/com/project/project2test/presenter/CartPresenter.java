package com.project.project2test.presenter;

import com.project.project2test.bean.CartBean;
import com.project.project2test.model.LoadCartListData;
import com.project.project2test.utils.Constant;
import com.project.project2test.utils.LoadNetDataListener;
import com.project.project2test.view.CartView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartPresenter {
    private static final String TAG = "购物车";
    private LoadCartListData loadCartListData;
    private CartView cartView;
    private int num;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        this.loadCartListData = new LoadCartListData();
    }

    //开始请求购物车
    public void loadCartListData() {
        loadCartListData.checkCartList(Constant.Cart, new LoadNetDataListener<CartBean>() {
            @Override
            public void loadSuccess(CartBean netData) {
                cartView.loadCartListData(netData);
            }

            @Override
            public void loadFailed(CartBean netData) {

            }
        });
    }

    /**
     * 显示数量和价钱
     */
    public void showCountAndPrice(List<CartBean.DataBean> list, int num) {
        double sum = loadCartListData.getSum(list, num);
        double round = round(sum, 2);
        cartView.priceAndNum(round, num);
    }

    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
