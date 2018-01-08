package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * 目前详情页的搜索点击、分享点击事件在用
 * Created by yangjingan on 17-11-13.
 */

public class ShareBean extends ApkBean {

    @StatisticsKey(Constants.PropertyName.MAP_KEY_SHARE_TO_PKG)
    public String share_to_apkname;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_NAME)
    public String share_title;

    @StatisticsKey(Constants.PropertyName.SHARE_DESCRIPTON)
    public String share_description;

    @StatisticsKey(Constants.PropertyName.SHARE_URL)
    public String share_url;

}
