package com.yidian.geek.page;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.umeng.analytics.MobclickAgent;
import com.yidian.geek.R;
import com.yidian.newssdk.YdCustomConfigure;
import com.yidian.newssdk.exportui.NewsPortalFragment;
import com.yidian.newssdk.theme.ThemeManager;

/**
 * Created by chenyichang on 2018/5/22.
 *
 * 多tab接入
 */

public class NewsPortalActivity extends FragmentActivity {

    private Fragment fragmentNavi;
    private Button btnRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        fragmentNavi = new NewsPortalFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.portal_container, fragmentNavi)
                .commitNowAllowingStateLoss();
        btnRefresh = findViewById(R.id.expose_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewsPortalFragment)fragmentNavi).refreshCurrentChannel();
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
