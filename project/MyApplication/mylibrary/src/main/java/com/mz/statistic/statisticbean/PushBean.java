package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class PushBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.push_info)
    public String push_info;
    @StatisticsKey(value = Constants.PropertyName.push_id,minValue = 1)
    public long push_id;

    @StatisticsKey(Constants.PropertyName.NOW_VERSION)
    public String now_version;
    @StatisticsKey(Constants.PropertyName.UPDATE_VERSION)
    public String update_version;

}
