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
import android.content.pm.PackageInstaller;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.google.a.b.a.a.a.b;
import com.google.a.b.a.a.a.c;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.FatalException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/* compiled from: InstallService */
final class w {

    /* renamed from: a  reason: collision with root package name */
    private final Queue f40a;
    private Context b;
    /* access modifiers changed from: private */
    public c c;
    private final ServiceConnection d;
    private BroadcastReceiver e;
    private Context f;
    /* access modifiers changed from: private */
    public PackageInstaller g;
    private PackageInstaller.SessionCallback h;
    private volatile int i;

    w() {
    }

    w(byte[] bArr) {
        this();
        this.f40a = new ArrayDeque();
        this.i = 1;
        this.d = new x(this);
    }

    static /* synthetic */ Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("package.name", "com.google.ar.core");
        return bundle;
    }

    public synchronized void a(Context context) {
        this.b = context;
        if (context.bindService(new Intent("com.google.android.play.core.install.BIND_INSTALL_SERVICE").setPackage("com.android.vending"), this.d, 1)) {
            this.i = 2;
            return;
        }
        this.i = 1;
        this.b = null;
        Log.w("ARCore-InstallService", "bindService returned false.");
        context.unbindService(this.d);
    }

    public synchronized void a(Context context, i iVar) {
        try {
            a((Runnable) new z(this, context, iVar));
        } catch (af unused) {
            Log.e("ARCore-InstallService", "Play Store install service could not be bound.");
            iVar.a(ArCoreApk.Availability.UNKNOWN_ERROR);
        }
    }

    /* access modifiers changed from: private */
    public static void b(Activity activity, u uVar) {
        boolean z;
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.google.ar.core"));
            k a2 = k.a();
            Iterator<ResolveInfo> it = activity.getPackageManager().queryIntentActivities(intent, ImageMetadata.CONTROL_AE_ANTIBANDING_MODE).iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                ResolveInfo next = it.next();
                if (next.activityInfo != null && next.activityInfo.name.equals("com.sec.android.app.samsungapps.MainForChina")) {
                    z = true;
                    break;
                }
            }
            a2.b = !z;
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            uVar.a((Exception) new FatalException("Failed to launch installer.", e2));
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(IBinder iBinder) {
        c a2 = b.a(iBinder);
        Log.i("ARCore-InstallService", "Install service connected");
        this.c = a2;
        this.i = 3;
        for (Runnable run : this.f40a) {
            run.run();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        Log.i("ARCore-InstallService", "Install service disconnected");
        this.i = 1;
        this.c = null;
    }

    public void a(Activity activity, u uVar) {
        if (this.h == null) {
            this.g = activity.getPackageManager().getPackageInstaller();
            this.h = new aa(this, uVar);
            this.g.registerSessionCallback(this.h);
        }
        if (this.e == null) {
            this.e = new ab(uVar);
            this.f = activity;
            this.f.registerReceiver(this.e, new IntentFilter("com.google.android.play.core.install.ACTION_INSTALL_STATUS"));
        }
        try {
            a((Runnable) new ae(this, activity, uVar));
        } catch (af unused) {
            Log.w("ARCore-InstallService", "requestInstall bind failed, launching fullscreen.");
            b(activity, uVar);
        }
    }

    private synchronized void a(Runnable runnable) throws af {
        int i2 = this.i;
        int i3 = i2 - 1;
        if (i2 == 0) {
            throw null;
        } else if (i3 == 0) {
            throw new af();
        } else if (i3 == 1) {
            this.f40a.offer(runnable);
        } else if (i3 == 2) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public static void a(Activity activity, Bundle bundle, u uVar) {
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("resolution.intent");
        if (pendingIntent != null) {
            try {
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1234, new Intent(activity, activity.getClass()), 0, 0, 0);
            } catch (IntentSender.SendIntentException e2) {
                uVar.a((Exception) new FatalException("Installation Intent failed", e2));
            }
        } else {
            Log.e("ARCore-InstallService", "Did not get pending intent.");
            uVar.a((Exception) new FatalException("Installation intent failed to unparcel."));
        }
    }

    public synchronized void a() {
        int i2 = this.i;
        int i3 = i2 - 1;
        if (i2 != 0) {
            if (i3 == 1 || i3 == 2) {
                this.b.unbindService(this.d);
                this.b = null;
                this.i = 1;
            }
            BroadcastReceiver broadcastReceiver = this.e;
            if (broadcastReceiver != null) {
                this.f.unregisterReceiver(broadcastReceiver);
            }
            PackageInstaller.SessionCallback sessionCallback = this.h;
            if (sessionCallback != null) {
                this.g.unregisterSessionCallback(sessionCallback);
                this.h = null;
                return;
            }
            return;
        }
        throw null;
    }
}
