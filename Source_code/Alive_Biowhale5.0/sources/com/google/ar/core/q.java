package com.google.ar.core;

import android.view.View;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: InstallActivity */
final class q implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ InstallActivity f34a;

    q(InstallActivity installActivity) {
        this.f34a = installActivity;
    }

    public final void onClick(View view) {
        this.f34a.finishWithFailure(new UnavailableUserDeclinedInstallationException());
    }
}
