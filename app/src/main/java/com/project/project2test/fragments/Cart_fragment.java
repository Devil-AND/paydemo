package com.project.project2test.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.project.project2test.R;
import com.project.project2test.adapters.CartAdapter;
import com.project.project2test.bean.CartBean;
import com.project.project2test.presenter.CartPresenter;
import com.project.project2test.view.CartView;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/26.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */


public class Cart_fragment extends Fragment implements CartView {
    private CartPresenter cartPresenter = new CartPresenter(this);
    private ExpandableListView mExpandableCart;
    private CheckBox mQuanxuan;
    private TextView mQujiesuan;
    private List<CartBean.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cart_fragment, container, false);
        initView(inflate);
        return inflate;
    }


    private void initView(@NonNull final View itemView) {
        mExpandableCart = (ExpandableListView) itemView.findViewById(R.id.cart_expandable);
        mQuanxuan = (CheckBox) itemView.findViewById(R.id.quanxuan);
        mQujiesuan = (TextView) itemView.findViewById(R.id.qujiesuan);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartPresenter.loadCartListData();//购物车数据


    }

    //购物车
    @Override
    public void loadCartListData(CartBean cartBean) {
        data = cartBean.getData();
        final CartAdapter cartAdapter = new CartAdapter(data, getActivity(), this);
        mExpandableCart.setAdapter(cartAdapter);
        mExpandableCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        int count = mExpandableCart.getCount();
        for (int i = 0; i < count; i++) {
            mExpandableCart.expandGroup(i);
        }
        //全选/反选
        mQuanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartAdapter.checkAllGoods(mQuanxuan.isChecked());
                cartPresenter.showCountAndPrice(data, 1);
            }
        });
    }

    //显示价格和数量
    @Override
    public void priceAndNum(double sum, int num) {
        mQujiesuan.setText("合计:" + sum);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
