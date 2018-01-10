package com.othershe.jnitest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yangjingan on 18-1-8.
 */

public class JniUtil {

    private static final char[] HEX_CHAR = {

            '0', '1', '2', '3', '4', '5', '6', '7',

            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'

    };

    private static final String KEY_ALGORITHM = "AES";

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final Charset charset =Charset.forName( "utf-8" );//  Charset.forName("UTF-16");  //Charset.forName( "utf-8" );
    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("jniutil");
    }
    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用
    public native String test();

    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用,
    // 传入要加密的内容，在Jni中有秘钥和加密算法，防止apk被反编译拦截秘钥和加密算法，返回加密好的内容，只有服务器知道秘钥和算法。
    public native String StrEncrypt(String content);

    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用,
    // 传入要加密的内容，在Jni中有秘钥和加密算法，防止apk被反编译拦截秘钥和加密算法，返回加密好的内容，只有服务器知道秘钥和算法。
    public native byte[] byteEncrypt(byte[] content);

    private static Context gContext;
    public static void init(Context context){
        gContext = context.getApplicationContext();
    }

    /** 获取签名的MD5摘要 */

    public static String[] signatureDigest() {

        PackageInfo pkgInfo = null;
        try {
            pkgInfo = gContext.getPackageManager().getPackageInfo(gContext.getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
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


    public String getEncodeString(String content){


        byte[] data = content.getBytes(charset);
        byte[] res = byteEncrypt(data);

        String rs =  toHexString(res); // new String(res,charset);

        byte[] decode = byteEncrypt(res);

        String ds = new String(decode,charset);
        return  rs;
    }





    public static void print(String res){
        Log.e("JniTest","print:"+res);
    }

    public static String encrypt( String content, String passwd, String iv ) throws InvalidAlgorithmParameterException {
        try {
            Log.e("JniTest","encrypt:"+content);
            Cipher aesCbcCipher = Cipher.getInstance( CIPHER_ALGORITHM );
            SecretKeySpec key = new SecretKeySpec( passwd.getBytes( charset ), KEY_ALGORITHM );
            aesCbcCipher.init( Cipher.ENCRYPT_MODE, key, getIV( iv ) );
            byte[] result = aesCbcCipher.doFinal( content.getBytes( charset ) );
            return parseByte2HexStr( result );
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static IvParameterSpec getIV(String ivKey ) {
        IvParameterSpec iv = new IvParameterSpec( ivKey.getBytes( charset ) );
        return iv;
    }


    public static String decrypt( String content, String passwd, String iv ) throws InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );// 创建密码器
            SecretKeySpec key = new SecretKeySpec( passwd.getBytes( charset ), KEY_ALGORITHM );
            cipher.init( Cipher.DECRYPT_MODE, key, getIV( iv ) );// 初始化
            byte[] result = parseHexStr2Byte( content );
            return new String( cipher.doFinal( result ) ); // 解密
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] parseHexStr2Byte( String hexStr ) {
        byte[] result = new byte[ hexStr.length() / 2 ];
        for( int i = 0; i < hexStr.length() / 2; i++ ) {
            int high = Integer.parseInt( hexStr.substring( i * 2, i * 2 + 1 ), 16 );
            int low = Integer.parseInt( hexStr.substring( i * 2 + 1, i * 2 + 2 ), 16 );
            result[ i ] = ( byte )( high * 16 + low );
        }
        return result;
    }

    public static String parseByte2HexStr( byte buf[] ) {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < buf.length; i++ ) {
            String hex = Integer.toHexString( buf[ i ] & 0xFF );
            if( hex.length() == 1 ) {
                hex = '0' + hex;
            }
            sb.append( hex.toUpperCase() );
        }
        return sb.toString();
    }
}
