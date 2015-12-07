package com.example.james.CommonAdapterEx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.james.R;

import java.util.List;

/**
 * Created by Administrator on 2015/12/7.
 */
public class CommonAdapter extends BaseAdapter {
    public Context mContext;
    public List<String> mDatas;


    public CommonAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        if (mDatas != null && mDatas.size() > 0)
            return mDatas.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=ViewHolder.get(mContext,convertView,parent,R.layout.item_adapter,position);
        TextView tv=viewHolder.getView(R.id.tv_item_common_adapter);
        tv.setText(mDatas.get(position)+"哈哈");
        return viewHolder.getConvertView();
    }
}
