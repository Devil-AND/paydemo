package com.project.zhengpengfei20180306.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhengpengfei20180306.R;
import com.project.zhengpengfei20180306.bean.CartBean;
import com.project.zhengpengfei20180306.customview.AmountView;
import com.project.zhengpengfei20180306.presenter.CartPresenter;
import com.project.zhengpengfei20180306.view.CartView;

import java.util.HashMap;
import java.util.List;

public class CartAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "购车";
    private List<CartBean.DataBean> list;
    private Context context;
    private CartPresenter cartPresenter;


    public CartAdapter(List<CartBean.DataBean> list, Context context, CartView cartView) {
        this.list = list;
        this.context = context;
        this.cartPresenter = new CartPresenter(cartView);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.group_item, null);
            groupViewHolder.checkmerchant = convertView.findViewById(R.id.checkmerchant);
            groupViewHolder.merchantname = convertView.findViewById(R.id.merchantname);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final CartBean.DataBean dataBean = list.get(groupPosition);//获取商家数据
        groupViewHolder.merchantname.setText(dataBean.getSellerName() + "");//给商家赋值
        cartPresenter.showCountAndPrice(list);//结算选中商品
        groupViewHolder.checkmerchant.setChecked(dataBean.isGrooup_flag());//设置父列表状态
        boolean checkAllChildGoods = isCheckAllChildGoods(list.get(groupPosition).getList());//判断商家下的商品是否被选中
        groupViewHolder.checkmerchant.setChecked(checkAllChildGoods);//根据判断结果给商家设置状态
        //点击事件
        groupViewHolder.checkmerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = groupViewHolder.checkmerchant.isChecked();//获取按钮点击事件
                dataBean.setGrooup_flag(checked);//给bean类赋值

                changeMerchantGoods(checked, groupPosition);//全选反选

                cartPresenter.showCountAndPrice(list);//结算
            }
        });

        return convertView;
    }

    /**
     * 全选和反选商家下的商品
     */
    private void changeMerchantGoods(boolean checked, int groupPosition) {
        //获取当前商家下的商品数据
        List<CartBean.DataBean.ListBean> childList = list.get(groupPosition).getList();
        for (int i = 0; i < childList.size(); i++) {
            childList.get(i).setChild_flag(checked);
            if (checked == false) {
                childList.get(i).setSelected(0);
            } else if (checked == true) {
                childList.get(i).setSelected(1);
            }
            HashMap<String, String> map = new HashMap<>();//获取商品参数
            map.put("uid", "71");
            map.put("sellerid", "" + childList.get(i).getSellerid());
            map.put("pid", "" + childList.get(i).getPid());
            map.put("selected", "" + childList.get(i).getSelected());
            map.put("num", "" + childList.get(i).getNum());
            cartPresenter.updateCart(map);
        }
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.child_item, null);
            childViewHolder.goods_checkbox = convertView.findViewById(R.id.goods_checkbox);
            childViewHolder.goods_iamge = convertView.findViewById(R.id.goods_iamge);
            childViewHolder.goods_title = convertView.findViewById(R.id.goods_title);
            childViewHolder.goods_price = convertView.findViewById(R.id.goods_price);
            childViewHolder.delete = convertView.findViewById(R.id.delete);
            childViewHolder.add_down = convertView.findViewById(R.id.add_down);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        Button decrease = childViewHolder.add_down.findViewById(R.id.btnDecrease);
        Button increase = childViewHolder.add_down.findViewById(R.id.btnIncrease);
        EditText etAmount = childViewHolder.add_down.findViewById(R.id.etAmount);
        cartPresenter.showCountAndPrice(list);//结算
        //获取商品数据
        final CartBean.DataBean.ListBean goodsBean = list.get(groupPosition).getList().get(childPosition);
        //赋值
        childViewHolder.goods_title.setText(goodsBean.getTitle() + "");
        childViewHolder.goods_price.setText("¥" + goodsBean.getPrice() + "");
        Glide.with(context).load(list.get(groupPosition).getList().get(childPosition).getImages().split("[|]")[0]).into(childViewHolder.goods_iamge);
        //根据selected值改变商品复选框状态
        switch (goodsBean.getSelected()) {
            case 0:
                childViewHolder.goods_checkbox.setChecked(false);
                goodsBean.setChild_flag(false);
                break;
            case 1:
                childViewHolder.goods_checkbox.setChecked(true);
                goodsBean.setChild_flag(true);
                break;
        }

        //商品点击事件
        childViewHolder.goods_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = childViewHolder.goods_checkbox.isChecked();//获取CheckBox的点击状态
                goodsBean.setChild_flag(checked);
                if (checked == true) {
                    goodsBean.setSelected(1);
                } else {
                    goodsBean.setSelected(0);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", "71");
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
                notifyDataSetChanged();
            }
        });
        //删除购物车
        childViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartPresenter.deleteCart(goodsBean.getPid() + "", list.get(groupPosition).getList());//删除购物车
                list.get(groupPosition).getList().remove(childPosition);//删除bean类数据
                List<CartBean.DataBean.ListBean> childList = CartAdapter.this.list.get(groupPosition).getList();
                if (childList == null || childList.isEmpty()) {
                    list.remove(groupPosition);
                }
                cartPresenter.showCountAndPrice(list);//结算
                notifyDataSetChanged();
            }
        });

        etAmount.setText(goodsBean.getNum() + "");//给加减器数量赋值
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsBean.getNum();
                if (num == 1) {
                    goodsBean.setNum(1);
                } else {
                    num--;
                    goodsBean.setNum(num);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", "71");
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsBean.getNum();
                if (num < 1) {
                    goodsBean.setNum(1);
                } else {
                    num++;
                    goodsBean.setNum(num);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", "71");
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
            }
        });

        return convertView;
    }

    /**
     * 判断是否选中当前商家下的商品
     */
    private boolean isCheckAllChildGoods(List<CartBean.DataBean.ListBean> childList) {
        //遍历字列表,判断是否全选所有的子列表的商品
        for (int i = 0; i < childList.size(); i++) {
            int selected = childList.get(i).getSelected();
            if (selected == 0) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 全选和反选所有的商品
     */
    public void checkAllGoods(boolean checked) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setGrooup_flag(checked);//商家设置状态值
            List<CartBean.DataBean.ListBean> childList = list.get(i).getList();//商家集合
            for (int j = 0; j < childList.size(); j++) {
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

    class GroupViewHolder {
        CheckBox checkmerchant;
        TextView merchantname;
    }

    class ChildViewHolder {
        CheckBox goods_checkbox;
        TextView goods_title;
        TextView goods_price;
        ImageView goods_iamge;
        Button delete;
        AmountView add_down;
    }
}
