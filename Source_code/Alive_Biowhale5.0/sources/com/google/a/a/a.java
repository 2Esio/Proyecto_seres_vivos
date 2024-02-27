package com.google.a.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: BaseProxy */
public class a implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    private final IBinder f1a;
    private final String b;

    protected a(IBinder iBinder, String str) {
        this.f1a = iBinder;
        this.b = str;
    }

    /* access modifiers changed from: protected */
    public final Parcel a() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.b);
        return obtain;
    }

    public final IBinder asBinder() {
        return this.f1a;
    }

    /* access modifiers changed from: protected */
    public final Parcel a(int i, Parcel parcel) throws RemoteException {
        parcel = Parcel.obtain();
        try {
            this.f1a.transact(i, parcel, parcel, 0);
            parcel.readException();
            return parcel;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            parcel.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void b(int i, Parcel parcel) throws RemoteException {
        try {
            this.f1a.transact(i, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
}
