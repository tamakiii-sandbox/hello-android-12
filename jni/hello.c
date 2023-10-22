#include <jni.h>
#include <string.h>

JNIEXPORT jstring JNICALL
Java_com_tamakiii_tamakiii_1sandbox_hello_1android_112_MainActivity_getMessage(JNIEnv *env, jobject this) {
    return (*env)->NewStringUTF(env, "Hello World from C!");
}
