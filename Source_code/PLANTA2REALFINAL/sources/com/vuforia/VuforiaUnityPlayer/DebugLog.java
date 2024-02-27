package com.vuforia.VuforiaUnityPlayer;

import android.util.Log;

public class DebugLog {
    private static final String LOGTAG = "Vuforia";

    public static final void LOGE(String str) {
        Log.e(LOGTAG, str);
    }

    public static final void LOGW(String str) {
        Log.w(LOGTAG, str);
    }

    public static final void LOGD(String str) {
        Log.d(LOGTAG, str);
    }

    public static final void LOGI(String str) {
        Log.i(LOGTAG, str);
    }
}
