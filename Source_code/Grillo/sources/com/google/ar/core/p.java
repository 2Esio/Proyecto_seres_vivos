package com.google.ar.core;

import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: InstallService */
class p {

    /* renamed from: a  reason: collision with root package name */
    boolean f37a = false;
    final /* synthetic */ InstallActivity b;

    public void a(q qVar) {
        synchronized (this.b) {
            if (!this.f37a) {
                q unused = this.b.lastEvent = qVar;
                int ordinal = qVar.ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        this.b.finishWithFailure(new UnavailableUserDeclinedInstallationException());
                    } else if (ordinal == 2) {
                        if (!this.b.waitingForCompletion) {
                            this.b.closeInstaller();
                        }
                        this.b.finishWithFailure((Exception) null);
                    }
                    this.f37a = true;
                }
            }
        }
    }

    public void a(Exception exc) {
        synchronized (this.b) {
            if (!this.f37a) {
                this.f37a = true;
                q unused = this.b.lastEvent = q.CANCELLED;
                boolean z = exc instanceof UnavailableException;
                this.b.finishWithFailure(exc);
            }
        }
    }

    p(InstallActivity installActivity) {
        this.b = installActivity;
    }
}
