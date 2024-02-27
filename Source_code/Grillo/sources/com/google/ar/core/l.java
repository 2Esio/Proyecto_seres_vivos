package com.google.ar.core;

import android.animation.ValueAnimator;

/* compiled from: InstallActivity */
final class l implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ int f33a;
    private final /* synthetic */ int b;
    private final /* synthetic */ int c;
    private final /* synthetic */ InstallActivity d;

    l(InstallActivity installActivity, int i, int i2, int i3) {
        this.d = installActivity;
        this.f33a = i;
        this.b = i2;
        this.c = i3;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = 1.0f - valueAnimator.getAnimatedFraction();
        float animatedFraction2 = valueAnimator.getAnimatedFraction();
        int i = this.b;
        this.d.getWindow().setLayout((int) ((((float) this.f33a) * animatedFraction) + (((float) i) * animatedFraction2)), (int) ((((float) this.c) * animatedFraction) + (((float) i) * animatedFraction2)));
        this.d.getWindow().getDecorView().refreshDrawableState();
    }
}
