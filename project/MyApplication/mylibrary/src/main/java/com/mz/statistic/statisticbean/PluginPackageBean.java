package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-20.
 */

public class PluginPackageBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.PLUGIN_MAPKEY_PACKAGENAME)
    public String package_name;
}
