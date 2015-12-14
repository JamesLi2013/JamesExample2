package com.example.james;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.james.weixinContact.Person;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**下拉刷新的控件*/
public class SwipeRefreshDemoUI extends AppCompatActivity {

    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<Person> mDatas = new ArrayList<>();
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);
        mListView = (ListView) findViewById(R.id.lv_swipe_refresh);
        //开源包实现同样功能
        mDatas.add(new Person("张三", "http://p0.so.qhimg.com/bdr/_240_/t01f381d5358c9c4dc0.jpg"));
        mDatas.add(new Person("李四", "http://p3.so.qhimg.com/bdr/_240_/t0102c941378c25b8c8.jpg"));
        mDatas.add(new Person("王五", "http://p2.so.qhimg.com/bdr/_240_/t014e5a26b8df21c737.jpg"));
        mAdapter = new MyAdapter(this, R.layout.item_adapter, mDatas);
        mListView.setAdapter(mAdapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_swipe_refresh);
        //设置转动的线条颜色变化
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getApplicationContext(), "已经刷新了", Toast.LENGTH_SHORT).show();
                mAdapter.add(new Person("新增", "http://p0.so.qhimg.com/bdr/_240_/t011dd52f666931c02f.jpg"));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }
        });
    }

    class MyAdapter extends QuickAdapter<Person> {


        public MyAdapter(Context context, int layoutResId, List<Person> data) {
            super(context, layoutResId, data);
        }

        @Override
        protected void convert(BaseAdapterHelper helper, Person item) {
            helper.setText(R.id.tv_item_common_adapter, item.getName());
            helper.setImageUrl(R.id.iv_item_common_adapter, item.getImgUrl());

        }
    }
}
