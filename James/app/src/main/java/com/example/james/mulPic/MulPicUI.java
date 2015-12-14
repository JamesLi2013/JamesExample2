package com.example.james.mulPic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.james.R;

import java.util.ArrayList;

public class MulPicUI extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<MulPicBean> mMulPicBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mul_pic);
        mListView = (ListView) findViewById(R.id.lv_mul_pic);
        mMulPicBeans=new ArrayList<>();
        initData();
        MulPicAdapter mMulPicAdapter=new MulPicAdapter(this,R.layout.item_mul_pic,mMulPicBeans);
        mListView.setAdapter(mMulPicAdapter);

    }

    private void initData() {
        MulPicBean mulPicbean=new MulPicBean();
        mulPicbean.setTitle("1111111111111");
        ArrayList<String> pics=new ArrayList<>();
        pics.add("http://p3.so.qhimg.com/bdr/_240_/t0102c941378c25b8c8.jpg");
        pics.add("http://p2.so.qhimg.com/bdr/_240_/t014e5a26b8df21c737.jpg");
        pics.add("http://p2.so.qhimg.com/bdr/_240_/t01edb6fa6bd2c70f4c.jpg");
        mulPicbean.setPics(pics);
        mMulPicBeans.add(mulPicbean);

        MulPicBean mulPicbean2=new MulPicBean();
        ArrayList<String> pics2=new ArrayList<>();
        mulPicbean2.setTitle("22222222222222222222");
        pics2.add("http://p3.so.qhimg.com/bdr/_240_/t01fdedbb9681277aed.jpg");
        pics2.add("http://p1.so.qhimg.com/bdr/_240_/t016b6f7623e353ac06.jpg");
        pics2.add("http://p4.so.qhimg.com/bdr/_240_/t01c2215680fafc881e.jpg");
        mulPicbean2.setPics(pics2);
        mMulPicBeans.add(mulPicbean2);

        MulPicBean mulPicbean3=new MulPicBean();
        ArrayList<String> pics3=new ArrayList<>();
        mulPicbean3.setTitle("33333333333333333333");
        pics3.add("http://p2.so.qhimg.com/bdr/_240_/t018d192e2eb74749fc.jpg");
        mulPicbean3.setPics(pics3);
        mMulPicBeans.add(mulPicbean3);
    }
}
