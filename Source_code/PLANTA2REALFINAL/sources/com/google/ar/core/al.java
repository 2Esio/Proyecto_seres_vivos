package com.google.ar.core;

import android.hardware.camera2.CameraDevice;

final /* synthetic */ class al implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final CameraDevice.StateCallback f19a;
    private final CameraDevice b;

    al(CameraDevice.StateCallback stateCallback, CameraDevice cameraDevice) {
        this.f19a = stateCallback;
        this.b = cameraDevice;
    }

    public final void run() {
        this.f19a.onClosed(this.b);
    }
}
