package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-23.
 */

public class ApkBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.MAP_KEY_APKNAME)
    public String apk_name;

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_APPID,minValue = 1)
    public String app_id;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_APPNAME)
    public String app_name;

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_POS,minValue = 1)
    public int key_pos;
}
