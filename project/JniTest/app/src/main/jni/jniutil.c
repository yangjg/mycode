//
// Created by yangjingan on 18-1-8.
//
#include "com_othershe_jnitest_JniUtil.h"
#include "string.h"
#include "global.h"
JNIEXPORT jstring  JNICALL Java_com_othershe_jnitest_JniUtil_test(JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "jni调用成功");
}


JNIEXPORT jstring JNICALL Java_com_othershe_jnitest_JniUtil_StrEncrypt
        (JNIEnv *env, jobject obj, jstring content) {
    /*  jclass  cls = (*env)->GetObjectClass(env, obj);
      jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,cls,"encrypt","(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
      jstring pri = (*env)->NewStringUTF(env,priv_key);
      jstring iv =(*env)->NewStringUTF(env,iv_key);
      jstring res = (jstring)((*env)->CallStaticObjectMethod(env,cls,aesEncrypt,content,pri,iv));
      (*env)->DeleteLocalRef(env,pri);
      (*env)->DeleteLocalRef(env,iv);*/
/*    jclass  cls = (*env)->GetObjectClass(env, obj);
    jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,cls,"print","(Ljava/lang/String;)Ljava/lang/String;");
    jstring data =(*env)->NewStringUTF(env,priv_key);
    jstring res =(*env)->CallStaticObjectMethod(env,cls,aesEncrypt,data);*/
    /*jstring  res =(*env)->NewStringUTF(env,"jni aesencrypt call success");
    (*env)->DeleteLocalRef(env,data);*/

    jstring  res = NULL;
    jstring data;
    jclass  cls = (*env)->GetObjectClass(env, obj);
    jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,cls,"print","(Ljava/lang/String;)V");
    jobject  m_cipher;
    jmethodID doFinal;
    if(NULL==m_cipher || NULL==doFinal) {

        __const char *CIPHER = "javax/crypto/Cipher";
        __const char *SecretKey = "javax/crypto/spec/SecretKeySpec";
        __const char *IVParameterSpec = "javax/crypto/spec/IvParameterSpec";
        //查找javax.crypto.Cipher类和 javax.crypto.spec.SecretKeySpec,javax.crypto.spec.IvParameterSpec
        jclass cipherCls = (*env)->FindClass(env, CIPHER);
        jclass secretCls = (*env)->FindClass(env, SecretKey);
        jclass ivparameterCls = (*env)->FindClass(env, IVParameterSpec);

         data =(*env)->NewStringUTF(env,"finclass success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取SecretKeySpec类的getInstance方法；
        jmethodID instance = (*env)->GetStaticMethodID(env, cipherCls, "getInstance",
                                                       "(Ljava/lang/String;)Ljavax/crypto/Cipher;");

        //把CIPHER_ALGORITHM,KEY_ALGORITHM 转换成string
        jstring algorithm = (*env)->NewStringUTF(env, CIPHER_ALGORITHM);
        jstring key_algorithm = (*env)->NewStringUTF(env, KEY_ALGORITHM);

        //调用Cipher的静态方法 getInstance，得到 Cipher对象；
        m_cipher = (*env)->CallStaticObjectMethod(env, cipherCls, instance, algorithm);

        data =(*env)->NewStringUTF(env,"Cipher getInstance success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);


        //把iv_key，priv_key转换成jbyteArray 数组;
        jsize len = strlen(priv_key);
        jbyteArray keybytes = (*env)->NewByteArray(env, len);
        if (NULL != keybytes) {
            (*env)->SetByteArrayRegion(env, keybytes, 0, len, (jbyte *) priv_key);
        }

        jsize ivlen = strlen(iv_key);
        jbyteArray ivbytes = (*env)->NewByteArray(env, ivlen);
        if (NULL != ivbytes) {
            (*env)->SetByteArrayRegion(env, ivbytes, 0, ivlen, (jbyte *) iv_key);
        }
        data =(*env)->NewStringUTF(env,"priv_key转换成jbyteArray success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取 SecretKeySpec类的对象的构造函数
        jmethodID secretTor = (*env)->GetMethodID(env, secretCls, "<init>",
                                                  "([BLjava/lang/String;)V");

        //构造 SecretKeySpec 对象
        jobject secretObj = (*env)->NewObject(env, secretCls, secretTor, keybytes, key_algorithm);


        //获取 IvParameterSpec的构造方法
        jmethodID ivTor = (*env)->GetMethodID(env, ivparameterCls, "<init>", "([B)V");

        //构造IvParameterSpec对象;
        jobject ivobj = (*env)->NewObject(env, ivparameterCls, ivTor, ivbytes);

        data =(*env)->NewStringUTF(env,"构造IvParameterSpec对象 success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取Cipher类的init方法;
        int opmode = 1;
        jmethodID initId = (*env)->GetMethodID(env, cipherCls, "init",
                                               "(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V");

        (*env)->CallVoidMethod(env, m_cipher, initId, opmode, secretObj, ivobj);

        doFinal =(*env)->GetMethodID(env,cipherCls,"doFinal","([B)[B");

        data =(*env)->NewStringUTF(env,"获取doFinal方法 success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

    }


    __const char* str =(__const char*)content;

    //把jstring转换成jbyteArray;
    jsize slen = strlen(str);
    jbyteArray strbytes = (*env)->NewByteArray(env, slen);
    if (NULL != strbytes) {
        (*env)->SetByteArrayRegion(env, strbytes, 0, slen, (jbyte *) str);
    }


    data =(*env)->NewStringUTF(env,"content to jbytearray success");
    (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);


    //调用doFinal方法
     jbyteArray resData = (jbyteArray)((*env)->CallObjectMethod(env,m_cipher,doFinal,strbytes));




    data =(*env)->NewStringUTF(env,"doFinal call ok");
    (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);


    //把jbyteArray转换成jstring;
    res = stoJstring(env,resData);

    data =(*env)->NewStringUTF(env,"resData to jstring success");
    (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

    return res;
}

JNIEXPORT jbyteArray JNICALL Java_com_othershe_jnitest_JniUtil_byteEncrypt
        (JNIEnv *env, jobject obj, jbyteArray bytes){

  /*  static jobject  s_cipher;
    static jmethodID s_doFinal;

    jstring  res = NULL;
    jstring data;
    jclass  cls = (*env)->GetObjectClass(env, obj);
    jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,cls,"print","(Ljava/lang/String;)V");
    if(NULL==s_cipher || NULL==s_doFinal) {

        __const char *CIPHER = "javax/crypto/Cipher";
        __const char *SecretKey = "javax/crypto/spec/SecretKeySpec";
        __const char *IVParameterSpec = "javax/crypto/spec/IvParameterSpec";
        //查找javax.crypto.Cipher类和 javax.crypto.spec.SecretKeySpec,javax.crypto.spec.IvParameterSpec
        jclass cipherCls = (*env)->FindClass(env, CIPHER);
        jclass secretCls = (*env)->FindClass(env, SecretKey);
        jclass ivparameterCls = (*env)->FindClass(env, IVParameterSpec);

        data =(*env)->NewStringUTF(env,"finclass success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取SecretKeySpec类的getInstance方法；
        jmethodID instance = (*env)->GetStaticMethodID(env, cipherCls, "getInstance",
                                                       "(Ljava/lang/String;)Ljavax/crypto/Cipher;");

        //把CIPHER_ALGORITHM,KEY_ALGORITHM 转换成string
        jstring algorithm = (*env)->NewStringUTF(env, CIPHER_ALGORITHM);
        jstring key_algorithm = (*env)->NewStringUTF(env, KEY_ALGORITHM);

        //调用Cipher的静态方法 getInstance，得到 Cipher对象；
        jobject m_cipher = (*env)->CallStaticObjectMethod(env, cipherCls, instance, algorithm);

        data =(*env)->NewStringUTF(env,"Cipher getInstance success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);


        //把iv_key，priv_key转换成jbyteArray 数组;
        jsize len = strlen(priv_key);
        jbyteArray keybytes = (*env)->NewByteArray(env, len);
        if (NULL != keybytes) {
            (*env)->SetByteArrayRegion(env, keybytes, 0, len, (jbyte *) priv_key);
        }

        jsize ivlen = strlen(iv_key);
        jbyteArray ivbytes = (*env)->NewByteArray(env, ivlen);
        if (NULL != ivbytes) {
            (*env)->SetByteArrayRegion(env, ivbytes, 0, ivlen, (jbyte *) iv_key);
        }
        data =(*env)->NewStringUTF(env,"priv_key转换成jbyteArray success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取 SecretKeySpec类的对象的构造函数
        jmethodID secretTor = (*env)->GetMethodID(env, secretCls, "<init>",
                                                  "([BLjava/lang/String;)V");

        //构造 SecretKeySpec 对象
        jobject secretObj = (*env)->NewObject(env, secretCls, secretTor, keybytes, key_algorithm);


        //获取 IvParameterSpec的构造方法
        jmethodID ivTor = (*env)->GetMethodID(env, ivparameterCls, "<init>", "([B)V");

        //构造IvParameterSpec对象;
        jobject ivobj = (*env)->NewObject(env, ivparameterCls, ivTor, ivbytes);

        data =(*env)->NewStringUTF(env,"构造IvParameterSpec对象 success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        //获取Cipher类的init方法;
        int opmode = 1;
        jmethodID initId = (*env)->GetMethodID(env, cipherCls, "init",
                                               "(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V");

        (*env)->CallVoidMethod(env, m_cipher, initId, opmode, secretObj, ivobj);

        jmethodID doFinal =(*env)->GetMethodID(env,cipherCls,"doFinal","([B)[B");

        data =(*env)->NewStringUTF(env,"获取doFinal方法 success");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        s_cipher =(*env)->NewGlobalRef(env,m_cipher);
        s_doFinal = (*env)->NewGlobalRef(env,doFinal);
        (*env)->DeleteLocalRef(env,m_cipher);
        (*env)->DeleteLocalRef(env, doFinal);
    }




    //调用doFinal方法
    jbyteArray resData = (jbyteArray)((*env)->CallObjectMethod(env,s_cipher,s_doFinal,bytes));


    data =(*env)->NewStringUTF(env,"doFinal call ok");
    (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);*/

   /* jstring  res = NULL;
    jstring data;
    jclass  cls = (*env)->GetObjectClass(env, obj);
    jmethodID  aesEncrypt = (*env)->GetStaticMethodID(env,cls,"print","(Ljava/lang/String;)V");
    if (stringClass == NULL) {
        data =(*env)->NewStringUTF(env,"stringClass is null");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);

        jclass localRefCls =
                (*env)->FindClass(env, "java/lang/String");
        if (localRefCls == NULL) {
            return NULL; *//* exception thrown *//*
        }
        *//* Create a global reference *//*
        stringClass = (*env)->NewGlobalRef(env, localRefCls);
        *//* The local reference is no longer useful *//*
        (*env)->DeleteLocalRef(env, localRefCls);
        *//* Is the global reference created successfully? *//*
        if (stringClass == NULL) {
            return NULL; *//* out of memory exception thrown *//*
        }
    }
    else{
        data =(*env)->NewStringUTF(env,"stringClass is not null");
        (*env)->CallStaticVoidMethod(env,cls,aesEncrypt,data);
    }*/
    return getEncrypt(env,bytes);

}





JNIEXPORT jstring stoJstring(JNIEnv* env, jbyteArray bytes)
{
    jclass strClass = (*env)->FindClass(env,"java/lang/String");
    jmethodID ctorID = (*env)->GetMethodID(env,strClass, "<init>", "([BLjava/lang/String;)V");
    jstring encoding = (*env)->NewStringUTF(env,"utf-8");
    return (jstring)(*env)->NewObject(env, strClass, ctorID, bytes, encoding);
}

