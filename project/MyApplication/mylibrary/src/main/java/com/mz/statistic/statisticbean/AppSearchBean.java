package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-20.
 */

public class AppSearchBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.MAP_KEY_SEARCH_ID)
    public String search_id;
    @StatisticsKey(Constants.PropertyName.SEARCH_TYPE)
    public String search_type;

    @StatisticsKey(Constants.PropertyName.KEYWORD)
    public String key_word;

    @StatisticsKey(Constants.PropertyName.SEARCH_ENGINE)
    public String cp;

    @StatisticsKey(Constants.PropertyName.SEARCH_ALG_ID)
    public String rule;

    @StatisticsKey(Constants.PropertyName.FROM)
    public String from;

    @StatisticsKey(Constants.PropertyName.MAP_KEY_POS)
    public String key_pos;

    @StatisticsKey(Constants.PropertyName.SOURCE_BLOCK_TYPE)
    public String source_block_type;

    @StatisticsKey(Constants.PropertyName.SOURCE_PAGE)
    public String source_page;

    @ClassType
    public IStatisticData extralInfo;

}
