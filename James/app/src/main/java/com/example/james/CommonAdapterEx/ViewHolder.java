package com.example.james.CommonAdapterEx;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2015/12/7.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context,View convertView,ViewGroup parent,int layoutId,int position){
        mContext=context;
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

    /**得到复用的View*/
    public View getConvertView(){
        return mConvertView;
    }

    public void setTextView(int itemId,String content){
        TextView tv=getView(itemId);
        tv.setText(content);
    }

    public void setImageView(int itemId,int resId){
        ImageView iv=getView(itemId);
        iv.setImageResource(resId);
    }

    public void setImageView(int itemId, Bitmap bitmap){
        ImageView iv=getView(itemId);
        iv.setImageBitmap(bitmap);
    }

    public void setImageView(int itemId,String url){
        ImageView iv=getView(itemId);
        Picasso.with(mContext).load(url).into(iv);
    }

    public void setCheckBox(int itemId,boolean isCheck){
        CheckBox cb=getView(itemId);
        cb.setChecked(isCheck);
    }


}
