package com.google.ar.core;

import android.animation.ValueAnimator;

/* compiled from: InstallActivity */
final class s implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f36a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ InstallActivity d;

    s(InstallActivity installActivity, int i, int i2, int i3) {
        this.d = installActivity;
        this.f36a = i;
        this.b = i2;
        this.c = i3;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = 1.0f - valueAnimator.getAnimatedFraction();
        float animatedFraction2 = valueAnimator.getAnimatedFraction();
        int i = this.f36a;
        int i2 = this.b;
        float f = ((float) i2) * animatedFraction2;
        this.d.getWindow().setLayout((int) ((((float) i) * animatedFraction) + f), (int) ((((float) this.c) * animatedFraction) + f));
        this.d.getWindow().getDecorView().refreshDrawableState();
    }
}
