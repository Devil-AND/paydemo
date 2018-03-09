package com.project.project2test.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:封装基类
 * Detail:
 */

public abstract class BaseActivity<T extends IPresenter> extends Fragment {
    T Presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        complatePresenterFragMent();
    }

    protected abstract void complatePresenterFragMent();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Presenter == null) {
            Presenter.detach();
//            finish();
        }
    }
}
