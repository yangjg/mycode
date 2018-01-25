//
// Created by yangjingan on 18-1-10.
//

#ifndef JNITEST_GLOBAL_H
#define JNITEST_GLOBAL_H
#endif //JNITEST_GLOBAL_H
#ifdef __cplusplus
extern "C" {
#endif

/*#ifndef DEBUG_SO
#define DEBUG_SO
#endif*/

#include "jni.h"
#include "string.h"
#include <stdbool.h>
static jclass g_cls_jni_util;
static jclass g_cls_cipher;
static jclass g_cls_secret_spec;
static jclass g_cls_iv_parameter;
static jmethodID g_method_jni_print;
static jmethodID g_method_cipher_get_instance;
static jmethodID g_method_cipher_init;
static jmethodID g_method_cipher_do_final;
static jmethodID g_method_secret_tor;
static jmethodID g_method_iv_tor;
static jstring  g_string_algorithm;
static jstring  g_string_key_algorithm;
static jobject  g_object_cipher;
static jobject  g_object_secret_spec;
static jobject  g_object_iv_parameter;
static jbyteArray g_private_bytes;
static jbyteArray g_iv_bytes;
static jboolean  g_check_sign;
static jboolean g_jni_debug;
static jint g_mode;
static jstring  g_app_sign_key;
static jstring  g_app_code_sign_key;
static jstring  g_upload_sign_key;
static jstring  g_game_sign_key;
static jstring  g_game_code_sign_key;

static __const int APP_CODE_SIGN_TYPE = 1 ;
static __const int APP_KEY_SIGN_TYPE = 2 ;
static __const int UPLOAD_SIGN_TYPE = 3 ;
static __const int GAME_CODE_SIGN_TYPE = 4 ;
static __const int GAME_KEY_SIGN_TYPE = 5 ;

/*__const char* G_PRIVATE_KEY ="[.7=a$K#z)d3Eu^P";

__const char* G_IV_KEY ="_g7=a$K#)d*E#fg7";

__const char*  G_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

__const char* G_KEY_ALGORITHM = "AES";*/


jbyteArray getEncrypt(JNIEnv*, jbyteArray,jint);

bool checkedSign(JNIEnv*);
bool isSecurity(JNIEnv*);
bool initClass(JNIEnv*);
jstring getAppSignKey(JNIEnv*,jint type);
void debugLog(JNIEnv*,__const char*);
#ifdef __cplusplus
}
#endif