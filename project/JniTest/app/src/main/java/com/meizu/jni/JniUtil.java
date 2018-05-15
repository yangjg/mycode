package com.meizu.jni;

import android.util.Log;

import java.nio.charset.Charset;

/**
 * Created by yangjingan on 18-1-8.
 */

public class JniUtil {




    private static final Charset charset =Charset.forName( "utf-8" );

    private static final int ENCRYPT_MODE =1 ;
    private static final int DECRYPT_MODE = 2;

    private static final int APP_CODE_SIGN_TYPE = 1 ;
    private static final int APP_KEY_SIGN_TYPE = 2 ;
    private static final int UPLOAD_SIGN_TYPE = 3 ;
    private static final int GAME_CODE_SIGN_TYPE = 4 ;
    private static final int GAME_KEY_SIGN_TYPE = 5 ;

    private static final int AES_PRIVATE_TYPE = 6;
    private static final int AES_IV_TYPE=7;

    private static final boolean DEBUG= true ;

    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("jniutil");
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
     * @param mode 是加密模式还是解密模式
     * @return
     */
    private native byte[] encrypt(byte[] data,int  mode);

    /**
     * 获取app的签名key，具体实现在c/c++；
     * 1表示公用接口，2表示上传接口，3表示游戏接口。，传入接口获取对应的签名Key；
     * @return
     */
    private native String getAppSignKey(int type);


    /**
     * 获APP_CODE接口签名Key
     * @return
     */
    public String getAppCodeSignKey(){
        return  getAppSignKey(APP_CODE_SIGN_TYPE);
    }


    /**
     * 获取GAME_CODE接口签名Key
     * @return
     */
    public String getGameCodeSignKey(){
        return getAppSignKey(GAME_CODE_SIGN_TYPE);
    }

    /**
     * 获取上传接口签名Key
     * @return
     */
    public String getUploadSignKey(){
        return getAppSignKey(UPLOAD_SIGN_TYPE);
    }

    /**
     * 获取游戏GAME_KEY接口签名Key
     * @return
     */
    public String getGameKeySignKey(){
        return getAppSignKey(GAME_KEY_SIGN_TYPE);
    }

    /**
     * 获取游戏APP_KEY接口签名Key
     * @return
     */
    public String getAppKeySignKey(){
        return getAppSignKey(APP_KEY_SIGN_TYPE);
    }


    /**
     * 获取AES的private key，用于测试使用
     * @return
     */
    public String getAESPrivateKey(){
        return getAppSignKey(AES_PRIVATE_TYPE);
    }

    /**
     * 获取AES的IV key，用于测试使用
     * @return
     */
    public String getAESIVKey(){
        return getAppSignKey(AES_IV_TYPE);
    }


    /**
     * 传入要加密的字符串，返回加密后的字符串
     * @param content
     * @return
     */
    public String getEncrypt(String content){

        byte[] source = content.getBytes(charset);
        byte[] res = encrypt(source,ENCRYPT_MODE);
        String ds =  JniTool.BytesToHexString(res);
        return ds ;
    }


    /**
     * 传入要解密的字符串，返回解密后的字符串
     * @param content
     * @return
     */
    public String getDecrypt(String content){
        byte[] source = JniTool.HexStringToBytes(content);
        byte[] res = encrypt(source,DECRYPT_MODE);
        String ds = new String(res,charset);
        return ds ;
    }




    private static String[] signatureDigest(){
        return JniTool.signatureDigest();
    }


    private static void print(String msg){
        Log.e("JniTest",msg);
    }



    private static class JniUtilHolder{
        private static final JniUtil gInstance = new JniUtil();
    }


}
