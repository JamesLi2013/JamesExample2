package com.example.james.others;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.MyData;
import com.example.james.R;
import com.example.james.weixinContact.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerDemoUI extends AppCompatActivity {

    private RecyclerView mRvContent;
    private SwipeRefreshLayout mSrlRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mSrlRefresh = (SwipeRefreshLayout) findViewById(R.id.srl_swipe_refresh);

        mRvContent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        mRvContent.setLayoutManager(new GridLayoutManager(this,3));
        //瀑布流
//        mRvContent.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRvContent.setAdapter(new MyAdapter(this, MyData.getContactData()));
        mRvContent.setItemAnimator(new DefaultItemAnimator());
//        设置分割线,分割线样式可自定义
        mRvContent.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== RecyclerView.SCROLL_STATE_IDLE){
                    Log.e("lqx","SCROLL_STATE_IDLE");
                }else  if(newState== RecyclerView.SCROLL_STATE_DRAGGING){
                    Log.e("lqx","SCROLL_STATE_DRAGGING");
                }else  if(newState== RecyclerView.SCROLL_STATE_SETTLING){
                    Log.e("lqx","SCROLL_STATE_SETTLING");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("lqx","dx:"+dx+",dy:"+dy);
            }
        });

        //设置转动的线条颜色变化
        mSrlRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light);
        mSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "已经刷新了", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSrlRefresh.setRefreshing(false);
                    }
                }, 2000);

            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context mContext;
        private ArrayList<Person> mData;
        private static final int TYPE_VERTICAL=0;
        private static final int TYPE_HORIZONTAL=1;

        public MyAdapter() {
        }
        public MyAdapter(Context context, ArrayList<Person> data) {
            mContext=context;
            mData=data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==TYPE_HORIZONTAL){
                View view=View.inflate(mContext,R.layout.item_adapter,null);
                return  new HorizontalViewHolder(view);
            }else if(viewType==TYPE_VERTICAL){
                View view2=View.inflate(mContext,R.layout.item_adapter,null);
                return new MyViewHolder(view2);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if(holder instanceof MyViewHolder){
                MyViewHolder viewHolder=(MyViewHolder)holder;
                viewHolder.mTvTitle.setText(mData.get(position).getName());
//            int side=300+ new Random().nextInt(150);
//            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(500,side);
//            viewHolder.mIvHeader.setLayoutParams(layoutParams);
                Picasso.with(mContext).load(mData.get(position).getImgUrl()).into(viewHolder.mIvHeader);
                viewHolder.mIvHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"点击的位置是:"+position,Toast.LENGTH_SHORT).show();
                    }
                });
            }else if(holder instanceof HorizontalViewHolder){
                HorizontalViewHolder viewHolder=(HorizontalViewHolder)holder;
                viewHolder.mIvHeader.setVisibility(View.GONE);
                viewHolder.mTvTitle.setText("哈哈哈哈");
            }

        }

        @Override
        public int getItemCount() {
            if(mData!=null)
                return mData.size()+10;
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if(mData!=null&&position<mData.size()){
                return TYPE_VERTICAL;
            }else{
                return TYPE_HORIZONTAL;
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvTitle;
        public ImageView mIvHeader;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle= (TextView) itemView.findViewById(R.id.tv_item_common_adapter);
            mIvHeader= (ImageView) itemView.findViewById(R.id.iv_item_common_adapter);
        }
    }
    class HorizontalViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvTitle;
        public ImageView mIvHeader;
        public HorizontalViewHolder(View itemView) {
            super(itemView);
            mTvTitle= (TextView) itemView.findViewById(R.id.tv_item_common_adapter);
            mIvHeader= (ImageView) itemView.findViewById(R.id.iv_item_common_adapter);
        }
    }


}
