package com.mz.statistic.statisticbean;

import com.mz.statistic.annotations.StatisticsKey;
import com.mz.statistic.common.Constants;

/**
 * Created by yangjingan on 17-11-16.
 */

public class CloseRecommendBean extends BaseBean {

    //展示的被推荐ID列表，用逗号分隔
    @StatisticsKey(Constants.PropertyName.RECOM_ID)
    public String recom_id;
    //展示的被推荐类型
    @StatisticsKey(Constants.PropertyName.RECOM_TYPE)
    public String recom_type;
    //推荐的主ID（相关推荐代表就是APP_ID
    @StatisticsKey(Constants.PropertyName.SOURCE_ID)
    public long source_id;
    //推荐算法版本，服务端从数据库拿到
    @StatisticsKey(Constants.PropertyName.RECOM_VER)
    public String recom_ver;
    //相关推荐埋点 标识是否是推荐item
    @StatisticsKey(Constants.PropertyName.ITEM_TYPE)
    public String item_type;
    //相关推荐父apkname;
    @StatisticsKey(Constants.PropertyName.PARENT_APKNAME)
    public String parent_apkname;
    @StatisticsKey(Constants.PropertyName.PARENT_APPID)
    public String parent_appid; //父的appid
    @StatisticsKey(Constants.PropertyName.PARENT_APPNAME)
    public String parent_appname; //父的名称
    @StatisticsKey(Constants.PropertyName.PARENT_BLOCK_TYPE)
    public String parent_block_type;//父app的blocktype
    @StatisticsKey(Constants.PropertyName.PARENT_BLOCK_ID)
    public String parent_block_id;//父app的blockid
    @StatisticsKey(Constants.PropertyName.PARENT_BLOCK_NAME)
    public String parent_block_name;//父app的blockname
    @StatisticsKey(Constants.PropertyName.PARENT_POS)
    public String parent_pos; ///父app的垂直坐标

}
