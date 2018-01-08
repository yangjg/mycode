package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-19.
 */

public class DownloadDialogBean extends BaseBean {
    @StatisticsKey(Constants.PropertyName.DOWNLOAD_DIALOG_APPID)
    public String app_id;
    @StatisticsKey(Constants.PropertyName.DOWNLOAD_DIALOG_SIZE)
    public String size;
    @StatisticsKey(Constants.PropertyName.DOWNLOAD_DIALOG_TYPE)
    public String type;
}
