package com.project.zhengpengfei20180306.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhengpengfei20180306.Main2Activity;
import com.project.zhengpengfei20180306.R;
import com.project.zhengpengfei20180306.adapters.MiaoShaAdapter;
import com.project.zhengpengfei20180306.adapters.TuiJianAdapter;
import com.project.zhengpengfei20180306.bean.PosterBean;
import com.project.zhengpengfei20180306.customview.AutoBanner;
import com.project.zhengpengfei20180306.presenter.HomePresenter;
import com.project.zhengpengfei20180306.view.HomeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:首页页面
 * Detail:
 */

public class HomeFragment extends Fragment implements HomeView {

    private static final String TAG = "首页";
    private ArrayList<String> urls;
    private AutoBanner mAutobanner;
    private HomePresenter homePresenter;
    private Banner banner;
    private ArrayList<String> iamges;
    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };
    private TextView mMiaoshaTv;
    private TextView mMiaoshaTimeTv;
    private TextView mMiaoshaShiTv;
    private TextView mMiaoshaMinterTv;
    private TextView mMiaoshaSecondTv;
    private RecyclerView mMiaoshaxrecl;
    private RecyclerView mTuijianxrecl;

    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            mMiaoshaTimeTv.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            mMiaoshaTimeTv.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(totime);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            mMiaoshaShiTv.setText("0" + hours + "");
            if (minute >= 10) {
                mMiaoshaMinterTv.setText(minute + "");
            } else {
                mMiaoshaMinterTv.setText("0" + minute + "");
            }
            if (second >= 10) {
                mMiaoshaSecondTv.setText(second + "");
            } else {
                mMiaoshaSecondTv.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        banner = (Banner) itemView.findViewById(R.id.banner);
        mMiaoshaTv = (TextView) itemView.findViewById(R.id.tv_miaosha);
        mMiaoshaTimeTv = (TextView) itemView.findViewById(R.id.tv_miaosha_time);
        mMiaoshaShiTv = (TextView) itemView.findViewById(R.id.tv_miaosha_shi);
        mMiaoshaMinterTv = (TextView) itemView.findViewById(R.id.tv_miaosha_minter);
        mMiaoshaSecondTv = (TextView) itemView.findViewById(R.id.tv_miaosha_second);
        mMiaoshaxrecl = (RecyclerView) itemView.findViewById(R.id.miaoshaxrecl);
        mTuijianxrecl = (RecyclerView) itemView.findViewById(R.id.tuijianxrecl);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homePresenter = new HomePresenter(this);
        homePresenter.getPosterData();//加载轮播数据
        startCountDown();//开启倒计时
    }

    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }

    //开启轮播
    private void startautobanner() {
        urls = new ArrayList<>();
        urls.add("http://pic71.nipic.com/file/20150709/9885883_133323978000_2.jpg");
        urls.add("http://image.club.china.com/twhb/2014/8/24/1011/1408883429066.jpg");
        urls.add("http://photocdn.sohu.com/20130115/Img363518686.jpg");
        urls.add("http://image.club.china.com/twhb/2016/1/14/1013/1452772291776_495.jpg");
        urls.add("http://images.china.cn/attachement/jpg/site1000/20150827/001fd04cf0161748f83d1c.jpg");
        urls.add("http://image.club.china.com/twhb/2016/2/3/1013/1454513937918.jpeg");
        //解耦
        mAutobanner.loadData(urls).display();//构建者模式返回对象本身
        mAutobanner.setBannerClicklistener(new AutoBanner.BannerClicklistener() {
            @Override
            public void onClickListener(int pos) {

            }
        });
    }

    //加载轮播数据
    @Override
    public void loadPosterData(PosterBean posterBean) {
        iamges = new ArrayList<>();
        List<PosterBean.DataBean> data = posterBean.getData();
        for (int i = 0; i < data.size(); i++) {
            iamges.add(data.get(i).getIcon());
        }
        startBanner();//开启轮播
        homePresenter.getMiaoSha();//获取秒杀数据
    }

    //开始秒杀
    @Override
    public void loadMiaoShaData(PosterBean posterBean) {
        PosterBean.MiaoshaBean miaosha = posterBean.getMiaosha();
        List<PosterBean.MiaoshaBean.ListBeanX> list = miaosha.getList();
        //设置适配器
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(list, getActivity());
        mMiaoshaxrecl.setAdapter(miaoShaAdapter);
        mMiaoshaxrecl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //设置推荐数据
        PosterBean.TuijianBean tuijian = posterBean.getTuijian();
        List<PosterBean.TuijianBean.ListBean> tuijianList = tuijian.getList();
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(tuijianList, getActivity());
        mTuijianxrecl.setAdapter(tuiJianAdapter);
        mTuijianxrecl.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    //轮播
    private void startBanner() {
        banner.setImages(iamges);
        banner.setImageLoader(new ImageLoader() {//配置重写好的加载图片的类
            public void displayImage(Context context, Object path, ImageView imageView) {
                //全包名导入框架
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path, imageView);
            }
        });
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();//开启轮播

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
