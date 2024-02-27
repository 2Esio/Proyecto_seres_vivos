package com.google.ar.core;

import android.view.View;

/* renamed from: com.google.ar.core.r  reason: case insensitive filesystem */
/* compiled from: InstallActivity */
final class C0001r implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ InstallActivity f35a;

    C0001r(InstallActivity installActivity) {
        this.f35a = installActivity;
    }

    public final void onClick(View view) {
        this.f35a.animateToSpinner();
        this.f35a.startInstaller();
    }
}
