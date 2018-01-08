package com.example.yangjingan.myapplication.Annotation.FastJson;

import android.os.Bundle;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by yangjingan on 17-10-26.
 */

public class AppItem1 {

  /*  public String acion = "test";
    private int value =3;

    public Class<?> classtype;

    public Bundle extras;*/
   /* public String userId="xxxx";
    public String token="xxxxxxx";*/

  /*public String order_id="546546556";
  public String create_time="xxxxx";
  public String total_price="165651";
  public String produce_name="米老鼠";
*/

  public static final int UNKNOWN = 10000;
  public static final int OK = 100001;
  public static final int ERROR = 100002;
  public static final int TOKEN_INVALID = 100003;

  @JSONField(name = "code")
  public int status = UNKNOWN;
  @JSONField(serialize = false, deserialize = false)
  public String callBack;
  @JSONField(serialize = false, deserialize = false)
  public String orderInfo ="{\"create_time\":\"xxxxx\",\"order_id\":\"546546556\",\"produce_name\":\"taidi\",\"total_price\":\"165651\"}";
  @JSONField(name = "errorMsg")
  public String errorMsg="net error";
  @JSONField(name = "newToken")
  public String newToken="xxsfwegwegwegwegwg";
  @JSONField(name = "orderInfo")
  public orderinfo doYouOrderInfo=new orderinfo();


  public static class OrderStatus {
    //下面的状态逗游那边需要识别的。
    public static final int UNKNOWN = 10000;
    public static final int OK = 100001;
    public static final int ERROR = 100002;
    public static final int TOKEN_INVALID = 100003;

    @JSONField(name = "code")
    public int status = UNKNOWN;
    @JSONField(serialize = false, deserialize = false)
    public String callBack;
    @JSONField(serialize = false, deserialize = false)
    public String orderInfo;
    @JSONField(name = "errorMsg")
    public String errorMsg;
    @JSONField(name = "newToken")
    public String newToken;
    @JSONField(name = "orderInfo")
    public String doYouOrderInfo;
  }
}
