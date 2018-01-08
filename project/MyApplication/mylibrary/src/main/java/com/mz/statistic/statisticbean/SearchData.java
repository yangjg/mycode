package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class SearchData extends BaseBean {
    @StatisticsKey(Constants.PropertyName.SEARCH_TIP)
    public String search_tip;

}
