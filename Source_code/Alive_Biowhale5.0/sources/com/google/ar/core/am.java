package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class am implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f19a;
    private final CameraDevice b;
    private final int c;

    am(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice, int i) {
        this.f19a = stateCallback;
        this.b = cameraDevice;
        this.c = i;
    }

    public final void run() {
        CameraDevice.StateCallback stateCallback = this.f19a;
        CameraDevice cameraDevice = this.b;
        int i = this.c;
        int i2 = an.d;
        stateCallback.onError(cameraDevice, i);
    }
}
