package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

class UpdateBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.MAP_KEY_APKNAME)
    public String apk_name;

    @StatisticsKey(Constants.PropertyName.WLAN_DOWNLOADED_SIZE)
    public String download_size;
    @StatisticsKey(Constants.PropertyName.WLAN_DOWNLOADED_TIME)
    public String download_time;

    @StatisticsKey(Constants.PropertyName.UPDATE_INTERVAL)
    public String update_interval;
    @StatisticsKey(Constants.PropertyName.UPDATE_NEED_PACKAGES_SIZE)
    public String update_need_packages_size;

    @StatisticsKey(Constants.PropertyName.UPDATE_STRATEGY)
    public String update_strategy;
    @StatisticsKey(Constants.PropertyName.UPDATE_ALLOW_RATE)
    public String update_all_rate;

    @StatisticsKey(Constants.PropertyName.UPDATE_DELAY)
    public String update_delay;
    @StatisticsKey(Constants.PropertyName.UPDATE_DELAY_SECONDS)
    public String update_delay_seconds;

    @StatisticsKey(Constants.PropertyName.UPDATE_ALLOW_PACKAGES_SIZE)
    public String update_allow_packages_size;


}
