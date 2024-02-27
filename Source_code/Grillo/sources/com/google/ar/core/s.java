package com.google.ar.core;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.ar.core.ArCoreApk;

/* compiled from: InstallServiceImpl */
final class s implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ArCoreApk.a f39a;
    private final /* synthetic */ Context b;
    private final /* synthetic */ n c;

    s(n nVar, Context context, ArCoreApk.a aVar) {
        this.c = nVar;
        this.b = context;
        this.f39a = aVar;
    }

    public final void run() {
        try {
            this.c.d.a(this.b.getApplicationInfo().packageName, n.b(), new v(this));
        } catch (RemoteException e) {
            Log.e("ARCore-InstallService", "requestInfo threw", e);
            this.f39a.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        }
    }
}
