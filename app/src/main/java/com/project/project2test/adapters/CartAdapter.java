package com.project.project2test.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.project2test.R;
import com.project.project2test.bean.CartBean;
import com.project.project2test.customview.AmountView;
import com.project.project2test.presenter.CartPresenter;
import com.project.project2test.view.CartView;

import java.util.List;

/**
 * Author:AND
 * Time:2018/3/4.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "购物车适配器";
    private List<CartBean.DataBean> list;
    private Context context;
    private CartView cartView;
    private CartPresenter cartPresenter;

    public CartAdapter(List<CartBean.DataBean> list, Context context, CartView cartView) {
        this.list = list;
        this.context = context;
        this.cartView = cartView;
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
            convertView = View.inflate(context, R.layout.group_view, null);
            groupViewHolder.groupcheckbox = convertView.findViewById(R.id.chack_merchant);
            groupViewHolder.merchant = convertView.findViewById(R.id.merchant);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //获取商家数据
        final CartBean.DataBean dataBean = list.get(groupPosition);
        //商家复选框设置状态值
        groupViewHolder.groupcheckbox.setChecked(dataBean.isGroup_flag());
        //商家名称
        groupViewHolder.merchant.setText(dataBean.getSellerName() + "");
        //选中商家下的商品事件
        groupViewHolder.groupcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = groupViewHolder.groupcheckbox.isChecked();
                //给复选框设置状态
                dataBean.setGroup_flag(checked);
                changeMerchantGoods(checked, groupPosition);//全选和反选商家下的商品
                boolean flag = isCheckAllGoods();//判断是否选中所有商品
                notifyDataSetChanged();
                //显示数量和价格
                cartPresenter.showCountAndPrice(list, 1);

            }
        });
        return convertView;
    }

    /**
     * 选中所有的商品
     */
    public void checkAllGoods(boolean checked) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setGroup_flag(checked);
            List<CartBean.GoodsBean> childList = list.get(i).getList();
            for (int j = 0; j < childList.size(); j++) {
                childList.get(j).setChild_flag(checked);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 判断是否选中所有商品
     */
    public boolean isCheckAllGoods() {
        boolean flag = true;
        //遍历父列表是否被选中
        for (int i = 0; i < list.size(); i++) {
            boolean group_flag = list.get(i).getGroup_flag();
            if (group_flag == false) {
                flag = false;
            }
            //遍历子列表是否被选中
            List<CartBean.GoodsBean> childList = list.get(i).getList();
            for (int j = 0; j < childList.size(); j++) {
                boolean child_flag = childList.get(j).getChild_flag();
                if (child_flag == false) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * 全选和反选商家下的商品
     */
    private void changeMerchantGoods(boolean checked, int groupPosition) {
        List<CartBean.GoodsBean> childList = list.get(groupPosition).getList();
        for (int i = 0; i < childList.size(); i++) {
            childList.get(i).setChild_flag(checked);
        }
        notifyDataSetChanged();
    }


    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.child_view, null);
            childViewHolder.goodstitle = convertView.findViewById(R.id.goods_title);
            childViewHolder.goodprice = convertView.findViewById(R.id.goods_price);
            childViewHolder.goodsImage = convertView.findViewById(R.id.goods_img);
            childViewHolder.amountView = convertView.findViewById(R.id.add_and_down);
            childViewHolder.childgroupcheckbox = convertView.findViewById(R.id.check_goods);

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //获取当前子列表集合
        List<CartBean.GoodsBean> childList = list.get(groupPosition).getList();
        //获取bean类
        final CartBean.GoodsBean goodsBean = childList.get(childPosition);
        //设置状态值
        childViewHolder.childgroupcheckbox.setChecked(goodsBean.isChild_flag());
        //商品标题
        childViewHolder.goodstitle.setText(goodsBean.getTitle() + "");
        childViewHolder.goodprice.setText("¥" + goodsBean.getPrice());
        Glide.with(context).load(list.get(groupPosition).getList().get(childPosition).getImages().split("[|]")[0]).into(childViewHolder.goodsImage);
        childViewHolder.amountView.setGoods_storage(50);
        //购物车加减器
        childViewHolder.amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

                cartPresenter.showCountAndPrice(list, amount);
            }
        });
        childViewHolder.childgroupcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子列表设置状态
                boolean checked = childViewHolder.childgroupcheckbox.isChecked();
                goodsBean.setChild_flag(checked);

                boolean flag = isCheckAllChildGoods(list.get(groupPosition).getList());//判断商家下的商品是否被全选
                list.get(groupPosition).setGroup_flag(flag);//根据判断结果,给父列表设置状态
                notifyDataSetChanged();
                cartPresenter.showCountAndPrice(list, 1);

            }
        });
        return convertView;
    }

    /**
     * 判断商家下的商品是否被全选
     */
    private boolean isCheckAllChildGoods(List<CartBean.GoodsBean> childList) {
        //遍历字列表,判断是否全选所有的子列表的商品
        for (int i = 0; i < childList.size(); i++) {
            boolean child_flag = childList.get(i).getChild_flag();
            if (child_flag == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        CheckBox groupcheckbox;
        TextView merchant;
    }

    class ChildViewHolder {
        CheckBox childgroupcheckbox;
        ImageView goodsImage;
        TextView goodstitle;
        TextView goodprice;
        AmountView amountView;
    }
}
