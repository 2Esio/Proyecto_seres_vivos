package com.google.ar.core;

import android.content.pm.PackageInstaller;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* compiled from: InstallServiceImpl */
final class aa extends PackageInstaller.SessionCallback {

    /* renamed from: a  reason: collision with root package name */
    final Map<Integer, PackageInstaller.SessionInfo> f8a = new HashMap();
    final /* synthetic */ u b;
    final /* synthetic */ w c;

    aa(w wVar, u uVar) {
        this.c = wVar;
        this.b = uVar;
    }

    public final void onActiveChanged(int i, boolean z) {
    }

    public final void onBadgingChanged(int i) {
    }

    public final void onProgressChanged(int i, float f) {
    }

    public final void onCreated(int i) {
        this.f8a.put(Integer.valueOf(i), this.c.g.getSessionInfo(i));
    }

    public final void onFinished(int i, boolean z) {
        PackageInstaller.SessionInfo remove = this.f8a.remove(Integer.valueOf(i));
        if (remove != null && "com.google.ar.core".equals(remove.getAppPackageName())) {
            Log.i("ARCore-InstallService", "Detected ARCore install completion");
            this.b.a(v.COMPLETED);
        }
    }
}
