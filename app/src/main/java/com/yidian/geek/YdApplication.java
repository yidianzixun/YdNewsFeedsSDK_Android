package com.yidian.geek;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.yidian.newssdk.NewsFeedsSDK;
import com.yidian.newssdk.export.IReportInterface;

//import com.yidian.geek.custom.YdMediaIjkplayer;

/**
 * Created by chenyichang on 2018/5/18.
 */

public class YdApplication extends Application {
    private static  final String TAG  = YdApplication.class.getSimpleName();


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(
                this,
                BuildConfig.UMENG_KEY,
                BuildConfig.CHANNEL,
                MobclickAgent.EScenarioType.E_UM_NORMAL);

        MobclickAgent.startWithConfigure(config);
        MobclickAgent.setCatchUncaughtExceptions(true);
        MobclickAgent.setDebugMode(BuildConfig.DEBUG);
        MobclickAgent.openActivityDurationTrack(false);  //禁止默认的页面统计

        initLeakCanary();

        /**
         * 初始化SDK
         */
        new NewsFeedsSDK.Builder()
                .setAppId(BuildConfig.APP_ID)
                .setAppKey(BuildConfig.APP_KEY)
                .setContext(getApplicationContext())
                .setDebugEnabled(BuildConfig.DEBUG)
                .build();

        NewsFeedsSDK.getInstance().setReportInterface(new IReportInterface() {

            @Override
            public void onPageSelected(String channelPageName) {
                Log.d(TAG, channelPageName);
            }
        });

    }

    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }
}
