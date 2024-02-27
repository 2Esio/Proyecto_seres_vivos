package com.unity3d.player;

import android.os.Build;
import java.lang.Thread;

final class m implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private volatile Thread.UncaughtExceptionHandler f122a;

    m() {
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean a() {
        boolean z;
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler == this) {
            z = false;
        } else {
            this.f122a = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
            z = true;
        }
        return z;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        try {
            Error error = new Error(String.format("FATAL EXCEPTION [%s]\n", new Object[]{thread.getName()}) + String.format("Unity version     : %s\n", new Object[]{"2019.4.18f1"}) + String.format("Device model      : %s %s\n", new Object[]{Build.MANUFACTURER, Build.MODEL}) + String.format("Device fingerprint: %s\n", new Object[]{Build.FINGERPRINT}));
            error.setStackTrace(new StackTraceElement[0]);
            error.initCause(th);
            this.f122a.uncaughtException(thread, error);
        } catch (Throwable th2) {
            this.f122a.uncaughtException(thread, th);
        }
    }
}
