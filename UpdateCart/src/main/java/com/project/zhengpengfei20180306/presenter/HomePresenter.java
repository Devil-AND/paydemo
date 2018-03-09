package com.project.zhengpengfei20180306.presenter;

import com.project.zhengpengfei20180306.bean.PosterBean;
import com.project.zhengpengfei20180306.modeldatatask.HomeModel;
import com.project.zhengpengfei20180306.utils.Constant;
import com.project.zhengpengfei20180306.utils.LoadNetListener;
import com.project.zhengpengfei20180306.view.HomeView;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class HomePresenter {
    private HomeModel homeModel;
    private HomeView homeView;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        this.homeModel = new HomeModel();
    }

    public void getPosterData() {
        homeModel.loadPoster(Constant.Url, new LoadNetListener<PosterBean>() {
            @Override
            public void loadSuccess(PosterBean data) {
                homeView.loadPosterData(data);
            }

            @Override
            public void loadFailed(PosterBean data) {

            }
        });
    }

    public void getMiaoSha() {
        homeModel.loadMiaoSha(Constant.Url, new LoadNetListener<PosterBean>() {
            @Override
            public void loadSuccess(PosterBean data) {
                homeView.loadMiaoShaData(data);
            }

            @Override
            public void loadFailed(PosterBean data) {

            }
        });
    }
}
