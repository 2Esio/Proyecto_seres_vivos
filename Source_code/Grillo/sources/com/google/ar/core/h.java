package com.google.ar.core;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableUserDeclinedInstallationException;

/* compiled from: ArCoreApkImpl */
final class h extends ArCoreApk {
    private static final h b = new h();

    /* renamed from: a  reason: collision with root package name */
    Exception f30a;
    private boolean c;
    private int d;
    private long e;
    /* access modifiers changed from: private */
    public ArCoreApk.Availability f;
    /* access modifiers changed from: private */
    public boolean g;
    private n h;
    private boolean i;
    private boolean j;
    private int k;

    h() {
    }

    public static h a() {
        return b;
    }

    public final ArCoreApk.Availability checkAvailability(Context context) {
        if (!c()) {
            return ArCoreApk.Availability.UNSUPPORTED_DEVICE_NOT_CAPABLE;
        }
        try {
            if (b(context)) {
                b();
                return aj.a(context);
            }
            synchronized (this) {
                if ((this.f == null || this.f.isUnknown()) && !this.g) {
                    this.g = true;
                    aj ajVar = new aj(this);
                    if (b(context)) {
                        ajVar.a(ArCoreApk.Availability.SUPPORTED_INSTALLED);
                    } else if (d(context) != -1) {
                        ajVar.a(ArCoreApk.Availability.SUPPORTED_APK_TOO_OLD);
                    } else if (c(context)) {
                        ajVar.a(ArCoreApk.Availability.SUPPORTED_NOT_INSTALLED);
                    } else {
                        a(context).a(context, (ArCoreApk.a) ajVar);
                    }
                }
                if (this.f != null) {
                    ArCoreApk.Availability availability = this.f;
                    return availability;
                } else if (this.g) {
                    ArCoreApk.Availability availability2 = ArCoreApk.Availability.UNKNOWN_CHECKING;
                    return availability2;
                } else {
                    Log.e("ARCore-ArCoreApk", "request not running but result is null?");
                    ArCoreApk.Availability availability3 = ArCoreApk.Availability.UNKNOWN_ERROR;
                    return availability3;
                }
            }
        } catch (FatalException e2) {
            Log.e("ARCore-ArCoreApk", "Error while checking app details and ARCore status", e2);
            return ArCoreApk.Availability.UNKNOWN_ERROR;
        }
    }

