package com.google.ar.core;

import android.hardware.camera2.CameraCaptureSession;

final /* synthetic */ class at implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraCaptureSession.StateCallback f27a;
    private final CameraCaptureSession b;

    at(CameraCaptureSession.StateCallback stateCallback, CameraCaptureSession cameraCaptureSession) {
        this.f27a = stateCallback;
        this.b = cameraCaptureSession;
    }

    public final void run() {
        this.f27a.onReady(this.b);
    }
}
