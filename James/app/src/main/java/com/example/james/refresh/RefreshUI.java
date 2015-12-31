package com.example.james.refresh;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.R;

import java.util.ArrayList;

public class RefreshUI extends AppCompatActivity {
    private PtrClassicFrameLayout mPtrFrame;
    private ListView mListView;
    private ArrayList<String> mDataLists;
    private TextView mTvRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mPtrFrame = (PtrClassicFrameLayout)findViewById(R.id.store_house_ptr_frame);
        mListView = (ListView) findViewById(R.id.lv_content);
//        findViewById(R.id.bt_click).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(RefreshUI.this,PtrDemoHomeActivity.class);
//                startActivity(intent);
//            }
//        });
        mDataLists=new ArrayList<>();
        for (int i=0;i<20;i++){
            mDataLists.add(i+"xxxxxxxxxxxxxxxxxx");
        }
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mDataLists.size();
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
                TextView tv=new TextView(getApplicationContext());
                tv.setHeight(50);
                tv.setTextColor(Color.BLACK);
                tv.setText(mDataLists.get(position));
                return tv;
            }
        });
//        // header
//        final StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
//        header.initWithString("xxxxx");

//        final RentalsSunHeaderView header = new RentalsSunHeaderView(this);
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
//        header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
//        header.setUp(mPtrFrame);
//
//        mPtrFrame.setHeaderView(header);
//        mPtrFrame.setHeaderView(new PtrClassicDefaultHeader(this));
//        mPtrFrame.setPullToRefresh(true);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
//        mPtrFrame.setHeaderView(new PtrClassicDefaultHeader(this));
      /*  View view=View.inflate(getApplicationContext(),R.layout.item_head_refresh,null);
        mTvRefresh = (TextView) view.findViewById(R.id.tv_refresh_text);
        mTvRefresh.setTextColor(Color.BLACK);
        mPtrFrame.setHeaderView(view);
        mPtrFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mTvRefresh.setText("onUIReset");
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
                mTvRefresh.setText("onUIRefreshPrepare");
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                mTvRefresh.setText("onUIRefreshBegin");
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                mTvRefresh.setText("onUIRefreshComplete");
            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
//                mTvRefresh.setText("onUIPositionChange");
            }


        });*/
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                Toast.makeText(SecondActivity.this,"刷新了!!!!!!!!!",Toast.LENGTH_SHORT).show();
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
//                Toast.makeText(SecondActivity.this,"检查刷新",Toast.LENGTH_SHORT).show();
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }
}
