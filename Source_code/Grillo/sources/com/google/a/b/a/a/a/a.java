package com.google.a.b.a.a.a;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: IInstallService */
public abstract class a extends com.google.a.a.a implements b {
    public static b a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.play.core.install.protocol.IInstallService");
        if (queryLocalInterface instanceof b) {
            return (b) queryLocalInterface;
        }
        return new d(iBinder);
    }
}
