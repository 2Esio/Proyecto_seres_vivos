package com.google.ar.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.ResourceExhaustedException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import java.util.HashMap;
import java.util.Map;

class ArCoreApkJniAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<? extends Throwable>, Integer> f7a;

    ArCoreApkJniAdapter() {
    }

    static int checkAvailability(Context context) {
        try {
            return ArCoreApk.getInstance().checkAvailability(context).nativeCode;
        } catch (Throwable th) {
            a(th);
            return ArCoreApk.Availability.UNKNOWN_ERROR.nativeCode;
        }
    }

    static int requestInstall(Activity activity, boolean z, int[] iArr) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        try {
            iArr[0] = ArCoreApk.getInstance().requestInstall(activity, z).nativeCode;
            return Session.c.SUCCESS.j;
        } catch (Throwable th) {
            return a(th);
        }
    }

    static int requestInstallCustom(Activity activity, boolean z, int i, int i2, int[] iArr) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        try {
            iArr[0] = ArCoreApk.getInstance().requestInstall(activity, z, ArCoreApk.InstallBehavior.forNumber(i), ArCoreApk.UserMessageType.forNumber(i2)).nativeCode;
            return Session.c.SUCCESS.j;
        } catch (Throwable th) {
            return a(th);
        }
    }

    private static int a(Throwable th) {
        Log.e("ARCore-ArCoreApkJniAdapter", "Exception details:", th);
        Class<?> cls = th.getClass();
        if (f7a.containsKey(cls)) {
            return f7a.get(cls).intValue();
        }
        return Session.c.ERROR_FATAL.j;
    }

    static {
        HashMap hashMap = new HashMap();
        f7a = hashMap;
        hashMap.put(IllegalArgumentException.class, Integer.valueOf(Session.c.ERROR_INVALID_ARGUMENT.j));
        f7a.put(ResourceExhaustedException.class, Integer.valueOf(Session.c.ERROR_RESOURCE_EXHAUSTED.j));
        f7a.put(UnavailableArcoreNotInstalledException.class, Integer.valueOf(Session.c.UNAVAILABLE_ARCORE_NOT_INSTALLED.j));
        f7a.put(UnavailableDeviceNotCompatibleException.class, Integer.valueOf(Session.c.UNAVAILABLE_DEVICE_NOT_COMPATIBLE.j));
        f7a.put(UnavailableApkTooOldException.class, Integer.valueOf(Session.c.UNAVAILABLE_APK_TOO_OLD.j));
        f7a.put(UnavailableSdkTooOldException.class, Integer.valueOf(Session.c.UNAVAILABLE_SDK_TOO_OLD.j));
        f7a.put(UnavailableUserDeclinedInstallationException.class, Integer.valueOf(Session.c.UNAVAILABLE_USER_DECLINED_INSTALLATION.j));
    }
}
