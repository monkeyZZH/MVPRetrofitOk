package com.wpz.mymvpframe.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.model.bean.ArticleDetailsBean;

import java.util.List;

/**
 * Created by wpz on 2017/11/3 0003.
 * 类作用：
 */

public class RelatedArticlesListAdapter extends BaseAdapter {
    private Context context;
    private List<ArticleDetailsBean.DataBean.CorArtilcesBean> mData;

    public RelatedArticlesListAdapter(Context context, List<ArticleDetailsBean.DataBean.CorArtilcesBean> mData) {
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
        ViewHodler hodler;
        if (convertView == null){
            convertView = convertView.inflate(context, R.layout.item_article_details_list, null);
            hodler = new ViewHodler();
            hodler.article_name_text = (TextView) convertView.findViewById(R.id.article_name_text);
            convertView.setTag(hodler);//绑定ViewHolder对象
        }else {
            hodler = (ViewHodler) convertView.getTag();//取出ViewHolder对象
        }
        hodler.article_name_text.setText(mData.get(position).getTitle());
        return convertView;
    }


    class ViewHodler {
        TextView article_name_text;
    }
}
