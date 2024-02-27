package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class ao implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f21a;
    private final CameraCaptureSession b;

    ao(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f21a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        CameraCaptureSession.StateCallback stateCallback = this.f21a;
        CameraCaptureSession cameraCaptureSession = this.b;
        int i = at.d;
        stateCallback.onClosed(cameraCaptureSession);
    }
}
