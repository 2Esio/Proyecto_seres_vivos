package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class au implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f28a;
    private final CameraCaptureSession b;

    au(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f28a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        this.f28a.onConfigureFailed(this.b);
    }
}
