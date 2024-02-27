package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class aq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f24a;
    private final CameraDevice b;
    private final int c;

    aq(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice, int i) {
        this.f24a = stateCallback;
        this.b = cameraDevice;
        this.c = i;
    }

    public final void run() {
        this.f24a.onError(this.b, this.c);
    }
}
