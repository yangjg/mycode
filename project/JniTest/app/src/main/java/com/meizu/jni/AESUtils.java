package com.meizu.jni;

import android.util.Log;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by liwenbin on 16-11-17.
 */

public class AESUtils {

    private static final String TAG = "AESUtils";

    private static final String KEY_ALGORITHM = "AES";

    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private static final Charset charset = Charset.forName( "UTF-8" );

    private static final String PRIV_KEY ="[.7=a$K#z)d3Eu^A";//"[.7=a$K#z)d3Eu^P";

    private static final String IV_KEY = "_g7=a$K#)d*E#fg8";//"_g7=a$K#)d*E#fg7";



    public static String encrypt(String content ) {
        try {
            Cipher aesCbcCipher = Cipher.getInstance( CIPHER_ALGORITHM );
            SecretKeySpec key = new SecretKeySpec( PRIV_KEY.getBytes( charset ), KEY_ALGORITHM );
            aesCbcCipher.init( Cipher.ENCRYPT_MODE, key, getIV( IV_KEY ) );
            byte[] result = aesCbcCipher.doFinal( content.getBytes( charset ) );
            //return new String(result,charset);
            return parseByte2HexStr( result );
        } catch( Exception e ) {
            e.printStackTrace();
            Log.e("AES",e.getMessage());
        }
        return null;
    }

    public static IvParameterSpec getIV(String ivKey ) {
        IvParameterSpec iv = new IvParameterSpec( ivKey.getBytes( charset ) );
        return iv;

    }

    public static String decrypt(String content)  {
        try {
            Cipher cipher = Cipher.getInstance( CIPHER_ALGORITHM );// 创建密码器
            SecretKeySpec key = new SecretKeySpec( PRIV_KEY.getBytes( charset ), KEY_ALGORITHM );
            cipher.init( Cipher.DECRYPT_MODE, key, getIV( IV_KEY ) );// 初始化
            byte[] or =content.getBytes(charset);
            //byte[] result = cipher.doFinal( or );
            byte[] result = parseHexStr2Byte( content );
            result = cipher.doFinal( result );
            return new String( result,charset ); // 解密
        } catch( Exception e ) {
             e.printStackTrace();
            Log.e("AES",e.getMessage());
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

    public static String parseByte2HexStr(byte buf[] ) {

        /*String res =  new String(buf,charset);
        return res;*/

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
