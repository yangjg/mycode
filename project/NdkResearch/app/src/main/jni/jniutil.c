//
// Created by yangjingan on 18-1-8.
//

#include "com_example_yangjingan_ndkresearch_JniUtil.h"
JNIEXPORT jstring JNICALL Java_com_example_yangjingan_ndkresearch_JniUtil_test
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env,"jni调用成功");
}