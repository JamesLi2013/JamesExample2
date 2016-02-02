package com.example.james.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.james.MyData;
import com.example.james.R;
import com.example.james.weixinContact.Person;

import java.util.ArrayList;

public class CommonRecyclerViewUI extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);
        ArrayList<String> data=new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add(i+"----");
        }
        ArrayList<Person> mData= MyData.getContactData();
        MyRecyclerAdapter mAdapter= new MyRecyclerAdapter<Person>(this, R.layout.item_adapter, mData) {
            @Override
            public void convert(CommonViewHolder viewHolder, Person person) {
                viewHolder.setTextView(R.id.tv_item_common_adapter,person.getName());
                viewHolder.setImageView(R.id.iv_item_common_adapter,person.getImgUrl());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mData.remove(0);
        mAdapter.notifyDataSetChanged();
    }
}
