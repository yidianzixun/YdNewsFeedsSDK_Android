# 一点资讯开放平台SDK-Android

<p align="center">
   <a href="https://jcenter.bintray.com/com/yidian/android/feeds/1.0.8/">
    <img src="https://img.shields.io/badge/Jcenter-v1.0.8-brightgreen.svg?style=flat-square" alt="Latest Stable Version" />

  </a>
  <a href="https://developer.android.com/about/versions/android-4.1.html">
    <img src="https://img.shields.io/badge/API-16%2B-blue.svg?style=flat-square" alt="Min Sdk Version" />
  
  <a href="http://qm.qq.com/cgi-bin/qm/qr?k=W64EsNGAK6aVub49Aj0sLNlXzxb9gSjs">
    <img src="https://img.shields.io/badge/QQ群-685541331-orange.svg?style=flat-square" alt="QQ Group" />
  </a>
</p>


## 阅读对象
本文档面向所有使用一点资讯开放平台SDK的开发、测试人员等, 要求读者具有一定的Android编程开发经验。

<p>
	<img src="http://si1.go2yd.com/get-image/0PsJ2mP3BtA" width="70%" height="70%"/>
	<img src="http://si1.go2yd.com/get-image/0PsJBrEjNU8" width="70%" height="70%"/>
</p>


## 1.产品概述

一点资讯开放平台Android SDK是一点资讯Android开发团队推出的Android平台上的个性化信息流推荐开发集成包(SDK)，为Android开发者提供简单、快捷的接口，帮助开发者实现Android平台上的个性化信息流产品。

