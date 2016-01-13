package com.example.james.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.james.MyData;
import com.example.james.R;
import com.example.james.others.DividerItemDecoration;
import com.example.james.weixinContact.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * RecyclerView的简单使用,包括SwipeRefreshLayout下拉刷新,简单的上拉加载更多,标题栏背景透明度渐变,瀑布流
 */
public class RecyclerDemoUI extends AppCompatActivity {

    private RecyclerView mRvContent;
    private SwipeRefreshLayout mSrlRefresh;
    private int mLoadState = 1;//记录下拉加载状态值
    private static final int NOT_LOADING = 1;//没有加载
    private static final int LOADINGING = 2;//加载中
    private static final int LOADING_COMPLETE = 3;//加载完成
    private static final int NOT_MORE_DATA = 4;//没有更多数据
    private LinearLayoutManager mLinearLayoutManager;
    private MyAdapter mAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ImageView mIvTitle;//标题栏的背景颜色填充view
    private View mFirstView;//背景颜色渐变的参考View,根据View的可见高度来实时调整mIvTitile的背景透明度
    private int mImgBottom;//mIvTitle右下角的全局坐标Y方向的值
    private Rect mIvTitleRect = new Rect();//mIvTitle的坐标
    private Rect mFirstViewRect = new Rect();//mFirstView的坐标


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
        mSrlRefresh = (SwipeRefreshLayout) findViewById(R.id.srl_swipe_refresh);
        mIvTitle = (ImageView) findViewById(R.id.iv_title);
        mIvTitle.setAlpha(0.0f);
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvContent.setLayoutManager(mLinearLayoutManager);
//        mRvContent.setLayoutManager(new GridLayoutManager(this,3));
        //瀑布流
//        mRvContent.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MyAdapter(this, MyData.getContactData());
        mRvContent.setAdapter(mAdapter);
        mRvContent.setItemAnimator(new DefaultItemAnimator());
//        设置分割线,分割线样式可自定义
        mRvContent.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        final MyLoadMorelistener myLoadMorelistener = new MyLoadMorelistener();

        mRvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.e("lqx", "SCROLL_STATE_IDLE");
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    Log.e("lqx", "SCROLL_STATE_DRAGGING");
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    Log.e("lqx", "SCROLL_STATE_SETTLING");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                Log.e("lqx", "dx:" + dx + ",dy:" + dy);
//                RecyclerView的第一个view可见,在滑动过程中根据参考view的可见高度不同,改变标题栏的背景透明度
                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0 && mFirstView != null) {
/*                    int totalHeight = mFirstView.getHeight();
                    Rect r = new Rect();
                    mFirstView.getGlobalVisibleRect(r);

                    Rect rect = new Rect();
                    mIvTitle.getGlobalVisibleRect(rect);
                    mImgBottom = rect.bottom;

                    int currentHeight = r.bottom - mImgBottom;
                    Log.e("lqx", "totalHeight:" + totalHeight + ",currentHeight:" + currentHeight);
                    mIvTitle.setAlpha(1.0f - (float) currentHeight / totalHeight);*/
                    mFirstView.getGlobalVisibleRect(mFirstViewRect);
                    mIvTitle.getGlobalVisibleRect(mIvTitleRect);
                    mIvTitle.setAlpha(1.0f-(float) (mFirstViewRect.bottom -mIvTitleRect.bottom)/mFirstView.getHeight());
                } else {
                    mIvTitle.setAlpha(1.0f);
                }


                int totalCount = recyclerView.getAdapter().getItemCount() - 1;
                int visiblePositon = mLinearLayoutManager.findLastVisibleItemPosition();
                if (dy > 0 && (visiblePositon == totalCount) && mLoadState == NOT_LOADING) {
                    Log.e("lqx", "正在加载中!!!!");
                    mLoadState = LOADINGING;
                    mAdapter.notifyDataSetChanged();
                    myLoadMorelistener.loadMore();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        RecyclerView.LayoutManager layoutManager = mRvContent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            int lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

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
        private static final int TYPE_VERTICAL = 0;
        private static final int TYPE_HORIZONTAL = 1;

        public MyAdapter() {
        }

        public MyAdapter(Context context, ArrayList<Person> data) {
            mContext = context;
            mData = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HORIZONTAL) {
                View view = View.inflate(mContext, R.layout.item_load_foot, null);
                return new FootViewHolder(view);
            } else if (viewType == TYPE_VERTICAL) {
                View view2 = View.inflate(mContext, R.layout.item_adapter, null);
                return new MyViewHolder(view2);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof MyViewHolder) {
                MyViewHolder viewHolder = (MyViewHolder) holder;
                viewHolder.mTvTitle.setText(mData.get(position).getName());
//            int side=300+ new Random().nextInt(150);
//            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(500,side);
//            viewHolder.mIvHeader.setLayoutParams(layoutParams);
                if (position == 0) {
                    mFirstView = viewHolder.mIvHeader;
                }
                Picasso.with(mContext).load(mData.get(position).getImgUrl()).into(viewHolder.mIvHeader);
                viewHolder.mIvHeader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int visiblePosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                        Toast.makeText(mContext, "点击的位置是:" + position + ",第一个可见的位置:" + visiblePosition, Toast.LENGTH_SHORT).show();
                    }
                });
            } else if (holder instanceof FootViewHolder) {
                FootViewHolder viewHolder = (FootViewHolder) holder;
                viewHolder.mPbLoading.setVisibility(View.GONE);
                if (mLoadState == 1) {
                    viewHolder.mTvTitle.setVisibility(View.GONE);
                } else if (mLoadState == LOADINGING) {
                    viewHolder.mTvTitle.setVisibility(View.VISIBLE);
                    viewHolder.mTvTitle.setText("正在加载");
                    viewHolder.mPbLoading.setVisibility(View.VISIBLE);
                } else if (mLoadState == LOADING_COMPLETE) {
                    viewHolder.mTvTitle.setVisibility(View.GONE);
                    viewHolder.mTvTitle.setText("加载完成");
                } else if (mLoadState == NOT_MORE_DATA) {
                    viewHolder.mTvTitle.setVisibility(View.VISIBLE);
                    viewHolder.mTvTitle.setText("没有更多数据了");
                }
            }

        }

        @Override
        public int getItemCount() {
            if (mData != null)
                return mData.size() + 1;
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (mData != null && position < mData.size()) {
                return TYPE_VERTICAL;
            } else {
                return TYPE_HORIZONTAL;
            }
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvTitle;
        public ImageView mIvHeader;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_item_common_adapter);
            mIvHeader = (ImageView) itemView.findViewById(R.id.iv_item_common_adapter);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvTitle;
        public ProgressBar mPbLoading;

        public FootViewHolder(View itemView) {
            super(itemView);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_item_common_adapter);
            mPbLoading = (ProgressBar) itemView.findViewById(R.id.pb_loading);
        }
    }

    private interface LoadMorelistener {
        void loadMore();

        void loadComplete();
    }

    private class MyLoadMorelistener implements LoadMorelistener {

        @Override
        public void loadMore() {

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(getApplicationContext(),"数据加载完成",Toast.LENGTH_SHORT).show();
//                    loadComplete();
                    mLoadState = NOT_MORE_DATA;
                    mAdapter.notifyDataSetChanged();
                }
            }, 5000);
        }

        @Override
        public void loadComplete() {
            mLoadState = LOADING_COMPLETE;
            mAdapter.notifyDataSetChanged();
        }
    }

}
