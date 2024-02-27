package com.google.ar.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* compiled from: InstallServiceImpl */
final class ab extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ u f9a;

    ab(u uVar) {
        this.f9a = uVar;
    }

    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();
        if ("com.google.android.play.core.install.ACTION_INSTALL_STATUS".equals(action) && extras != null && extras.containsKey("install.status")) {
            int i = extras.getInt("install.status");
            if (i == 1 || i == 2 || i == 3) {
                this.f9a.a(v.ACCEPTED);
            } else if (i == 4) {
                this.f9a.a(v.COMPLETED);
            } else if (i == 6) {
                this.f9a.a(v.CANCELLED);
            }
        }
    }
}
