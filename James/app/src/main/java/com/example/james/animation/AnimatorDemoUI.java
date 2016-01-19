package com.example.james.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.james.R;
/**尝试各种animator效果*/
public class AnimatorDemoUI extends AppCompatActivity {

    private ImageView mIvPic3;
    private ObjectAnimator mAnimator;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_demo);
        initView();
    }

    private void initView() {
        mIvPic3 = (ImageView) findViewById(R.id.iv_pic3);
        mAnimator = ObjectAnimator.ofFloat(mIvPic3,"scaleX",1f,2.0f);
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(2);
        findViewById(R.id.btn_start_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimator.start();

//                PropertyValuesHolder pvh1=PropertyValuesHolder.ofFloat("translationX",0,300f);
//                PropertyValuesHolder pvh2=PropertyValuesHolder.ofFloat("scaleX",0,1.0f);
//                PropertyValuesHolder pvh3=PropertyValuesHolder.ofFloat("alpha",0,1.0f);
//                PropertyValuesHolder pvh4=PropertyValuesHolder.ofFloat("rotationY",0,360f);
//                ObjectAnimator.ofPropertyValuesHolder(mIvPic3,pvh1,pvh2,pvh3,pvh4).setDuration(2000).start();

//                valueAnim();
            }});
    }

    private void valueAnim() {
        ValueAnimator va = new ValueAnimator();
        va.setDuration(4000);
        va.setObjectValues(new PointF(0, 0));
        va.setInterpolator(new LinearInterpolator());
        va.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                PointF point = new PointF();
                point.x = fraction *200;
                int r=400;
                point.y = (float)Math.sqrt(r*r-(r-point.x)*(r-point.x));
                return point;
            }
        });
        va.start();
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mIvPic3.setX(point.x);
                mIvPic3.setY(point.y);

            }
        });
    }
}
