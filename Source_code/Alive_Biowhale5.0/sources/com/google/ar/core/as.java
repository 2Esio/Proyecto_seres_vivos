package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class as implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f25a;
    private final CameraCaptureSession b;

    as(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f25a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        CameraCaptureSession.StateCallback stateCallback = this.f25a;
        CameraCaptureSession cameraCaptureSession = this.b;
        int i = at.d;
        stateCallback.onActive(cameraCaptureSession);
    }
}
