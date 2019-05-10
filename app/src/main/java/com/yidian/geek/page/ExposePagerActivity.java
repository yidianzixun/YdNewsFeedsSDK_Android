package com.yidian.geek.page;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.yidian.geek.R;
import com.yidian.newssdk.libraries.flyco.SlidingTabLayout;

public class ExposePagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expose_pager);
        mViewPager = findViewById(R.id.viewpager_expose);
        mSlidingTab = findViewById(R.id.sliding_tab);
        mViewPager.setAdapter(new ExposePageAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(1);
        mSlidingTab.setViewPager(mViewPager);
    }
}
