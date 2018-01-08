package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-23.
 */

public class NetStatusData extends BaseBean {

    @StatisticsKey(Constants.PropertyName.NET_TYPE)
    public String net_type;

    @ClassType
    public IStatisticData extrasData;
}
