package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class EntranceData extends BaseBean {

    @StatisticsKey(Constants.PropertyName.BLOCK_NAME)
    public String block_name;
    @StatisticsKey(Constants.PropertyName.BLOCK_TYPE)
    public String block_type;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_POS)
    public String click_pos;

}
