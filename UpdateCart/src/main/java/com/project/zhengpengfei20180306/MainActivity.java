package com.project.zhengpengfei20180306;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.project.zhengpengfei20180306.fragments.CartFragment;
import com.project.zhengpengfei20180306.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private BottomTabBar mTabBarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mTabBarBottom.init(getSupportFragmentManager())
                .setChangeColor(Color.RED, Color.BLACK)
                .isShowDivider(true)
                .addTabItem("首页", R.drawable.ic_home_black, HomeFragment.class)
                .addTabItem("购物车", R.drawable.ic_cart_black, CartFragment.class);
    }

    private void initView() {
        mTabBarBottom = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
    }
}
