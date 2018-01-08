package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * 有parentApp的AppStructItem
 * Created by leaves on 17-11-13.
 */

public class ChildAppStructItem extends BaseBean {
    @StatisticsKey(value = Constants.PropertyName.PARENT_APPNAME)
    public String parent_appname;

    @StatisticsKey(value = Constants.PropertyName.PARENT_POS, minValue = 0)
    public int parent_pos = -1;

    @StatisticsKey(value = Constants.PropertyName.PARENT_APPID, minValue = 1)
    public int parent_appid;

    @StatisticsKey(value = Constants.PropertyName.PARENT_BLOCK_ID, minValue = 1)
    public int parent_block_id;

    @StatisticsKey(value = Constants.PropertyName.PARENT_APKNAME)
    public String parent_apkname;

    @StatisticsKey(value = Constants.PropertyName.PARENT_BLOCK_NAME)
    public String parent_block_name;

    @StatisticsKey(value = Constants.PropertyName.PARENT_BLOCK_TYPE)
    public String parent_block_type;

    @ClassType
    public IStatisticData mAppStructItem;

    public ChildAppStructItem(IStatisticData appStructItem) {
        mAppStructItem = appStructItem;
    }
}
