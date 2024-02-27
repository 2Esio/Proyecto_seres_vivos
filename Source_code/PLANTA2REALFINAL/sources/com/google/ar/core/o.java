package com.google.ar.core;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: InstallActivity */
final class o extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ InstallActivity f36a;

    o(InstallActivity installActivity) {
        this.f36a = installActivity;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f36a.showSpinner();
    }
}
