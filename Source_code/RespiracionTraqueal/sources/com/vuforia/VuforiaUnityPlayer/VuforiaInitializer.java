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

    public static void setInitParameters(Activity activity, int graphicsAPI, String licenseKey) {
        setInitParametersNative(activity, graphicsAPI, licenseKey);
    }

    private static boolean loadLibrary(String nLibName) {
        try {
            System.loadLibrary(nLibName);
            return true;
        } catch (UnsatisfiedLinkError ulee) {
            DebugLog.LOGE("The library lib" + nLibName + ".so could not be loaded: " + ulee.toString());
            return false;
        } catch (SecurityException e) {
            DebugLog.LOGE("The library lib" + nLibName + ".so was not allowed to be loaded");
            return false;
        }
    }
}
