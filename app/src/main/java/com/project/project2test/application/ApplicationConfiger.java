package com.project.project2test.application;

import android.app.Application;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.project.project2test.utils.NetWorkUtil;

/**
 * Author:AND
 * Time:2018/3/3.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class ApplicationConfiger extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        netWork();
    }

    private void netWork() {
        int netWorkType = NetWorkUtil.getNetWorkType(getApplicationContext());
        Log.e("网络状态", "netWork: " + netWorkType);
    }

    private void init() {
        DisplayImageOptions o = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .defaultDisplayImageOptions(o)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
