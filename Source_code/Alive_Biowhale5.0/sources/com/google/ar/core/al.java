package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class al implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f18a;
    private final CameraDevice b;

    al(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f18a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        CameraDevice.StateCallback stateCallback = this.f18a;
        CameraDevice cameraDevice = this.b;
        int i = an.d;
        stateCallback.onDisconnected(cameraDevice);
    }
}
