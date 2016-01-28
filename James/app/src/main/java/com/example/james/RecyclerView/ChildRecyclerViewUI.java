package com.example.james.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.james.R;
import com.example.james.others.DividerItemDecoration;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChildRecyclerViewUI extends AppCompatActivity {

    private RecyclerView mRvContent;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_recycler_view);
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRvContent.setItemAnimator(new DefaultItemAnimator());
        mDatas=new ArrayList<>();
        for (int i=0;i<20;i++){
            mDatas.add(i+"-------");
        }
        mRvContent.setAdapter(new MyAdapter(this,mDatas));
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        public static final int TYPE_FIRST=1;
        public MyAdapter(Activity activity, List<String> mDatas) {

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==TYPE_FIRST){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first,parent,false);
                return new MyViewHolder(view);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyViewHolder){

            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            return TYPE_FIRST;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private final GridView mGvFirst;

        public MyViewHolder(View itemView) {
            super(itemView);
            mGvFirst = (GridView) itemView.findViewById(R.id.gv_first);
            mGvFirst.setAdapter(new QuickAdapter<String>(ChildRecyclerViewUI.this,R.layout.item_adapter,mDatas) {
                @Override
                protected void convert(BaseAdapterHelper helper, String item) {
                    helper.setText(R.id.tv_item_common_adapter,"BaseAdapter"+item);
                }
            });
        }
    }

}
