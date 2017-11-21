package com.wpz.mymvpframe.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
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
import com.wpz.mymvpframe.base.BaseActivity;
import com.wpz.mymvpframe.model.bean.ArticleDetailsBean;
import com.wpz.mymvpframe.presenter.ArticleDetailsPresenter;
import com.wpz.mymvpframe.view.adapter.RelatedArticlesListAdapter;
import com.wpz.mymvpframe.view.bdbkview.MyListView;
import com.wpz.mymvpframe.view.iview.IArticleDetailsView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wpz on 2017/11/1 0001.
 * 类作用：文章详情
 */

public class ArticleDetailsActivity extends BaseActivity<ArticleDetailsPresenter> implements IArticleDetailsView, View.OnClickListener {

    private TextView title_left_text;
    private WebView web;
    private TextView viewById;
    private ImageView announcer_img;
    private TextView announcer_name_text;
    private TextView attention_text;
    private ImageView article_details_title_img;
    private TextView article_details_release_time;
    private TextView article_details_release_writer;
    private TextView recommended_direct;
    private TextView recommend_one;
    private TextView recommend_two;
    private TextView recommend_three;
    private RelativeLayout ad_layout;
    private TextView ad_title;
    private ImageView ad_return;
    private ImageView ad_img_one;
    private ImageView ad_img_two;
    private ImageView ad_img_three;
    private TextView ad_description;
    private MyListView related_articles;
    private TextView article_like_num;
    private MyListView article_course_list;
    private TextView article_name_text;
    private List<ArticleDetailsBean.DataBean.CorArtilcesBean> cor_artilces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        int itemId = bundle.getInt("ItemId");
        Toast.makeText(mContext, itemId + "", Toast.LENGTH_SHORT).show();
        title_left_text = (TextView) findViewById(R.id.title_left_text);
        title_left_text.setText("文章");
        mPresenter.getArticleDetailsData(this, itemId);
    }

    @Override
    public void onsuccess(Object o) {
        ArticleDetailsBean articleDetailsBean = (ArticleDetailsBean) o;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load(Content.BASE_URL + articleDetailsBean.getData().getArticle_detail().getCover())
                .apply(options)
                .into(announcer_img);
        web.loadDataWithBaseURL("http://47.104.2.125", getNewContent(articleDetailsBean.getData().getArticle_detail().getContent()), "text/html", "UTF-8", null);
        article_name_text.setText(articleDetailsBean.getData().getArticle_detail().getTitle());
        String author = articleDetailsBean.getData().getArticle_detail().getAuthor();
        author = author.substring(author.indexOf(" "), author.length());
        announcer_name_text.setText(author);

        Glide.with(this)
                .load(Content.BASE_URL + articleDetailsBean.getData().getArticle_detail().getCover())
                .apply(options)
                .into(article_details_title_img);
        article_details_release_time.setText(articleDetailsBean.getData().getArticle_detail().getCreate_time());
        article_details_release_writer.setText(articleDetailsBean.getData().getArticle_detail().getAuthor());
        String keywords = articleDetailsBean.getData().getArticle_detail().getKeywords();
        String str2 = keywords.replace(" ", "");//去掉所用空格
        List<String> list = Arrays.asList(str2.split(","));
        recommend_one.setText(list.get(0));
        recommend_two.setText(list.get(1));
        recommend_three.setText(list.get(2));
        ad_title.setText(articleDetailsBean.getData().getAdvert().getName());
        Glide.with(this)
                .load(Content.BASE_URL + articleDetailsBean.getData().getAdvert().getContent().get(0))
                .apply(options)
                .into(ad_img_one);
        Glide.with(this)
                .load(Content.BASE_URL + articleDetailsBean.getData().getAdvert().getContent().get(1))
                .apply(options)
                .into(ad_img_two);
        Glide.with(this)
                .load(Content.BASE_URL + articleDetailsBean.getData().getAdvert().getContent().get(2))
                .apply(options)
                .into(ad_img_three);
        ad_description.setText(articleDetailsBean.getData().getAdvert().getDescription());
        cor_artilces = articleDetailsBean.getData().getCor_artilces();
        RelatedArticlesListAdapter relatedArticlesListAdapter = new RelatedArticlesListAdapter(this,cor_artilces);
        related_articles.setAdapter(relatedArticlesListAdapter);
    }

    @Override
    public void onError(Object o) {

    }

    @Override
    protected void createPresenter() {
        mPresenter = new ArticleDetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    protected void initView() {
        //文章内容
        web = (WebView) findViewById(R.id.web);
        //文章大标题
        article_name_text = (TextView) findViewById(R.id.article_name_text);
        //作者头像
        announcer_img = (ImageView) findViewById(R.id.announcer_img);
        //作者名字
        announcer_name_text = (TextView) findViewById(R.id.announcer_name_text);
        //关注按钮
        attention_text = (TextView) findViewById(R.id.attention_text);
        //文章大图片
        article_details_title_img = (ImageView) findViewById(R.id.article_details_title_img);
        //文章时间
        article_details_release_time = (TextView) findViewById(R.id.article_details_release_time);
        //原创人
        article_details_release_writer = (TextView) findViewById(R.id.article_details_release_writer);
        //推荐直达
        recommended_direct = (TextView) findViewById(R.id.recommended_direct);
        //第一个标签
        recommend_one = (TextView) findViewById(R.id.recommend_one);
        //第二个标签
        recommend_two = (TextView) findViewById(R.id.recommend_two);
        //第三个标签
        recommend_three = (TextView) findViewById(R.id.recommend_three);
        //广告页面
        ad_layout = (RelativeLayout) findViewById(R.id.ad_layout);
        //广告标题
        ad_title = (TextView) findViewById(R.id.ad_title);
        //关闭广告
        ad_return = (ImageView) findViewById(R.id.ad_return);
        //广告图片1
        ad_img_one = (ImageView) findViewById(R.id.ad_img_one);
        //广告图片2
        ad_img_two = (ImageView) findViewById(R.id.ad_img_two);
        //广告图片3
        ad_img_three = (ImageView) findViewById(R.id.ad_img_three);
        //广告小标语
        ad_description = (TextView) findViewById(R.id.ad_description);
        //相关文章的列表
        related_articles = (MyListView) findViewById(R.id.related_articles);
        //点赞次数
        article_like_num = (TextView) findViewById(R.id.article_like_num);
        //评论列表
        article_course_list = (MyListView) findViewById(R.id.article_course_list);
//        ArticleDetailsCourseListAdapter
        initEvents();
    }

    private void initEvents() {
        ad_return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ad_return:
                ad_layout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void initDatas() {

    }

    private String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "50%").attr("height", "auto");
        }

        return doc.toString();
    }
}
