package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-23.
 */

class AppSourcePageData extends ApkBean {

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_COUNT,minValue = 0)
    public String load_times;

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_SUM,minValue = 0)
    public String total_counts;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_TOPICID)
    public String topic_id;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_TOPICNAME)
    public String topic_name;

    @StatisticsKey(Constants.PropertyName.H5_EXT_URL)
    public String url;

    @ClassType
    public IStatisticData source_info;

    @ClassType
    public IStatisticData extrasInfo;
}
