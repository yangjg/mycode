//
// Created by yangjingan on 18-1-10.
//
#include "stdbool.h"
#include "string.h"
#include "jni.h"
#include "global.h"
bool isSecurity(JNIEnv *env) {
    bool res = false ;
    if (initClass(env)) {
        debugLog(env,"begin call  checkedSign");
        res = checkedSign(env);
      /*  if(res){
            debugLog(env,"check sign success");
        }
        else{
            debugLog(env,"check sign failed, can't call any jni method");
        }*/
        res == true ? debugLog(env,"check sign success"):debugLog(env,"check sign failed, can't call any jni method");

    }
    return res;
}

bool checkedSign(JNIEnv *env) {
    if (g_check_sign) {
        return g_check_sign;
    }

    //JniTest：这个是android的默认签名,调试的时候可添加上这个签名，不然demo会崩溃的。
    //6C43F4D93B1E66BFDF9D3C44C46E72D3
    //以下是商店各个签名后的md5，用于so校验
    //debugdemo :6E657F8C6D195D3A3DEB5D21C9D77C53
    //debugeng:8DDB342F2DA5408402D7568AF21E29F9
    //debugprd:1C44D05F494A1C2DC6CBCBAC9C92ECFD
    //debuguser:C4AA9B9DEB124FE4BAE4C2FFDC05FAC6
    //releasedemo:6E657F8C6D195D3A3DEB5D21C9D77C53
    //releaseeng：8DDB342F2DA5408402D7568AF21E29F9
    //releaseprd:1C44D05F494A1C2DC6CBCBAC9C92ECFD
    //releaseuser：C4AA9B9DEB124FE4BAE4C2FFDC05FAC
    __const char* md5Array[]={
                              "6E657F8C6D195D3A3DEB5D21C9D77C53",
                              "8DDB342F2DA5408402D7568AF21E29F9",
                              "1C44D05F494A1C2DC6CBCBAC9C92ECFD",
                              "C4AA9B9DEB124FE4BAE4C2FFDC05FAC6"
                              };
    int md5Len = 4 ;

    jmethodID signatureDigest = (*env)->GetStaticMethodID(env, g_cls_jni_util, "signatureDigest", "()[Ljava/lang/String;");
    jobjectArray ja = (*env)->CallStaticObjectMethod(env, g_cls_jni_util, signatureDigest);
    jsize len = (*env)->GetArrayLength(env, ja);
    jstring jstr;
    char **pstr = (char **) malloc(len * sizeof(char *));
    int i=0;

    for (i = 0; i < len; ++i) {
        jstr = (*env)->GetObjectArrayElement(env, ja, i);
        pstr[i] = (char *) (*env)->GetStringUTFChars(env, jstr, 0);

        int j =0;
        for(j=0;j<md5Len;j++){
            if (0 == strcmp(md5Array[j], pstr[i])) {
                g_check_sign = true;
                break;
            }
        }
        if(g_check_sign){
            break;
        }
    }
    (*env)->DeleteLocalRef(env, ja);
    (*env)->DeleteLocalRef(env, jstr);
    free(pstr);


    return g_check_sign;
}


