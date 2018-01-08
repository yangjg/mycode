package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-11-17.
 */

public class HorizontalBean extends BaseBean {


    @StatisticsKey(Constants.PropertyName.MAP_KEY_APPNAME)
    public String name;

    @StatisticsKey(value = Constants.PropertyName.H5_EXT_ID,minValue = 1)
    public int id;

    @StatisticsKey(Constants.PropertyName.APP_AD_TYPE)
    public String type;

    @StatisticsKey(value = Constants.PropertyName.MAP_KEY_POS,minValue = 1)
    public int ver_pos;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_HOR_POS)
    public int hor_pos;


    @StatisticsKey(value = Constants.PropertyName.BLOCK_ID,minValue = 1)
    public int block_id;

    @StatisticsKey(Constants.PropertyName.BLOCK_NAME)
    public String block_name;

    @StatisticsKey(Constants.PropertyName.BLOCK_TYPE)
    public String block_type;


}
