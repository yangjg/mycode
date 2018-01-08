package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-11-7.
 */

public class TagBeans extends BaseBean {


    @StatisticsKey(Constants.PropertyName.TAG_TILE)
    public String tag_titles;
    @StatisticsKey(Constants.PropertyName.TAG_ID)
    public String tag_ids;
    @StatisticsKey(value = Constants.PropertyName.TAG_COUNT,minValue = 1)
    public int tag_count;
}
