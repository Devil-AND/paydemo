package com.project.zhengpengfei20180306.presenter;

import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.bean.UpdateCartBean;
import com.project.zhengpengfei20180306.modeldatatask.CartModel;
import com.project.zhengpengfei20180306.utils.Constant;
import com.project.zhengpengfei20180306.utils.LoadNetListener;
import com.project.zhengpengfei20180306.view.CartView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class CartPresenter {
    private CartModel cartModel;
    private CartView cartView;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        this.cartModel = new CartModel();
    }

    //获取购物车数据
    public void getCartBean() {
        cartModel.getCartData(Constant.Url, new LoadNetListener<CartBean>() {
            @Override
            public void loadSuccess(CartBean data) {
                cartView.showCartDataBean(data);
            }

            @Override
            public void loadFailed(CartBean data) {

            }
        });
    }

    /**
     * 显示数量和价钱
     */
    public void showCountAndPrice(List<CartBean.DataBean> list) {
        double v = cartModel.settleAcount(list);
        double round = round(v, 2);
        cartView.priceAndNum(round);//将结果展示在页面
    }

    /**
     * 刷新购物车
     */

    public void updateCart(HashMap<String, String> params) {
        cartModel.updateCartData(Constant.Url, params, new LoadNetListener<UpdateCartBean>() {
            @Override
            public void loadSuccess(UpdateCartBean data) {
                cartView.updateCartData();
            }

            @Override
            public void loadFailed(UpdateCartBean data) {

            }
        });
    }

    /**
     * 删除购物车
     */
    public void deleteCart(String pid, final List<CartBean.DataBean.ListBean> childList) {
        cartModel.deleteCartData(Constant.Url, pid, new LoadNetListener<UpdateCartBean>() {
            @Override
            public void loadSuccess(UpdateCartBean data) {
                cartView.updateCartData();
            }

            @Override
            public void loadFailed(UpdateCartBean data) {

            }
        });
    }

    //精准到小数两位
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
