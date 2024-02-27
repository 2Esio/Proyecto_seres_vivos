package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class as implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f26a;
    private final CameraCaptureSession b;

    as(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f26a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        this.f26a.onClosed(this.b);
    }
}
