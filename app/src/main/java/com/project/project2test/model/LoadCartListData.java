package com.project.project2test.model;

import com.project.project2test.bean.CartBean;
import com.project.project2test.utils.LoadNetDataListener;
import com.project.project2test.utils.Request_Interface;
import com.project.project2test.utils.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class LoadCartListData implements CartInter {

    @Override
    public void checkCartList(String url, final LoadNetDataListener<CartBean> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<CartBean> cartListData = request_interface.getCartListData();
        //开始请求
        cartListData.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<CartBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CartBean cartBean) {
                loadNetDataListener.loadSuccess(cartBean);
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
    public double getSum(List<CartBean.DataBean> list, int num) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<CartBean.GoodsBean> list1 = list.get(i).getList();
            for (int j = 0; j < list1.size(); j++) {
                boolean child_flag = list1.get(j).getChild_flag();
                if (child_flag) {
                    double v = list1.get(j).getPrice() * num;
                    sum = v + sum;
                }
            }
        }
        return sum;
    }
}
