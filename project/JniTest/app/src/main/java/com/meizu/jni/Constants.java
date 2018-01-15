package com.meizu.jni;

/**
 * Created by yangjingan on 18-1-15.
 */

public class Constants {

    //APP下载签名公钥
    public static long[] APP_CODES = new long[]{0x3F8987226A3BE643L, 0x27E57E7DFD5CCBDFL, 0xAD538C0D7A56C240L};
    public static long[] PARAM_CODES_UPLOAD_LOG = new long[]{-3148125950561243941L,-1617674215097421250L,4365526036386212852L};
    //GAME下载签名公钥
    public static long[] GAME_CODES = new long[]{0x2DD7196AE5B43A5AL, 0x83EA8B305259EB9DL, 0x3B3ECD85F98B9E8FL};


    //    APP消息回调公钥:
    static long[] APP_KEY = new long[] {0x1DBA66A05095EFDDL, 0xA18BCF4498A04E0BL, 0xBC447E464B8079ECL, 0x30D38FA044F4F4CEL, 0xF15666E921CBE1E5L};
    //    GAME消息回调公钥:
    static long[] GAME_KEY = new long[] {0x25F212FFD4E197D0L, 0xB50F4E6B6FA7936FL, 0xF8EFD03C959DB6DL, 0xE836EB75D41172D4L, 0x79D03A5E0F99E801L};

    public static String test(){
        //加上加密串
        ObfuscatedDecode obfuscatedDecode = new ObfuscatedDecode(APP_CODES);
        String sign = obfuscatedDecode.toString();
        obfuscatedDecode = new ObfuscatedDecode(PARAM_CODES_UPLOAD_LOG);
        String param = obfuscatedDecode.toString();

        obfuscatedDecode =  new ObfuscatedDecode(GAME_CODES);
        String game = obfuscatedDecode.toString();


        obfuscatedDecode =  new ObfuscatedDecode(APP_KEY);
        String appKey = obfuscatedDecode.toString();

        obfuscatedDecode =  new ObfuscatedDecode(GAME_KEY);
        String gameKEY = obfuscatedDecode.toString();

        String res ="fwfew";

        return res ;
    }

}
