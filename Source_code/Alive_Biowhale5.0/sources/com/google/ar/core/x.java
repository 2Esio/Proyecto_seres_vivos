package com.google.ar.core;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: InstallServiceImpl */
final class x implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ w f41a;

    x(w wVar) {
        this.f41a = wVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f41a.a(iBinder);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f41a.c();
    }
}
