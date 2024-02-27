package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class aq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f23a;
    private final CameraCaptureSession b;

    aq(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f23a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        CameraCaptureSession.StateCallback stateCallback = this.f23a;
        CameraCaptureSession cameraCaptureSession = this.b;
        int i = at.d;
        stateCallback.onConfigureFailed(cameraCaptureSession);
    }
}
