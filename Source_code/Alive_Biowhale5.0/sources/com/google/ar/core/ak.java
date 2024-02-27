package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class ak implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f17a;
    private final CameraDevice b;

    ak(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f17a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        CameraDevice.StateCallback stateCallback = this.f17a;
        CameraDevice cameraDevice = this.b;
        int i = an.d;
        stateCallback.onOpened(cameraDevice);
    }
}
