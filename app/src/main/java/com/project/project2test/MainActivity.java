package com.project.project2test;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.project.project2test.fragments.CLassify_fragment;
import com.project.project2test.fragments.Cart_fragment;
import com.project.project2test.fragments.Display_fragment;
import com.project.project2test.fragments.Home_fragment;
import com.project.project2test.fragments.Me_fragment;
import com.project.project2test.utils.NetWorkStateReceiver;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mFrame;
    private RadioButton mShouyeMain;
    private RadioButton mFenleiMain;
    private RadioButton mFaxianMain;
    private RadioButton mCarMain;
    private RadioButton mPersonMain;
    private RadioGroup mRgMain;
    private FragmentManager fragmentManager;
    private Home_fragment homeFragment;
    private CLassify_fragment cLassify_fragment;
    private Display_fragment display_fragment;
    private Cart_fragment cart_fragment;
    private Me_fragment me_fragment;
    private NetWorkStateReceiver netWorkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction tra = fragmentManager.beginTransaction();//开启事物
        //显示一个fragment
        homeFragment = new Home_fragment();
        tra.add(R.id.frame, homeFragment).commit();
        mRgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFragments();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.main_shouye:
                        fragmentTransaction.show(homeFragment).commit();
                        break;
                    case R.id.main_fenlei:
                        if (cLassify_fragment == null) {
                            cLassify_fragment = new CLassify_fragment();
                            fragmentTransaction.add(R.id.frame, cLassify_fragment).commit();
                        } else {
                            fragmentTransaction.show(cLassify_fragment).commit();
                        }

                        break;
                    case R.id.main_faxian:
                        if (display_fragment == null) {
                            display_fragment = new Display_fragment();
                            fragmentTransaction.add(R.id.frame, display_fragment).commit();
                        } else {
                            fragmentTransaction.show(display_fragment).commit();
                        }
                        break;
                    case R.id.main_car:
                        if (cart_fragment == null) {
                            cart_fragment = new Cart_fragment();
                            fragmentTransaction.add(R.id.frame, cart_fragment).commit();
                        } else {
                            fragmentTransaction.show(cart_fragment).commit();
                        }
                        break;
                    case R.id.main_person:
                        if (me_fragment == null) {
                            me_fragment = new Me_fragment();
                            fragmentTransaction.add(R.id.frame, me_fragment).commit();
                        } else {
                            fragmentTransaction.show(me_fragment).commit();
                        }
                        break;
                }
            }
        });
    }

    private void initView() {
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mShouyeMain = (RadioButton) findViewById(R.id.main_shouye);
        mFenleiMain = (RadioButton) findViewById(R.id.main_fenlei);
        mFaxianMain = (RadioButton) findViewById(R.id.main_faxian);
        mCarMain = (RadioButton) findViewById(R.id.main_car);
        mPersonMain = (RadioButton) findViewById(R.id.main_person);
        mRgMain = (RadioGroup) findViewById(R.id.main_rg);
    }

    //隐藏所有Fragment的方法
    public void hideFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (homeFragment != null && homeFragment.isAdded()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (cLassify_fragment != null && cLassify_fragment.isAdded()) {
            fragmentTransaction.hide(cLassify_fragment);
        }
        if (display_fragment != null && display_fragment.isAdded()) {
            fragmentTransaction.hide(display_fragment);
        }
        if (cart_fragment != null && cart_fragment.isAdded()) {
            fragmentTransaction.hide(cart_fragment);
        }
        if (me_fragment != null && me_fragment.isAdded()) {
            fragmentTransaction.hide(me_fragment);
        }
        fragmentTransaction.commit();

    }

    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
