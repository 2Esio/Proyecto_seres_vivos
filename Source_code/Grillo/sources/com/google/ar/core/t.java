package com.google.ar.core;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* compiled from: InstallServiceImpl */
final class t implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ n f40a;

    t(n nVar) {
        this.f40a = nVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f40a.a(iBinder);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f40a.d();
    }
}
