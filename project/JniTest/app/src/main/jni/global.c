//
// Created by yangjingan on 18-1-10.
//

#include "stdbool.h"
#include "string.h"
#include "jni.h"
#include "global.h"


/*extern jclass g_cls_cipher;
extern jclass g_cls_secret_spec;
extern jclass g_cls_iv_parameter;
extern jmethodID g_method_cipher_get_instance;
extern jmethodID g_method_cipher_init;
extern jmethodID g_method_cipher_do_final;
extern jmethodID g_method_secret_tor;
extern jmethodID g_method_iv_tor;
extern jstring  g_string_algorithm;
extern jstring  g_string_key_algorithm;
extern jobject  g_object_cipher;
extern jobject  g_object_secret_spec;
extern jobject  g_object_iv_parameter;
extern jbyteArray g_private_bytes;
extern jbyteArray g_iv_bytes;*/
bool isSecurity(JNIEnv *env) {
    bool res = false ;
    if (initClass(env)) {
#ifdef DEBUG_SO
        jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,g_cls_jni_util,"print","(Ljava/lang/String;)V");
        jstring data = (*env)->NewStringUTF(env, "begin call  checkedSign");
        (*env)->CallStaticVoidMethod(env, g_cls_jni_util, aesEncrypt, data);
#endif
        res = checkedSign(env);

#ifdef DEBUG_SO
        __const char* msg;
        if(g_check_sign){
            msg ="check sign success";
        }
        else{
            msg ="check sign failed, can't call any jni method";
        }
        data = (*env)->NewStringUTF(env, msg);
        (*env)->CallStaticVoidMethod(env, g_cls_jni_util, aesEncrypt, data);
        (*env)->DeleteLocalRef(env, data);
#endif
    }
    return res;
}

bool checkedSign(JNIEnv *env) {
    if (g_check_sign) {
        return g_check_sign;
    }

    __const char *apkMd5 = "6C43F4D93B1E66BFDF9D3C44C46E72D3";
    jmethodID signatureDigest = (*env)->GetStaticMethodID(env, g_cls_jni_util, "signatureDigest", "()[Ljava/lang/String;");
    jobjectArray ja = (*env)->CallStaticObjectMethod(env, g_cls_jni_util, signatureDigest);
    jsize len = (*env)->GetArrayLength(env, ja);
    jstring jstr;
    char **pstr = (char **) malloc(len * sizeof(char *));
    int i=0;
    for (i = 0; i < len; ++i) {
        jstr = (*env)->GetObjectArrayElement(env, ja, i);
        pstr[i] = (char *) (*env)->GetStringUTFChars(env, jstr, 0);
        if (0 == strcmp(apkMd5, pstr[i])) {
            g_check_sign = true;
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
    __const char *JniUtil = "com/othershe/jnitest/JniUtil";
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


#ifdef DEBUG_SO
    jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,g_cls_jni_util,"print","(Ljava/lang/String;)V");
    jstring data = (*env)->NewStringUTF(env, "find all class success");
    (*env)->CallStaticVoidMethod(env, g_cls_jni_util, aesEncrypt, data);
#endif
    //获取SecretKeySpec类的getInstance方法；
    jmethodID instance = (*env)->GetStaticMethodID(env, g_cls_cipher, "getInstance",
                                                   "(Ljava/lang/String;)Ljavax/crypto/Cipher;");
    g_method_cipher_get_instance = instance;

    //把CIPHER_ALGORITHM,KEY_ALGORITHM 转换成string
    __const char *G_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    __const char *G_KEY_ALGORITHM = "AES";;
    __const char *G_PRIVATE_KEY = "[.7=a$K#z)d3Eu^P";
    __const char *G_IV_KEY = "_g7=a$K#)d*E#fg7";
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

    (*env)->CallVoidMethod(env, g_object_cipher, g_method_cipher_init, 1, g_object_secret_spec,
                           g_object_iv_parameter);

    jmethodID doFinal = (*env)->GetMethodID(env, g_cls_cipher, "doFinal", "([B)[B");
    g_method_cipher_do_final = doFinal;


#ifdef DEBUG_SO
     data = (*env)->NewStringUTF(env, "init all object and  method success");
    (*env)->CallStaticVoidMethod(env, g_cls_jni_util, aesEncrypt, data);
    (*env)->DeleteLocalRef(env, data);
#endif

    return true;
}

jbyteArray getEncrypt(JNIEnv *env, jbyteArray bytes) {
    jbyteArray res = NULL;
    if (isSecurity(env)) {
        jbyteArray resData = (jbyteArray) ((*env)->CallObjectMethod(env, g_object_cipher,
                                                                    g_method_cipher_do_final, bytes));
        res = resData;
    }
    return res;
}