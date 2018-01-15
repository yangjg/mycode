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
    public static final boolean DEBUG= true ;

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
    public native byte[] encrypt(byte[] data,int  mode);

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




    public static String[] signatureDigest(){
        return JniTool.signatureDigest();
    }


    public static void print(String msg){
        Log.e("JniTest",msg);
    }



    private static class JniUtilHolder{
        private static final JniUtil gInstance = new JniUtil();
    }


}
