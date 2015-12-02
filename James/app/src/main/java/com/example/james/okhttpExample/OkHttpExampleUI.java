package com.example.james.okhttpExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.example.james.MyConstant;
import com.example.james.R;
import com.example.james.bean.LocationBean;
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

/**
 * Okhttp及hongyang工具简单使用,待补充
 */
public class OkHttpExampleUI extends AppCompatActivity {

    private TextView mTvContent;
    private ImageView mIvWebPic;

    private OkHttpClient mClient=new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_example);
        mTvContent=(TextView)findViewById(R.id.tv_web_content);
        mIvWebPic=(ImageView)findViewById(R.id.iv_web_pic);

        //使用hongyang的工具类
       getHttpAndJson();

        String picUrl=MyConstant.IMG_URL;
        Picasso.with(getApplicationContext()).load(picUrl).into(mIvWebPic);
    }

    private void getHttpAndJson() {
        //json数据url
        String url= MyConstant.JSON_LOCATION_URL;
        new OkHttpRequest.Builder()
                .url(url)
                .get(new ResultCallback<LocationBean>()
                {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        LogUtils.e("onBefore");
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        LogUtils.e("onAfter");
                    }

                    @Override
                    public void inProgress(float progress) {
                        super.inProgress(progress);
                    }

                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(LocationBean users)
                    {
                        LogUtils.e(users.getQueryLocation());
                        LogUtils.e(users.getAddrList());
                        mTvContent.setText("");
                    }
                });

    }


    private void run(String url) {
        Request request=new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Gson gson=new Gson();
                ArrayList<LocationBean> lists=gson.fromJson(response.body().string(), new TypeToken<ArrayList<LocationBean>>() {
                }.getType());
                LogUtils.d(lists);
            }
        });

    }
}
