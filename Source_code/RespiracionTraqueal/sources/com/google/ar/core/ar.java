package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class ar implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f25a;
    private final CameraCaptureSession b;

    ar(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f25a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        this.f25a.onConfigured(this.b);
    }
}
