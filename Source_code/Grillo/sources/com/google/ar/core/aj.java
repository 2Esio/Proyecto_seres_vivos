package com.google.ar.core;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: SetupContentResolver */
class aj implements ArCoreApk.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ h f17a;

    static ArCoreApk.Availability a(Context context) {
        try {
            if (b(context) != null) {
                return ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD;
            }
            return ArCoreApk.Availability.SUPPORTED_INSTALLED;
        } catch (UnavailableDeviceNotCompatibleException e) {
            return ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE;
        } catch (UnavailableUserDeclinedInstallationException | RuntimeException e2) {
            return ArCoreApk.Availability.UNKNOWN_ERROR;
        }
    }

    static PendingIntent b(Context context) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        RuntimeException runtimeException;
        try {
            Bundle call = context.getContentResolver().call(a(""), "getSetupIntent", context.getPackageName(), (Bundle) null);
            if (call == null) {
                return null;
            }
            PendingIntent pendingIntent = (PendingIntent) call.getParcelable("intent");
            if (pendingIntent != null) {
                return pendingIntent;
            }
            String string = call.getString("exceptionType", "");
            if (string.isEmpty()) {
                return null;
            }
            if (string.equals(UnavailableDeviceNotCompatibleException.class.getName())) {
                throw new UnavailableDeviceNotCompatibleException();
            } else if (!string.equals(UnavailableUserDeclinedInstallationException.class.getName())) {
                Class<? extends U> asSubclass = Class.forName(string).asSubclass(RuntimeException.class);
                String string2 = call.getString("exceptionText", (String) null);
                if (string2 != null) {
                    runtimeException = (RuntimeException) asSubclass.getConstructor(new Class[]{String.class}).newInstance(new Object[]{string2});
                } else {
                    runtimeException = (RuntimeException) asSubclass.getConstructor(new Class[0]).newInstance(new Object[0]);
                }
                throw runtimeException;
            } else {
                throw new UnavailableUserDeclinedInstallationException();
            }
        } catch (ReflectiveOperationException | RuntimeException e) {
            Log.i("ARCore-SetupContentResolver", "Post-install failed", e);
            return null;
        }
    }

    public static Uri a(String str) {
        return new Uri.Builder().scheme("content").authority("com.google.ar.core.services.arcorecontentprovider").path(str).build();
    }

    aj(h hVar) {
        this.f17a = hVar;
    }

    public void a(ArCoreApk.Availability availability) {
        synchronized (this.f17a) {
            ArCoreApk.Availability unused = this.f17a.f = availability;
            boolean unused2 = this.f17a.g = false;
        }
    }
}
