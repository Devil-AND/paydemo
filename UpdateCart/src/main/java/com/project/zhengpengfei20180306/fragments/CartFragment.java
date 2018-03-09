package com.project.zhengpengfei20180306.fragments;

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

import com.project.zhengpengfei20180306.R;
import com.project.zhengpengfei20180306.adapters.CartAdapter;
import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.presenter.CartPresenter;
import com.project.zhengpengfei20180306.view.CartView;

import java.util.HashMap;
import java.util.List;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartFragment extends Fragment implements CartView {

    private static final String TAG = "购物车页面";
    private CartPresenter cartPresenter;
    private ExpandableListView mCart;
    private CheckBox mCheckallgoods;
    private TextView mSumcount;
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cart_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mCart = (ExpandableListView) itemView.findViewById(R.id.cart);
        mCheckallgoods = (CheckBox) itemView.findViewById(R.id.checkallgoods);
        mSumcount = (TextView) itemView.findViewById(R.id.sumcount);
        mCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;//返回true,表示不可点击
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cartPresenter = new CartPresenter(this);
        cartPresenter.getCartBean();//获取购物车数据
    }

    @Override
    public void showCartDataBean(CartBean cartBean) {
        final List<CartBean.DataBean> data = cartBean.getData();
        cartAdapter = new CartAdapter(data, getActivity(), this);
        mCart.setAdapter(cartAdapter);
        int groupCount = cartAdapter.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            mCart.expandGroup(i);
        }
        //全选和反选所有商品
        mCheckallgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*cartAdapter.checkAllGoods(mCheckallgoods.isChecked());*/
                boolean checked = mCheckallgoods.isChecked();
                for (int i = 0; i < data.size(); i++) {
                    List<CartBean.DataBean.ListBean> childList = data.get(i).getList();
                    for (int j = 0; j < childList.size(); j++) {
                        CartBean.DataBean.ListBean listBean = childList.get(j);
                        listBean.setChild_flag(checked);
                        if (checked == true) {
                            childList.get(j).setSelected(1);
                            childList.get(j).setChild_flag(true);
                        } else {
                            childList.get(j).setSelected(0);
                            childList.get(j).setChild_flag(false);
                        }
                        HashMap<String, String> map = new HashMap<>();//获取商品参数
                        //获取购物车的参数
                        map.put("uid", "71");
                        map.put("sellerid", "" + childList.get(j).getSellerid());
                        map.put("pid", "" + childList.get(j).getPid());
                        map.put("selected", "" + childList.get(j).getSelected());
                        map.put("num", "" + childList.get(j).getNum());
                        cartPresenter.updateCart(map);//刷新购物车
                    }
                }
            }
        });

    }

    @Override
    public void priceAndNum(double round) {
        mSumcount.setText("合计: " + round + "    ");
    }

    //更新购物车
    @Override
    public void updateCartData() {
        cartAdapter.notifyDataSetChanged();
    }
}
