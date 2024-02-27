package com.google.ar.core;

import com.google.ar.core.ArCoreApk;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: InstallActivity */
final class p implements i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f33a;

    p(AtomicReference atomicReference) {
        this.f33a = atomicReference;
    }

    public final void a(ArCoreApk.Availability availability) {
        this.f33a.set(availability);
    }
}
