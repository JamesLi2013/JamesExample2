package com.example.james.weixinContact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_contact_ui);

        initView();
        initData();
        sortDatas();

    }

    private void initData() {
        mContactList.add(new Person("张三"));
        mContactList.add(new Person("李四"));
        mContactList.add(new Person("王五"));
        mContactList.add(new Person("赵六"));
        mContactList.add(new Person("田七"));
        mContactList.add(new Person("王八"));
        mContactList.add(new Person("123"));
        mContactList.add(new Person("adfw"));
        mContactList.add(new Person("###"));
        mContactList.add(new Person("  "));
        mContactList.add(new Person(" *"));
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        lv_contact = (ListView) findViewById(R.id.lv_contact);
        sb_letter = (SideBar) findViewById(R.id.sb_letter);
        mAdapter=new ContactAdapter(this,mContactList);
        lv_contact.setAdapter(mAdapter);
    }

    private void sortDatas() {
//        Collections.sort(mContactList,);
        CharacterParser characterParse=CharacterParser.getInstance();
        String firstLetter="";
        for (Person person:mContactList){
            if(TextUtils.isEmpty(person.getName().trim())){
                person.setLetter("#");//名字为空,首字母默认为"#"
            }else{
                firstLetter=characterParse.getSelling(person.getName().trim()).substring(0,1).toUpperCase();
                if(firstLetter.matches("[A-Z]"))//如果此字符不为A-Z中的一个,那么,则会设置为"#"
                    person.setLetter(firstLetter);
                else
                    person.setLetter("#");

            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
