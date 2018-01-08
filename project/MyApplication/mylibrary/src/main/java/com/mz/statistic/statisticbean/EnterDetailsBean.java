package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class EnterDetailsBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.MAP_KEY_APKNAME)
    public String package_name;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_APPID)
    public String app_id;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_APPNAME)
    public String app_name;
    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_POS,minValue = 1)
    public int click_pos;
    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_HOR_POS,minValue = 1)
    public int click_hor_pos;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_SEARCH_ID)
    public String search_id;
}