bool initClass(JNIEnv *env) {

    if (NULL != g_object_cipher && NULL != g_method_cipher_do_final) {
        return true;
    }
    //com.meizu.jni.JniUtil
    __const char *JniUtil = "com/meizu/jni/JniUtil";
    __const char *CIPHER = "javax/crypto/Cipher";
    __const char *SecretKey = "javax/crypto/spec/SecretKeySpec";
    __const char *IVParameterSpec = "javax/crypto/spec/IvParameterSpec";
    //查找javax.crypto.Cipher类和 javax.crypto.spec.SecretKeySpec,javax.crypto.spec.IvParameterSpec
    jclass cls = (*env)->FindClass(env, JniUtil);
    jclass cipherCls = (*env)->FindClass(env, CIPHER);
    jclass secretCls = (*env)->FindClass(env, SecretKey);
    jclass ivparameterCls = (*env)->FindClass(env, IVParameterSpec);

    if(NULL==cls || NULL == cipherCls || NULL == secretCls || NULL == ivparameterCls){
        return false ;
    }


    g_cls_jni_util = (*env)->NewGlobalRef(env, cls);
    g_cls_cipher = (*env)->NewGlobalRef(env, cipherCls);
    g_cls_secret_spec = (*env)->NewGlobalRef(env, secretCls);
    g_cls_iv_parameter = (*env)->NewGlobalRef(env, ivparameterCls);
    (*env)->DeleteLocalRef(env, cipherCls);
    (*env)->DeleteLocalRef(env, secretCls);
    (*env)->DeleteLocalRef(env, ivparameterCls);
    (*env)->DeleteLocalRef(env, cls);

    //如果没有定义DEBUG字段则不初始化print方法。
    jfieldID debug  = (*env)->GetStaticFieldID(env,g_cls_jni_util,"DEBUG","Z");
    if((*env)->ExceptionCheck(env)){
        (*env)->ExceptionClear(env);
        debug =NULL;
    }
    if(debug != NULL){
        g_jni_debug =(*env)->GetStaticBooleanField(env,g_cls_jni_util,debug);
        g_method_jni_print = (*env)->GetStaticMethodID(env, g_cls_jni_util, "print", "(Ljava/lang/String;)V");
    }

    debugLog(env,"find all class success");

    //获取SecretKeySpec类的getInstance方法；
    jmethodID instance = (*env)->GetStaticMethodID(env, g_cls_cipher, "getInstance",
                                                   "(Ljava/lang/String;)Ljavax/crypto/Cipher;");
    g_method_cipher_get_instance = instance;

    //把CIPHER_ALGORITHM,KEY_ALGORITHM 转换成string
    __const char *G_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    __const char *G_KEY_ALGORITHM = "AES";;
    __const char *G_PRIVATE_KEY = "[.7=a$K#z)d3Eu^A";
    __const char *G_IV_KEY = "_g7=a$K#)d*E#fg8";
    jstring algorithm = (*env)->NewStringUTF(env, G_CIPHER_ALGORITHM);
    jstring key_algorithm = (*env)->NewStringUTF(env, G_KEY_ALGORITHM);
    g_string_algorithm = (*env)->NewGlobalRef(env, algorithm);
    g_string_key_algorithm = (*env)->NewGlobalRef(env, key_algorithm);
    (*env)->DeleteLocalRef(env, algorithm);
    (*env)->DeleteLocalRef(env, key_algorithm);

    //调用Cipher的静态方法 getInstance，得到 Cipher对象；
    jobject m_cipher = (*env)->CallStaticObjectMethod(env, g_cls_cipher,
                                                      g_method_cipher_get_instance,
                                                      g_string_algorithm);
    g_object_cipher = (*env)->NewGlobalRef(env, m_cipher);
    (*env)->DeleteLocalRef(env, m_cipher);




    //把iv_key，priv_key转换成jbyteArray 数组;
    jsize len = strlen(G_PRIVATE_KEY);
    jbyteArray keybytes = (*env)->NewByteArray(env, len);
    if (NULL != keybytes) {
        (*env)->SetByteArrayRegion(env, keybytes, 0, len, (jbyte *) G_PRIVATE_KEY);
    }
    jsize ivlen = strlen(G_IV_KEY);
    jbyteArray ivbytes = (*env)->NewByteArray(env, ivlen);
    if (NULL != ivbytes) {
        (*env)->SetByteArrayRegion(env, ivbytes, 0, ivlen, (jbyte *) G_IV_KEY);
    }
    g_private_bytes = (*env)->NewGlobalRef(env, keybytes);
    g_iv_bytes = (*env)->NewGlobalRef(env, ivbytes);
    (*env)->DeleteLocalRef(env, keybytes);
    (*env)->DeleteLocalRef(env, ivbytes);

    //获取 SecretKeySpec类的对象的构造函数
    jmethodID secretTor = (*env)->GetMethodID(env, g_cls_secret_spec, "<init>",
                                              "([BLjava/lang/String;)V");
    g_method_secret_tor = secretTor;

    //构造 SecretKeySpec 对象
    jobject secretObj = (*env)->NewObject(env, g_cls_secret_spec, g_method_secret_tor,
                                          g_private_bytes, g_string_key_algorithm);
    g_object_secret_spec = (*env)->NewGlobalRef(env, secretObj);
    (*env)->DeleteLocalRef(env, secretObj);

    //获取 IvParameterSpec的构造方法
    jmethodID ivTor = (*env)->GetMethodID(env, g_cls_iv_parameter, "<init>", "([B)V");
    g_method_iv_tor = ivTor;

    //构造IvParameterSpec对象;
    jobject ivobj = (*env)->NewObject(env, g_cls_iv_parameter, g_method_iv_tor, g_iv_bytes);
    g_object_iv_parameter = (*env)->NewGlobalRef(env, ivobj);
    (*env)->DeleteLocalRef(env, ivobj);


    //获取Cipher类的init方法;
    jmethodID initId = (*env)->GetMethodID(env, g_cls_cipher, "init",
                                           "(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V");
    g_method_cipher_init = initId;

    jmethodID doFinal = (*env)->GetMethodID(env, g_cls_cipher, "doFinal", "([B)[B");
    g_method_cipher_do_final = doFinal;

    debugLog(env,"init all object and  method success");

    return true;
}

