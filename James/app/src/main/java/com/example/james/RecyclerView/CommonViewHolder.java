package com.example.james.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/1/28.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> mViews;
    public View mConvertView;

    public CommonViewHolder(View itemView) {
        super(itemView);
        this.mViews=new SparseArray<>();
        mConvertView=itemView;
    }
    public CommonViewHolder(View itemView, Context context) {
        super(itemView);
        this.mViews=new SparseArray<>();
        mConvertView=itemView;
        mContext=context;
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
