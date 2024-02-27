package com.google.ar.core;

import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class ad implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicBoolean f11a;
    final /* synthetic */ ae b;

    ad(ae aeVar, AtomicBoolean atomicBoolean) {
        this.b = aeVar;
        this.f11a = atomicBoolean;
    }

    public final void run() {
        if (!this.f11a.getAndSet(true)) {
            Log.w("ARCore-InstallService", "requestInstall timed out, launching fullscreen.");
            ae aeVar = this.b;
            w.b(aeVar.f12a, aeVar.b);
        }
    }
}
