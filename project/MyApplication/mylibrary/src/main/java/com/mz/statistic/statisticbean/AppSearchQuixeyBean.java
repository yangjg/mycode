package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-20.
 */

 public class AppSearchQuixeyBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.SEARCH_ID)
    public String quixey_search_id;

    @StatisticsKey(Constants.PropertyName.SESSION_ID)
    public String quixey_session_id;

    @StatisticsKey(Constants.PropertyName.CATEGORY)
    public String quixey_category;

    @StatisticsKey(Constants.PropertyName.ACTION)
    public String quixey_action;

    @StatisticsKey(Constants.PropertyName.LABEL)
    public String quixey_label;

    @StatisticsKey(Constants.PropertyName.USER_AGENT)
    public String quixey_user_agent;

    @StatisticsKey(Constants.PropertyName.POSITION)
    public String quixey_postion;

    @StatisticsKey(Constants.PropertyName.QUERY)
    public String quixey_query;


    @StatisticsKey(Constants.PropertyName.APP_AD_TYPE)
    public String app_ad_type;

    @StatisticsKey(Constants.PropertyName.SEARCH_ENGINE)
    public String cp;

    @StatisticsKey(Constants.PropertyName.SEARCH_ALG_ID)
    public String rule;

}
