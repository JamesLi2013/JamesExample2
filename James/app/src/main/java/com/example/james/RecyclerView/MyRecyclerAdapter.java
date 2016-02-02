package com.example.james.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/4.
 */
public abstract class MyRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<T> mData;
    private int mLayoutResourse;

    public MyRecyclerAdapter(Context context, int mLayoutResourse, ArrayList<T> data) {
        mContext = context;
        mData = data;
        this.mLayoutResourse = mLayoutResourse;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutResourse, parent, false);
        return new CommonViewHolder(view,mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonViewHolder) {
            convert((CommonViewHolder) holder, mData.get(position));
        }
    }

    public abstract void convert(CommonViewHolder viewHolder, T t);

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }
}
