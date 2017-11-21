package com.wpz.mymvpframe.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.model.bean.MainBean;
import com.wpz.mymvpframe.view.activity.ArticleDetailsActivity;
import com.wpz.mymvpframe.view.activity.CourseDetailsActivity;

import java.util.List;

/**
 * Created by wpz on 2017/10/27 0027.
 * 类作用：
 */

public class TrainingExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private TextView tv_group3;
    private RelativeLayout expand_video_one;
    private RelativeLayout expand_video_two;
    private RelativeLayout expand_article_one;
    private List<MainBean.DataBean.CourseBean> mData;
    private TextView expand_video_one_name_text;
    private ImageView expand_video_one_lock_img;
    private ImageView expand_video_one_img;
    private TextView expand_video_one_time_text;
    private TextView expand_video_one_energy_text;
    private ImageView expand_video_two_img;
    private TextView expand_video_two_name_text;
    private TextView expand_video_two_time_text;
    private TextView expand_video_two_energy_text;
    private ImageView expand_video_two_lock_img;
    private ImageView expand_article_one_img;
    private TextView expand_article_one_name_text;
    private TextView expand_article_one_provide;

    public TrainingExpandableListViewAdapter(Context context, List<MainBean.DataBean.CourseBean> mData) {
        this.context = context;
        this.mData = mData;
    }

//    private String[] groups = {"第一周", "第二周", "第三周"};
//    private String[][] childs={{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"},{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"},{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}};


    //返回一级列表的个数
    @Override
    public int getGroupCount() {
        return mData.size();
    }

    //返回每个二级列表的个数
    @Override
    public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
        Log.d("smyhvae", "-->" + groupPosition);
        return mData.get(groupPosition).getList().size();
    }

    //返回一级列表的单个item（返回的是对象）
    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    //返回二级列表中的单个item（返回的是对象）
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getList().get(childPosition);  //不要误写成groups[groupPosition][childPosition]
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //每个item的id是否是固定 一般为true
    @Override
    public boolean hasStableIds() {
        return true;
    }

    //【重要】填充一级列表
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_training_bdbk_group, null);
        }
        TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group_text1);
        TextView tv_group2 = (TextView) convertView.findViewById(R.id.tv_group_text2);
        tv_group3 = (TextView) convertView.findViewById(R.id.tv_group_text3);
        tv_group2.setText("训练课程还未解锁，好身材在向你招手，努力吧！");
        tv_group3.setText("展开");
        if (isExpanded) {
            tv_group3.setText("收起");
        } else {
            tv_group3.setText("展开");
        }
        return convertView;
    }

    //【重要】填充二级列表
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_training_babk_item, null);
        }
        expand_video_one = (RelativeLayout) convertView.findViewById(R.id.expand_video_one);
        //第一个课程标题
        expand_video_one_name_text = (TextView) convertView.findViewById(R.id.expand_video_one_name_text);
        //第一个课程锁
        expand_video_one_lock_img = (ImageView) convertView.findViewById(R.id.expand_video_one_lock_img);
        //第一个课程图片
        expand_video_one_img = (ImageView) convertView.findViewById(R.id.expand_video_one_img);
        //第一个课程时间
        expand_video_one_time_text = (TextView) convertView.findViewById(R.id.expand_video_one_time_text);
        //第一个课程能量
        expand_video_one_energy_text = (TextView) convertView.findViewById(R.id.expand_video_one_energy_text);
        //第二个课程
        expand_video_two = (RelativeLayout) convertView.findViewById(R.id.expand_video_two);
        //第二个课程图片
        expand_video_two_img = (ImageView) convertView.findViewById(R.id.expand_video_two_img);
        //第二个课程标题
        expand_video_two_name_text = (TextView) convertView.findViewById(R.id.expand_video_two_name_text);
        //第二个课程时间
        expand_video_two_time_text = (TextView) convertView.findViewById(R.id.expand_video_two_time_text);
        //第二个课程能量
        expand_video_two_energy_text = (TextView) convertView.findViewById(R.id.expand_video_two_energy_text);
        //第二个课程锁
        expand_video_two_lock_img = (ImageView) convertView.findViewById(R.id.expand_video_two_lock_img);
        //文章
        expand_article_one = (RelativeLayout) convertView.findViewById(R.id.expand_article_one);
        //文章图片
        expand_article_one_img = (ImageView) convertView.findViewById(R.id.expand_article_one_img);
        //文章标题
        expand_article_one_name_text = (TextView) convertView.findViewById(R.id.expand_article_one_name_text);
        //文章出处
        expand_article_one_provide = (TextView) convertView.findViewById(R.id.expand_article_one_provide);
        //每天的标题
        TextView tv_child = (TextView) convertView.findViewById(R.id.expand_title);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        //第一个课程标题
        expand_video_one_name_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getTitle());
        //第一个课程锁
//        expand_video_one_lock_img.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getTitle());
        //第一个课程图片
        Glide.with(context)
                .load(Content.BASE_URL + mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getImg())
                .apply(options)
                .into(expand_video_one_img);
        //第一个课程时间
        expand_video_one_time_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getTags().get(0));
        //第一个课程能量
        expand_video_one_energy_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getTags().get(1));
        //第二个课程图片
        Glide.with(context)
                .load(Content.BASE_URL + mData.get(groupPosition).getList().get(childPosition).getCourses().get(1).getImg())
                .apply(options)
                .into(expand_video_two_img);
        //第二个课程标题
        expand_video_two_name_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(1).getTitle());
        //第二个课程时间
        expand_video_two_time_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(1).getTags().get(0));
        //第二个课程能量
        expand_video_two_energy_text.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(1).getTags().get(1));
        //第二个课程锁
//        expand_video_two_lock_imgt.setText(mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getTitle());
        //文章图片
        Glide.with(context)
                .load(Content.BASE_URL + mData.get(groupPosition).getList().get(childPosition).getArticle().getImg())
                .apply(options)
                .into(expand_article_one_img);
        //文章标题
        expand_article_one_name_text.setText(mData.get(groupPosition).getList().get(childPosition).getArticle().getTitle());
        //文章出处
        expand_article_one_provide.setText(mData.get(groupPosition).getList().get(childPosition).getArticle().getDescription());


        expand_video_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ItemId", mData.get(groupPosition).getList().get(childPosition).getCourses().get(0).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
                Toast.makeText(context, "点击了第一天的课程", Toast.LENGTH_SHORT).show();
            }
        });
        expand_video_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ItemId", mData.get(groupPosition).getList().get(childPosition).getCourses().get(1).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
                Toast.makeText(context, "点击了第二天的课程", Toast.LENGTH_SHORT).show();
            }
        });
        expand_article_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticleDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ItemId", mData.get(groupPosition).getList().get(childPosition).getArticle().getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
                Toast.makeText(context, "点击了文章", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    //二级列表中的item是否能够被选中？可以改为true
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
