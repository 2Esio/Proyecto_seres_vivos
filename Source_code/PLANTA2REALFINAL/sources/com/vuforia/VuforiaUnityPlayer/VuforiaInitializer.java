package com.vuforia.VuforiaUnityPlayer;

import android.app.Activity;

public class VuforiaInitializer {
    private static final String NATIVE_LIB_UNITYPLAYER = "VuforiaUnityPlayer";
    private static final String NATIVE_LIB_VUFORIA = "Vuforia";
    private static final String NATIVE_LIB_VUFORIAWRAPPER = "VuforiaWrapper";

    private static native void initPlatformNative();

    private static native void setInitParametersNative(Activity activity, int i, String str);

    public static void loadNativeLibraries() {
        loadLibrary(NATIVE_LIB_VUFORIA);
        loadLibrary(NATIVE_LIB_VUFORIAWRAPPER);
        loadLibrary(NATIVE_LIB_UNITYPLAYER);
    }

    public static void initPlatform() {
        initPlatformNative();
    }

    public static void setInitParameters(Activity activity, int i, String str) {
        setInitParametersNative(activity, i, str);
    }

    private static boolean loadLibrary(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            DebugLog.LOGE("The library lib" + str + ".so could not be loaded: " + e.toString());
            return false;
        } catch (SecurityException unused) {
            DebugLog.LOGE("The library lib" + str + ".so was not allowed to be loaded");
            return false;
        }
    }
}
