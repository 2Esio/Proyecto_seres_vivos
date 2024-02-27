package com.google.a.b.a.a.a;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.a.a.a;
import com.google.a.a.d;

/* compiled from: IInstallServiceCallback */
public abstract class e extends a implements c {
    public e() {
        super("com.google.android.play.core.install.protocol.IInstallServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel) throws RemoteException {
        if (i == 1) {
            a((Bundle) d.a(parcel, Bundle.CREATOR));
        } else if (i == 2) {
            b((Bundle) d.a(parcel, Bundle.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            d.a(parcel, Bundle.CREATOR);
            a();
        }
        return true;
    }
}
