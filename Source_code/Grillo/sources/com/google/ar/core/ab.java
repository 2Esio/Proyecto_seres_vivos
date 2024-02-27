package com.google.ar.core;

import android.content.Context;

/* compiled from: InstallServiceImpl */
final class ab extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private final Context f15a;
    private final p b;
    private volatile boolean c;

    ab(Context context, p pVar) {
        this.f15a = context;
        this.b = pVar;
    }

    public final void run() {
        while (!this.c) {
            if (h.a().b(this.f15a)) {
                this.b.a(q.COMPLETED);
                return;
            }
            try {
                sleep(200);
            } catch (InterruptedException e) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        this.c = true;
    }
}
