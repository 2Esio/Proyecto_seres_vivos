package com.google.ar.core;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.a.b.a.a.a.e;
import com.google.ar.core.exceptions.FatalException;
import com.vuforia.ar.pl.SystemTools;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class w extends e {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ AtomicBoolean f43a;
    private final /* synthetic */ x b;

    w(x xVar, AtomicBoolean atomicBoolean) {
        this.b = xVar;
        this.f43a = atomicBoolean;
    }

    public final void a() throws RemoteException {
    }

    public final void b(Bundle bundle) throws RemoteException {
    }

    public final void a(Bundle bundle) throws RemoteException {
        if (!this.f43a.getAndSet(true)) {
            int i = bundle.getInt("error.code", -100);
            int i2 = bundle.getInt("install.status", 0);
            if (i2 == 4) {
                this.b.b.a(q.COMPLETED);
            } else if (i != 0) {
                StringBuilder sb = new StringBuilder(51);
                sb.append("requestInstall = ");
                sb.append(i);
                sb.append(", launching fullscreen.");
                Log.w("ARCore-InstallService", sb.toString());
                n.b(this.b.f44a, this.b.b);
            } else if (bundle.containsKey("resolution.intent")) {
                n.a(this.b.f44a, bundle, this.b.b);
            } else if (i2 != 10) {
                switch (i2) {
                    case 1:
                    case 2:
                    case 3:
                        this.b.b.a(q.ACCEPTED);
                        return;
                    case SystemTools.AR_ERROR_INVALID_HANDLE /*4*/:
                        this.b.b.a(q.COMPLETED);
                        return;
                    case SystemTools.AR_ERROR_INVALID_OPERATION /*5*/:
                        this.b.b.a((Exception) new FatalException("Unexpected FAILED install status without error."));
                        return;
                    case SystemTools.AR_ERROR_OPERATION_FAILED /*6*/:
                        this.b.b.a(q.CANCELLED);
                        return;
                    default:
                        p pVar = this.b.b;
                        StringBuilder sb2 = new StringBuilder(38);
                        sb2.append("Unexpected install status: ");
                        sb2.append(i2);
                        pVar.a((Exception) new FatalException(sb2.toString()));
                        return;
                }
            } else {
                this.b.b.a((Exception) new FatalException("Unexpected REQUIRES_UI_INTENT install status without an intent."));
            }
        }
    }
}
