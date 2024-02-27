package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class av implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f29a;
    private final CameraCaptureSession b;

    av(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f29a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        this.f29a.onActive(this.b);
    }
}
