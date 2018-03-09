package com.project.project2test.model;

import android.util.Log;

import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.NewsBean;
import com.project.project2test.utils.LoadNetDataListener;
import com.project.project2test.utils.Request_Interface;
import com.project.project2test.utils.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class LoadNewsData implements NewsModel {
    private static final String TAG = "model层";
    public OkHttpClient okclient = new OkHttpClient.Builder().build();

    @Override
    public void getNewsData(String url, final LoadNetDataListener<NewsBean> loadNetDataListener) {
        //Constant域名的类
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<NewsBean> newsData = request_interface.getNewsData();
        //开始请求
        newsData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<NewsBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(NewsBean newsBean) {
                loadNetDataListener.loadSuccess(newsBean);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void getAndroidNewsData(String url, String page, final LoadNetDataListener<AndroidNewsData> loadNetDataListener) {
        //Constant域名的类
        Request_Interface request_interface = RetrofitManager.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<AndroidNewsData> androidNewsData = request_interface.getAndroidNewsData(page);
        androidNewsData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AndroidNewsData>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(AndroidNewsData androidNewsData) {
                loadNetDataListener.loadSuccess(androidNewsData);
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
