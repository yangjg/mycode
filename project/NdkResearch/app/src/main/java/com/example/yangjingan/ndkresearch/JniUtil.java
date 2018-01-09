package com.example.yangjingan.ndkresearch;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yangjingan on 18-1-8.
 */

public class JniUtil {

    private static final String KEY_ALGORITHM = "AES";

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final Charset charset = Charset.forName( "utf-8" );

    static{
        //jniutil 这个参数是 so的名字
        System.loadLibrary("jniutil");
    }
    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用
    public native String test();

    //java调用c/c++的方法，这个是native实现的，名字和签名（参数和返回值）必须要c/c++的名字一样才能调用,
    // 传入要加密的内容，在Jni中有秘钥和加密算法，防止apk被反编译拦截秘钥和加密算法，返回加密好的内容，只有服务器知道秘钥和算法。
    public native String AesEncrypt(String content);

    public static String encrypt( String content, String passwd, String iv ) throws InvalidAlgorithmParameterException {
        try {
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
