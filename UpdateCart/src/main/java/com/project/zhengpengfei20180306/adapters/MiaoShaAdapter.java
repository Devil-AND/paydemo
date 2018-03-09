package com.project.zhengpengfei20180306.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zhengpengfei20180306.R;
import com.project.zhengpengfei20180306.bean.PosterBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/3/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.MyViewHolder> {
    private List<PosterBean.MiaoshaBean.ListBeanX> list;
    private Context context;

    public MiaoShaAdapter(List<PosterBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.secondkill_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.mImageMiaosha);
        holder.mPriceMiaosha.setText(list.get(position).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageMiaosha;
        private TextView mPriceMiaosha;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mImageMiaosha = (ImageView) itemView.findViewById(R.id.miaosha_image);
            mPriceMiaosha = (TextView) itemView.findViewById(R.id.miaosha_price);
        }
    }
}
