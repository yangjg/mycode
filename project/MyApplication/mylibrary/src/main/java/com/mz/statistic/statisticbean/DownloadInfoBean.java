package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.ClassType;
import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-10-20.
 */

public class DownloadInfoBean extends BaseBean {

    @StatisticsKey(Constants.PropertyName.DOWNLOAD_ERROR_TYPE)
    public String download_error_type;
    @StatisticsKey(Constants.PropertyName.DOWNLOAD_URL)
    public String download_url;
    @StatisticsKey(Constants.PropertyName.DNS_DOWNLOAD_SERVER)
    public String download_dns;
    @StatisticsKey(Constants.PropertyName.IP_REMOTE)
    public String ip_remote;
    @StatisticsKey(Constants.PropertyName.AVG_DOWN_RATE)
    public String ave_rate;
    @StatisticsKey(Constants.PropertyName.DOWNLOAD_DUR)
    public String download_dur;
    @StatisticsKey(Constants.PropertyName.DOWNLOADED_SIZE)
    public String download_size;
    @StatisticsKey(Constants.PropertyName.TYPE)
    public String type;
    @StatisticsKey(Constants.PropertyName.STATUS)
    public String status;
    @StatisticsKey(Constants.PropertyName.STATUS_REASON)
    public String errorMsg;

    @ClassType
    public IStatisticData app_struct_item;


 /*      dataMap.put(Constants.PropertyName.TYPE, String.valueOf(type));
        dataMap.put(Constants.PropertyName.STATUS, String.valueOf(status));
        dataMap.put(Constants.PropertyName.STATUS_REASON, errorMsg);*/
  /*   if (downloadWrapper != null && !TextUtils.isEmpty(downloadWrapper.getCurDownloadUrl())) {
        downloadMap.put(Constants.PropertyName.DOWNLOAD_ERROR_TYPE, String.valueOf(downloadWrapper.getDownloadErrType()));
        downloadMap.put(Constants.PropertyName.DOWNLOAD_URL, downloadWrapper.getCurDownloadUrl());
        downloadMap.put(Constants.PropertyName.DNS_DOWNLOAD_SERVER, downloadWrapper.getDownloadServerDns());
        downloadMap.put(Constants.PropertyName.IP_REMOTE, downloadWrapper.getDownloadIpRemote());
        downloadMap.put(Constants.PropertyName.AVG_DOWN_RATE, String.valueOf(downloadWrapper.getAveDownloadSpe()));
        downloadMap.put(Constants.PropertyName.DOWNLOAD_DUR, String.valueOf(downloadWrapper.getTotalDownloadDur()));
        downloadMap.put(Constants.PropertyName.DOWNLOADED_SIZE, String.valueOf(downloadWrapper.getTotalDownloadedSize()));
        //这个用appid和appname替代了APP_ID和APP_NAME,不需重复统计
        addUserInfo(downloadMap);
    }*/
}
