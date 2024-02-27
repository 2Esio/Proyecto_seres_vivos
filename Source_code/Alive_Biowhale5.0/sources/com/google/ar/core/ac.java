package com.google.ar.core;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.a.b.a.a.a.d;
import com.google.ar.core.exceptions.FatalException;
import com.vuforia.ar.pl.SystemTools;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: InstallServiceImpl */
final class ac extends d {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicBoolean f10a;
    final /* synthetic */ ae b;

    ac(ae aeVar, AtomicBoolean atomicBoolean) {
        this.b = aeVar;
        this.f10a = atomicBoolean;
    }

    public final void b(Bundle bundle) throws RemoteException {
    }

    public final void a(Bundle bundle) throws RemoteException {
        if (!this.f10a.getAndSet(true)) {
            int i = bundle.getInt("error.code", -100);
            int i2 = bundle.getInt("install.status", 0);
            if (i2 == 4) {
                this.b.b.a(v.COMPLETED);
            } else if (i != 0) {
                StringBuilder sb = new StringBuilder(51);
                sb.append("requestInstall = ");
                sb.append(i);
                sb.append(", launching fullscreen.");
                Log.w("ARCore-InstallService", sb.toString());
                ae aeVar = this.b;
                w.b(aeVar.f12a, aeVar.b);
            } else if (bundle.containsKey("resolution.intent")) {
                ae aeVar2 = this.b;
                w.a(aeVar2.f12a, bundle, aeVar2.b);
            } else if (i2 != 10) {
                switch (i2) {
                    case 1:
                    case 2:
                    case 3:
                        this.b.b.a(v.ACCEPTED);
                        return;
                    case SystemTools.AR_ERROR_INVALID_HANDLE /*4*/:
                        this.b.b.a(v.COMPLETED);
                        return;
                    case SystemTools.AR_ERROR_INVALID_OPERATION /*5*/:
                        this.b.b.a((Exception) new FatalException("Unexpected FAILED install status without error."));
                        return;
                    case SystemTools.AR_ERROR_OPERATION_FAILED /*6*/:
                        this.b.b.a(v.CANCELLED);
                        return;
                    default:
                        u uVar = this.b.b;
                        StringBuilder sb2 = new StringBuilder(38);
                        sb2.append("Unexpected install status: ");
                        sb2.append(i2);
                        uVar.a((Exception) new FatalException(sb2.toString()));
                        return;
                }
            } else {
                this.b.b.a((Exception) new FatalException("Unexpected REQUIRES_UI_INTENT install status without an intent."));
            }
        }
    }
}
