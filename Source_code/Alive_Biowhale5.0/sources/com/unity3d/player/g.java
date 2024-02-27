package com.unity3d.player;

import android.util.Log;

final class g {

    /* renamed from: a  reason: collision with root package name */
    protected static boolean f108a = false;

    protected static void Log(int i, String str) {
        if (!f108a) {
            if (i == 6) {
                Log.e("Unity", str);
            }
            if (i == 5) {
                Log.w("Unity", str);
            }
        }
    }
}
