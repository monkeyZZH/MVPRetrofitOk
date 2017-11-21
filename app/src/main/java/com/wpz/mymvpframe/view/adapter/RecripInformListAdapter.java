package com.wpz.mymvpframe.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wpz.mymvpframe.R;
import com.wpz.mymvpframe.model.bean.ReceiptInformationBean;
import com.wpz.mymvpframe.view.activity.ModifyActivity;
import com.wpz.mymvpframe.view.bdbkview.SideslipListView;

import java.util.List;

/**
 * Created by wpz on 2017/10/20 0020.
 * 类作用：我的收货地址列表
 */

public class RecripInformListAdapter extends BaseAdapter {
    private Context context;
    private List<ReceiptInformationBean.DataBean> mDatas;
    private int clickPosition = -1;
    private SideslipListView sideslipListView;

    public RecripInformListAdapter(Context context, List<ReceiptInformationBean.DataBean> mDatas, SideslipListView sideslipListView) {
        this.sideslipListView = sideslipListView;
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = convertView.inflate(context, R.layout.item_receipt_information, null);
            holder = new ViewHolder();
            holder.receipt_information_address = (TextView) convertView.findViewById(R.id.receipt_information_address);
            holder.receipt_information_name = (TextView) convertView.findViewById(R.id.receipt_information_name);
            holder.receipt_information_phono = (TextView) convertView.findViewById(R.id.receipt_information_phono);
            holder.receipt_information_modify_img = (ImageView) convertView.findViewById(R.id.receipt_information_modify_img);
            holder.checkBox = (RadioButton) convertView.findViewById(R.id.receipt_information_check);
            holder.txtv_delete = (TextView) convertView.findViewById(R.id.txtv_delete);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        final RadioButton radio = (RadioButton) convertView.findViewById(R.id.receipt_information_check);
        holder.checkBox = radio;

        holder.receipt_information_address.setText(mDatas.get(position).getProvince() + mDatas.get(position).getCity() + mDatas.get(position).getCounty() + mDatas.get(position).getDetail());
        holder.receipt_information_name.setText(mDatas.get(position).getUname());
        holder.receipt_information_phono.setText(mDatas.get(position).getUmobile());
        final int pos = position;
        holder.txtv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.mipmap.ic_launcher);
                //设置标题
                builder.setTitle("删除条目");
                //设置内容
                builder.setMessage("确定要删除吗？");
                //确定
                /**
                 * 1.当前按钮的内容
                 * 2.添加监听
                 */
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //点击时所响应的事件
                        //关闭
                        Toast.makeText(context, mDatas.get(pos) + "被删除了",
                                Toast.LENGTH_SHORT).show();
                        mDatas.remove(pos);
                        notifyDataSetChanged();
                        sideslipListView.turnNormal();
                    }
                });
                //取消
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //点击时所响应的事件
                    }
                });
                builder.create();
                //展示
                builder.show();

            }
        });
        holder.receipt_information_modify_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "要去修改了", Toast.LENGTH_SHORT).show();
                int ItemId = mDatas.get(position).getId();
                String uname = mDatas.get(position).getUname();
                String umobile = mDatas.get(position).getUmobile();
                String province = mDatas.get(position).getProvince();
                String city = mDatas.get(position).getCity();
                String county = mDatas.get(position).getCounty();
                String detail = mDatas.get(position).getDetail();
                int is_default = mDatas.get(position).getIs_default();
                Intent intent = new Intent(context, ModifyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ItemId", ItemId);
                bundle.putString("uname", uname);
                bundle.putString("umobile", umobile);
                bundle.putString("province", province);
                bundle.putString("city", city);
                bundle.putString("county", county);
                bundle.putString("detail", detail);
                bundle.putInt("is_default", is_default);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        if (clickPosition == position) {
            holder.checkBox.setChecked(true);

        } else {
            holder.checkBox.setChecked(false);

        }
        return convertView;
    }


    class ViewHolder {
        public TextView receipt_information_address;
        public TextView receipt_information_name;
        public TextView receipt_information_phono;
        public ImageView receipt_information_modify_img;
        public TextView txtv_delete;
        public RadioButton checkBox;
    }
}
