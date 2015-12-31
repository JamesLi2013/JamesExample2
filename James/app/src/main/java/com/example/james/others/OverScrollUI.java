package com.example.james.others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.james.R;

public class OverScrollUI extends AppCompatActivity {

    private ImageView mIvScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_scroll);

        mIvScroll = (ImageView) findViewById(R.id.iv_scroll);

    }
}
