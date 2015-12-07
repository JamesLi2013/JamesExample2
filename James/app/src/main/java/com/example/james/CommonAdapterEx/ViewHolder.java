package com.example.james.CommonAdapterEx;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/12/7.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    private ViewHolder(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        this.mViews=new SparseArray<>();
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    /**
     * 得到ViewHolder
     */
    public static ViewHolder get(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        if(convertView==null){
            return new ViewHolder(context,convertView,parent,layoutId,position);
        }else{
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 根据view的Id获取View
     */
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView(){
        return mConvertView;
    }


}
