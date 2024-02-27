package com.google.ar.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* compiled from: InstallServiceImpl */
final class u extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ p f41a;
    private final /* synthetic */ n b;

    u(n nVar, p pVar) {
        this.b = nVar;
        this.f41a = pVar;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        if ("com.google.android.play.core.install.ACTION_INSTALL_STATUS".equals(action) && extras != null && extras.containsKey("install.status")) {
            this.b.c();
            int i = extras.getInt("install.status");
            if (i == 1 || i == 2 || i == 3) {
                this.f41a.a(q.ACCEPTED);
            } else if (i == 4) {
                this.f41a.a(q.COMPLETED);
            } else if (i == 6) {
                this.f41a.a(q.CANCELLED);
            }
        }
    }
}
