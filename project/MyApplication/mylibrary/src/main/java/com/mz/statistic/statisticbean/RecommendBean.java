package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * 推荐位埋点，有点击和被展示两种事件
 * Created by leaves on 17-11-13.
 */

public class RecommendBean extends BaseBean {
    @StatisticsKey(value = Constants.PropertyName.RECOM_ID,minValue = 1)
    public String recom_id;

    @StatisticsKey(value = Constants.PropertyName.RECOM_TYPE,minValue = 1)
    public int recom_type;

    //推荐的主ID（相关推荐代表就是APP_ID）
    @StatisticsKey(value = Constants.PropertyName.SOURCE_ID)
    public long source_id;

    @StatisticsKey(value = Constants.PropertyName.RECOM_VER)
    public String recom_ver;

    @StatisticsKey(value = Constants.PropertyName.PROTO_VER)
    public int proto_ver = 1;

    /**
     * str(ms)
     */
    @StatisticsKey(value = Constants.PropertyName.TIME)
    public String time;

}
