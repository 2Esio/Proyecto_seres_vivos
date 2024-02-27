package com.google.ar.core;

import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class z implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ AtomicBoolean f46a;
    private final /* synthetic */ x b;

    z(x xVar, AtomicBoolean atomicBoolean) {
        this.b = xVar;
        this.f46a = atomicBoolean;
    }

    public final void run() {
        if (!this.f46a.getAndSet(true)) {
            Log.w("ARCore-InstallService", "requestInstall timed out, launching fullscreen.");
            n.b(this.b.f44a, this.b.b);
        }
    }
}