    public final ArCoreApk.InstallStatus requestInstall(Activity activity, boolean z, ArCoreApk.InstallBehavior installBehavior, ArCoreApk.UserMessageType userMessageType) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        if (!c()) {
            throw new UnavailableDeviceNotCompatibleException();
        } else if (b(activity)) {
            b();
            return a(activity);
        } else if (this.c) {
            return ArCoreApk.InstallStatus.INSTALL_REQUESTED;
        } else {
            Exception exc = this.f30a;
            if (exc != null) {
                if (z) {
                    Log.w("ARCore-ArCoreApk", "Clearing previous failure: ", exc);
                    this.f30a = null;
                } else if (exc instanceof UnavailableDeviceNotCompatibleException) {
                    throw ((UnavailableDeviceNotCompatibleException) exc);
                } else if (exc instanceof UnavailableUserDeclinedInstallationException) {
                    throw ((UnavailableUserDeclinedInstallationException) exc);
                } else if (exc instanceof RuntimeException) {
                    throw ((RuntimeException) exc);
                } else {
                    throw new RuntimeException("Unexpected exception type", exc);
                }
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.e > 5000) {
                this.d = 0;
            }
            this.d++;
            this.e = uptimeMillis;
            if (this.d <= 2) {
                try {
                    activity.startActivity(new Intent(activity, InstallActivity.class).putExtra("message", userMessageType).putExtra("behavior", installBehavior));
                    this.c = true;
                    return ArCoreApk.InstallStatus.INSTALL_REQUESTED;
                } catch (ActivityNotFoundException e2) {
                    throw new FatalException("Failed to launch InstallActivity", e2);
                }
            } else {
                throw new FatalException("Requesting ARCore installation too rapidly.");
            }
        }
    }

    public final ArCoreApk.InstallStatus requestInstall(Activity activity, boolean z) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        ArCoreApk.UserMessageType userMessageType;
        ArCoreApk.InstallBehavior installBehavior = c(activity) ? ArCoreApk.InstallBehavior.REQUIRED : ArCoreApk.InstallBehavior.OPTIONAL;
        if (c(activity)) {
            userMessageType = ArCoreApk.UserMessageType.APPLICATION;
        } else {
            userMessageType = ArCoreApk.UserMessageType.USER_ALREADY_INFORMED;
        }
        return requestInstall(activity, z, installBehavior, userMessageType);
    }

    private static ArCoreApk.InstallStatus a(Activity activity) throws UnavailableDeviceNotCompatibleException, UnavailableUserDeclinedInstallationException {
        PendingIntent b2 = aj.b(activity);
        if (b2 != null) {
            try {
                Log.i("ARCore-ArCoreApk", "Starting setup activity");
                activity.startIntentSender(b2.getIntentSender(), (Intent) null, 0, 0, 0);
                return ArCoreApk.InstallStatus.INSTALL_REQUESTED;
            } catch (IntentSender.SendIntentException | RuntimeException e2) {
                Log.w("ARCore-ArCoreApk", "Setup activity launch failed", e2);
            }
        }
        return ArCoreApk.InstallStatus.INSTALLED;
    }

    /* access modifiers changed from: package-private */
    public final synchronized n a(Context context) {
        if (this.h == null) {
            n nVar = new n((byte) 0);
            nVar.a(context.getApplicationContext());
            this.h = nVar;
        }
        return this.h;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void b() {
        if (this.f30a == null) {
            this.d = 0;
        }
        this.c = false;
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean b(Context context) {
        e(context);
        return d(context) == 0 || d(context) >= this.k;
    }

    private final boolean c(Context context) {
        e(context);
        return this.j;
    }

    private static boolean c() {
        return Build.VERSION.SDK_INT >= 24;
    }

    private static int d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.ar.core", 4);
            int i2 = packageInfo.versionCode;
            if (i2 == 0 && (packageInfo.services == null || packageInfo.services.length == 0)) {
                return -1;
            }
            return i2;
        } catch (PackageManager.NameNotFoundException e2) {
            return -1;
        }
    }

    private final synchronized void e(Context context) {
        if (!this.i) {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            try {
                Bundle bundle = packageManager.getApplicationInfo(packageName, 128).metaData;
                if (bundle.containsKey("com.google.ar.core")) {
                    this.j = bundle.getString("com.google.ar.core").equals("required");
                    if (bundle.containsKey("com.google.ar.core.min_apk_version")) {
                        this.k = bundle.getInt("com.google.ar.core.min_apk_version");
                        ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(packageName, 1).activities;
                        String canonicalName = InstallActivity.class.getCanonicalName();
                        int length = activityInfoArr.length;
                        boolean z = false;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                break;
                            } else if (canonicalName.equals(activityInfoArr[i2].name)) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z) {
                            String valueOf = String.valueOf(canonicalName);
                            throw new FatalException(valueOf.length() != 0 ? "Application manifest must contain activity ".concat(valueOf) : new String("Application manifest must contain activity "));
                        } else {
                            this.i = true;
                        }
                    } else {
                        throw new FatalException("Application manifest must contain meta-data com.google.ar.core.min_apk_version");
                    }
                } else {
                    throw new FatalException("Application manifest must contain meta-data com.google.ar.core");
                }
            } catch (PackageManager.NameNotFoundException e2) {
                throw new FatalException("Could not load application package metadata", e2);
            } catch (PackageManager.NameNotFoundException e3) {
                throw new FatalException("Could not load application package info", e3);
            }
        }
    }
}
