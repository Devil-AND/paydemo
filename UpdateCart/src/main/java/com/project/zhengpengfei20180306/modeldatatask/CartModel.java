package com.project.zhengpengfei20180306.modeldatatask;

import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.bean.UpdateCartBean;
import com.project.zhengpengfei20180306.utils.LoadNetListener;
import com.project.zhengpengfei20180306.utils.Request_Interface;
import com.project.zhengpengfei20180306.utils.RetrofitManager;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartModel implements ICartModel {
    @Override
    public void getCartData(String url, final LoadNetListener<CartBean> loadNetListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<CartBean> cartDataBean = request_interface.getCartDataBean();
        cartDataBean.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CartBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(CartBean cartBean) {
                loadNetListener.loadSuccess(cartBean);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });
    }
    @Override
    public void updateCartData(String url, Map<String, String> params, final LoadNetListener<UpdateCartBean> loadNetListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UpdateCartBean> updateCartBeanObservable = request_interface.updateCartData(params);
        updateCartBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpdateCartBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(UpdateCartBean updateCartBean) {
                loadNetListener.loadSuccess(updateCartBean);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });
    }

    //删除购物车
    @Override
    public void deleteCartData(String url, String pid, final LoadNetListener<UpdateCartBean> loadNetListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UpdateCartBean> updateCartBeanObservable = request_interface.deleteCartData(pid);
        //开始请求
        updateCartBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpdateCartBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(UpdateCartBean updateCartBean) {
                loadNetListener.loadSuccess(updateCartBean);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });
    }
    //结算
    @Override
    public double settleAcount(List<CartBean.DataBean> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<CartBean.DataBean.ListBean> childList = list.get(i).getList();
            for (int j = 0; j < childList.size(); j++) {
                int selected = childList.get(j).getSelected();
                int num = childList.get(j).getNum();
                double price = childList.get(j).getPrice();
                if (selected == 1) {
                    sum = sum + (num * price);
                }
            }
        }
        return sum;
    }
}
