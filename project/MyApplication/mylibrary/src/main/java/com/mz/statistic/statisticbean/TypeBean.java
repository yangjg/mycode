package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-11-6.
 */

public class TypeBean extends BaseBean {
    @StatisticsKey(Constants.PropertyName.TYPE)
    public String type;
}
