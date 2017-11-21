package com.wpz.mymvpframe.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.CourseDetailsBean;
import com.wpz.mymvpframe.presenter.CourseDetailsPresenter;
import com.wpz.mymvpframe.view.XXX.NoSkinActivity;
import com.wpz.mymvpframe.view.adapter.CourseDetailsCourseListAdapter;
import com.wpz.mymvpframe.view.adapter.CourseDetailsRecyclerAdapter;
import com.wpz.mymvpframe.view.iview.ICourseDrtailsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpz on 2017/10/18 0018.
 * 类作用：课程详情页
 */

public class CourseDetailsActivity extends BaseActivity<CourseDetailsPresenter> implements ICourseDrtailsView,View.OnClickListener {

    private RecyclerView course_details_img_recycler;
    private com.wpz.mymvpframe.view.bdbkview.MyListView course_list;
    private ImageView title_on_both_left;
    private ImageView title_on_both_right;
    private TextView course_details_big_title;
    private TextView course_details_small_title;
    private TextView course_details_time_text;
    private TextView course_details_difficulty_text;
    private TextView course_details_kcal_text;
    private TextView course_details_duration_text_two;
    private TextView course_details_duration_time_text;
    private TextView course_details_duration_introduction;
    private ImageView course_details_duration_more;
    private TextView course_details_duration_effort;
    private TextView course_details_duration_watch;
    private TextView course_details_action_text;
    private TextView course_details_situation_text;
    private EditText course_details_comment_edit;
    private LinearLayout course_details_all_dynamic;
    private List<CourseDetailsBean.DataBean.CommentsBean> mDate;
    private ImageView course_details_title_img;
    private List<CourseDetailsBean.DataBean.CourseDetailBean.VideoLinksBean> video_links;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        int itemId = bundle.getInt("ItemId");
        mPresenter.getCourseDetailsData(this,itemId);
        TextView title = (TextView) findViewById(R.id.title_on_both_text);
        title.setText("课程详情页");
    }

    @Override
    public void onsuccess(final Object o) {
        final CourseDetailsBean CourseDetailsBean = (CourseDetailsBean) o;
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(Content.BASE_URL + CourseDetailsBean.getData().getCourse_detail().getImg())
                .apply(options)
                .into(course_details_title_img);
        course_details_big_title.setText(CourseDetailsBean.getData().getCourse_detail().getTitle());
        course_details_time_text.setText(CourseDetailsBean.getData().getCourse_detail().getTimelen());
        course_details_difficulty_text.setText(CourseDetailsBean.getData().getCourse_detail().getDifficulty());
        course_details_duration_text_two.setText(CourseDetailsBean.getData().getCourse_detail().getTags_detail().get(0));
        course_details_duration_time_text.setText(CourseDetailsBean.getData().getCourse_detail().getTags_detail().get(1));
        course_details_duration_introduction.setText(CourseDetailsBean.getData().getCourse_detail().getDescription());
        course_details_duration_effort.setText(CourseDetailsBean.getData().getCourse_detail().getPlaying() + "人正在努力");
        course_details_duration_watch.setText(CourseDetailsBean.getData().getCourse_detail().getWatching() + "累计观看");
        course_details_situation_text.setText(CourseDetailsBean.getData().getCourse_detail().getContent());
//        course_details_duration_effort.setText(CourseDetailsBean.getData().getCourse_detail().getTimelen());
        mDate = CourseDetailsBean.getData().getComments();
        //评论列表
        CourseDetailsCourseListAdapter CourseDetailsCourseListAdapter = new CourseDetailsCourseListAdapter(this,mDate);
        course_list.setAdapter(CourseDetailsCourseListAdapter);
        LinearLayoutManager LM = new LinearLayoutManager(this);
        LM.setOrientation(LinearLayoutManager.HORIZONTAL);


        video_links = CourseDetailsBean.getData().getCourse_detail().getVideo_links();

        course_details_img_recycler.setLayoutManager(LM);
        CourseDetailsRecyclerAdapter courseDetailsRecyclerAdapter = new CourseDetailsRecyclerAdapter(this, video_links);
        courseDetailsRecyclerAdapter.setOnItemClickListener(new CourseDetailsRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "第" + position + "个视频被点击了", Toast.LENGTH_SHORT).show();
                //getVideoData(CourseDetailsBean.getData().getCourse_detail().getVideo_links().get(position).getId());

                startActivity(new Intent(CourseDetailsActivity.this, NoSkinActivity.class));

            }
        });
        course_details_img_recycler.setAdapter(courseDetailsRecyclerAdapter);
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new CourseDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_course_details;
    }

    @Override
    protected void initView() {
        //返回
        title_on_both_left = (ImageView) findViewById(R.id.title_on_both_left);
        //分享
        title_on_both_right = (ImageView) findViewById(R.id.title_on_both_right);
        //详情图片
        course_details_title_img = (ImageView) findViewById(R.id.course_details_title_img);
        //课程大标题
        course_details_big_title = (TextView) findViewById(R.id.course_details_big_title);
        //课程小标题
        course_details_small_title = (TextView) findViewById(R.id.course_details_small_title);
        //大标题的时间
        course_details_time_text = (TextView) findViewById(R.id.course_details_time_text);
        //大标题的难度
        course_details_difficulty_text = (TextView) findViewById(R.id.course_details_difficulty_text);
        //大标题的千卡
        course_details_kcal_text = (TextView) findViewById(R.id.course_details_kcal_text);
        //小详情
        course_details_duration_text_two = (TextView) findViewById(R.id.course_details_duration_text_two);
        //小详情时间
        course_details_duration_time_text = (TextView) findViewById(R.id.course_details_duration_time_text);
        //简单介绍
        course_details_duration_introduction = (TextView) findViewById(R.id.course_details_duration_introduction);
        //简单介绍的下拉按钮
        course_details_duration_more = (ImageView) findViewById(R.id.course_details_duration_more);
        //努力人数
        course_details_duration_effort = (TextView) findViewById(R.id.course_details_duration_effort);
        //观看人数
        course_details_duration_watch = (TextView) findViewById(R.id.course_details_duration_watch);
        //几组动作
        course_details_action_text = (TextView) findViewById(R.id.course_details_action_text);
        //动作的图片
        course_details_img_recycler = (RecyclerView) findViewById(R.id.course_details_img_recycler);
        //动作下边的介绍
        course_details_situation_text = (TextView) findViewById(R.id.course_details_situation_text);
        //课程评论
        course_details_comment_edit = (EditText) findViewById(R.id.course_details_comment_edit);
        //评论列表
        course_list = (com.wpz.mymvpframe.view.bdbkview.MyListView) findViewById(R.id.course_list);
        //全部动态的点击
        course_details_all_dynamic = (LinearLayout) findViewById(R.id.course_details_all_dynamic);
        initEvents();
    }

    private void initEvents() {
        title_on_both_left.setOnClickListener(this);//返回
        title_on_both_right.setOnClickListener(this);//分享
        course_details_duration_more.setOnClickListener(this);//更多介绍
        course_details_all_dynamic.setOnClickListener(this);//全部评论
    }

    @Override
    protected void initDatas() {
        List<String> ddd = new ArrayList<>();
        ddd.add("1");
        ddd.add("2");
        ddd.add("1");
        ddd.add("1");
        ddd.add("2");
        ddd.add("1");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_on_both_left:
                finish();
                break;

            case R.id.title_on_both_right:
                Toast.makeText(mContext, "分享，抓紧做", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void getVideoData(Object o) {
    }
}
