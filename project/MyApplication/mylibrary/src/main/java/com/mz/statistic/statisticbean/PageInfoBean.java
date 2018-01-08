package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-11-4.
 */

public class PageInfoBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.PAGE_NAME)
    public String page_name;

    @StatisticsKey(value = Constants.PropertyName.PAGE_ID,minValue = 1)
    public int page_id;

    @StatisticsKey(Constants.PropertyName.BLOCK_NAME)
    public String block_name;

    @StatisticsKey(Constants.PropertyName.BLOCK_TYPE)
    public String block_type;

    @StatisticsKey(value = Constants.PropertyName.BLOCK_ID,minValue = 1)
    public int block_id;

}
