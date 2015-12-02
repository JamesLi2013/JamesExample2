package com.example.james.weixinContact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.james.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 仿微信联系人列表,快速选取人名
 */
public class WeiXinContactUi extends AppCompatActivity {

    private ListView lv_contact;
    private SideBar sb_letter;
    private ContactAdapter mAdapter;
    private ArrayList<Person> mContactList=new ArrayList<>();
    private TextView tv_show_letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_contact_ui);

        initView();
        initData();
    }

    private void initData() {
        mContactList.add(new Person("张三"));
        mContactList.add(new Person("李四"));
        mContactList.add(new Person("王五"));
        mContactList.add(new Person("赵六"));
        mContactList.add(new Person("田七"));
        mContactList.add(new Person("王八"));
        mContactList.add(new Person("aaa"));
        mContactList.add(new Person("james"));
        mContactList.add(new Person("123341234"));
        mContactList.add(new Person("tinsa"));
        mContactList.add(new Person(" pony"));
        mContactList.add(new Person("n张三"));
        mContactList.add(new Person("ddd"));
        mContactList.add(new Person("eee"));
        mContactList.add(new Person("hhhh"));
        mContactList.add(new Person("iiii"));
        mContactList.add(new Person("jjj王八"));
        mContactList.add(new Person("白痴"));
        mContactList.add(new Person("傻瓜"));
        mContactList.add(new Person("弱智"));
        mContactList.add(new Person("高手"));
        mContactList.add(new Person("超神"));
        mContactList.add(new Person("o张三"));
        mContactList.add(new Person("s李四"));
        mContactList.add(new Person("q王五"));
        mContactList.add(new Person("u赵六"));
        mContactList.add(new Person("v田七"));
        mContactList.add(new Person("x王八"));
        mContactList.add(new Person("123"));
        mContactList.add(new Person("友爱"));
        mContactList.add(new Person("高超"));
        mContactList.add(new Person("某人"));
        mContactList.add(new Person("缘分"));
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        sb_letter = (SideBar) findViewById(R.id.sb_letter);
        tv_show_letter = (TextView) findViewById(R.id.tv_show_letter);
        mAdapter=new ContactAdapter(this,mContactList);
        lv_contact.setAdapter(mAdapter);
        sb_letter.setTextView(tv_show_letter);
        sb_letter.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int index=mAdapter.getFirstLetterPostion(s);
                if(index!=-1){
                    lv_contact.setSelection(index);
                }
            }
        });
    }


}
