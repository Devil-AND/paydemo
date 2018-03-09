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

public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.MyViewHolder> {
    private List<PosterBean.TuijianBean.ListBean> list;
    private Context context;

    public TuiJianAdapter(List<PosterBean.TuijianBean.ListBean> tuijianList, Context context) {
        this.list = tuijianList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.tuijian_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextTitle.setText(list.get(position).getTitle() + "");
        String images = list.get(position).getImages();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.mIamgeTuijian);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIamgeTuijian;
        private TextView mTextTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mIamgeTuijian = (ImageView) itemView.findViewById(R.id.tuijian_iamge);
            mTextTitle = (TextView) itemView.findViewById(R.id.tuijian_title);
        }
    }
}
