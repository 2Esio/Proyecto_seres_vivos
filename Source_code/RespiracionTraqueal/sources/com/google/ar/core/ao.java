package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class ao implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f22a;
    private final CameraDevice b;

    ao(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f22a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        this.f22a.onOpened(this.b);
    }
}
