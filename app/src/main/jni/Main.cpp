#include <pthread.h>
#include <jni.h>
#include <Includes/Utils.h>
#include <Substrate/SubstrateHook.h>
#include "KittyMemory/MemoryPatch.h"
#include <Icon.h>
#include "Unity/Quaternion.h"
#include "Unity/Vector3.h"
#include "Includes/Obfuscate.h"
#include <test.h>
#include "Includes/Chams.h"
#pragma once
#include <sstream>


extern "C" {
    //список функций и присвоение значений true/false
    //так же можем их прописывать через запятую для экономии места
    //пример | bool knife, god2,aim = false;
	bool knife = false;
	bool god2 = false;
    bool aim = false; 
   
    
    
    //присваиваем в память нашу функцию
    struct My_Patches {MemoryPatch Knife, God2, Aim;} hexPatches;
	
    //Либа в которую будем хукать оффсеты
    const char *libName = "libil2cpp.so";
	
    // главные титлы в меню
    JNIEXPORT jstring JNICALL
    Java_il2cpp_Main_apk(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF("SMERTNIX MODDING ");
    return str;
}
    JNIEXPORT jstring JNICALL
    Java_il2cpp_Main_down(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF(" Telegram : @nikita_pcholkin\n");
        return str;
    }
    
    JNIEXPORT jobjectArray  JNICALL
Java_il2cpp_Main_getFeatures(
        JNIEnv *env,
        jobject activityObject) {
    jobjectArray ret;
    

    //текст = TEXT
    //ButtonSwitch = SWITCH
    //Кнопка = BUTTON
    //Ползунок = Example = SeekBar_Name_0_2
    //Скрыть иконку = KIRA
    
    //Добавление и список кнопок в меню
    
    const char *features[] = {
            "TEXT_",
		    "TEXT_Выберите функцию",//0
		    "SWITCH_GOD MODE",//1 
            "SWITCH_GOD MODE2",//1 
            "SWITCH_AIMBOT",
            "BT_Кнопка",
            "TEXT_Other",
			"KIRA_ʜɪᴅᴇ ɪᴄᴏɴ",//98
	  
            };

    int Total_Feature = (sizeof features /
                         sizeof features[0]); //Now you dont have to manually update the number everytime;

    ret = (jobjectArray) env->NewObjectArray(Total_Feature, env->FindClass("java/lang/String"),
                                             env->NewStringUTF(""));
    int i;
    for (i = 0; i < Total_Feature; i++)
        env->SetObjectArrayElement(ret, i, env->NewStringUTF(features[i]));
    return (ret);
} 

//добавление логики к кнопкам и указывание их параметров

JNIEXPORT void JNICALL
Java_il2cpp_Main_Changes(
        JNIEnv *env,
        jobject activityObject,
        jint feature,
        jint value) {
    switch (feature) {
	    case 1:
		knife = !knife;
		if (knife) {
			hexPatches.Knife.Modify();
		} else {
			hexPatches.Knife.Restore();
		}
		break;
        
        case 2:
        god2 = !god2;
        if (god2) {
            hexPatches.God2.Modify();
        } else {
            hexPatches.God2.Restore();
        }
		break;
        
        case 3:  
        aim = !aim;
        if (aim) {
            hexPatches.Aim.Modify();
        } else {
            hexPatches.Aim.Restore();
        }
		break;
  
  }
}
JNIEXPORT jstring JNICALL
Java_il2cpp_typefaces_Menu_SliderString(
        JNIEnv *env,
        jobject clazz, jint feature, jint value) {
    // You must count your features from 0, not 1
    const char *SliderStr;
    if (feature == 500) {
        switch (value) {
            case 0:
                SliderStr = "Default";
                break;
            case 1:
                SliderStr = "2x";
                break;
            case 2:
                SliderStr = "4x";
                break;
            case 3:
                SliderStr = "8x";
                break;
            case 4:
                SliderStr = "12x";
                break;
            case 5:
                SliderStr = "24x";
                break;
        }
        return env->NewStringUTF(SliderStr);
    }
    if (feature == 500) {
        switch (value) {
            case 0:
                SliderStr = "Neck";
                break;
            case 1:
                SliderStr = "Hip";
                break;
            case 2:
                SliderStr = "Head";
                break;
        }
        return env->NewStringUTF(SliderStr);
    }
    if (feature == 500) {
        if (value <= 15){
            SliderStr = "Low";
        }
        else if (value >= 15 && value <= 35){
            SliderStr = "Medium";
        }
        else if (value >= 35){
            SliderStr = "High";
        }
        return env->NewStringUTF(SliderStr);
    }
    return env->NewStringUTF(NULL);
}
}
// ---------- Процесс хукания оффсетов ---------- //

void *hack_thread(void *) {
    
    ProcMap il2cppMap;
    do {
        il2cppMap = KittyMemory::getLibraryMap(libName);
        sleep(1);
    } while (!isLibraryLoaded(libName));
    
    
	            ////////ВЫДАЧА///////////
                // оффсет - какая-то ячейки в памяти игры
                // хекс - новое значение которое мы хотим присвоить к ячейке
	            hexPatches.Knife = MemoryPatch::createWithHex("libil2cpp.so", 0xAF3060, "01 00 A0 E3 1E FF 2F E1");
	            hexPatches.God2 = MemoryPatch::createWithHex("libil2cpp.so", 0xAF3744, "01 00 A0 E3 1E FF 2F E1");
	            hexPatches.Aim = MemoryPatch::createWithHex("libil2cpp.so", 0xAF3744, "20 20 31 30 30 30");
    return NULL;
}

JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *globalEnv;
    vm->GetEnv((void **) &globalEnv, JNI_VERSION_1_6);

    // Create a new thread so it does not block the main thread, means the game would not freeze
    pthread_t ptid;
    pthread_create(&ptid, NULL, hack_thread, NULL);

    return JNI_VERSION_1_6;
}

JNIEXPORT void JNICALL
JNI_OnUnload(JavaVM *vm, void *reserved) {}
