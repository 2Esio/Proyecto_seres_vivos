package com.google.ar.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class x implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f44a;
    final /* synthetic */ p b;
    final /* synthetic */ n c;

    x(n nVar, Activity activity, p pVar) {
        this.c = nVar;
        this.f44a = activity;
        this.b = pVar;
    }

    public final void run() {
        try {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.c.d.a(this.f44a.getApplicationInfo().packageName, Collections.singletonList(n.b()), new Bundle(), new w(this, atomicBoolean));
            new Handler().postDelayed(new z(this, atomicBoolean), 3000);
        } catch (RemoteException e) {
            Log.w("ARCore-InstallService", "requestInstall threw, launching fullscreen.", e);
            n.b(this.f44a, this.b);
        }
    }
}
