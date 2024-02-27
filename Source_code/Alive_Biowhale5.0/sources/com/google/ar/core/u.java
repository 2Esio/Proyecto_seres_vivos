package com.google.ar.core;

import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: InstallActivity */
final class u {

    /* renamed from: a  reason: collision with root package name */
    boolean f38a = false;
    final /* synthetic */ InstallActivity b;

    u(InstallActivity installActivity) {
        this.b = installActivity;
    }

    public final void a(v vVar) {
        synchronized (this.b) {
            if (!this.f38a) {
                v unused = this.b.lastEvent = vVar;
                v vVar2 = v.ACCEPTED;
                ArCoreApk.UserMessageType userMessageType = ArCoreApk.UserMessageType.APPLICATION;
                ArCoreApk.Availability availability = ArCoreApk.Availability.UNKNOWN_ERROR;
                int ordinal = vVar.ordinal();
                if (ordinal != 0) {
                    if (ordinal == 1) {
                        this.b.finishWithFailure(new UnavailableUserDeclinedInstallationException());
                    } else if (ordinal == 2) {
                        if (!this.b.waitingForCompletion && k.a().b) {
                            this.b.closeInstaller();
                        }
                        this.b.finishWithFailure((Exception) null);
                    }
                    this.f38a = true;
                }
            }
        }
    }

    public final void a(Exception exc) {
        synchronized (this.b) {
            if (!this.f38a) {
                this.f38a = true;
                v unused = this.b.lastEvent = v.CANCELLED;
                this.b.finishWithFailure(exc);
            }
        }
    }
}
