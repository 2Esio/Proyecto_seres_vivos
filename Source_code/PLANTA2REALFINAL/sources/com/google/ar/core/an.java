package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class an implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f21a;
    private final CameraDevice b;

    an(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f21a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        this.f21a.onDisconnected(this.b);
    }
}
