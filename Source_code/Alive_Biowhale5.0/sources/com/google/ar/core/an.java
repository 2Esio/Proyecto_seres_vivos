package com.google.ar.core;

import android.hardware.camera2.CameraDevice;
import android.os.Handler;

/* compiled from: SharedCamera */
final class an extends CameraDevice.StateCallback {
    public static final /* synthetic */ int d = 0;

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Handler f20a;
    final /* synthetic */ CameraDevice.StateCallback b;
    final /* synthetic */ SharedCamera c;

    an(SharedCamera sharedCamera, Handler handler, CameraDevice.StateCallback stateCallback) {
        this.c = sharedCamera;
        this.f20a = handler;
        this.b = stateCallback;
    }

    public final void onClosed(CameraDevice cameraDevice) {
        this.f20a.post(new aj(this.b, cameraDevice));
        this.c.onDeviceClosed(cameraDevice);
    }

    public final void onDisconnected(CameraDevice cameraDevice) {
        this.f20a.post(new al(this.b, cameraDevice));
        this.c.onDeviceDisconnected(cameraDevice);
    }

    public final void onError(CameraDevice cameraDevice, int i) {
        this.f20a.post(new am(this.b, cameraDevice, i));
        this.c.close();
    }

    public final void onOpened(CameraDevice cameraDevice) {
        this.c.sharedCameraInfo.a(cameraDevice);
        this.f20a.post(new ak(this.b, cameraDevice));
        this.c.onDeviceOpened(cameraDevice);
        this.c.sharedCameraInfo.a(this.c.getGpuSurfaceTexture());
        this.c.sharedCameraInfo.a(this.c.getGpuSurface());
    }
}
