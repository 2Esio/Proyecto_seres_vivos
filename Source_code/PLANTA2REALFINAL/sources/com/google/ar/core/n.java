package com.google.ar.core;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.google.a.b.a.a.a.a;
import com.google.a.b.a.a.a.b;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.FatalException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: InstallService */
class n {

    /* renamed from: a  reason: collision with root package name */
    private final Queue<Runnable> f35a;
    private Context b;
    private volatile int c;
    /* access modifiers changed from: private */
    public b d;
    private BroadcastReceiver e;
    private Context f;
    private final ServiceConnection g;
    private final AtomicReference<ab> h;

    n() {
    }

    public synchronized void a(Context context) {
        this.b = context;
        if (context.bindService(new Intent("com.google.android.play.core.install.BIND_INSTALL_SERVICE").setPackage("com.android.vending"), this.g, 1)) {
            this.c = y.b;
            return;
        }
        this.c = y.f45a;
        this.b = null;
        Log.w("ARCore-InstallService", "bindService returned false.");
        context.unbindService(this.g);
    }

    public synchronized void a() {
        c();
        int i = this.c - 1;
        if (i == 1 || i == 2) {
            this.b.unbindService(this.g);
            this.b = null;
            this.c = y.f45a;
        }
        if (this.e != null) {
            this.f.unregisterReceiver(this.e);
        }
    }

    public synchronized void a(Context context, ArCoreApk.a aVar) {
        try {
            a((Runnable) new s(this, context, aVar));
        } catch (aa unused) {
            Log.e("ARCore-InstallService", "Play Store install service could not be bound.");
            aVar.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        }
    }

    public void a(Activity activity, p pVar) {
        ab abVar = new ab(activity, pVar);
        ab andSet = this.h.getAndSet(abVar);
        if (andSet != null) {
            andSet.a();
        }
        abVar.start();
        if (this.e == null) {
            u uVar = new u(this, pVar);
            this.e = uVar;
            this.f = activity;
            activity.registerReceiver(uVar, new IntentFilter("com.google.android.play.core.install.ACTION_INSTALL_STATUS"));
        }
        try {
            a((Runnable) new x(this, activity, pVar));
        } catch (aa unused) {
            Log.w("ARCore-InstallService", "requestInstall bind failed, launching fullscreen.");
            b(activity, pVar);
        }
    }

    n(byte b2) {
        this();
        this.f35a = new ArrayDeque();
        this.c = y.f45a;
        this.g = new t(this);
        this.h = new AtomicReference<>();
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, p pVar) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ar.core")));
        } catch (ActivityNotFoundException e2) {
            pVar.a((Exception) new FatalException("Failed to launch installer.", e2));
        }
    }

    /* access modifiers changed from: private */
    public static void a(Activity activity, Bundle bundle, p pVar) {
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("resolution.intent");
        if (pendingIntent != null) {
            try {
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1234, new Intent(activity, activity.getClass()), 0, 0, 0);
            } catch (IntentSender.SendIntentException e2) {
                pVar.a((Exception) new FatalException("Installation Intent failed", e2));
            }
        } else {
            Log.e("ARCore-InstallService", "Did not get pending intent.");
            pVar.a((Exception) new FatalException("Installation intent failed to unparcel."));
        }
    }

    /* access modifiers changed from: private */
    public static Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("package.name", "com.google.ar.core");
        return bundle;
    }

    /* access modifiers changed from: private */
    public void c() {
        ab andSet = this.h.getAndSet((Object) null);
        if (andSet != null) {
            andSet.a();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(IBinder iBinder) {
        b a2 = a.a(iBinder);
        Log.i("ARCore-InstallService", "Install service connected");
        this.d = a2;
        this.c = y.c;
        for (Runnable run : this.f35a) {
            run.run();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void d() {
        Log.i("ARCore-InstallService", "Install service disconnected");
        this.c = y.f45a;
        this.d = null;
        c();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(java.lang.Runnable r3) throws com.google.ar.core.aa {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.c     // Catch:{ all -> 0x001f }
            r1 = 1
            int r0 = r0 - r1
            if (r0 == 0) goto L_0x0019
            if (r0 == r1) goto L_0x0012
            r1 = 2
            if (r0 == r1) goto L_0x000d
            goto L_0x0010
        L_0x000d:
            r3.run()     // Catch:{ all -> 0x001f }
        L_0x0010:
            monitor-exit(r2)
            return
        L_0x0012:
            java.util.Queue<java.lang.Runnable> r0 = r2.f35a     // Catch:{ all -> 0x001f }
            r0.offer(r3)     // Catch:{ all -> 0x001f }
            monitor-exit(r2)
            return
        L_0x0019:
            com.google.ar.core.aa r3 = new com.google.ar.core.aa     // Catch:{ all -> 0x001f }
            r3.<init>()     // Catch:{ all -> 0x001f }
            throw r3     // Catch:{ all -> 0x001f }
        L_0x001f:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ar.core.n.a(java.lang.Runnable):void");
    }
}