jbyteArray getEncrypt(JNIEnv *env, jbyteArray bytes,jint mode) {
    jbyteArray res = NULL;
    if (isSecurity(env)) {
        if(g_mode != mode){
            (*env)->CallVoidMethod(env, g_object_cipher, g_method_cipher_init, mode, g_object_secret_spec,
                                   g_object_iv_parameter);
            g_mode = mode ;
        }
        switch(mode){
            case 1:
                debugLog(env,"doFinal Encrypts mode");
                break;
            case 2:
                debugLog(env,"doFinal decrypts mode");
                break;
            default:
                debugLog(env,"doFinal other mode");
                break;
        }
        res = (jbyteArray) ((*env)->CallObjectMethod(env, g_object_cipher,
                                                     g_method_cipher_do_final, bytes));
    }
    return res;
}


jstring getAppSignKey(JNIEnv *env,jint type) {
    jstring res = NULL;
    if (isSecurity(env)) {
        switch (type){
            case APP_KEY_SIGN_TYPE:
                if (NULL == g_app_sign_key) {
                    jstring tp = (*env)->NewStringUTF(env, "8nLG89bQJ7t9jrhpjy6fXw4fLdZXbwWQ");
                    g_app_sign_key = (*env)->NewGlobalRef(env,tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_app_sign_key ;
                break;
            case APP_CODE_SIGN_TYPE:
                if (NULL == g_app_code_sign_key) {
                    jstring tp = (*env)->NewStringUTF(env, "ADFdfFFFDkluMHTM");
                    g_app_code_sign_key = (*env)->NewGlobalRef(env,tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_app_code_sign_key ;
                break;
            case UPLOAD_SIGN_TYPE:
                if (NULL == g_upload_sign_key) {
                    jstring  tp = (*env)->NewStringUTF(env, "x*Y^mdj@Fdu68*J");
                    g_upload_sign_key = (*env)->NewGlobalRef(env,tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_upload_sign_key ;
                break;
            case GAME_KEY_SIGN_TYPE:
                if(NULL == g_game_sign_key){
                    jstring  tp = (*env)->NewStringUTF(env, "mVqcNR9j7XbOFHTMGu55wQHKJUhiHJrl");
                    g_game_sign_key = (*env)->NewGlobalRef(env,tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_game_sign_key ;
                break;
            case GAME_CODE_SIGN_TYPE:
                if(NULL == g_game_code_sign_key){
                    jstring  tp = (*env)->NewStringUTF(env, "GOceSDIVXVJsdJLS");
                    g_game_code_sign_key = (*env)->NewGlobalRef(env,tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_game_code_sign_key ;
                break;
            case AES_PRIVATE_TYPE:
                if (NULL == g_aes_private_key) {
                    jstring tp = (*env)->NewStringUTF(env, "[.7=a$K#z)d3Eu^A");
                    g_aes_private_key = (*env)->NewGlobalRef(env, tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_aes_private_key;
                break;
            case AES_IV_TYPE:
                if (NULL == g_aes_iv_key) {
                    jstring tp = (*env)->NewStringUTF(env, "_g7=a$K#)d*E#fg8");
                    g_aes_iv_key = (*env)->NewGlobalRef(env, tp);
                    (*env)->DeleteLocalRef(env, tp);
                }
                res = g_aes_iv_key;
                break;
            default:
                res = (*env)->NewStringUTF(env, "unknown type");
                break;
        }
    }
    return res;

}


void debugLog(JNIEnv *env, __const char *msg) {
    if (g_jni_debug) {
        jstring data = (*env)->NewStringUTF(env, msg);
        (*env)->CallStaticVoidMethod(env, g_cls_jni_util, g_method_jni_print, data);
        (*env)->DeleteLocalRef(env, data);
    }
}