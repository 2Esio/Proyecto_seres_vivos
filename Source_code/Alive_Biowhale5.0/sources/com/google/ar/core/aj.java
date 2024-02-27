package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class aj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f16a;
    private final CameraDevice b;

    aj(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f16a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        CameraDevice.StateCallback stateCallback = this.f16a;
        CameraDevice cameraDevice = this.b;
        int i = an.d;
        stateCallback.onClosed(cameraDevice);
    }
}
