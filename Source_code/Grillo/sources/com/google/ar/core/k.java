package com.google.ar.core;

import android.view.View;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: InstallActivity */
final class k implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ InstallActivity f32a;

    k(InstallActivity installActivity) {
        this.f32a = installActivity;
    }

    public final void onClick(View view) {
        this.f32a.finishWithFailure(new UnavailableUserDeclinedInstallationException());
    }
}
