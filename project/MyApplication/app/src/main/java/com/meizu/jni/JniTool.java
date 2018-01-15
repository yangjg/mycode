package com.meizu.jni;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;


import com.example.yangjingan.myapplication.MainActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yangjingan on 18-1-11.
 */

public class JniTool {

    private static Context gContext;

    static {
        gContext = MainActivity.getContext();
    }


    /** 将字节数组转化为对应的十六进制字符串 */

    public static String BytesToHexString(byte[] buf) {
       if(null == buf)return null ;
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

    public static byte[] HexStringToBytes( String hexStr ) {
        byte[] result = new byte[ hexStr.length() / 2 ];
        for( int i = 0; i < hexStr.length() / 2; i++ ) {
            int high = Integer.parseInt( hexStr.substring( i * 2, i * 2 + 1 ), 16 );
            int low = Integer.parseInt( hexStr.substring( i * 2 + 1, i * 2 + 2 ), 16 );
            result[ i ] = ( byte )( high * 16 + low );
        }
        return result;
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
                digests[i] = BytesToHexString(digest);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                digests[i] = null;
            }
        }
        return digests;
    }




}
