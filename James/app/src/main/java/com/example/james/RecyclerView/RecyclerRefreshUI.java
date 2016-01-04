package com.example.james.RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.james.R;

public class RecyclerRefreshUI extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_refresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content);

    }
}
