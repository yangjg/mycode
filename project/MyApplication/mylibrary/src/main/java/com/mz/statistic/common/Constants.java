
package com.mz.statistic.common;
/**
 * Created by yangjingan on 17-11-23.
 */

public class Constants {


        public interface AnnotationValue {
                /**
                 * 默认不可能用的字符串
                 */
                String DEFAULT_INVALID_STRING = "null";
                /***
                 * 默认不可用的整形
                 */
                int DEFAULT_INVALID_INT = Integer.MIN_VALUE;
        }


        public interface PropertyName {

                //Map的key值
                String MAP_KEY_COUNT = "count";//页面总加载次数
                String MAP_KEY_SUM = "sum";//页面加载的总数据量
                String MAP_KEY_NAME = "name";//精选推荐(点更多)名称
                String MAP_KEY_TOPICNAME = "topicname";//专题页面点击专题名称
                String MAP_KEY_TOPICID = "topicid";//专题页面点击专题ID
                String MAP_KEY_POS = "pos";//点击位置
                String MAP_KEY_HOR_POS = "hor_pos";//水平位置
                String MAP_KEY_APKNAME = "apkname";//分类页面点击分类名称
                String MAP_KEY_APPID = "appid";//分类页面点击分类名称
                String MAP_KEY_APPNAME = "appname";//分类页面点击分类名称
                String MAP_KEY_SEARCH_ID = "search_id";//搜索ID
                String STATUS = "status";//status=1，成功；0，失败
                String STATUS_REASON = "status_reason";//下载或安装失败时传此参数，记录失败原因
                String FROM = "from";//区分点击推荐搜索词的来源


                String KEYWORD = "keyword";//用户输入的关键词

                //Share的key
                String SHARE_DESCRIPTON = "share_description";
                String SHARE_URL = "share_url";


                //key
                String TYPE = "type";
                String BLOCK_ID = "block_id";
                String BLOCK_TYPE = "block_type";
                String BLOCK_NAME = "block_name";
                String BLOCK_STYLE = "block_style";
                String PAGE_TYPE = "page_type";
                String MAP_KEY_SHARE_TO_PKG = "share_to_pkgName";//分享到的应用包名
                String LABLE_NAME = "label_name";
                String LABLE_ID = "label_id";
                String BLOCK_TAG = "block_tag";
                String BLOCK_CPDS = "block_cpds";

                String SOURCE_PAGE = "source_page";
                String SOURCE_BLOCK_TYPE = "source_block_type";

                String BLOCK_PROFILE_ID = "block_profile_id";
                String SEARCH_ENGINE = "search_engine";
                String SEARCH_ALG_ID = "search_alg_id";

                String APP_AD_TYPE = "app_ad_type";

                String H5_EXT_URL = "url";
                String H5_EXT_ID = "id";

                //精选页下载相关推荐
                String ITEM_TYPE = "item_type";
                String PARENT_APPID = "parent_appid";
                String PARENT_APKNAME = "parent_apkname";
                String PARENT_APPNAME = "parent_appname";
                String PARENT_BLOCK_TYPE = "parent_block_type";
                String PARENT_BLOCK_ID = "parent_block_id";
                String PARENT_BLOCK_NAME = "parent_block_name";
                String PARENT_POS = "parent_pos";

                //push
                String push_id = "push_id";
                String push_info = "push_info";
                String NOW_VERSION = "now_version";
                String UPDATE_VERSION = "update_version";
                //搜索结果
                String SEARCH_TYPE = "search_type";
                //WLAN
                String WLAN_DOWNLOADED_SIZE = "wlan_downloaded_size";
                String WLAN_DOWNLOADED_TIME = "wlan_downloaded_time";

                //搜索外漏
                String SEARCH_TIP = "app_name";

                //autoUpdate
                String UPDATE_INTERVAL = "interval";
                String UPDATE_NEED_PACKAGES_SIZE = "need_packages";
                String UPDATE_STRATEGY = "strategy";
                String UPDATE_ALLOW_RATE = "allow_rate";
                String UPDATE_DELAY = "delay";
                String UPDATE_DELAY_SECONDS = "delay_seconds";
                String UPDATE_ALLOW_PACKAGES_SIZE = "allow_packages";

                //downlaodDialogData
                String DOWNLOAD_DIALOG_APPID = "app_id";
                String DOWNLOAD_DIALOG_SIZE = "size";
                String DOWNLOAD_DIALOG_TYPE = "type";

                //netStatus
                String NET_TYPE = "net_type";

                String PAGE_NAME = "page_name";
                String PAGE_ID = "page_id";

                //taginfo
                String TAG_TILE = "tag_name";
                String TAG_ID = "tag_id";
                String TAG_COUNT = "tag_count";


                //参数key值
                String CATEGORY = "category";
                String ACTION = "action";
                String LABEL = "label";
                String POSITION = "position";
                String SEARCH_ID = "search_id";
                String SESSION_ID = "session_id";
                String QUERY = "query";
                String USER_AGENT = "user_agent";


                //推荐业务ID，服务端从数据库拿到（"相关推荐"和"猜你喜欢"）
                String RECOM_TYPE = "recom_type";
                //推荐的主ID（相关推荐代表就是APP_ID，猜你喜欢是设备imei）
                String SOURCE_ID = "source_id";
                //展示的被推荐ID列表，用逗号分隔。或被点击的推荐ID。
                String RECOM_ID = "recom_id";
                //推荐算法版本，服务端从数据库拿到
                String RECOM_VER = "recom_ver";
                //数据采集协议版本，固定写法（现在为"1"）
                String PROTO_VER = "proto_ver";
                String TIME = "time";


                //下载埋点的name
                String DOWNLOAD_ERROR_TYPE = "error_type";
                String DOWNLOAD_URL = "download_url";
                String DNS_DOWNLOAD_SERVER = "dns_server";
                String IP_REMOTE = "ip_remote";
                String AVG_DOWN_RATE = "avg_down_rate";
                String DOWNLOAD_DUR = "download_dur";
                String DOWNLOADED_SIZE = "downloaded_size";


                //插件的埋点name
                String PLUGIN_MAPKEY_PACKAGENAME = "packagename";

        }
}
