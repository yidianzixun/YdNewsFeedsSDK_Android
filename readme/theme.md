## 主题换肤
默认情况下，开发者无需对主题进行设置，默认的主题风格为红色。除了默认的主题外，开放平台SDK可以动态配置主题风格，开发者可以自由定制所需资源，如字体颜色、大小、动画颜色、分割线、背景等。
在demo示例代码中，请参考CustomThemeNewsPortalActivity、CustomThemeNewsPortalActivity2。
### 换肤步骤
1、新建一个style，parent设为@style/ydsdk_DefaultTheme，ydsdk_DefaultTheme中配置了一些可配置的选项，具体为：

| item配置项    | 描述|
| :---: | :---:| 
|newssdk_slidingtab_normal_txt_color|导航栏未选中状态下字体颜色|
|newssdk_sliding_tab_checked_txt_color|导航栏选中状态下字体颜色|
|newssdk_slidingtab_txt_size|导航栏字体大小|
|newssdk_common_bg_color|信息流整体背景|
|newssdk_common_font_color|信息流卡片字体颜色|
|newssdk_common_font_size|信息流卡片字体大小|
|newssdk_card_divider_color|信息流卡片分割线颜色|
|newssdk_card_divider_height|信息流卡片分割线粗细|
|newssdk_card_img_bg_color|信息流卡片图片背景颜色|
|newssdk_refresh_tip_color|信息流刷新提示字体颜色|
|newssdk_feedback_textcolor|负反馈的字体颜色|
|newssdk_feedback_state|负反馈的字体背景|
|newssdk_feedback_commontxt_color|负反馈的固定文案的字体颜色|
|newssdk_feedback_success_tip_bg|负反馈点击后顶部弹出文案提示的背景色|

开发者根据自己需求，重写上面item的值。

2、在接入SDK的Activity的onCreate方法中，调用以下方法：

``` java
    YdCustomConfigure.getInstance().setCustomThemeStyle(R.style.CustomTheme);
```
R.style.CustomTheme为开发者定义的style。

