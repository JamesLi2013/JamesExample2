package com.example.james;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.james.bean.LocationBean;
import com.example.james.okhttpExample.OkHttpExampleUI;
import com.example.james.weixinContact.WeiXinContactUi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.ResultCallback;
import com.zhy.http.okhttp.request.OkHttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        goOkHttpExample();
        goWeiXInContact();
    }

    private void goOkHttpExample(){
        Intent intent =new Intent(this, OkHttpExampleUI.class);
        startActivity(intent);

    }

    private void goWeiXInContact(){
        Intent intent =new Intent(this, WeiXinContactUi.class);
        startActivity(intent);
    }

}
