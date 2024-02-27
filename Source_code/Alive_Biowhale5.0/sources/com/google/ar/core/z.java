package com.google.ar.core;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.ar.core.ArCoreApk;

/* compiled from: InstallServiceImpl */
final class z implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f43a;
    final /* synthetic */ i b;
    final /* synthetic */ w c;

    z(w wVar, Context context, i iVar) {
        this.c = wVar;
        this.f43a = context;
        this.b = iVar;
    }

    public final void run() {
        try {
            this.c.c.a(this.f43a.getApplicationInfo().packageName, w.b(), new y(this));
        } catch (RemoteException e) {
            Log.e("ARCore-InstallService", "requestInfo threw", e);
            this.b.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        }
    }
}
