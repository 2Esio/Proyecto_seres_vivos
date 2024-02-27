package com.google.ar.core;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: InstallActivity */
final class t extends AnimatorListenerAdapter {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ InstallActivity f37a;

    t(InstallActivity installActivity) {
        this.f37a = installActivity;
    }

    public final void onAnimationEnd(Animator animator) {
        this.f37a.showSpinner();
    }
}
