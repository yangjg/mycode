package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-31.
 */

public class TitleItemBean extends BlockBean {

    @StatisticsKey(value = Constants.PropertyName.BLOCK_PROFILE_ID,minValue = 1)
    public long profile_id;

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_POS,minValue = 1)
    public int pos_ver;

//    @StatisticsKey(Constants.PropertyName.BLOCK_STYLE)
//    public String block_style;
}
