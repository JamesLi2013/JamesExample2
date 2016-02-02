package com.example.james.others;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.james.R;
import com.example.james.bean.GenericBean;
import com.example.james.weixinContact.Person;

public class GenericUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
//        GenericBean<Person> genericBean=new GenericBean<>();
//        Type genericType = genericBean.getType();
        Log.e("hh",new GenericBean<Person>(){}.getType().toString());
        TextView tv = (TextView) findViewById(R.id.tv_content);
//        tv.setText(genericType.toString());
    }
}
