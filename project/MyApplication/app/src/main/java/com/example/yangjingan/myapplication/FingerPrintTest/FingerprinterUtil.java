package com.example.yangjingan.myapplication.FingerPrintTest;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by yangjingan on 18-1-25.
 */

public class FingerprinterUtil {



  //  public static Fingerpri

    public static <T> T getFingerprintManager(Context context) {
        T fingerprintManager = null;
        try {
            fingerprintManager = (T) context.getSystemService(Context.FINGERPRINT_SERVICE);
        } catch (Exception e) {

        }
        return fingerprintManager;
    }

}
