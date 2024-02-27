package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class ap implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f22a;
    private final CameraCaptureSession b;

    ap(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f22a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        CameraCaptureSession.StateCallback stateCallback = this.f22a;
        CameraCaptureSession cameraCaptureSession = this.b;
        int i = at.d;
        stateCallback.onConfigured(cameraCaptureSession);
    }
}
