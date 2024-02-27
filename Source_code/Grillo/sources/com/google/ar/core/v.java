package com.google.ar.core;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.a.b.a.a.a.e;
import com.google.ar.core.ArCoreApk;

/* compiled from: InstallServiceImpl */
final class v extends e {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ s f42a;

    v(s sVar) {
        this.f42a = sVar;
    }

    public final void b(Bundle bundle) throws RemoteException {
        int i = bundle.getInt("error.code", -100);
        if (i == -5) {
            Log.e("ARCore-InstallService", "The device is not supported.");
            this.f42a.f39a.a(ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE);
        } else if (i == -3) {
            Log.e("ARCore-InstallService", "The Google Play application must be updated.");
            this.f42a.f39a.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        } else if (i != 0) {
            StringBuilder sb = new StringBuilder(33);
            sb.append("requestInfo returned: ");
            sb.append(i);
            Log.e("ARCore-InstallService", sb.toString());
            this.f42a.f39a.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        } else {
            this.f42a.f39a.a(ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED);
        }
    }

    public final void a(Bundle bundle) throws RemoteException {
    }

    public final void a() throws RemoteException {
    }
}
