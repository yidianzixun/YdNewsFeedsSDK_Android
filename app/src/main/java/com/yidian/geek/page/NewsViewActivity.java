package com.yidian.geek.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.yidian.geek.R;
import com.yidian.newssdk.exportui.NewsEmbedFragment;
import com.yidian.newssdk.theme.ThemeManager;

/**
 * Created by chenyichang on 2018/5/22.
 * <p>
 * 可以自由配置条数的信息流方式接入
 */

public class NewsViewActivity extends FragmentActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        fragment = NewsEmbedFragment.newInstance("推荐", 1);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.portal_container, fragment)
                .commitNowAllowingStateLoss();
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
