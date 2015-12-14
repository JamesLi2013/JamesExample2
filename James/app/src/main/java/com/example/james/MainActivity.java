package com.example.james;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.james.commonAdapterEx.AdapterExUI;
import com.example.james.dialog.DialogExUI;
import com.example.james.mulPic.MulPicUI;
import com.example.james.okhttpExample.OkHttpExampleUI;
import com.example.james.weixinContact.WeiXinContactUi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goNavigation();

    }

    private void goCommonAdapterUI(){
        Intent intent=new Intent(this, AdapterExUI.class);
        startActivity(intent);
    }

    private void goOkHttpExample(){
        Intent intent =new Intent(this, OkHttpExampleUI.class);
        startActivity(intent);

    }

    private void goWeiXInContact(){
        Intent intent =new Intent(this, WeiXinContactUi.class);
        startActivity(intent);
    }
    private void goDialogEx(){
        Intent intent =new Intent(this, DialogExUI.class);
        startActivity(intent);
    }

    private void goSwipeRefreshEx(){
        Intent intent =new Intent(this, SwipeRefreshDemoUI.class);
        startActivity(intent);
    }
    private void goMulPic(){
        Intent intent =new Intent(this, MulPicUI.class);
        startActivity(intent);
    }

    private void goNavigation(){
        Intent intent =new Intent(this, NavigationUI.class);
        startActivity(intent);
    }


}
