package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-31.
 */

public class BlockBean extends BaseBean {

    @StatisticsKey(value = Constants.PropertyName.BLOCK_ID,minValue = 1)
    public int id;

    @StatisticsKey(Constants.PropertyName.BLOCK_NAME)
    public String name;

    @StatisticsKey(Constants.PropertyName.BLOCK_TYPE)
    public String type;

    @StatisticsKey(Constants.PropertyName.BLOCK_CPDS)
    public String cpds;

    @StatisticsKey(Constants.PropertyName.BLOCK_STYLE)
    public String style;

    @StatisticsKey(Constants.PropertyName.BLOCK_TAG)
    public String tag;
}
