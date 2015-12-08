package com.example.james.CommonAdapterEx;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/12/7.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected int mItemLayoutId;


    public CommonAdapter(Context context, List<T> datas,int itemLayoutId) {
        mContext = context;
        mDatas = datas;
        mItemLayoutId=itemLayoutId;
    }

    @Override
    public int getCount() {
        if (mDatas != null)
            return mDatas.size();
        return 0;
    }

    @Override
    public T getItem(int position) {
        if(mDatas!=null)
            return mDatas.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=ViewHolder.get(mContext,convertView,parent,mItemLayoutId,position);
        convert(viewHolder,getItem(position));
       return viewHolder.getConvertView();
    }

   public abstract void convert(ViewHolder viewHolder,T item);
}
