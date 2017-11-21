package com.wpz.mymvpframe.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.model.bean.CourseDetailsBean;

import java.util.List;

/**
 * Created by wpz on 2017/10/30 0030.
 * 类作用：
 */

public class CourseDetailsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<CourseDetailsBean.DataBean.CourseDetailBean.VideoLinksBean> mData;
    public final static int TYPE_NORMAL = 1;//Item的第一种布局
    public final static int TYPE_NORTWO = 2;//Item的第二种布局
    private CourseDetailsRecyclerAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public CourseDetailsRecyclerAdapter(Context context, List<CourseDetailsBean.DataBean.CourseDetailBean.VideoLinksBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
//        if (mData.get(position) == "1") {
            return TYPE_NORMAL;
//        } else {
//            return TYPE_NORTWO;
//        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh;
        View view;
        switch (i) {
            default:
            case TYPE_NORMAL:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course_details_recycler, viewGroup, false);
                vh = new CourseDetailsRecyclerAdapter.WenTiHolder(view);
                return vh;
            case TYPE_NORTWO:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_course_details_rest_recycler, viewGroup, false);
                vh = new CourseDetailsRecyclerAdapter.TwoHolder(view);
                return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof WenTiHolder) {
            WenTiHolder newHolder = (WenTiHolder) viewHolder;
//            newHolder.nametext.setText(mData.get(position));
//给整条Item添加点击事件，因为是自定义的，可以写多种点击事件，或者点击限制等。
            newHolder.itemView.setTag(position);
            newHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//接收到点击事件后，返回给外部数据回调
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
        if (viewHolder instanceof TwoHolder) {
            TwoHolder newHolder = (TwoHolder) viewHolder;
            newHolder.itemView.setTag(position);
            newHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    //自定义的Item响应事件需要接收OnRecyclerViewItemClickListener接口的函数
    public void setOnItemClickListener(CourseDetailsRecyclerAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    /**
     * 第一种布局
     */
    class WenTiHolder extends RecyclerView.ViewHolder {

        TextView nametext;
        TextView timatext;
        ImageView img;
        public WenTiHolder(View itemView) {
            super(itemView);
            nametext = (TextView)itemView.findViewById(R.id.recycler_text);
            timatext = (TextView)itemView.findViewById(R.id.recycler_time_text);
            img = (ImageView)itemView.findViewById(R.id.recycler_img);
        }
    }

    /**
     * 第二种布局
     */
    class TwoHolder extends RecyclerView.ViewHolder {
        public TwoHolder(View itemView) {
            super(itemView);
        }
    }

}
