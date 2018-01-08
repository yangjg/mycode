package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class LabelBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.BLOCK_NAME)
    public String block_name;
    @StatisticsKey(Constants.PropertyName.BLOCK_TYPE)
    public String block_type;
    @StatisticsKey(Constants.PropertyName.BLOCK_ID)
    public String block_id;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_POS)
    public String pos_ver;
    @StatisticsKey(Constants.PropertyName.MAP_KEY_HOR_POS)
    public String hor_pos;
    @StatisticsKey(Constants.PropertyName.PAGE_TYPE)
    public String page_type;
    @StatisticsKey(Constants.PropertyName.LABLE_NAME)
    public String lable_name;
    @StatisticsKey(Constants.PropertyName.LABLE_ID)
    public String lable_id;
}


