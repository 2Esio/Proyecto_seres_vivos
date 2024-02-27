package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class ar implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f24a;
    private final CameraCaptureSession b;

    ar(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f24a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        CameraCaptureSession.StateCallback stateCallback = this.f24a;
        CameraCaptureSession cameraCaptureSession = this.b;
        int i = at.d;
        stateCallback.onReady(cameraCaptureSession);
    }
}
