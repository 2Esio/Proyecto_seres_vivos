package com.google.ar.core;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.annotations.UsedByNative;
import com.google.ar.core.exceptions.ResourceExhaustedException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;
import java.util.HashMap;
import java.util.Map;

@UsedByNative("arcoreapk.cc")
class ArCoreApkJniAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<? extends Throwable>, Integer> f6a = new HashMap();

    static {
        f6a.put(IllegalArgumentException.class, Integer.valueOf(ag.ERROR_INVALID_ARGUMENT.A));
        f6a.put(ResourceExhaustedException.class, Integer.valueOf(ag.ERROR_RESOURCE_EXHAUSTED.A));
        f6a.put(UnavailableArcoreNotInstalledException.class, Integer.valueOf(ag.UNAVAILABLE_ARCORE_NOT_INSTALLED.A));
        f6a.put(UnavailableDeviceNotCompatibleException.class, Integer.valueOf(ag.UNAVAILABLE_DEVICE_NOT_COMPATIBLE.A));
        f6a.put(UnavailableApkTooOldException.class, Integer.valueOf(ag.UNAVAILABLE_APK_TOO_OLD.A));
        f6a.put(UnavailableSdkTooOldException.class, Integer.valueOf(ag.UNAVAILABLE_SDK_TOO_OLD.A));
        f6a.put(UnavailableUserDeclinedInstallationException.class, Integer.valueOf(ag.UNAVAILABLE_USER_DECLINED_INSTALLATION.A));
    }

    ArCoreApkJniAdapter() {
    }

    @UsedByNative("arcoreapk.cc")
    static int checkAvailability(Context context) {
        try {
            return ArCoreApk.getInstance().checkAvailability(context).nativeCode;
        } catch (Throwable th) {
            a(th);
            return ArCoreApk.Availability.UNKNOWN_ERROR.nativeCode;
        }
    }

    private static int a(Throwable th) {
        Log.e("ARCore-ArCoreApkJniAdapter", "Exception details:", th);
        Class<?> cls = th.getClass();
        if (f6a.containsKey(cls)) {
            return f6a.get(cls).intValue();
        }
        return ag.ERROR_FATAL.A;
    }

    @UsedByNative("arcoreapk.cc")
    static int requestInstall(Activity activity, boolean z, int[] iArr) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        try {
            iArr[0] = ArCoreApk.getInstance().requestInstall(activity, z).nativeCode;
            return ag.SUCCESS.A;
        } catch (Throwable th) {
            return a(th);
        }
    }

    @UsedByNative("arcoreapk.cc")
    static int requestInstallCustom(Activity activity, boolean z, int i, int i2, int[] iArr) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        try {
            iArr[0] = ArCoreApk.getInstance().requestInstall(activity, z, ArCoreApk.InstallBehavior.forNumber(i), ArCoreApk.UserMessageType.forNumber(i2)).nativeCode;
            return ag.SUCCESS.A;
        } catch (Throwable th) {
            return a(th);
        }
    }
}
