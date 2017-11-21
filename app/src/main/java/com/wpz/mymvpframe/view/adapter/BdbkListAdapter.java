package com.wpz.mymvpframe.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by wpz on 2017/10/24 0024.
 * 类作用：
 */

public class BdbkListAdapter extends BaseAdapter {
    private Context context;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    class ViewHolder{

    }
}
