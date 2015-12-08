package com.example.james.CommonAdapterEx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.james.R;
import com.example.james.weixinContact.Person;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterExUI extends AppCompatActivity {
    List<Person> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_ex);
        initData();
        ListView listView = (ListView) findViewById(R.id.lv_adapter_ex);
        //简单的仿写
       /* listView.setAdapter(new CommonAdapter<Person>(this, mDatas, R.layout.item_adapter) {
            @Override
            public void convert(ViewHolder viewHolder, Person item) {
                viewHolder.setTextView(R.id.tv_item_common_adapter,"hahaha"+item.getName());
                viewHolder.setImageView(R.id.iv_item_common_adapter,item.getImgUrl());
            }
        });*/
        //开源包实现同样功能
        listView.setAdapter(new QuickAdapter<Person>(this,R.layout.item_adapter,mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, Person item) {
                helper.setText(R.id.tv_item_common_adapter,"BaseAdapter"+item.getName());
                helper.setImageUrl(R.id.iv_item_common_adapter,item.getImgUrl());
            }
        });
    }

    private void initData() {
        mDatas.add(new Person("张三","http://p0.so.qhimg.com/bdr/_240_/t01f381d5358c9c4dc0.jpg"));
        mDatas.add(new Person("李四","http://p3.so.qhimg.com/bdr/_240_/t0102c941378c25b8c8.jpg"));
        mDatas.add(new Person("王五","http://p2.so.qhimg.com/bdr/_240_/t014e5a26b8df21c737.jpg"));
        mDatas.add(new Person("赵六","http://p2.so.qhimg.com/bdr/_240_/t01edb6fa6bd2c70f4c.jpg"));
        mDatas.add(new Person("田七","http://p3.so.qhimg.com/bdr/_240_/t01fdedbb9681277aed.jpg"));
        mDatas.add(new Person("王八","http://p1.so.qhimg.com/bdr/_240_/t016b6f7623e353ac06.jpg"));
        mDatas.add(new Person("aaa","http://p4.so.qhimg.com/bdr/_240_/t01c2215680fafc881e.jpg"));
        mDatas.add(new Person("james","http://p2.so.qhimg.com/bdr/_240_/t018d192e2eb74749fc.jpg"));
        mDatas.add(new Person("1233","http://p0.so.qhimg.com/bdr/_240_/t011dd52f666931c02f.jpg"));
        mDatas.add(new Person("tinsa","http://p2.so.qhimg.com/bdr/_240_/t01e618fc6c0dd9cef7.jpg"));
        mDatas.add(new Person("pony","http://p0.so.qhimg.com/bdr/_240_/t01553564eff73343da.jpg"));
        mDatas.add(new Person("nnnn","http://p1.so.qhimg.com/bdr/_240_/t01f09d5bee4ec109f6.jpg"));
        mDatas.add(new Person("ddd","http://p2.so.qhimg.com/bdr/_240_/t0113e48314cd66e722.jpg"));
        mDatas.add(new Person("eee","http://p0.so.qhimg.com/bdr/_240_/t01f997a6beac5d62ca.jpg"));
        mDatas.add(new Person("hhhh","http://p2.so.qhimg.com/bdr/_240_/t017cbd220b63f4575c.jpg"));
        mDatas.add(new Person("iiii","http://p3.so.qhimg.com/bdr/_240_/t01128d191120961a13.jpg"));
        mDatas.add(new Person("jjjj","http://p4.so.qhimg.com/bdr/_240_/t01fed6ce905d74d904.jpg"));
        mDatas.add(new Person("白痴","http://p1.so.qhimg.com/bdr/_240_/t01f83004f1f070cbe6.jpg"));
        mDatas.add(new Person("傻瓜","http://p3.so.qhimg.com/bdr/_240_/t015ba23f2ca3eb6423.jpg"));
        mDatas.add(new Person("弱智","http://p1.so.qhimg.com/bdr/_240_/t01e7d3d622c5c8d12b.jpg"));
        mDatas.add(new Person("高手","http://p4.so.qhimg.com/bdr/_240_/t01d1e4f921245fc724.jpg"));
        mDatas.add(new Person("超神","http://p0.so.qhimg.com/bdr/_240_/t0174cb7a4d82ea439a.jpg"));
    }
}
