package com.google.a.b.a.a.a;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.a.a.b;
import java.util.List;

/* compiled from: IInstallService */
public final class d extends b implements b {
    d(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.install.protocol.IInstallService");
    }

    public final void a(String str, List<Bundle> list, Bundle bundle, c cVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        a2.writeTypedList(list);
        com.google.a.a.d.a(a2, (Parcelable) bundle);
        com.google.a.a.d.a(a2, (IInterface) cVar);
        b(1, a2);
    }

    public final void a(String str, Bundle bundle, c cVar) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        com.google.a.a.d.a(a2, (Parcelable) bundle);
        com.google.a.a.d.a(a2, (IInterface) cVar);
        b(2, a2);
    }
}
