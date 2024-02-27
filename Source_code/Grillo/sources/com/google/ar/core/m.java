package com.google.ar.core;

import android.view.View;

/* compiled from: InstallActivity */
final class m implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ InstallActivity f34a;

    m(InstallActivity installActivity) {
        this.f34a = installActivity;
    }

    public final void onClick(View view) {
        this.f34a.animateToSpinner();
        this.f34a.startInstaller();
    }
}
