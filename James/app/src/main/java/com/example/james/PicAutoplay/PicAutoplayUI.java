package com.example.james.PicAutoplay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.james.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PicAutoplayUI extends AppCompatActivity {

    private MyViewPager mViewPager;
    private List<String> mPicUrls;
    private LinearLayout mContainer;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_autoplay);
        mViewPager = (MyViewPager) findViewById(R.id.vp_main);
        mContainer = (LinearLayout) findViewById(R.id.ll_container);
        mPicUrls = new ArrayList<>();
        mPicUrls.add("http://p3.so.qhimg.com/bdr/_240_/t0141039d5ada55d678.jpg");
        mPicUrls.add("http://p1.so.qhimg.com/bdr/_240_/t01bf8c29d9565b2fbb.jpg");
        mPicUrls.add("http://p0.so.qhimg.com/bdr/_240_/t01f74e609cd3f9cc00.jpg");
        mPicUrls.add("http://p0.so.qhimg.com/bdr/_240_/t0159ab4842f48ff1d0.jpg");
        mPicUrls.add("http://p3.so.qhimg.com/bdr/_240_/t01b7368b6284a15413.jpg");
        initMyViewPager(mPicUrls);

    }

    private void initMyViewPager(List<String> picturesUrl) {
        mPicUrls = picturesUrl;
        mViewPager.setAdapter(new PicAdapter());

        for (int i = 0; i < picturesUrl.size(); i++) {
            View pointView = new View(this);
            pointView.setBackgroundResource(R.mipmap.gray_point);
            LinearLayout.LayoutParams pramas = new LinearLayout.LayoutParams(dip2Px(12), dip2Px(12));
            pramas.leftMargin = dip2Px(5);
            pramas.bottomMargin = dip2Px(5);
            mContainer.addView(pointView, pramas);
            if (i == 0) {
                pointView.setBackgroundResource(R.mipmap.blue_point);
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                position = position % mPicUrls.size();
                for (int i = 0; i < mPicUrls.size(); i++) {
                    View pointView = mContainer.getChildAt(i);
                    pointView.setBackgroundResource(R.mipmap.gray_point);
                }
                mContainer.getChildAt(position).setBackgroundResource(R.mipmap.blue_point);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int initItem = Integer.MAX_VALUE / 2;
        int remainder = initItem % mPicUrls.size();
        mViewPager.setCurrentItem(initItem - remainder);

        final AutoplayTask autoplayTask = new AutoplayTask();
        autoplayTask.start();

        /*mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        autoplayTask.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        autoplayTask.start();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });*/
    }

    class AutoplayTask implements Runnable {
        public void start() {
            mHandler.postDelayed(this, 4000);
        }

        public void stop() {
            mHandler.removeCallbacks(this);
        }

        @Override
        public void run() {
            int preItem = mViewPager.getCurrentItem();
            preItem++;
            mViewPager.setCurrentItem(preItem);
            start();
        }
    }

    class PicAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mPicUrls != null) {
                return Integer.MAX_VALUE;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % mPicUrls.size();
            ImageView iv = new ImageView(PicAutoplayUI.this);
            iv.setImageResource(R.mipmap.gray_point);
            String url = mPicUrls.get(position);
            Picasso.with(PicAutoplayUI.this).load(url).into(iv);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(iv);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index=mViewPager.getCurrentItem()%mPicUrls.size();
                    Toast.makeText(getApplicationContext(),"你点击了"+index+"图",Toast.LENGTH_SHORT).show();
                }
            });
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private int dip2Px(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dp * density +0.5f);
    }
}
