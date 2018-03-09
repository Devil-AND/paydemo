package com.project.project2test.fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.project.project2test.R;
import com.project.project2test.bean.AndroidNewsData;
import com.project.project2test.bean.NewsBean;
import com.project.project2test.customview.AmountView;
import com.project.project2test.customview.AuToBanner;
import com.project.project2test.customview.NoticeView;
import com.project.project2test.model.LoadNewsData;
import com.project.project2test.presenter.NewsPresenter;
import com.project.project2test.utils.BaseActivity;
import com.project.project2test.view.NewsView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author:AND
 * Time:2018/2/26.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */


public class Home_fragment extends Fragment implements NewsView {
    private static final String TAG = "fragment层";
    private LoadNewsData loadNewsData;
    private NewsPresenter newsPresenter;
    private AuToBanner banner;
    private List<String> urls;
    private AmountView amountView;
    private NoticeView mViewNotice;
    private AuToBanner mBanner;
    private AmountView mViewAmount;
    private TextView mMiaoshaTv;
    private TextView mMiaoshaTimeTv;
    private TextView mMiaoshaShiTv;
    private TextView mMiaoshaMinterTv;
    private TextView mMiaoshaSecondTv;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment, container, false);
        banner = inflate.findViewById(R.id.banner);
        amountView = inflate.findViewById(R.id.amount_view);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mViewNotice = (NoticeView) itemView.findViewById(R.id.notice_view);
        mBanner = (AuToBanner) itemView.findViewById(R.id.banner);
        mViewAmount = (AmountView) itemView.findViewById(R.id.amount_view);
        mMiaoshaTv = (TextView) itemView.findViewById(R.id.tv_miaosha);
        mMiaoshaTimeTv = (TextView) itemView.findViewById(R.id.tv_miaosha_time);
        mMiaoshaShiTv = (TextView) itemView.findViewById(R.id.tv_miaosha_shi);
        mMiaoshaMinterTv = (TextView) itemView.findViewById(R.id.tv_miaosha_minter);
        mMiaoshaSecondTv = (TextView) itemView.findViewById(R.id.tv_miaosha_second);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //MVP模式
        newsPresenter = new NewsPresenter(this);
        newsPresenter.getAndroidNewDataFromNet(1 + "");//安卓新闻数据
        init();//跑马灯
        autoBanner();//开启轮播
        addAndCount();//加减器
        startCountDown();//倒计时

    }

    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }

    /**
     * 秒杀
     */
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
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
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

    /**
     * 开启轮播
     */
    private void autoBanner() {
        urls = new ArrayList<>();
        urls.add("http://pic71.nipic.com/file/20150709/9885883_133323978000_2.jpg");
        urls.add("http://image.club.china.com/twhb/2014/8/24/1011/1408883429066.jpg");
        urls.add("http://photocdn.sohu.com/20130115/Img363518686.jpg");
        urls.add("http://image.club.china.com/twhb/2016/1/14/1013/1452772291776_495.jpg");
        urls.add("http://images.china.cn/attachement/jpg/site1000/20150827/001fd04cf0161748f83d1c.jpg");
        urls.add("http://image.club.china.com/twhb/2016/2/3/1013/1454513937918.jpeg");
        //解耦
        banner.loadData(urls).display();//构建者模式返回对象本身
        banner.setBannerClicklistener(new AuToBanner.BannerClicklistener() {
            @Override
            public void onClickListener(int pos) {
                Toast.makeText(getActivity(), "" + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 购物车加减器
     */
    private void addAndCount() {
        amountView.setGoods_storage(50);
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(getActivity(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void init() {
        NoticeView noticeView = (NoticeView) getActivity().findViewById(R.id.notice_view);
        List<String> notices = new ArrayList<>();
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
    }

    @Override
    public void showNewsToView(NewsBean newsBean) {
        //请求结果
        Log.e(TAG, "聚合数据: " + newsBean.getReason());

    }

    @Override
    public void showAndroidNewsToView(AndroidNewsData androidNewsData) {
        Log.e(TAG, "安卓数据请求结果: " + androidNewsData.getResults().get(1).getCreatedAt());
        newsPresenter.getNewDataFromNet();//新闻数据
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


}
