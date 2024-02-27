package com.google.ar.core;

import com.google.ar.core.ArCoreApk;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Session */
class ac implements ArCoreApk.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicReference f16a;

    ac(AtomicReference atomicReference) {
        this.f16a = atomicReference;
    }

    public void a(ArCoreApk.Availability availability) {
        this.f16a.set(availability);
    }
}
