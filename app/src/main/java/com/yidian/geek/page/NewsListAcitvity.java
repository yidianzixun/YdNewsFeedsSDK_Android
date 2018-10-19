package com.yidian.geek.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.yidian.geek.R;
import com.yidian.newssdk.YdCustomConfigure;
import com.yidian.newssdk.exportui.NewsListFragment;
import com.yidian.newssdk.theme.ThemeManager;

/**
 * Created by chenyichang on 2018/5/22.
 *
 * 单列表样式接入
 */

public class NewsListAcitvity extends FragmentActivity {

    private Fragment fragment;
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //单列表如果是在ViewPager中的话，请将第二个参数置为true
        fragment = NewsListFragment.newInstance("视频集锦", false);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.portal_container, fragment)
                .commitNowAllowingStateLoss();
        btnRefresh = findViewById(R.id.expose_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewsListFragment)fragment).refreshCurrentChannel();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
