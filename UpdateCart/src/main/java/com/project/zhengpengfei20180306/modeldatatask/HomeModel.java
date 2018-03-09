package com.project.zhengpengfei20180306.modeldatatask;

import android.util.Log;

import com.project.zhengpengfei20180306.bean.PosterBean;
import com.project.zhengpengfei20180306.utils.LoadNetListener;
import com.project.zhengpengfei20180306.utils.Request_Interface;
import com.project.zhengpengfei20180306.utils.RetrofitManager;

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

public class HomeModel implements IHomeModel {

    @Override
    public void loadPoster(String url, final LoadNetListener<PosterBean> loadNetListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<PosterBean> posterData = request_interface.getPosterData();
        //开始请求
        posterData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PosterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PosterBean posterBean) {
                loadNetListener.loadSuccess(posterBean);
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
    public void loadMiaoSha(String url, final LoadNetListener<PosterBean> loadNetListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<PosterBean> miaoShaData = request_interface.getMiaoShaData();
        miaoShaData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PosterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PosterBean posterBean) {
                loadNetListener.loadSuccess(posterBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
