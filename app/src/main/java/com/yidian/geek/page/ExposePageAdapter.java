package com.yidian.geek.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yidian.newssdk.exportui.NewsListFragment;

/**
 * @author zhangzhun
 * @date 2018/9/28
 */

public class ExposePageAdapter extends FragmentStatePagerAdapter {

    public ExposePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return NewsListFragment.newInstance("推荐", true);
        } else {
            return BlankFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "频道" + position;
    }
}
