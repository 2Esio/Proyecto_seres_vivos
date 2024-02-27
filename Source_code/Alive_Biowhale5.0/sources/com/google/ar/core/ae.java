package com.google.ar.core;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class ae implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f12a;
    final /* synthetic */ u b;
    final /* synthetic */ w c;

    ae(w wVar, Activity activity, u uVar) {
        this.c = wVar;
        this.f12a = activity;
        this.b = uVar;
    }

    public final void run() {
        try {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            this.c.c.a(this.f12a.getApplicationInfo().packageName, Collections.singletonList(w.b()), new Bundle(), new ac(this, atomicBoolean));
            new Handler().postDelayed(new ad(this, atomicBoolean), 3000);
        } catch (RemoteException e) {
            Log.w("ARCore-InstallService", "requestInstall threw, launching fullscreen.", e);
            w.b(this.f12a, this.b);
        }
    }
}
