package com.google.ar.core;

import com.google.ar.core.ArCoreApk;

/* compiled from: ArCoreApkImpl */
final class j implements i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f28a;

    j(k kVar) {
        this.f28a = kVar;
    }

    public final void a(ArCoreApk.Availability availability) {
        synchronized (this.f28a) {
            this.f28a.g = availability;
            this.f28a.h = false;
        }
    }
}
