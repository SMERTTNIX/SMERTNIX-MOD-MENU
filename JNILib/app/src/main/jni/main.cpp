#include <pthread.h>
#include <jni.h>
#include <Includes/Utils.h>
#include <Substrate/SubstrateHook.h>
#include "KittyMemory/MemoryPatch.h"
#include <Icon.h>
extern "C" {
    /*Time For Bools And all*/
    bool feature1 = false, feature2 = false, feature3 = false;
    int speedHack = 0;
    struct My_Patches {MemoryPatch GodMode, SpeedHack, Patch1;} hexPatches;
    const char *libName = "libil2cpp.so";
    /*Time for Menu         */
    JNIEXPORT jstring JNICALL
    Java_il2cpp_Main_apk(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF("      Modded by (yourname)");// do not earse the space this for normal text view
    return str;
}
    JNIEXPORT jstring JNICALL
    Java_il2cpp_Main_down(
        JNIEnv *env,
        jobject activityObject) {
    jstring str = env->NewStringUTF("Tempale menu");
        return str;
    }
    
    JNIEXPORT jobjectArray  JNICALL
Java_il2cpp_Main_getFeatures(
        JNIEnv *env,
        jobject activityObject) {
    jobjectArray ret;
    //text = CRASH
    //ButtonSwitch = HL1
    //Button = HL2
    //SeekBar = Example = SeekBar_Name_0_2
    //Hide icon = KIRA
    const char *features[] = {
            "CRASH_Example",
            "HL1_The button1",
            "HL2_The button2",
            "HL1_The Memory hack Button", // in java Menu you can change this
            "SeekBar_The slider hook example_0_100",
            "SeekBar_The slider kittymemory example_0_3",
            "SeekBar_Slider 12_0_5",
            "SeekBar_Slider 2_0_2",
            "SeekBar_Slider 3_0_50",
            "CRASH_Icon && Menu",
            "KIRA_Icon invisible",
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
JNIEXPORT void JNICALL
Java_il2cpp_Main_Changes(
        JNIEnv *env,
        jobject activityObject,
        jint feature,
        jint value) {
    switch (feature) {
        case 1:
            feature1 = !feature1;
            if (feature1) {
                hexPatches.GodMode.Modify();
            } else {
                hexPatches.GodMode.Restore();
            }
            break;
        case 2:
            feature2 = !feature2;
            break;
        case 4:
            speedHack = value;
            break;
        case 5:
            if (value == 0) {
                hexPatches.SpeedHack.Restore();
            } else if (value == 1) {
                hexPatches.SpeedHack = MemoryPatch(libName, 0x10000,
                                                   "\x02\x00\xa0\xE3\x1E\xFF\x2F\xE1", 8);
                hexPatches.SpeedHack.Modify();
            } else if (value == 2) {
                hexPatches.SpeedHack = MemoryPatch(libName, 0x10000,
                                                   "\x03\x00\xa0\xE3\x1E\xFF\x2F\xE1", 8);
                hexPatches.SpeedHack.Modify();
            } else if (value == 3) {
                hexPatches.SpeedHack = MemoryPatch(libName, 0x10000,
                                                   "\x04\x00\xa0\xE3\x1E\xFF\x2F\xE1", 8);
                hexPatches.SpeedHack.Modify();
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
    if (feature == 6) {
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
    if (feature == 7) {
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
    if (feature == 8) {
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
// ---------- Hooking ---------- //

bool (*old_get_IsInvincible)(void *instance);

bool get_IsInvincible(void *instance) {
    if (instance != NULL && feature2) {
        return true;
    }
    //LOGI("get_IsInvincible");
    return old_get_IsInvincible(instance);
}

float (*old_get_MoveSpeedUpRate)(void *instance);

float get_MoveSpeedUpRate(void *instance) {
    if (instance != NULL && speedHack > 1) {
        //LOGI("get_MoveSpeedUpRate hacked");
        return (float) speedHack;
    }
    // LOGI("get_MoveSpeedUpRate");
    return old_get_MoveSpeedUpRate(instance);
}



void *hack_thread(void *) {
    
    ProcMap il2cppMap;
    do {
        il2cppMap = KittyMemory::getLibraryMap(libName);
        sleep(1);
    } while (!isLibraryLoaded(libName));
    hexPatches.GodMode = MemoryPatch::createWithHex("libil2cpp.so", 0xA672EE,
                                                    "00 00 A0 E3 1E FF 2F E1");

    // ---------- Hook ---------- //
    MSHookFunction((void *) getAbsoluteAddress(libName, 0xA62284), (void *) get_IsInvincible,
                   (void **) &old_get_IsInvincible);
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
