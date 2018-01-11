package com.othershe.jnitest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangjingan on 18-1-8.
 */

public class JniUtil {

    private static Context gContext;
    public static void init(Context context){
        gContext = context.getApplicationContext();
    }


    private static final Charset charset =Charset.forName( "utf-8" );
    private static final char[] HEX_CHAR = {

            '0', '1', '2', '3', '4', '5', '6', '7',

            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'

    };
    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("jniutil");
       // gContext = BaseApplication.getContext();
    }
    private JniUtil(){

    }
    public static JniUtil getInstance(){
        return JniUtilHolder.gInstance;
    }

    /**
     *     //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用,
     // 传入要加密的内容，在Jni中有秘钥和加密算法，防止apk被反编译拦截秘钥和加密算法，返回加密好的内容，只有服务器知道秘钥和算法。
     jni中实现了缓存，所以多次调用不会去重复加载类和方法，调用一次后类对象和方法都缓存了，只有进程被kill掉才会释放。
     * @param data
     * @return
     */
    public native byte[] encrypt(byte[] data);

    /**
     * 传入要加密的字符串，返回加密后的字符串
     * @param content
     * @return
     */
    public String getEncrypt(String content){

        byte[] source = content.getBytes(charset);
        byte[] res = encrypt(source);
        String ds = new String(res,charset);
        return ds ;
    }

    /***
     * 传入要加密的字符串，返回加密后的字符串，返回的后的字符串是16进制的字符串
     * @param content
     * @return
     */
    public String getHexEncrpt(String content){
        byte[] source = content.getBytes(charset);
        byte[] res = encrypt(source);
        return   toHexString(res);
    }



    /** 将字节数组转化为对应的十六进制字符串 */

    private static String toHexString(byte[] rawByteArray) {

        char[] chars = new char[rawByteArray.length * 2];

        for (int i = 0; i < rawByteArray.length; ++i) {

            byte b = rawByteArray[i];

            chars[i*2] = HEX_CHAR[(b >>> 4 & 0x0F)];

            chars[i*2+1] = HEX_CHAR[(b & 0x0F)];

        }
        return new String(chars);
    }


    /** 获取Apk签名的MD5摘要，防止包篡改 */
    public static String[] signatureDigest() {
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = gContext.getPackageManager().getPackageInfo(gContext.getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int length = pkgInfo.signatures.length;
        String[] digests = new String[length];

        for (int i = 0; i < length; ++i) {
            Signature sign = pkgInfo.signatures[i];
            try {
                MessageDigest md5 = null;
                md5 = MessageDigest.getInstance("MD5");
                byte[] digest = md5.digest(sign.toByteArray()); // get digest with md5 algorithm
                digests[i] = toHexString(digest);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                digests[i] = null;
            }
        }
        return digests;
    }

    public static void print(String msg){
        Log.e("JniUtil",msg);
    }



    private static class JniUtilHolder{
        private static final JniUtil gInstance = new JniUtil();
    }


}
