package com.sclbxx.mytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    PagerContainer mContainer;
    private FrameLayout.LayoutParams lp;
    private ViewPager pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mContainer = (PagerContainer) findViewById(R.id.pager_container);

        pager = mContainer.getViewPager();
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);
        //Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(adapter.getCount());
        //A little space between pages
//        pager.setPageMargin(15);
        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        lp = new FrameLayout.LayoutParams(width - 200, FrameLayout.LayoutParams.MATCH_PARENT);
//此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = Gravity.LEFT;
        pager.setLayoutParams(lp);
        //If hardware acceleration is enabled, you should also remove
        // clipping on the pager for its children.
        pager.setClipChildren(false);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    lp.gravity = Gravity.LEFT;
                    pager.setLayoutParams(lp);
                } else if (position == 4) {
                    lp.gravity = Gravity.RIGHT;
                    pager.setLayoutParams(lp);
                } else {
                    lp.gravity = Gravity.CENTER_HORIZONTAL;
                    pager.setLayoutParams(lp);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView view = new TextView(Main2Activity.this);
            view.setText("Item " + position);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}
