package com.wpz.mymvpframe.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.model.bean.CourseDetailsBean;

import java.util.List;

/**
 * Created by wpz on 2017/10/30 0030.
 * 类作用：
 */

public class CourseDetailsCourseListAdapter extends BaseAdapter {
    private Context context;
    private List<CourseDetailsBean.DataBean.CommentsBean> mData;

    public CourseDetailsCourseListAdapter(Context context, List<CourseDetailsBean.DataBean.CommentsBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CourseDetailsCourseListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_course_comment_list, null);
            holder = new CourseDetailsCourseListAdapter.ViewHolder();
            holder.comment_img = (ImageView) convertView.findViewById(R.id.comment_img);
            holder.user_name_text = (TextView) convertView.findViewById(R.id.user_name_text);
            holder.user_name_mood = (ImageView) convertView.findViewById(R.id.user_name_mood);
            holder.user_time_text = (TextView) convertView.findViewById(R.id.user_time_text);
            holder.user_content_text = (TextView) convertView.findViewById(R.id.user_content_text);
            holder.love_img = (ImageView) convertView.findViewById(R.id.love_img);
            holder.love_quantity_text = (TextView) convertView.findViewById(R.id.love_quantity_text);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (CourseDetailsCourseListAdapter.ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        holder.user_name_text.setText(mData.get(position).getNickname());
        holder.user_time_text.setText(mData.get(position).getCreate_time() + "");
        holder.user_content_text.setText(mData.get(position).getContent());
        return convertView;
    }


    class ViewHolder {
        ImageView comment_img;
        TextView user_name_text;
        ImageView user_name_mood;
        TextView user_time_text;
        TextView user_content_text;
        ImageView love_img;
        TextView love_quantity_text;
    }
}
