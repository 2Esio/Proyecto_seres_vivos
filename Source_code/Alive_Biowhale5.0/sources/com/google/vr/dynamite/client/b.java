package com.google.vr.dynamite.client;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.a.a.a;

/* compiled from: INativeLibraryLoader */
public final class b extends a implements INativeLibraryLoader {
    b(IBinder iBinder) {
        super(iBinder, "com.google.vr.dynamite.client.INativeLibraryLoader");
    }

    public final int checkVersion(String str) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        Parcel a3 = a(2, a2);
        int readInt = a3.readInt();
        a3.recycle();
        return readInt;
    }

    public final long initializeAndLoadNativeLibrary(String str) throws RemoteException {
        Parcel a2 = a();
        a2.writeString(str);
        Parcel a3 = a(1, a2);
        long readLong = a3.readLong();
        a3.recycle();
        return readLong;
    }
}
