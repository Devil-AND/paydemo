package com.project.project2test.presenter;

import android.util.Log;

import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.NewsBean;
import com.project.project2test.model.LoadNewsData;
import com.project.project2test.utils.Constant;
import com.project.project2test.utils.LoadNetDataListener;
import com.project.project2test.view.NewsView;

/**
 * Author:AND
 * Time:2018/2/27.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class NewsPresenter {
    private static final String TAG = "presenter层";
    private LoadNewsData loadNewsData;
    private NewsView newsView;

    public NewsPresenter(NewsView newsView) {
        this.newsView = newsView;
        loadNewsData = new LoadNewsData();
    }

    public void getNewDataFromNet() {
        loadNewsData.getNewsData(Constant.NewsUrl, new LoadNetDataListener<NewsBean>() {
            @Override
            public void loadSuccess(NewsBean netData) {
                newsView.showNewsToView(netData);
            }

            @Override
            public void loadFailed(NewsBean netData) {
                Log.e(TAG, "加载失败: ");
            }
        });
    }

    public void getAndroidNewDataFromNet(String page) {
        loadNewsData.getAndroidNewsData(Constant.AndroidNews, page, new LoadNetDataListener<AndroidNewsData>() {
            @Override
            public void loadSuccess(AndroidNewsData netData) {
                newsView.showAndroidNewsToView(netData);
            }

            @Override
            public void loadFailed(AndroidNewsData netData) {

            }
        });
    }

}
