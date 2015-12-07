package com.example.james.CommonAdapterEx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.james.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterExUI extends AppCompatActivity {
    List<String> mDatas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_ex);
        for(int i=0;i<60;i++){
            mDatas.add(i+"    "+i+"   "+i);
        }
        ListView listView= (ListView) findViewById(R.id.lv_adapter_ex);
        listView.setAdapter(new CommonAdapter(this,mDatas));
    }
}
