package com.example.james.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.james.R;

import java.util.ArrayList;

/**
 * 实现阿里巴巴主页的1688刷新效果:从左到右每个字左右翻转,到最后一个字翻转回来,同时改变翻转方向
 */
public class AnimationDemoUI extends AppCompatActivity {

    private ImageView mIvPic1,mIvPic2,mIvPic3,mIvPic4;
    private ArrayList<View> mPicLists=new ArrayList<>();
    private int mCount=0;
    private boolean mIsReverse=false;
    private ObjectAnimator mAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_demo);
        mIvPic1 = (ImageView)findViewById(R.id.iv_pic1);
        mIvPic2 = (ImageView)findViewById(R.id.iv_pic2);
        mIvPic3 = (ImageView)findViewById(R.id.iv_pic3);
        mIvPic4 = (ImageView)findViewById(R.id.iv_pic4);
        mPicLists.add(mIvPic1);
        mPicLists.add(mIvPic2);
        mPicLists.add(mIvPic3);
        mPicLists.add(mIvPic4);
        findViewById(R.id.btn_show_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startAnim();
            }
        });
        findViewById(R.id.btn_cancel_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnimator!=null){
                    mAnimator.removeAllListeners();//移出原有的监听器,从而不会执行下次的方法了
                    mAnimator.end();//使用mAnimator.cancel()会使View停留在当前状态,而end会直接跳到执行完的状态
                    mCount=0;
                    mIsReverse=false;
                }
            }
        });

    }
    private void startAnim() {
        mAnimator = ObjectAnimator.ofFloat(mPicLists.get(mCount), "rotationX", 0f, 360f);
        mAnimator.setDuration(500);
        if(mIsReverse){
            mCount--;
        }else{
            mCount++;
        }
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator anim) {
                if(mCount==mPicLists.size()){
                    if(!mIsReverse){
                        mIsReverse=true;
                        mCount--;
                       startAnim();
                    }
                }else if(mCount==-1){
                    mCount=0;
                    mIsReverse=false;
                }
                else{
                    startAnim();
                }
            }
        });
        mAnimator.start();
    }
}
