package com.example.james.others;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.james.R;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.view.LineChartView;

public class ChartUI extends AppCompatActivity {

    private LineChartView mLcvChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        mLcvChart = (LineChartView) findViewById(R.id.lcv_chart);
        mLcvChart.setInteractive(true);
        mLcvChart.setZoomType(ZoomType.HORIZONTAL);
        mLcvChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);

    }
}
