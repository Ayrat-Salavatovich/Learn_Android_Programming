#include <string.h>
#include <jni.h>

jstring Java_ayrat_salavatovich_gmail_com_day_164_hellondk_MainActivity_naGetHelloNDKStr(
		JNIEnv* pEnv, jobject pObj) {
	return (*pEnv)->NewStringUTF(pEnv, "Hello NDK!");
}