### 1.1 Demo
下载地址：[点击下载](https://download.yidianzixun.com/android/geek-demo1.0.6_8a22537.apk)


## 2.SDK 功能说明

- [1] 提供完整个性化推荐信息流解决方案
- [2] 一键接入多频道推荐列表
- [3] 一键接入自定义单频道频道页面
- [4] 支持列表中单插信息流推荐卡片
- [5] SDK初始化流程便捷，接入成本低
- [6] SDK体积轻，大小仅有1M+
- [7] 卡片样式丰富，有小图、大图、组图、视频、图集卡片
- [8] 正文页用户体验佳，侧滑返回、视频推荐、评论页应有尽有
- [9] 强大的后台管理

## 3.SDK使用

### 3.1 Download

#### jcenter
* 1.先在工程目录下的build.gradle的repositories添加：

``` gradle
 buildscript {
    
    repositories {
        google()
        jcenter()
        maven { url "https://jitpack.io" }
        maven { url "https://dl.bintray.com/yidian-android/open_android_sdk/" }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.0.0'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // 一定要添加，greendao使用
    }
}
```

**备注**：SDK使用到了greendao数据库框架，开发者需要在dependencies中进行配置

* 2.然后在app的build.gradle的denpendencies中添加：


``` gradle
dependencies {
    implementation ("com.android.support:support-fragment:${rootProject.ext.supportVersion}")
    implementation ("com.android.support:support-annotations:${rootProject.ext.supportVersion}")
    implementation ("com.android.support:recyclerview-v7:${rootProject.ext.supportVersion}")
    implementation "org.greenrobot:greendao:3.2.2"
    implementation 'com.yidian.android:feeds:+'


}
```
**备注**：开放平台SDK一直在更新迭代，为了稳定性及新特性，请开发者依赖最新版本，查看各个版本更新信息请点击[版本更新记录](https://github.com/yidianzixun/YdNewsFeedsSDK_Android/releases/)

### 3.2 使用方法
com.yidian.newssdk.NewsFeedsSDK.java 这是SDK的配置入口类，目前对外提供了响应的配置方法，开发者可以通过配置

private String mAppKey;
SDK初始化所需要的APP_KEY

private String mAppId;
SDK初始化所需的APP_ID

private boolean debug;
是否开启DEBUG模式，开启Debug模式后会输出更多的log信息。

在自定义Application的onCreate中添加如下代码，初始化我们的SDK。由于您的应用可能不止一个进程，建议只在主进程初始化我们的SDK。
SDK初始化示例：

``` java
new NewsFeedsSDK.Builder()
        .setAppId(BuildConfig.APP_ID)
        .setAppKey(BuildConfig.APP_KEY)
        .setContext(getApplicationContext())
        .setDebugEnabled(BuildConfig.DEBUG)
        .build();
}
```
您需要在SDK初始化代码中传入APP_ID、APP_KEY。

## 4.接入场景

### 4.1 多频道多列表的形式

**NewsPortalFragment**

SDK目前支持接入方采用多TAB的形式接入，SDK已经对NewsPortalFragment进行封装，接入方只需将NewsPortalFragment放置在对应的container容器中即可，具体调用方式可以参考：com.yidian.geek.page.NewsPortalActivity，接入代码如下：

``` java
fragmentNavi = new NewsPortalFragment();
getSupportFragmentManager().beginTransaction()
                .replace(R.id.portal_container, fragmentNavi)
                .commitNowAllowingStateLoss();
```

### 4.2 自定义单频道、单列表的形式

**NewsListFragment**

NewsListFragment为单列表的Fragment，接入方式示例具体参考：com.yidian.geek.page. NewsListActivity
NewsListFragment支持展示自定义的频道列表，接入方需要在构造NewsListFragment传入需要配置的频道名称及是ViewPager中接入，如：

``` java
fragment = NewsListFragment.newInstance("视频集锦", false);
getSupportFragmentManager().beginTransaction()
        .replace(R.id.portal_container, fragment)
        .commitNowAllowingStateLoss();

```
NewsListFragment.newInstance(String channelName, boolean inViewPager)参数含义：


| 参数    | 描述|
| :---: | :---:| 
|String channelName|频道名称|
|boolean inViewPager|NewsListFragment是否是在ViewPager中集成，如集成在ViewPager,传入true，否则传入false|

注意事项：

在使用NewsListFragment单列表接入的情况下，如果是接入在ViewPager中的话，需要注意：

1、NewsListFragment.newInstance方法的第二个参数需要设置成true，否则会出现一些问题

2、如果接入方式是采用接入方自己的Fragment包装了NewsListFragment的话，需要在接入方的包装的Fragment的setUserVisibleHint方法中调用下NewsListFragment的setUserVisibleHint方法，不然和1一样会出现一些问题，如：

``` java
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        newlistFragment.setUserVisibleHint(isVisibleToUser);
    }
```

### 4.3 View的形式接入

**NewsEmbedFragment**

NewsEmbedFragment支持接入方将信息流插入在接入方的布局中，接入方可根据自己的实际场景需要使用该Fragment。
NewsEmbedFragment的构造需要传入频道名及信息流列表卡片的数量，如：

``` java
fragment = NewsEmbedFragment.newInstance("推荐", 1);
getSupportFragmentManager().beginTransaction()
        .replace(R.id.portal_container, fragment)
        .commitNowAllowingStateLoss();

```
具体使用方式请参考：com.yidian.geek.page.NewsViewActivity

### 4.4 SDK对外暴露方法
SDK提供对外暴露接口或方法的方式，辅助开发者实现一些功能.
#### 4.4.1 外部刷新方法

开发者接入多频道列表或单频道列表过程中，可能需要通过点击底部Tab或者其他的方式进行信息流刷新操作，目前SDK对外暴露刷新方法来帮助开发者实现刷新操作。

| 方法    | 描述|
| :---: | :---:| 
|public void refreshCurrentChannel()|对所处的当前频道进行刷新操作。在接入NewsPortalFragment或NewsListFragment时可以调用此Fragment的refreshCurrentChannel方法。|
#### 4.4.2 滑动到顶部

开发者可调用此方法将列表滑动到顶部

| 方法    | 描述|
| :---: | :---:| 
|public void scrollToTopPosition()|将信息流列表滑动到顶部。在接入NewsPortalFragment或NewsListFragment时可以调用此Fragment的scrollToTopPosition方法。|
#### 4.4.3 判断当前列表是否在最顶部


| 方法    | 描述|
| :---: | :---:| 
|public boolean isScrollToTopPosition()|判断当前所在位置是否在信息流最顶部。在接入NewsPortalFragment或NewsListFragment时可以调用此Fragment的isScrollToTopPosition方法。|

#### 4.4.4 多频道接入时，切换频道回调频道名信息

考虑到在使用NewsPortalFragment多频道接入方式时，接入方可能需要切换频道时的频道名信息，以便数据统计使用。SDK提供了接口调用来支持接入方完成此操作，接入代码如下：


``` java
   NewsFeedsSDK.getInstance().setReportInterface(new IReportInterface() {

            @Override
            public void onPageSelected(String channelPageName) {
                Toast.makeText(getApplicationContext(), channelPageName, Toast.LENGTH_SHORT).show();
            }
        });
```

## 5.其他
### 5.1 声明必要权限
客户端需要在AndroidManifest.xml文件中增加SDK所需要的访问网络等权限，代码示例：

``` xml
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

### 5.2 关于混淆

SDK提供时已经通过consumerProguardFile 方式提供混淆文件，所以客户端不需要重新keep防止混淆

## 6.反馈与建议
### 6.1 反馈模板  

| 类型    | 描述|
| :---: | :---:| 
| SDK版本 | v1.0.0|
| 设备型号  | 小米 8  |
| OS版本  | Android 8.0.0 |
| 问题描述  | 描述问题出现的现象  |
| 操作描述  | 描述经过如何操作出现上述问题                     |
| 额外附件   | 文本形式控制台log、crash报告、其他辅助信息（界面截屏或录像等） |

### 6.2 联系方式
- 主页：[一点资讯开放平台](https://developer.yidianzixun.com/) 
- Issues：<https://github.com/yidianzixun/YdNewsFeedsSDK_Android/issues>

