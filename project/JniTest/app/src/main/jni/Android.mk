LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := jniutil
LOCAL_LDFLAGS := -Wl,--build-id
LOCAL_SRC_FILES := \
	/home/yangjingan/work/code/appstoredoc/mycode/project/JniTest/app/src/main/jni/global.c \
	/home/yangjingan/work/code/appstoredoc/mycode/project/JniTest/app/src/main/jni/util.c \
	/home/yangjingan/work/code/appstoredoc/mycode/project/JniTest/app/src/main/jni/jniutil.c \

LOCAL_C_INCLUDES += /home/yangjingan/work/code/appstoredoc/mycode/project/JniTest/app/src/main/jni
LOCAL_C_INCLUDES += /home/yangjingan/work/code/appstoredoc/mycode/project/JniTest/app/src/release/jni

include $(BUILD_SHARED_LIBRARY)