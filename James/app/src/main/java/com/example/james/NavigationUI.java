package com.example.james;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.example.james.fragment.FirstFragment;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;

public class NavigationUI extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;

//    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyTheme();
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setLogo(R.drawable.bookingdetail_timeline_normal);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
//                Toast.makeText(NavigationUI.this,"点击回到顶部",Toast.LENGTH_SHORT).show();
//                mListView.setSelection(0);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main_title);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab4"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab5"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab6"));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab1").setIcon(R.drawable.bookingdetail_dayicon_normal));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab2").setIcon(R.drawable.bookingdetail_timeline_normal));
//        tabLayout.addTab(tabLayout.newTab().setText("Tab3").setIcon(R.drawable.bookingdetail_dayicon_normal));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.white));
        tabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(NavigationUI.this,tab.getText(),Toast.LENGTH_SHORT).show();
                //ViewPager左右滑动的时候,通过log打印,没有调用此方法
                mViewPager.setCurrentItem(tab.getPosition());
                /*if(mViewPager.getCurrentItem()!=tab.getPosition()){
                    mViewPager.setCurrentItem(tab.getPosition());
                }*/
//                LogUtils.e(tab.getPosition()+"-------------");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ArrayList<String> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("shuju" + i);
        }
/*        mListView = (ListView) findViewById(R.id.lv_main_content);
        mListView.setAdapter(new QuickAdapter<String>(NavigationUI.this,R.layout.item_adapter,mDatas) {
            @Override
            protected void convert(BaseAdapterHelper helper, String item) {
                helper.setText(R.id.tv_item_common_adapter,item);
                helper.getView(R.id.iv_item_common_adapter).setVisibility(View.GONE);
            }
        });*/
        mViewPager = (ViewPager) findViewById(R.id.vp_main_content);
        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        };
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mViewPager.setCurrentItem(position);
//                Toast.makeText(NavigationUI.this,""+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 设置日/夜间主题
     */
    private void setMyTheme() {
        SharedPreferences sp=getSharedPreferences("james",MODE_PRIVATE);
        boolean isNight=sp.getBoolean("isNightTheme",false);
        setTheme(R.style.NightTheme);
        if(isNight){
            setTheme(R.style.NightTheme);
        }else{
            setTheme(R.style.DayTheme);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_ui, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(NavigationUI.this, "hello,go to settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_sharing) {
            Toast.makeText(NavigationUI.this, "hello,it is shared", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Toast.makeText(NavigationUI.this, "点击了照相功能", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(NavigationUI.this, "点击了图片功能", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            SharedPreferences sp=getSharedPreferences("james",MODE_PRIVATE);
            boolean isNight=sp.getBoolean("isNightTheme",false);
            SharedPreferences.Editor editor=sp.edit().putBoolean("isNightTheme",!isNight);
            editor.commit();
//            findViewById(R.id.nav_view).setBackgroundColor(NavigationUI.this.getResources().getColor(R.color.colorAccent));
//            NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
            recreate();
/*            setTheme(R.style.NightTheme);
            if(isNight){
                setTheme(R.style.NightTheme);
            }else{
                setTheme(R.style.DayTheme);
            }*/
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(0, "Page # 1");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FirstFragment.newInstance(1, "Page # 2");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FirstFragment.newInstance(2, "Page # 3");
                case 3: // Fragment # 1 - This will show SecondFragment
                    return FirstFragment.newInstance(3, "Page # 4");
              /*  case 4: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(4, "Page # 5");
                case 5: // Fragment # 0 - This will show FirstFragment different title
                    return FirstFragment.newInstance(5, "Page # 6");*/
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
